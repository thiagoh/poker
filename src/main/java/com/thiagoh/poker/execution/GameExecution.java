package com.thiagoh.poker.execution;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.TableCardsState;
import com.thiagoh.poker.util.PokerUtils;

public class GameExecution {

	private Game _game;
	private Set<Player> _players;
	private Map<Player, GamePlayer> _gamePlayersMap;
	private Card[] _tableCards;
	private Set<Card> _availableCards;
	private Set<Card> _outCards;
	private GameState _state;
	private TableCardsState _tableCardsState;

	private GameExecution() {

		_players = new HashSet<>();
		_gamePlayersMap = new HashMap<>();

		_tableCards = new Card[5];
		_availableCards = PokerUtils.getPack();
		_outCards = new HashSet<>();
		_state = GameState.PRE_GAME;

		_game = new Game();
	}

	public static GameExecution createNewGame() {

		return new GameExecution();
	}

	public static GameExecution loadGame(Game game) {

		GameExecution gameExecutor = new GameExecution();

		gameExecutor._players = new HashSet<>();
		gameExecutor._gamePlayersMap = new HashMap<>();

		com.thiagoh.poker.model.Card tc1 = game.getTableCard1();
		com.thiagoh.poker.model.Card tc2 = game.getTableCard2();
		com.thiagoh.poker.model.Card tc3 = game.getTableCard3();
		com.thiagoh.poker.model.Card tc4 = game.getTableCard4();
		com.thiagoh.poker.model.Card tc5 = game.getTableCard5();

		Card tableCard1 = new Card(tc1.getFace(), tc1.getSuit());
		Card tableCard2 = new Card(tc2.getFace(), tc2.getSuit());
		Card tableCard3 = new Card(tc3.getFace(), tc3.getSuit());
		Card tableCard4 = new Card(tc4.getFace(), tc4.getSuit());
		Card tableCard5 = new Card(tc5.getFace(), tc5.getSuit());

		gameExecutor._tableCards = new Card[5];
		gameExecutor._tableCards[0] = tableCard1;
		gameExecutor._tableCards[1] = tableCard2;
		gameExecutor._tableCards[2] = tableCard3;
		gameExecutor._tableCards[3] = tableCard4;
		gameExecutor._tableCards[4] = tableCard5;

		gameExecutor._availableCards = PokerUtils.getPack();
		gameExecutor._outCards = new HashSet<>();

		Set<com.thiagoh.poker.model.GamePlayer> gamePlayers = game.getGamePlayers();

		for (com.thiagoh.poker.model.GamePlayer modelGamePlayer : gamePlayers) {

			com.thiagoh.poker.model.Player modelPlayer = modelGamePlayer.getPlayer();

			Player player = new Player(modelPlayer.getName(), modelPlayer.getEmail());
			com.thiagoh.poker.model.Card modelCard1 = modelGamePlayer.getCard1();
			com.thiagoh.poker.model.Card modelCard2 = modelGamePlayer.getCard2();

			Card card1 = new Card(modelCard1.getFace(), modelCard1.getSuit());
			Card card2 = new Card(modelCard2.getFace(), modelCard2.getSuit());

			Hand hand = new Hand(card1, card2);

			GamePlayer gamePlayer = new GamePlayer(gameExecutor, player, hand);

			gameExecutor._players.add(player);
			gameExecutor._gamePlayersMap.put(player, gamePlayer);

			gameExecutor._availableCards.remove(hand.getCard1());
			gameExecutor._availableCards.remove(hand.getCard2());
		}

		gameExecutor._state = game.getState();
		gameExecutor._tableCardsState = game.getTableCardsState();

		gameExecutor._game = game;

		return gameExecutor;
	}

	public void start() {

		if (_state == GameState.FINISHED) {
			throw new RuntimeException("This game is over");
		}

		if (_state == GameState.STARTED) {
			throw new RuntimeException("This game is already started");
		}

		if (_players.size() < 2) {
			throw new RuntimeException("Game cannot start with less than two players");
		}

		_state = GameState.STARTED;

		_distributeCards();
	}

	public void join(Player... players) {

		for (Player player : players) {
			_join(player);
		}
	}

	public void join(Collection<Player> players) {

		for (Player player : players) {
			_join(player);
		}
	}

	public void join(Player player) {

		_join(player);
	}

