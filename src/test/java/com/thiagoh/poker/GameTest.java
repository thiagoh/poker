package com.thiagoh.poker;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.Player;

public class GameTest {

	@Test
	public void testGame1() {

		Game game = Game.createNewGame();

		Player p1 = new Player("paulo@thiagoh.com", "paulo leite");
		Player p2 = new Player("antonio@thiagoh.com", "antonio carlos");

		game.join(p1);
		game.join(p2);

		Assert.assertEquals(game.countPlayers(), 2);

		game.start();

		Assert.assertEquals(game.countPlayers(), 2);

		GamePlayer gamePlayer1 = game.getGamePlayer(p1);

		Assert.assertNotNull(gamePlayer1);
		Assert.assertNotNull(gamePlayer1.getHand());
		Assert.assertNotNull(gamePlayer1.getGame());
		Assert.assertNotNull(gamePlayer1.getPlayer());

		System.out.println(gamePlayer1);

		GamePlayer gamePlayer2 = game.getGamePlayer(p2);

		Assert.assertNotNull(gamePlayer2);
		Assert.assertNotNull(gamePlayer2.getHand());
		Assert.assertNotNull(gamePlayer2.getGame());
		Assert.assertNotNull(gamePlayer2.getPlayer());

		System.out.println(gamePlayer2);

		Assert.assertEquals(game.countPlayers(), 2);

		game.dropCards();

		Assert.assertEquals(game.countPlayers(), 2);
	}

	@Test
	public void testGame2() {

		Game game = Game.createNewGame();

		Player p1 = new Player("paulo@thiagoh.com", "paulo leite");
		Player p2 = new Player("antonio@thiagoh.com", "antonio carlos");

		game.join(p1, p2);

		Assert.assertEquals(game.countPlayers(), 2);

		game.start();

		Assert.assertEquals(game.countPlayers(), 2);

		GamePlayer gamePlayer1 = game.getGamePlayer(p1);

		Assert.assertNotNull(gamePlayer1);
		Assert.assertNotNull(gamePlayer1.getHand());
		Assert.assertNotNull(gamePlayer1.getGame());
		Assert.assertNotNull(gamePlayer1.getPlayer());

		System.out.println(gamePlayer1);

		GamePlayer gamePlayer2 = game.getGamePlayer(p2);

		Assert.assertNotNull(gamePlayer2);
		Assert.assertNotNull(gamePlayer2.getHand());
		Assert.assertNotNull(gamePlayer2.getGame());
		Assert.assertNotNull(gamePlayer2.getPlayer());

		System.out.println(gamePlayer2);

		Assert.assertEquals(game.countPlayers(), 2);

		game.dropCards();

		Assert.assertEquals(game.countPlayers(), 2);
	}

	@Test
	public void testGame3() {

		Game game = Game.createNewGame();

		Player p1 = new Player("paulo@thiagoh.com", "paulo leite");
		Player p2 = new Player("antonio@thiagoh.com", "antonio carlos");

		List<Player> players = new ArrayList<Player>();

		players.add(p1);
		players.add(p2);

		game.join(players);

		Assert.assertEquals(game.countPlayers(), players.size());

		game.start();

		Assert.assertEquals(game.countPlayers(), players.size());

		for (Player player : players) {

			Assert.assertNotNull(player);

			GamePlayer gamePlayer = game.getGamePlayer(p1);

			Assert.assertNotNull(gamePlayer);
			Assert.assertNotNull(gamePlayer.getHand());
			Assert.assertNotNull(gamePlayer.getGame());
			Assert.assertNotNull(gamePlayer.getPlayer());

			System.out.println(gamePlayer);
		}

		Assert.assertEquals(game.countPlayers(), players.size());

		Card[] tableCards1 = game.dropCards();

		Assert.assertEquals(game.countPlayers(), players.size());

		Card[] tableCards2 = game.getTableCards();

		Assert.assertArrayEquals(tableCards1, tableCards2);
	}
}
