package com.thiagoh.poker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Event;

@Repository
public class EventDao extends BaseDao<Event> {

	@Override
	public Class<Event> getClazz() {
		return Event.class;
	}

	@SuppressWarnings("unchecked")
	public Event get(String name) throws PortalException, SystemException {

		Event card = fetch(name);

		if (card == null) {

			PortalException pe = null;

			try {

				String simpleName = getClazz().getSimpleName();

				Class<? extends PortalException> clazz = (Class<? extends PortalException>) Class
						.forName("com.thiagoh.poker.NoSuch" + simpleName + "Exception");

				String message = "No such " + simpleName + " with name = " + name ;

				pe = clazz.getConstructor(String.class).newInstance(message);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			throw pe;

		}

		return card;
	}

	public Event fetch(String name) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			Query query = session.createQuery("FROM " + Event.class.getName()
					+ " c WHERE c.name = :name");

			query.setString("name", name);

			query.setMaxResults(1);

			@SuppressWarnings("unchecked")
			List<Event> list = query.list();

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
