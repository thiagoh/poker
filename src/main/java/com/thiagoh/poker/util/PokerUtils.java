package com.thiagoh.poker.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.thiagoh.poker.execution.Card;
import com.thiagoh.poker.execution.Face;
import com.thiagoh.poker.execution.Suit;

public class PokerUtils {

	private static final long INITIAL_SEED = System.currentTimeMillis();
	private static Set<Card> _pack;

	public static Card randomFromPack() {

		return random(getPack());
	}

	public static Card random(Set<Card> availableCards) {

		Random random = new Random(System.currentTimeMillis() * INITIAL_SEED);

		int nextInt = random.nextInt(availableCards.size());

		Iterator<Card> iterator = availableCards.iterator();
		Card card = null;

		for (int i = 0; i <= nextInt; i++, card = iterator.next())
			;

		if (card == null)
			System.out.println();

		return card;
	}

	public static Set<Card> getPack() {

		if (_pack == null) {

			synchronized (PokerUtils.class) {

				if (_pack == null) {

					Set<Card> pack = new HashSet<Card>();

					pack.add(new Card(Face.TWO, Suit.CLUBS));
					pack.add(new Card(Face.TWO, Suit.DIAMONDS));
					pack.add(new Card(Face.TWO, Suit.HEARTS));
					pack.add(new Card(Face.TWO, Suit.SPADES));

					pack.add(new Card(Face.THREE, Suit.CLUBS));
					pack.add(new Card(Face.THREE, Suit.DIAMONDS));
					pack.add(new Card(Face.THREE, Suit.HEARTS));
					pack.add(new Card(Face.THREE, Suit.SPADES));

					pack.add(new Card(Face.FOUR, Suit.CLUBS));
					pack.add(new Card(Face.FOUR, Suit.DIAMONDS));
					pack.add(new Card(Face.FOUR, Suit.HEARTS));
					pack.add(new Card(Face.FOUR, Suit.SPADES));

					pack.add(new Card(Face.FIVE, Suit.CLUBS));
					pack.add(new Card(Face.FIVE, Suit.DIAMONDS));
					pack.add(new Card(Face.FIVE, Suit.HEARTS));
					pack.add(new Card(Face.FIVE, Suit.SPADES));

					pack.add(new Card(Face.SIX, Suit.CLUBS));
					pack.add(new Card(Face.SIX, Suit.DIAMONDS));
					pack.add(new Card(Face.SIX, Suit.HEARTS));
					pack.add(new Card(Face.SIX, Suit.SPADES));

					pack.add(new Card(Face.SEVEN, Suit.CLUBS));
					pack.add(new Card(Face.SEVEN, Suit.DIAMONDS));
					pack.add(new Card(Face.SEVEN, Suit.HEARTS));
					pack.add(new Card(Face.SEVEN, Suit.SPADES));

					pack.add(new Card(Face.EIGHT, Suit.CLUBS));
					pack.add(new Card(Face.EIGHT, Suit.DIAMONDS));
					pack.add(new Card(Face.EIGHT, Suit.HEARTS));
					pack.add(new Card(Face.EIGHT, Suit.SPADES));

					pack.add(new Card(Face.NINE, Suit.CLUBS));
					pack.add(new Card(Face.NINE, Suit.DIAMONDS));
					pack.add(new Card(Face.NINE, Suit.HEARTS));
					pack.add(new Card(Face.NINE, Suit.SPADES));

					pack.add(new Card(Face.TEN, Suit.CLUBS));
					pack.add(new Card(Face.TEN, Suit.DIAMONDS));
					pack.add(new Card(Face.TEN, Suit.HEARTS));
					pack.add(new Card(Face.TEN, Suit.SPADES));

					pack.add(new Card(Face.JACK, Suit.CLUBS));
					pack.add(new Card(Face.JACK, Suit.DIAMONDS));
					pack.add(new Card(Face.JACK, Suit.HEARTS));
					pack.add(new Card(Face.JACK, Suit.SPADES));

					pack.add(new Card(Face.QUEEN, Suit.CLUBS));
					pack.add(new Card(Face.QUEEN, Suit.DIAMONDS));
					pack.add(new Card(Face.QUEEN, Suit.HEARTS));
					pack.add(new Card(Face.QUEEN, Suit.SPADES));

					pack.add(new Card(Face.KING, Suit.CLUBS));
					pack.add(new Card(Face.KING, Suit.DIAMONDS));
					pack.add(new Card(Face.KING, Suit.HEARTS));
					pack.add(new Card(Face.KING, Suit.SPADES));

					pack.add(new Card(Face.ACE, Suit.CLUBS));
					pack.add(new Card(Face.ACE, Suit.DIAMONDS));
					pack.add(new Card(Face.ACE, Suit.HEARTS));
					pack.add(new Card(Face.ACE, Suit.SPADES));

					_pack = pack;
				}
			}
		}

		return new HashSet<Card>(_pack);
	}
}
