package com.thiagoh.poker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Player;

@Repository
public class PlayerDao extends BaseDao<Player> {

	@Override
	public Class<Player> getClazz() {
		return Player.class;
	}

	@SuppressWarnings("unchecked")
	public Player get(String email) throws PortalException, SystemException {

		Player model = fetch(email);

		if (model == null) {

			PortalException pe = null;

			try {

				String simpleName = getClazz().getSimpleName();

				Class<? extends PortalException> clazz = (Class<? extends PortalException>) Class.forName("NoSuch"
						+ simpleName + "Exception");

				String message = "No such " + simpleName + " with email " + email;

				pe = clazz.getConstructor(String.class).newInstance(message);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			throw pe;
		}

		return model;
	}

	public Player fetch(String email) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			Query query = session.createQuery("select p from " + Player.class.getName() + " p where p.email = :email");

			query.setString("email", email);

			query.setMaxResults(1);

			@SuppressWarnings("unchecked")
			List<Player> list = query.list();

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
