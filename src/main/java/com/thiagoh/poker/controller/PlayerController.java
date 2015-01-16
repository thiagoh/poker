package com.thiagoh.poker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoh.poker.PlayerEmailAlreadyInUseException;
import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Player;

@Service
public class PlayerController extends BaseController {

	@Autowired
	protected GamePlayerController gamePlayerController;

	public Player add(String name, String email) throws PortalException, SystemException {

		return save(true, 0L, name, email);
	}

	public Player update(long playerId, String name, String email) throws PortalException, SystemException {

		return save(false, playerId, name, email);
	}

	private Player save(boolean isNew, long playerId, String name, String email) throws PortalException,
			SystemException {

		Player player = null;
		Player otherPlayer = playerDao.fetch(email);

		if (isNew) {

			player = playerDao.create();

			if (otherPlayer != null) {
				throw new PlayerEmailAlreadyInUseException("Email is already in use");
			}

		} else {

			player = playerDao.get(playerId);

			if (otherPlayer != null && !player.equals(otherPlayer)) {
				throw new PlayerEmailAlreadyInUseException("Email is already in use");
			}
		}

		player.setEmail(email);
		player.setName(name);

		playerDao.save(player);

		return player;
	}

	public void delete(long playerId) throws SystemException {

		Player player = playerDao.fetch(playerId);

		if (player == null) {
			return;
		}

		delete(player);
	}

	public void delete(Player player) throws SystemException {

		gamePlayerController.deleteByPlayerId(player.getId());

		playerDao.delete(player);
	}

	public List<Player> findAll() throws SystemException {

		return playerDao.findAll();
	}

	public long countAll() throws SystemException {

		return playerDao.countAll();
	}

	public Player get(long playerId) throws PortalException, SystemException {

		return playerDao.get(playerId);
	}

	public Player get(String email) throws PortalException, SystemException {

		return playerDao.get(email);
	}

	public Player fetch(String email) throws SystemException {

		return playerDao.fetch(email);
	}
}
