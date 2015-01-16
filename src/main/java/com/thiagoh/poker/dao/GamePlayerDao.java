package com.thiagoh.poker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.GamePlayer;

@Repository
public class GamePlayerDao extends BaseDao<GamePlayer> {

	@Override
	public Class<GamePlayer> getClazz() {
		return GamePlayer.class;
	}

	public List<GamePlayer> findByGameId(long gameId) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			Query query = session.createQuery("select gp from " + GamePlayer.class.getName() + " gp where gp.game.id = :gameId ");

			query.setLong("gameId", gameId);

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
	
	public List<GamePlayer> findByPlayerId(long playerId) throws SystemException {
		
		Session session = null;
		
		try {
			
			session = openSession();
			
			Query query = session.createQuery("select gp from " + GamePlayer.class.getName() + " gp where gp.player.id = :playerId ");
			
			query.setLong("playerId", playerId);
			
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
