package com.thiagoh.poker.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thiagoh.poker.util.PokerUtils;

public class Game {

	private Set<Player> _players;
	private Map<Player, GamePlayer> _gamePlayersMap;
	private Card[] _tableCards;
	private Set<Card> _availableCards;
	private Set<Card> _outCards;
	private GameState _state;
	private TableCardsState _tableCardsState;

	private Game() {

		_players = new HashSet<Player>();
		_gamePlayersMap = new HashMap<>();

		_tableCards = new Card[5];
		_availableCards = PokerUtils.getPack();
		_outCards = new HashSet<Card>();
		_state = GameState.PRE_GAME;
	}

	public static Game createNewGame() {

		return new Game();
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

		Card randomCard = PokerUtils.random(availableCards);

		if (!_availableCards.contains(randomCard)) {
			throw new RuntimeException("The card is not available");
		}

		availableCards.remove(randomCard);

		return randomCard;
	}

	public Card[] dropCards() {

		if (_state != GameState.STARTED) {
			throw new RuntimeException("This game is not started");
		}

		if (_tableCardsState == TableCardsState.PRE_FLOP) {

			_flop();

		} else if (_tableCardsState == TableCardsState.FLOP) {

			_turn();

		} else if (_tableCardsState == TableCardsState.TURN) {

			_river();
		}

		return _tableCards;
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
