package com.example.simplegame.service;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.simplegame.SimpleGameApplication;
import com.example.simplegame.domain.AuditInfo;
import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.domain.PlayResponse;
import com.example.simplegame.domain.PlayerInfo;
import com.example.simplegame.framework.pojo.builder.GenericPojoBuilder;
import com.example.simplegame.repository.GameRoundRepository;
import com.example.simplegame.repository.PlayerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { SimpleGameApplication.class })
public class GameServiceTest {

	@Mock
	private PlayerRepository playerRepository;
	
	@Mock
	private GameRoundRepository gameRoundRepository;
	
	@InjectMocks
	private GameService gameService;
	
	@Test
	public void testPlay() {

		ReflectionTestUtils.setField(this.gameService, "winPercentage", 30.0);
		
		Timestamp timestamp = new Timestamp(Instant.now().toEpochMilli());
		AuditInfo audit = GenericPojoBuilder.of(AuditInfo::new)
				.with(AuditInfo::setCreateTime, timestamp)
				.with(AuditInfo::setUpdateTime, timestamp)
				.build();

		PlayerInfo pInfo = GenericPojoBuilder.of(PlayerInfo::new)
				.with(PlayerInfo::setFreeRounds, 0)
				.with(PlayerInfo::setPlayerId, "test")
				.with(PlayerInfo::setWinMoney, 0.0)
				.with(PlayerInfo::setTotalMoney, Double.POSITIVE_INFINITY)
				.with(PlayerInfo::setAudit, audit)
				.build();

		when(this.playerRepository.findOne(anyString())).thenReturn(pInfo);

		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new)
								.with(PlayRequest::setFreeRound, false)
								.with(PlayRequest::setCoins, 10)
								.with(PlayRequest::setPlayerId, "test")
								.build();
		
		PlayResponse playResponse = this.gameService.play(betInfo);
		assertNotNull(playResponse);
		
		assertEquals("test", playResponse.getPlayerId());
		assertNotNull(playResponse.getRoundId());
	}
}
