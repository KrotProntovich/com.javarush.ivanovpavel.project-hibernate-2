package org.example.dao;

import org.example.entity.City;
import org.hibernate.SessionFactory;

public class CityDAO extends EntityDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City findByNameAndCountryID(String name, Short id) {
        String hql = "from City c where c.city like :name and c.country.id = :id";
        return getCurrentSession()
                .createQuery(hql, City.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .uniqueResult();
    }
}
