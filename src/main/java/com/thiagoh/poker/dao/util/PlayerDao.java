package com.thiagoh.poker.dao.util;

import com.thiagoh.poker.model.Player;

public class PlayerDao extends BaseDao<Player> {

	@Override
	public Class<Player> getClazz() {
		return Player.class;
	}

}
