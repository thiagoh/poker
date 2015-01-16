package com.thiagoh.poker.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.execution.GameState;
import com.thiagoh.poker.execution.TableCardsState;
import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.GamePlayerForm;

@Service
public class GameController extends BaseController {

	@Autowired
	protected GamePlayerController gamePlayerController;

	@Autowired
	protected CardController cardController;

	public Game add(String face1, String suit1, String face2, String suit2, String face3, String suit3, String face4,
			String suit4, String face5, String suit5, GameState state, TableCardsState tableCardsState,
			List<GamePlayerForm> gamePlayerForms) throws PortalException, SystemException {

		return save(true, 0L, face1, suit1, face2, suit2, face3, suit3, face4, suit4, face5, suit5, state,
				tableCardsState, gamePlayerForms);
	}

	public Game update(long gameId, String face1, String suit1, String face2, String suit2, String face3, String suit3,
			String face4, String suit4, String face5, String suit5, GameState state, TableCardsState tableCardsState,
			List<GamePlayerForm> gamePlayerForms) throws PortalException, SystemException {

		return save(false, gameId, face1, suit1, face2, suit2, face3, suit3, face4, suit4, face5, suit5, state,
				tableCardsState, gamePlayerForms);
	}

	private Game save(boolean isNew, long gameId, String face1, String suit1, String face2, String suit2, String face3,
			String suit3, String face4, String suit4, String face5, String suit5, GameState state,
			TableCardsState tableCardsState, List<GamePlayerForm> gamePlayerForms) throws PortalException,
			SystemException {

		Game game = null;

		if (isNew) {

			game = gameDao.create();

		} else {

			game = gameDao.get(gameId);
		}

		Card tableCard1 = cardController.get(face1, suit1);
		Card tableCard2 = cardController.get(face2, suit2);
		Card tableCard3 = cardController.get(face3, suit3);
		Card tableCard4 = cardController.get(face4, suit4);
		Card tableCard5 = cardController.get(face5, suit5);

		game.setTableCard1(tableCard1);
		game.setTableCard2(tableCard2);
		game.setTableCard3(tableCard3);
		game.setTableCard4(tableCard4);
		game.setTableCard5(tableCard5);

		game.setState(state.toString());
		game.setTableCardsState(tableCardsState.toString());

		Set<GamePlayer> gamePlayers = new HashSet<GamePlayer>();

		if (!game.isNew()) {
			gamePlayerController.deleteByGameId(gameId);
		}

		for (GamePlayerForm form : gamePlayerForms) {

			GamePlayer gamePlayer = gamePlayerController.add(form.getPlayerId(), form.getFace1(), form.getSuit1(),
					form.getFace2(), form.getSuit2(), form.getState());

			gamePlayers.add(gamePlayer);
		}

		game.setGamePlayers(gamePlayers);

		gameDao.save(game);

		return game;
	}

	public Game get(long gameId) throws PortalException, SystemException {

		return gameDao.get(gameId);
	}
	
	public Game fetch(long gameId) throws SystemException {
		
		return gameDao.fetch(gameId);
	}

	public void delete(Game game) throws SystemException {

		List<GamePlayer> gamePlayers = gamePlayerDao.findByGameId(game.getId());
		
		for (GamePlayer gamePlayer : gamePlayers) {
			
			gamePlayerController.delete(gamePlayer);
		}
	}

	public void delete(long gameId) throws SystemException {

		Game game = gameDao.fetch(gameId);

		if (game == null) {
			return;
		}

		delete(game);
	}

	public List<Game> findAll() throws SystemException {

		return gameDao.findAll();
	}

	public long countAll() throws SystemException {

		return gameDao.countAll();
	}
}
