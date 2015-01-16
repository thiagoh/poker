package com.thiagoh.poker.dao.util;

import com.thiagoh.poker.model.Game;

public class GameDao extends BaseDao<Game> {

	@Override
	public Class<Game> getClazz() {
		return Game.class;
	}

}
