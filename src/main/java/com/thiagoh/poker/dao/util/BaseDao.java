package com.thiagoh.poker.dao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thiagoh.poker.PortalException;
import com.thiagoh.poker.SystemException;
import com.thiagoh.poker.model.BaseModel;

public abstract class BaseDao<T extends BaseModel> {

	@Autowired
	private SessionFactory sessionFactory;

	public abstract Class<T> getClazz();

	public T create() {

		Class<T> clazz = getClazz();

		T model = null;

		try {

			model = (T) clazz.newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		model.setNew(true);

		return model;
	}

	@SuppressWarnings("unchecked")
	public T get(long id) throws PortalException, SystemException {

		T model = fetch(id);

		if (model == null) {

			PortalException pe = null;
			
			try {

				String simpleName = getClazz().getSimpleName();

				Class<? extends PortalException> clazz = (Class<? extends PortalException>) Class.forName("NoSuch"
						+ simpleName + "Exception");

				String message = "No such " + simpleName + " with id " + id;

				pe = clazz.getConstructor(String.class).newInstance(message);


			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			throw pe;
		}

		return model;
	}

	public T fetch(long id) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			@SuppressWarnings("unchecked")
			T model = (T) session.get(getClazz(), id);

			return model;

		} catch (Exception e) {

			throw new SystemException(e);

		} finally {
			closeSession();
		}

	}

	public Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	public void closeSession() {
	}

	public T save(T model) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			if (model.isNew()) {

				model.setNew(false);

				session.save(model);

			} else {

				if (!session.contains(model)) {
					session.save(model);
				}

				session.merge(model);
			}

			return model;
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

			closeSession();
		}
	}

	public void delete(T model) throws SystemException {

		Session session = null;

		try {

			session = openSession();

			session.delete(model);
			session.flush();

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

			closeSession();
		}
	}
}
