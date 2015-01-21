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
import com.thiagoh.poker.model.GamePlayerForm;
import com.thiagoh.poker.model.GamePlayerState;
import com.thiagoh.poker.model.Player;
import com.thiagoh.poker.model.TableCardsState;
import com.thiagoh.poker.service.CardService;
import com.thiagoh.poker.service.GameService;
import com.thiagoh.poker.service.PlayerService;
import com.thiagoh.poker.util.PokerUtils;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "/spring-defs.xml" and
// "/spring-defs-test.xml"
// in the root of the classpath
@ContextConfiguration(locations = { "/spring-defs.xml" })
public class GameServiceTest {

	@Autowired
	protected PlayerService playerController;

	@Autowired
	protected CardService cardController;

	@Autowired
	protected GameService gameController;

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

			List<Game> list = gameController.findAll();

			for (Game game : list) {
				gameController.delete(game);
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
	public void testGame1() {

		Set<Card> availableCards = PokerUtils.getPack();

		Card card1 = PokerUtils.randomCard(availableCards);
		Card card2 = PokerUtils.randomCard(availableCards);
		Card card3 = PokerUtils.randomCard(availableCards);
		Card card4 = PokerUtils.randomCard(availableCards);
		Card card5 = PokerUtils.randomCard(availableCards);

		GameState state = GameState.STARTED;
		TableCardsState tableCardsState = TableCardsState.RIVER;
		List<GamePlayerForm> gamePlayerForms = new ArrayList<GamePlayerForm>();

		try {

			Hand randomHand1 = PokerUtils.randomHand(availableCards);

			GamePlayerForm gpf1 = new GamePlayerForm();

			gpf1.setFace1(randomHand1.getCard1().getFace());
			gpf1.setSuit1(randomHand1.getCard1().getSuit());
			gpf1.setFace2(randomHand1.getCard2().getFace());
			gpf1.setSuit2(randomHand1.getCard2().getSuit());

			String p1name = "carlos kami";
			String p1email = "carlos@thiagoh.com";

			Player player1 = playerController.add(p1name, p1email);

			players.add(player1);

			gpf1.setPlayerId(player1.getId());
			gpf1.setState(GamePlayerState.GAMING);

			Hand randomHand2 = PokerUtils.randomHand(availableCards);

			GamePlayerForm gpf2 = new GamePlayerForm();

			gpf2.setFace1(randomHand2.getCard1().getFace());
			gpf2.setSuit1(randomHand2.getCard1().getSuit());
			gpf2.setFace2(randomHand2.getCard2().getFace());
			gpf2.setSuit2(randomHand2.getCard2().getSuit());

			String p2name = "joao grandao";
			String p2email = "ygor@thiagoh.com";

			Player player2 = playerController.add(p2name, p2email);

			players.add(player2);

			gpf2.setPlayerId(player2.getId());
			gpf2.setState(GamePlayerState.GAMING);

			gamePlayerForms.add(gpf1);
			gamePlayerForms.add(gpf2);

			Game game = gameController.add(card1.getFace(), card1.getSuit(), card2.getFace(), card2.getSuit(),
					card3.getFace(), card3.getSuit(), card4.getFace(), card4.getSuit(), card5.getFace(),
					card5.getSuit(), state, tableCardsState, gamePlayerForms);

			Assert.assertEquals(game.getState(), state);
			Assert.assertEquals(game.getTableCardsState(), tableCardsState);

			com.thiagoh.poker.model.Card modelCard1 = cardController.get(card1.getFace(), card1.getSuit());
			com.thiagoh.poker.model.Card modelCard2 = cardController.get(card2.getFace(), card2.getSuit());
			com.thiagoh.poker.model.Card modelCard3 = cardController.get(card3.getFace(), card3.getSuit());
			com.thiagoh.poker.model.Card modelCard4 = cardController.get(card4.getFace(), card4.getSuit());
			com.thiagoh.poker.model.Card modelCard5 = cardController.get(card5.getFace(), card5.getSuit());

			Assert.assertEquals(game.getTableCard1(), modelCard1);
			Assert.assertEquals(game.getTableCard2(), modelCard2);
			Assert.assertEquals(game.getTableCard3(), modelCard3);
			Assert.assertEquals(game.getTableCard4(), modelCard4);
			Assert.assertEquals(game.getTableCard5(), modelCard5);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
}
