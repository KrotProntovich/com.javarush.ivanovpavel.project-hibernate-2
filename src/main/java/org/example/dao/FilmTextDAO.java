package org.example.dao;

import org.example.entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends EntityDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
