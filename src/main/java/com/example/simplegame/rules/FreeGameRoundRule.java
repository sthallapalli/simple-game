package com.example.simplegame.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.framework.exception.RuleEvaluationException;
import com.example.simplegame.framework.rules.AbstractRule;
import com.example.simplegame.framework.rules.annotation.RuleQualifier;
import com.example.simplegame.framework.rules.enums.FailureLevel;
import com.example.simplegame.rules.context.FreeGameRoundRuleContext;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@RuleQualifier(level = FailureLevel.FATAL)
public class FreeGameRoundRule extends AbstractRule<FreeGameRoundRuleContext> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeGameRoundRule.class);

	public FreeGameRoundRule(FreeGameRoundRuleContext context) {
		super(context);
	}

	@Override
	public boolean process() {
		
		LOGGER.debug("Evaluating the FreeGameRoundRule");
		
		FreeGameRoundRuleContext context = getRuleContext();
		PlayRequest playRequest = context.getPlayRequest();
		int playerFreeRounds = context.getPlayerFreeRounds();

		// A free round should not cost coins
		if (playRequest.isFreeRound() && playRequest.getCoins() > 0) {
			if (getRuleFailureLevel() == FailureLevel.FATAL)			
				throw new RuleEvaluationException("A free round should not have bet amount");
			else 
				return false;
		}

		// No free rounds in player account
		if (playRequest.isFreeRound() && playerFreeRounds != 1) {
			if (getRuleFailureLevel() == FailureLevel.FATAL)	
				throw new RuleEvaluationException("No free round in player [" + playRequest.getPlayerId() + "] account."); 
			else 
				return false;
		}

		// Free round should immediately follow the normal round
		if (!playRequest.isFreeRound() && playerFreeRounds == 1) {
			if (getRuleFailureLevel() == FailureLevel.FATAL)	
				throw new RuleEvaluationException("A free round should immediately follow the normal round. "
						+ "You have one free round in account."); 
			else 
				return false;
		}

		return true;
	}
}
