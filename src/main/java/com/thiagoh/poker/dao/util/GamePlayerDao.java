package com.thiagoh.poker.dao.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.GamePlayer;

public class GamePlayerDao extends BaseDao<GamePlayer> {

	@Override
	public Class<GamePlayer> getClazz() {
		return GamePlayer.class;
	}

	public List<GamePlayer> findByGameId(long gameId) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			Query query = session.createQuery("select gp from " + GamePlayer.class.getName() + " where gp.gameId = ?");

			query.setLong(1, gameId);

			query.setMaxResults(1);

			@SuppressWarnings("unchecked")
			List<GamePlayer> list = query.list();

			return list;

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

			closeSession();
		}

	}
}
