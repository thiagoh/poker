package com.thiagoh.poker.view;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.controller.GameController;
import com.thiagoh.poker.execution.GameState;
import com.thiagoh.poker.model.Face;
import com.thiagoh.poker.model.Game;
import com.thiagoh.poker.model.GamePlayerForm;
import com.thiagoh.poker.model.Suit;
import com.thiagoh.poker.model.TableCardsState;

@Controller
@RequestMapping(value = { "/game" })
public class GameView extends BaseView {

	private final static org.slf4j.Logger log = LoggerFactory.getLogger(GameView.class);

	@Autowired
	protected GameController gameController;

	private final static String MAPPING_PREFIX = "game/";

	/*
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/
	 * mvc.html
	 */

	@RequestMapping(value = { "/{gameId}" })
	public String view(@PathVariable long gameId, Model model) {

		try {

			return _view(gameId, model);

		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}

		return _error(model);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam("face1") String face1Param, @RequestParam("suit1") String suit1Param,
			@RequestParam("face2") String face2Param, @RequestParam("suit2") String suit2Param,
			@RequestParam("face3") String face3Param, @RequestParam("suit3") String suit3Param,
			@RequestParam("face4") String face4Param, @RequestParam("suit4") String suit4Param,
			@RequestParam("face5") String face5Param, @RequestParam("suit5") String suit5Param,
			@RequestParam("state") String stateParam, @RequestParam("tableCardsState") String tableCardsStateParam,
			Model model) {

		try {

			GameState gameState = GameState.valueOf(stateParam);

			TableCardsState tableCardsState = TableCardsState.valueOf(tableCardsStateParam);

			List<GamePlayerForm> gamePlayerForms = new ArrayList<GamePlayerForm>();

			Face face1 = Face.valueOf(face1Param);
			Suit suit1 = Suit.valueOf(suit1Param);
			Face face2 = Face.valueOf(face2Param);
			Suit suit2 = Suit.valueOf(suit2Param);
			Face face3 = Face.valueOf(face3Param);
			Suit suit3 = Suit.valueOf(suit3Param);
			Face face4 = Face.valueOf(face4Param);
			Suit suit4 = Suit.valueOf(suit4Param);
			Face face5 = Face.valueOf(face5Param);
			Suit suit5 = Suit.valueOf(suit5Param);

			Game game = gameController.add(face1, suit1, face2, suit2, face3, suit3, face4, suit4, face5, suit5,
					gameState, tableCardsState, gamePlayerForms);

			return _view(game.getId(), model);

		} catch (PortalException e) {
			log.error(e.getMessage(), e);
		} catch (SystemException e) {
			log.error(e.getMessage(), e);
		}

		return _error(model);
	}

	private String _error(Model model) {

		return MAPPING_PREFIX + "error";
	}

	private String _view(long gameId, Model model) throws SystemException {

		try {

			Game game = gameController.get(gameId);

			model.addAttribute("game", game);

		} catch (PortalException e) {
			log.error(e.getMessage(), e);
		}

		return MAPPING_PREFIX + "view";
	}

}
