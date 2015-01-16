package com.thiagoh.poker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.dao.util.CardDao;
import com.thiagoh.poker.dao.util.GameDao;
import com.thiagoh.poker.dao.util.GamePlayerDao;
import com.thiagoh.poker.dao.util.PlayerDao;

@Transactional(rollbackFor = { PortalException.class, SystemException.class }, propagation = Propagation.SUPPORTS)
public abstract class BaseController {

	@Autowired
	protected GameDao gameDao;
	
	@Autowired
	protected CardDao cardDao;
	
	@Autowired
	protected PlayerDao playerDao;
	
	@Autowired
	protected GamePlayerDao gamePlayerDao;
	
	@Autowired
	protected GamePlayerController gamePlayerController;
	
	@Autowired
	protected GameController gameController;
}
