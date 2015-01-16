package com.thiagoh.poker.dao;

import org.springframework.stereotype.Repository;

import com.thiagoh.poker.model.Player;

@Repository
public class PlayerDao extends BaseDao<Player> {

	@Override
	public Class<Player> getClazz() {
		return Player.class;
	}

}
