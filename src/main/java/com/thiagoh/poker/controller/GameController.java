package com.thiagoh.poker.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/game" })
public class GameController {

	private final static org.slf4j.Logger log = LoggerFactory.getLogger(GameController.class);

	private final static String MAPPING_PREFIX = "game/";

	/*
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
	 */

	@RequestMapping(value = { "/{gameId}" })
	public String view(@PathVariable String gameId, Model model) {

		return _view(gameId, model);
	}

	private String _view(String gameId, Model model) {

		return MAPPING_PREFIX + "view";
	}

}
