package com.example.simplegame.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.simplegame.domain.AuditInfo;
import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.domain.PlayResponse;
import com.example.simplegame.domain.PlayerInfo;
import com.example.simplegame.domain.RoundInfo;
import com.example.simplegame.framework.pojo.builder.GenericPojoBuilder;
import com.example.simplegame.framework.rules.Rule;
import com.example.simplegame.framework.rules.RuleContext;
import com.example.simplegame.framework.rules.RuleExecutor;
import com.example.simplegame.repository.GameRoundRepository;
import com.example.simplegame.repository.PlayerRepository;
import com.example.simplegame.rules.FreeGameRoundRule;
import com.example.simplegame.rules.NormalGameRoundRule;
import com.example.simplegame.rules.context.FreeGameRoundRuleContext;
import com.example.simplegame.rules.context.NormalGameRoundContext;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@Service
public class GameService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

	@Value("${game.player.win.percentage:30}")
	private double winPercentage;

	@Autowired
	private PlayerRepository playerRepo;

	@Autowired
	private GameRoundRepository gameRoundRepository;

	private final static Random RANDOM = new Random();

	public PlayResponse play(PlayRequest playRequest) {

		if (this.winPercentage < 0 || this.winPercentage > 100) {
			LOGGER.error("The winning percentage range must be between 0 to 100");
			throw new RuntimeException("The winning percentage range must be between 0 to 100");
		}

		PlayerInfo playerInfo = null;
		synchronized (this) {

			playerInfo = this.playerRepo.findOne(playRequest.getPlayerId());

			if (playerInfo == null) {
				LOGGER.debug("The player [{}] does not exists. Creating an account.", playRequest.getPlayerId());
				PlayerInfo pInfo = GenericPojoBuilder.of(PlayerInfo::new).with(PlayerInfo::setFreeRounds, 0)
						.with(PlayerInfo::setPlayerId, playRequest.getPlayerId()).with(PlayerInfo::setWinMoney, 0.0)
						.with(PlayerInfo::setTotalMoney, Double.POSITIVE_INFINITY)
						.with(PlayerInfo::setAudit, prepareAuditInfo()).build();

				playerInfo = this.playerRepo.save(pInfo);
			}
		}

		NormalGameRoundContext normalGameContext = new NormalGameRoundContext(playRequest.getCoins(), playRequest.isFreeRound());
		NormalGameRoundRule normalRoundRule = new NormalGameRoundRule(normalGameContext);
		
		FreeGameRoundRuleContext freeGameRuleContext = new FreeGameRoundRuleContext(playRequest, playerInfo.getFreeRounds());
		FreeGameRoundRule freeGameRule = new FreeGameRoundRule(freeGameRuleContext);

		List<Rule<? extends RuleContext>> rules = Arrays.asList(normalRoundRule, freeGameRule);
		RuleExecutor.execute(rules);

		String roundId = UUID.randomUUID().toString();

		PlayResponse response = getPlayResponse(roundId, playRequest.getPlayerId());

		RoundInfo roundInfo = GenericPojoBuilder.of(RoundInfo::new)
				.with(RoundInfo::setFreeRound, response.isOfferFreeRound())
				.with(RoundInfo::setPlayerId, playRequest.getPlayerId()).with(RoundInfo::setRoundId, roundId)
				.with(RoundInfo::setAudit, prepareAuditInfo()).build();

		roundInfo.setWon(response.isWin());
		this.gameRoundRepository.save(roundInfo);

		if (response.isWin()) {
			playerInfo.setWinMoney(playerInfo.getWinMoney() + 20);
		}

		if (playRequest.isFreeRound()) {
			playerInfo.setFreeRounds(playerInfo.getFreeRounds() - 1);
		}

		if (response.isOfferFreeRound()) {
			playerInfo.setFreeRounds(playerInfo.getFreeRounds() + 1);
		}

		this.playerRepo.save(playerInfo);
		return response;
	}

	private PlayResponse getPlayResponse(String roundId, String playerId) {
		PlayResponse response = new PlayResponse(roundId, playerId);
		response.setTimestamp(Instant.now());
		int x = RANDOM.ints(1, 101).limit(1).findFirst().getAsInt();

		if (x <= this.winPercentage)
			response.setWin(true);

		if (x <= 10)
			response.setOfferFreeRound(true);
		return response;

	}

	private AuditInfo prepareAuditInfo() {
		Timestamp timestamp = new Timestamp(Instant.now().toEpochMilli());
		return GenericPojoBuilder.of(AuditInfo::new).with(AuditInfo::setCreateTime, timestamp)
				.with(AuditInfo::setUpdateTime, timestamp).build();
	}
}
