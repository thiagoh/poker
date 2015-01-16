package com.thiagoh.poker.dao;

import org.springframework.stereotype.Repository;

import com.thiagoh.poker.model.Game;

@Repository
public class GameDao extends BaseDao<Game> {

	@Override
	public Class<Game> getClazz() {
		return Game.class;
	}

}
