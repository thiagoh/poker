package com.thiagoh.poker.controller;

import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Card;

public class CardController extends BaseController {

	public Card get(String face, String suit) throws SystemException {

		Card card = cardDao.fetch(face, suit);

		if (card == null) {

			card = cardDao.create();

			card.setFace(face);
			card.setSuit(suit);

			card = cardDao.save(card);
		}

		return card;
	}
}
