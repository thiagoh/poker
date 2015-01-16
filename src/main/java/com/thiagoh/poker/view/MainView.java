package com.thiagoh.poker.view;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/main" })
public class MainView {

	private final static org.slf4j.Logger log = LoggerFactory.getLogger(MainView.class);

	private final static String MAPPING_PREFIX = "main/";

	/*
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
	 */

	@RequestMapping(value = { "/list" })
	public String list(Model model) {

		return _list(model);
	}

	@RequestMapping(value = { "/new" })
	public String add(Model model) {

		return _add(model);
	}

	@RequestMapping(value = { "/edit" })
	public String edit(Model model) {

		return _edit(model);
	}

	private String _add(Model model) {

		return MAPPING_PREFIX + "edit";
	}

	private String _edit(Model model) {

		return MAPPING_PREFIX + "edit";
	}

	private String _list(Model model) {

		return MAPPING_PREFIX + "view";
	}

}
