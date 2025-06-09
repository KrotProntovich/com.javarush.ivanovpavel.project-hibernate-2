package org.example.dao;

import org.example.entity.Actor;
import org.hibernate.Session;

public class ActorDAO extends EntityDAO<Actor> {
    public ActorDAO() {
        super(Actor.class);
    }

    public Actor findByName(Session session, String firstName, String lastName){
        String hql = "from Actor a where a.firstName like :firstName and a.lastName like :lastName";
        return session.createQuery(hql,  Actor.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .uniqueResult();
    }
}
