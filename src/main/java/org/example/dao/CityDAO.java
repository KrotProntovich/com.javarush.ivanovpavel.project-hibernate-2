package org.example.dao;

import org.example.entity.City;
import org.hibernate.Session;

public class CityDAO extends EntityDAO<City> {
    public CityDAO() {
        super(City.class);
    }

    public City findByNameAndCountryID(Session session, String name, Short id) {
        String hql = "from City c where c.city like :name and c.country.id = :id";
        return session
                .createQuery(hql, City.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .uniqueResult();
    }
}
