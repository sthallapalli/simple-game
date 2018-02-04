package com.example.simplegame.rules;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.framework.exception.RuleEvaluationException;
import com.example.simplegame.framework.pojo.builder.GenericPojoBuilder;
import com.example.simplegame.rules.context.FreeGameRoundRuleContext;
import com.example.simplegame.rules.context.NormalGameRoundContext;

public class RulesTests {

	@Test
	public void freeGameRuleTest() {
		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new)
				.with(PlayRequest::setFreeRound, false)
				.with(PlayRequest::setCoins, 10)
				.with(PlayRequest::setPlayerId, "test")
				.build();

		FreeGameRoundRuleContext freeGameRuleContext = new FreeGameRoundRuleContext(betInfo, 0);
		FreeGameRoundRule freeGameRule = new FreeGameRoundRule(freeGameRuleContext);
		boolean res = freeGameRule.process();
		assertTrue(res);
	}
	
	@Test(expected = RuleEvaluationException.class)
	public void freeGameRuleTest1() {
		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new)
				.with(PlayRequest::setFreeRound, true)
				.with(PlayRequest::setCoins, 10)
				.with(PlayRequest::setPlayerId, "test")
				.build();

		FreeGameRoundRuleContext freeGameRuleContext = new FreeGameRoundRuleContext(betInfo, 0);
		FreeGameRoundRule freeGameRule = new FreeGameRoundRule(freeGameRuleContext);
		boolean res = freeGameRule.process();
		assertTrue(res);
	}
	
	@Test(expected = RuleEvaluationException.class)
	public void freeGameRuleTest2() {
		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new)
				.with(PlayRequest::setFreeRound, false)
				.with(PlayRequest::setCoins, 0)
				.with(PlayRequest::setPlayerId, "test")
				.build();

		FreeGameRoundRuleContext freeGameRuleContext = new FreeGameRoundRuleContext(betInfo, 1);
		FreeGameRoundRule freeGameRule = new FreeGameRoundRule(freeGameRuleContext);
		boolean res = freeGameRule.process();
		assertTrue(res);
	}

	@Test
	public void normalGameRuleTest() {
		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new)
				.with(PlayRequest::setFreeRound, false)
				.with(PlayRequest::setCoins, 10)
				.with(PlayRequest::setPlayerId, "test")
				.build();

		
		NormalGameRoundContext normalGameContext = new NormalGameRoundContext(betInfo.getCoins(), betInfo.isFreeRound());
		NormalGameRoundRule normalRoundRule = new NormalGameRoundRule(normalGameContext);
		boolean res = normalRoundRule.process();
		assertTrue(res);
	}

	@Test(expected = RuleEvaluationException.class)
	public void normalGameRuleTest1() {
		PlayRequest betInfo = GenericPojoBuilder.of(PlayRequest::new)
				.with(PlayRequest::setFreeRound, false)
				.with(PlayRequest::setCoins, 5)
				.with(PlayRequest::setPlayerId, "test")
				.build();

		
		NormalGameRoundContext normalGameContext = new NormalGameRoundContext(betInfo.getCoins(), betInfo.isFreeRound());
		NormalGameRoundRule normalRoundRule = new NormalGameRoundRule(normalGameContext);
		boolean res = normalRoundRule.process();
		assertTrue(res);
	}
}
