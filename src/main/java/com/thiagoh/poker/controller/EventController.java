package com.thiagoh.poker.controller;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.Event;

@Service
public class EventController extends BaseController {

	public Event add(String name, String description, Date date, double buyInPrice, int buyInChipCount)
			throws SystemException, PortalException {

		return save(true, 0L, name, description, date, buyInPrice, buyInChipCount);
	}

	public Event update(long eventId, String name, String description, Date date, double buyInPrice, int buyInChipCount)
			throws SystemException, PortalException {

		return save(false, eventId, name, description, date, buyInPrice, buyInChipCount);
	}

	private Event save(boolean isNew, long eventId, String name, String description, Date date, double buyInPrice,
			int buyInChipCount) throws SystemException, PortalException {

		Event event = null;

		if (isNew) {

			event = eventDao.create();

		} else {

			event = eventDao.get(eventId);
		}

		event.setName(name);
		event.setDescription(description);
		event.setDate(date);
		event.setBuyInPrice(buyInPrice);
		event.setBuyInChipCount(buyInChipCount);

		eventDao.save(event);

		return event;
	}

	public Event get(String name) throws SystemException, PortalException {

		return eventDao.get(name);
	}

	public Event fetch(String name) throws SystemException {

		return eventDao.fetch(name);
	}
}
