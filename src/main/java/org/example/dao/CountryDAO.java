package org.example.dao;

import org.example.entity.Country;
import org.hibernate.Session;

public class CountryDAO extends EntityDAO<Country> {
    public CountryDAO() {
        super(Country.class);
    }

    public Country findByName(Session session, String name) {
        String hql = "from Country where name = :name";
        return session
                .createQuery(hql, Country.class)
                .setParameter("name", name)
                .uniqueResult();
    }
}
