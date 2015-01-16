package com.thiagoh.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thiagoh.poker.model.Card;
import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.Player;
import com.thiagoh.poker.model.TableCardsState;

public class GameTest {

	@Test
	public void testGame1() {

		System.out.println();

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
	}

	@Test
	public void testGame2() {

		System.out.println();

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
		Assert.assertEquals(p1, gamePlayer1.getPlayer());

		System.out.println(gamePlayer1);

		GamePlayer gamePlayer2 = game.getGamePlayer(p2);

		Assert.assertNotNull(gamePlayer2);
		Assert.assertNotNull(gamePlayer2.getHand());
		Assert.assertNotNull(gamePlayer2.getGame());
		Assert.assertNotNull(gamePlayer2.getPlayer());
		Assert.assertEquals(p2, gamePlayer2.getPlayer());

		System.out.println(gamePlayer2);

		Assert.assertEquals(game.countPlayers(), 2);
	}

	@Test
	public void testGame3() {

		System.out.println();

		Game game = Game.createNewGame();

		List<Player> players = new ArrayList<Player>();

		players.add(new Player("paulo@thiagoh.com", "paulo amorim"));
		players.add(new Player("joca@thiagoh.com", "joca"));
		players.add(new Player("danilo@thiagoh.com", "danilo novelino"));
		players.add(new Player("thiago@thiagoh.com", "thiago andrade"));
		players.add(new Player("rafaprax@thiagoh.com", "prax"));
		players.add(new Player("victor@thiagoh.com", "victor"));
		players.add(new Player("renato@thiagoh.com", "renato"));
		players.add(new Player("ygor@thiagoh.com", "joao grandao"));

		game.join(players);

		Assert.assertEquals(game.countPlayers(), players.size());

		game.start();

		Assert.assertEquals(game.countPlayers(), players.size());

		for (Player player : players) {

			Assert.assertNotNull(player);

			GamePlayer gamePlayer = game.getGamePlayer(player);

			Assert.assertNotNull(gamePlayer);
			Assert.assertNotNull(gamePlayer.getHand());
			Assert.assertNotNull(gamePlayer.getGame());
			Assert.assertNotNull(gamePlayer.getPlayer());
			Assert.assertEquals(player, gamePlayer.getPlayer());

			System.out.println(gamePlayer);
		}

		Assert.assertEquals(game.countPlayers(), players.size());

		Assert.assertEquals(game.countPlayers(), players.size());

		Assert.assertEquals(game.getTableCardsState(), TableCardsState.PRE_FLOP);

		Card[] floppedCards = game.dropCards();

		Card[] tableCardsFlop = game.getFlop();

		Assert.assertEquals(floppedCards.length, 3);
		Assert.assertEquals(tableCardsFlop.length, 3);
		Assert.assertArrayEquals(floppedCards, tableCardsFlop);

		Assert.assertEquals(game.countPlayers(), players.size());

		Card[] tableCards = game.getTableCards();

		tableCards = Arrays.copyOfRange(tableCards, 0, 3);

		Assert.assertArrayEquals(floppedCards, tableCards);

		Assert.assertEquals(game.getTableCardsState(), TableCardsState.FLOP);

		System.out.println(game.getTableCardsState());

		for (Card card : floppedCards) {
			System.out.println(card);
		}

		floppedCards = game.dropCards();

		Card tableCardsTurn = game.getTurn();

		Assert.assertNotNull(tableCardsTurn);
		Assert.assertEquals(game.getTableCardsState(), TableCardsState.TURN);

		System.out.println(game.getTableCardsState());

		for (Card card : floppedCards) {
			System.out.println(card);
		}

		floppedCards = game.dropCards();

		Card tableCardsRiver = game.getRiver();

		Assert.assertNotNull(tableCardsRiver);
		Assert.assertEquals(game.getTableCardsState(), TableCardsState.RIVER);

		System.out.println(game.getTableCardsState());

		for (Card card : floppedCards) {
			System.out.println(card);
		}
	}
}
