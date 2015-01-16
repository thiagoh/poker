package com.thiagoh.poker;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.thiagoh.poker.execution.Card;
import com.thiagoh.poker.util.PokerUtils;

public class PokerUtilsTest {

	@Test
	public void test_getPack() {

		Set<Card> pack = PokerUtils.getPack();

		Assert.assertNotNull(pack);
		Assert.assertEquals(pack.size(), 52);
	}

	@Test
	public void test_random() {

		Set<Card> pack = PokerUtils.getPack();

		int i = 0;

		while (!pack.isEmpty()) {

			Card card = PokerUtils.randomCard(pack);

			Assert.assertNotNull(card);
			Assert.assertNotNull(card.getFace());
			Assert.assertNotNull(card.getSuit());

			pack.remove(card);

			i++;
		}

		Assert.assertEquals(52, i);
	}
}
