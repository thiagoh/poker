package com.thiagoh.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thiagoh.poker.execution.Card;
import com.thiagoh.poker.execution.GameState;
import com.thiagoh.poker.execution.Hand;
import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.GamePlayer;
import com.thiagoh.poker.model.GamePlayerForm;
import com.thiagoh.poker.model.GamePlayerState;
import com.thiagoh.poker.model.Player;
import com.thiagoh.poker.model.TableCardsState;
import com.thiagoh.poker.service.CardService;
import com.thiagoh.poker.service.GamePlayerService;
import com.thiagoh.poker.service.GameService;
import com.thiagoh.poker.service.PlayerService;
import com.thiagoh.poker.util.PokerUtils;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "/applicationContext.xml" and
// "/applicationContext-test.xml"
// in the root of the classpath
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class GamePlayerServiceTest {

	@Autowired
	protected PlayerService playerController;

	@Autowired
	protected GamePlayerService gamePlayerController;

	@Autowired
	protected CardService cardController;

	private List<Player> players;

	@Before
	public void setup() {

		deleteAll();
	}

	@After
	public void tearDown() {

		deleteAll();
	}

	private void deleteAll() {

		try {

			List<GamePlayer> list = gamePlayerController.findAll();

			for (GamePlayer game : list) {
				gamePlayerController.delete(game);
			}

		} catch (SystemException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		if (players == null) {
			players = new ArrayList<Player>();
		} else {

			for (Player player : players) {

				try {

					playerController.delete(player);

				} catch (SystemException e) {
					e.printStackTrace();
					Assert.fail(e.getMessage());
				}
			}
		}
	}

	@Test
	public void test_add() {

		Set<Card> availableCards = PokerUtils.getPack();

		Card card1 = PokerUtils.randomCard(availableCards);
		Card card2 = PokerUtils.randomCard(availableCards);

		GamePlayerState state = GamePlayerState.GAMING;

		String p1name = "carlos kami";
		String p1email = "carlos@thiagoh.com";

		try {

			Player player1 = playerController.add(p1name, p1email);

			players.add(player1);

			GamePlayer gamePlayer = gamePlayerController.add(player1.getId(), card1.getFace(), card1.getSuit(),
					card2.getFace(), card2.getSuit(), state);

			Assert.assertEquals(gamePlayer.getState(), state);
			Assert.assertEquals(gamePlayer.getPlayer(), player1);

			com.thiagoh.poker.model.Card modelCard1 = cardController.get(card1.getFace(), card1.getSuit());
			com.thiagoh.poker.model.Card modelCard2 = cardController.get(card2.getFace(), card2.getSuit());

			Assert.assertEquals(gamePlayer.getCard1(), modelCard1);
			Assert.assertEquals(gamePlayer.getCard2(), modelCard2);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void test_add_game_data_exception() {

		Set<Card> availableCards = PokerUtils.getPack();

		Card card1 = PokerUtils.randomCard(availableCards);
		Card card2 = PokerUtils.randomCard(availableCards);

		GamePlayerState state = GamePlayerState.GAMING;

		String p1name = "carlos kami";
		String p1email = "carlos@thiagoh.com";

		try {

			Player player1 = playerController.add(p1name, p1email);

			players.add(player1);

			try {

				GamePlayer gamePlayer = gamePlayerController.add(player1.getId(), null, card1.getSuit(),
						card2.getFace(), card2.getSuit(), state);

				Assert.fail("Cannot be here");

			} catch (GamePlayerDataException e) {
			}

			try {

				GamePlayer gamePlayer = gamePlayerController.add(player1.getId(), card1.getFace(), null,
						card2.getFace(), card2.getSuit(), state);

				Assert.fail("Cannot be here");

			} catch (GamePlayerDataException e) {
			}

			try {

				GamePlayer gamePlayer = gamePlayerController.add(player1.getId(), card1.getFace(), card1.getSuit(),
						null, card2.getSuit(), state);

				Assert.fail("Cannot be here");

			} catch (GamePlayerDataException e) {
			}

			try {

				GamePlayer gamePlayer = gamePlayerController.add(player1.getId(), card1.getFace(), card1.getSuit(),
						card2.getFace(), null, state);

				Assert.fail("Cannot be here");

			} catch (GamePlayerDataException e) {
			}

			try {

				GamePlayer gamePlayer = gamePlayerController.add(player1.getId(), card1.getFace(), card1.getSuit(),
						card1.getFace(), card1.getSuit(), state);

				Assert.fail("Cannot be here");

			} catch (GamePlayerDataException e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void test_update() {

		Set<Card> availableCards = PokerUtils.getPack();

		Card card1 = PokerUtils.randomCard(availableCards);
		Card card2 = PokerUtils.randomCard(availableCards);

		GamePlayerState state1 = GamePlayerState.GAMING;

		String p1name = "carlos kami";
		String p1email = "carlos@thiagoh.com";

		try {

			Player player1 = playerController.add(p1name, p1email);

			players.add(player1);

			GamePlayer gamePlayer1 = gamePlayerController.add(player1.getId(), card1.getFace(), card1.getSuit(),
					card2.getFace(), card2.getSuit(), state1);

			GamePlayerState state2 = GamePlayerState.GAMING;

			Card card3 = PokerUtils.randomCard(availableCards);
			Card card4 = PokerUtils.randomCard(availableCards);

			GamePlayer gamePlayer2 = gamePlayerController.update(gamePlayer1.getId(), card3.getFace(), card3.getSuit(),
					card4.getFace(), card4.getSuit(), state2);

			Assert.assertEquals(gamePlayer2.getState(), state2);
			Assert.assertEquals(gamePlayer2.getPlayer(), player1);

			com.thiagoh.poker.model.Card modelCard1 = cardController.get(card3.getFace(), card3.getSuit());
			com.thiagoh.poker.model.Card modelCard2 = cardController.get(card4.getFace(), card4.getSuit());

			Assert.assertEquals(gamePlayer2.getCard1(), modelCard1);
			Assert.assertEquals(gamePlayer2.getCard2(), modelCard2);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
