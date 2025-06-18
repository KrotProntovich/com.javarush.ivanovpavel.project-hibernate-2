package org.example.dao;

import org.example.entity.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends EntityDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class,  sessionFactory);
    }

    public Actor findByName(String firstName, String lastName){
        String hql = "from Actor a where a.firstName like :firstName and a.lastName like :lastName";
        return getCurrentSession().createQuery(hql,  Actor.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .uniqueResult();
    }
}
