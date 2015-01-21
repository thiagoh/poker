package com.thiagoh.poker.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.execution.GameState;
import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.Face;
import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.GamePlayerForm;
import com.thiagoh.poker.model.Suit;
import com.thiagoh.poker.model.TableCardsState;

@Service
public class GameService extends BaseService {

	@Autowired
	protected GamePlayerService gamePlayerService;

	@Autowired
	protected CardService cardService;

	public Game add(Face face1, Suit suit1, Face face2, Suit suit2, Face face3,
			Suit suit3, Face face4, Suit suit4, Face face5, Suit suit5, GameState state, TableCardsState tableCardsState,
			List<GamePlayerForm> gamePlayerForms) throws PortalException, SystemException {

		return save(true, 0L, face1, suit1, face2, suit2, face3, suit3, face4, suit4, face5, suit5, state,
				tableCardsState, gamePlayerForms);
	}

	public Game update(long gameId, Face face1, Suit suit1, Face face2, Suit suit2, Face face3,
			Suit suit3, Face face4, Suit suit4, Face face5, Suit suit5, GameState state, TableCardsState tableCardsState,
			List<GamePlayerForm> gamePlayerForms) throws PortalException, SystemException {

		return save(false, gameId, face1, suit1, face2, suit2, face3, suit3, face4, suit4, face5, suit5, state,
				tableCardsState, gamePlayerForms);
	}

	private Game save(boolean isNew, long gameId, Face face1, Suit suit1, Face face2, Suit suit2, Face face3,
			Suit suit3, Face face4, Suit suit4, Face face5, Suit suit5, GameState state,
			TableCardsState tableCardsState, List<GamePlayerForm> gamePlayerForms) throws PortalException,
			SystemException {

		Game game = null;

		if (isNew) {

			game = gameDao.create();

		} else {

			game = gameDao.get(gameId);
		}

		Card tableCard1 = cardService.get(face1, suit1);
		Card tableCard2 = cardService.get(face2, suit2);
		Card tableCard3 = cardService.get(face3, suit3);
		Card tableCard4 = cardService.get(face4, suit4);
		Card tableCard5 = cardService.get(face5, suit5);

		game.setTableCard1(tableCard1);
		game.setTableCard2(tableCard2);
		game.setTableCard3(tableCard3);
		game.setTableCard4(tableCard4);
		game.setTableCard5(tableCard5);

		game.setState(state);
		game.setTableCardsState(tableCardsState);

		Set<GamePlayer> gamePlayers = new HashSet<GamePlayer>();

		if (!game.isNew()) {
			gamePlayerService.deleteByGameId(gameId);
		}

		for (GamePlayerForm form : gamePlayerForms) {

			GamePlayer gamePlayer = gamePlayerService.add(form.getPlayerId(), form.getFace1(), form.getSuit1(),
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
			
			gamePlayerService.delete(gamePlayer);
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
