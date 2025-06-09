package org.example.dao;

import org.example.entity.Film;
import org.hibernate.Session;

public class FilmDAO extends EntityDAO<Film> {
    public FilmDAO() {
        super(Film.class);
    }

    public Film findByName(Session session, String filmName) {
        String hql = "from Film where title like :filmName";
        return session
                .createQuery(hql, Film.class)
                .setParameter("filmName", filmName)
                .uniqueResult();
    }
}
