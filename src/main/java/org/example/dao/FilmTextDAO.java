package org.example.dao;

import org.example.entity.FilmText;
import org.hibernate.Session;

public class FilmTextDAO extends EntityDAO<FilmText> {
    public FilmTextDAO() {
        super(FilmText.class);
    }
}
