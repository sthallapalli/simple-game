package com.example.simplegame.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.simplegame.framework.exception.RuleEvaluationException;
import com.example.simplegame.framework.rules.AbstractRule;
import com.example.simplegame.framework.rules.annotation.RuleQualifier;
import com.example.simplegame.framework.rules.enums.FailureLevel;
import com.example.simplegame.rules.context.NormalGameRoundContext;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@RuleQualifier(level = FailureLevel.FATAL)
public class NormalGameRoundRule extends AbstractRule<NormalGameRoundContext> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NormalGameRoundRule.class);

	public NormalGameRoundRule(NormalGameRoundContext context) {
		super(context);
	}

	@Override
	public boolean process() {

		LOGGER.debug("Evaluating the NormalGameRoundRule");
		
		int coins = getRuleContext().getCoins();
		boolean isFreeRound = getRuleContext().isFreeRound();

		// Normal round should cost only 10 coins and should not be tagged as free round.
		if (coins != 10 && !isFreeRound) {
			if (getRuleFailureLevel() == FailureLevel.FATAL)
				throw new RuleEvaluationException("Normal round bet cost is 10 coins and should not be a free round");
			else 
				return false;
		}
		return true;
	}

}
