package org.example.dao;

import org.example.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends EntityDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    public Country findByName(String name) {
        String hql = "from Country where name = :name";
        return getCurrentSession()
                .createQuery(hql, Country.class)
                .setParameter("name", name)
                .uniqueResult();
    }
}
