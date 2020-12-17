package com.cg.ovms.repository;

import javax.persistence.EntityManager;

import com.cg.ovms.entities.User;

public class IUserRepositoryImpl implements IUserRepository {

	private EntityManager entityManager;
	
	public IUserRepositoryImpl() {	   
		entityManager = JPAUtil.getEntityManager();
	}

	
	public User validateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		return user;
	}

	public User removeUser(User user) {
		// TODO Auto-generated method stub
		entityManager.remove(user);
		return user;
	}

	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
