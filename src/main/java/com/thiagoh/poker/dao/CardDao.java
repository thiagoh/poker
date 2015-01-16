package com.thiagoh.poker.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

				Class<? extends PortalException> clazz = (Class<? extends PortalException>) Class.forName("NoSuch"
						+ simpleName + "Exception");

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

			Criteria criteria = session.createCriteria(Card.class);

			criteria.add(Restrictions.eq("face", face));
			criteria.add(Restrictions.eq("suit", suit));

			criteria.setMaxResults(1);

			@SuppressWarnings("unchecked")
			List<Card> list = criteria.list();

			if (list.isEmpty()) {
				return null;
			}

			return list.get(0);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

			closeSession();
		}
	}

}
