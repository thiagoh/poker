package com.thiagoh.poker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.execution.GamePlayerState;
import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.Player;

@Service
public class GamePlayerController extends BaseController {
	
	@Autowired
	protected GameController gameController;
	
	@Autowired
	protected PlayerController playerController;
	
	@Autowired
	protected CardController cardController;

	public GamePlayer add(long playerId, String face1, String suit1, String face2, String suit2, GamePlayerState state)
			throws SystemException, PortalException {

		GamePlayer gamePlayer = gamePlayerDao.create();

		Card card1 = cardController.get(face1, suit1);
		gamePlayer.setCard1(card1);

		Card card2 = cardController.get(face2, suit2);
		gamePlayer.setCard2(card2);

		gamePlayer.setState(state.toString());

		Player player = playerController.get(playerId);

		gamePlayer.setPlayer(player);

		gamePlayerDao.save(gamePlayer);

		return gamePlayer;
	}

	public void deleteByGameId(long gameId) throws SystemException {

		List<GamePlayer> list = gamePlayerDao.findByGameId(gameId);

		for (GamePlayer gamePlayer : list) {
			delete(gamePlayer);
		}
	}
	
	public void deleteByPlayerId(long playerId) throws SystemException {
		
		List<GamePlayer> list = gamePlayerDao.findByPlayerId(playerId);
		
		for (GamePlayer gamePlayer : list) {
			delete(gamePlayer);
		}
	}

	public List<GamePlayer> findByPlayerId(long playerId) throws SystemException {

		return gamePlayerDao.findByPlayerId(playerId);
	}
	
	public List<GamePlayer> findByGameId(long gameId) throws SystemException {
		
		return gamePlayerDao.findByGameId(gameId);
	}

	public void delete(GamePlayer gamePlayer) throws SystemException {

		gamePlayerDao.delete(gamePlayer);
	}

}
