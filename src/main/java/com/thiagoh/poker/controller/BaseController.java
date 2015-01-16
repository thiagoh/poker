package com.thiagoh.poker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.dao.CardDao;
import com.thiagoh.poker.dao.EventDao;
import com.thiagoh.poker.dao.GameDao;
import com.thiagoh.poker.dao.GamePlayerDao;
import com.thiagoh.poker.dao.PlayerDao;

@Service
@Transactional(rollbackFor = { PortalException.class, SystemException.class }, propagation = Propagation.SUPPORTS)
public abstract class BaseController {
	
	@Autowired
	protected CardDao cardDao;
	
	@Autowired
	protected EventDao eventDao;
	
	@Autowired
	protected GameDao gameDao;
	
	@Autowired
	protected PlayerDao playerDao;
	
	@Autowired
	protected GamePlayerDao gamePlayerDao;
}
