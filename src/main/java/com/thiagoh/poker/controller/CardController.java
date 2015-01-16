package com.thiagoh.poker.controller;

import org.springframework.stereotype.Service;

import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.Face;
import com.thiagoh.poker.model.Suit;

@Service
public class CardController extends BaseController {

	public Card get(Face face, Suit suit) throws SystemException {

		Card card = cardDao.fetch(face.toString(), suit.toString());

		if (card == null) {

			card = cardDao.create();

			card.setFace(face);
			card.setSuit(suit);

			card = cardDao.save(card);
		}

		return card;
	}
}
