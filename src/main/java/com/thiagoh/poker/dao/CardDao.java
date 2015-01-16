package com.thiagoh.poker.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Card;

@Repository
public class CardDao extends BaseDao<Card> {

	@Override
	public Class<Card> getClazz() {
		return Card.class;
	}

	@SuppressWarnings("unchecked")
	public Card get(String face, String suit) throws PortalException, SystemException {

		Card card = fetch(face, suit);

		if (card == null) {

			PortalException pe = null;

			try {

				String simpleName = getClazz().getSimpleName();

				Class<? extends PortalException> clazz = (Class<? extends PortalException>) Class
						.forName("com.thiagoh.poker.NoSuch" + simpleName + "Exception");

				String message = "No such " + simpleName + " with face = " + face + " and suit = " + suit;

				pe = clazz.getConstructor(String.class).newInstance(message);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			throw pe;

		}

		return card;
	}

	public Card fetch(String face, String suit) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			Query query = session.createQuery("FROM " + Card.class.getName()
					+ " c WHERE c.face = :face AND c.suit = :suit ");

			query.setString("face", face);
			query.setString("suit", suit);

			query.setMaxResults(1);

			return (Card) query.uniqueResult();

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

			closeSession();
		}
	}

}
