package com.thiagoh.poker.controller;

import java.util.List;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.execution.GamePlayerState;
import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.Player;

public class GamePlayerController extends BaseController {

	public GamePlayer add(long playerId, String face1, String suit1, String face2, String suit2, GamePlayerState state)
			throws SystemException, PortalException {

		GamePlayer gamePlayer = gamePlayerDao.create();

		Card card1 = cardDao.fetch(face1, suit1);
		gamePlayer.setCard1(card1);

		Card card2 = cardDao.fetch(face2, suit2);
		gamePlayer.setCard2(card2);

		gamePlayer.setState(state.toString());

		Player player = playerDao.get(playerId);

		gamePlayer.setPlayer(player);

		return gamePlayer;
	}

	public void deleteByGameId(long gameId) throws SystemException {

		List<GamePlayer> list = gamePlayerDao.findByGameId(gameId);

		for (GamePlayer gamePlayer : list) {
			delete(gamePlayer);
		}
	}

	public void delete(GamePlayer gamePlayer) throws SystemException {

		gamePlayerDao.delete(gamePlayer);
	}

}
