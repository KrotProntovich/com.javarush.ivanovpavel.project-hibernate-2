package org.example.dao;

import org.example.entity.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends EntityDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film findByName(String filmName) {
        String hql = "from Film where title like :filmName";
        return getCurrentSession()
                .createQuery(hql, Film.class)
                .setParameter("filmName", filmName)
                .uniqueResult();
    }
}
