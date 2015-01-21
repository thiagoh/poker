package com.thiagoh.poker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoh.poker.GamePlayerDataException;
import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.Face;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.GamePlayerState;
import com.thiagoh.poker.model.Player;
import com.thiagoh.poker.model.Suit;

@Service
public class GamePlayerService extends BaseService {

	@Autowired
	protected GameService gameService;

	@Autowired
	protected PlayerService playerService;

	@Autowired
	protected CardService cardService;

	public GamePlayer add(long playerId, Face face1, Suit suit1, Face face2, Suit suit2, GamePlayerState state)
			throws SystemException, PortalException {

		return save(true, 0L, playerId, face1, suit1, face2, suit2, state);
	}

	public GamePlayer update(long gamePlayerId, Face face1, Suit suit1, Face face2, Suit suit2, GamePlayerState state)
			throws SystemException, PortalException {

		return save(false, gamePlayerId, 0L, face1, suit1, face2, suit2, state);
	}

	private GamePlayer save(boolean isNew, long gamePlayerId, long playerId, Face face1, Suit suit1, Face face2,
			Suit suit2, GamePlayerState state) throws SystemException, PortalException {

		GamePlayer gamePlayer = null;

		if (isNew) {

			gamePlayer = gamePlayerDao.create();

			Player player = playerService.get(playerId);

			gamePlayer.setPlayer(player);

		} else {

			gamePlayer = gamePlayerDao.get(gamePlayerId);
		}

		validateData(face1, suit1, face2, suit2);

		Card card1 = cardService.get(face1, suit1);
		gamePlayer.setCard1(card1);

		Card card2 = cardService.get(face2, suit2);
		gamePlayer.setCard2(card2);

		gamePlayer.setState(state);

		gamePlayerDao.save(gamePlayer);

		return gamePlayer;
	}

	private void validateData(Face face1, Suit suit1, Face face2, Suit suit2) throws GamePlayerDataException {

		if (face1 == null) {
			throw new GamePlayerDataException();
		}

		if (face2 == null) {
			throw new GamePlayerDataException();
		}

		if (suit1 == null) {
			throw new GamePlayerDataException();
		}

		if (suit2 == null) {
			throw new GamePlayerDataException();
		}

		if (face1 == face2 && suit1 == suit2) {
			throw new GamePlayerDataException("Cards must be different");
		}
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

	public List<GamePlayer> findAll() throws SystemException {

		return gamePlayerDao.findAll();
	}

	public long countAll() throws SystemException {

		return gamePlayerDao.countAll();
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