	private void _join(Player player) {

		if (_state != GameState.PRE_GAME) {
			throw new RuntimeException("Cannot add player to this game");
		}

		if (player == null) {
			throw new NullPointerException("Player cannot be null");
		}

		if (isInGame(player)) {
			return;
		}

		_players.add(player);
		_gamePlayersMap.put(player, null);
	}

	public boolean isInGame(Player player) {

		return _players.contains(player);
	}

	public boolean isPlaying(Player player) {

		if (!isInGame(player)) {
			return false;
		}

		GamePlayer gamePlayer = _gamePlayersMap.get(player);

		if (gamePlayer == null) {
			return false;
		}

		return gamePlayer.isPlaying();
	}

	public GamePlayer getGamePlayer(Player player) {

		if (!isInGame(player)) {
			return null;
		}

		GamePlayer gamePlayer = _gamePlayersMap.get(player);

		return gamePlayer;
	}

	public void distributeCards() {

		_distributeCards();
	}

	public void redistributeCards() {

		_distributeCards();
	}

	private synchronized void _distributeCards() {

		if (_state != GameState.STARTED) {
			throw new RuntimeException("This game is not started");
		}

		for (Player player : _players) {

			distributeCardsToPlayer(player);
		}

		_tableCardsState = TableCardsState.PRE_FLOP;
	}

	public void fold(Player player) {

		if (player == null) {
			throw new NullPointerException("Player cannot be null");
		}

		if (_state != GameState.STARTED) {
			throw new RuntimeException("This game is not started");
		}

		if (!isInGame(player)) {
			throw new RuntimeException("Player is not present on this game");
		}

		GamePlayer gamePlayer = _gamePlayersMap.get(player);

		if (gamePlayer == null) {
			throw new RuntimeException("Player is not present on this game");
		}

		if (!gamePlayer.isPlaying()) {
			throw new RuntimeException("Player is out of this game");
		}

		gamePlayer.fold();
	}

	private void distributeCardsToPlayer(Player player) {

		if (player == null) {
			throw new NullPointerException("Player cannot be null");
		}

		if (!isInGame(player)) {
			throw new RuntimeException("Player is not present on this game");
		}

		Card randomCard1 = _retrieveRandomCard();
		Card randomCard2 = _retrieveRandomCard();

		Hand hand = new Hand(randomCard1, randomCard2);

		GamePlayer gamePlayer = new GamePlayer(this, player, hand);

		_gamePlayersMap.put(player, gamePlayer);
	}

	private Card _retrieveRandomCard() {

		Set<Card> availableCards = getAvailableCards();

		Card randomCard = PokerUtils.randomCard(availableCards);

		return randomCard;
	}

	public Card[] dropCards() {

		if (_state != GameState.STARTED) {
			throw new RuntimeException("This game is not started");
		}

		if (_tableCardsState == TableCardsState.PRE_FLOP) {

			_flop();

			return getFlop();

		} else if (_tableCardsState == TableCardsState.FLOP) {

			_turn();
			return new Card[] { getTurn() };

		} else if (_tableCardsState == TableCardsState.TURN) {

			_river();
			return new Card[] { getRiver() };
		}

		throw new RuntimeException("This step is not valid");
	}

	public TableCardsState getTableCardsState() {

		if (_state != GameState.STARTED) {
			throw new RuntimeException("This game is not started");
		}

		return _tableCardsState;
	}

	public Card[] getTableCards() {

		return _tableCards;
	}

	public Card[] getFlop() {

		return new Card[] { _tableCards[0], _tableCards[1], _tableCards[2] };
	}

	private void _flop() {

		_tableCards[0] = _retrieveRandomCard();
		_tableCards[1] = _retrieveRandomCard();
		_tableCards[2] = _retrieveRandomCard();

		_tableCardsState = TableCardsState.FLOP;
	}

	public Card getTurn() {

		return _tableCards[3];
	}

	private void _turn() {

		_tableCards[3] = _retrieveRandomCard();

		_tableCardsState = TableCardsState.TURN;
	}

	public Card getRiver() {

		return _tableCards[4];
	}

	private void _river() {

		_tableCards[4] = _retrieveRandomCard();

		_tableCardsState = TableCardsState.RIVER;
	}

	private Set<Card> getAvailableCards() {

		return _availableCards;
	}

	public Set<Player> getPlayers() {

		return _players;
	}

	public int countPlayers() {

		if (_players == null)
			return 0;

		return _players.size();
	}
}
