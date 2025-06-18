package org.example.dao;

import org.example.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends EntityDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }

    public Language findLanguageByName(String languageName) {
        String hql = "from Language where name=:languageName";
        return getCurrentSession().createQuery(hql, Language.class)
                .setParameter("languageName", languageName)
                .uniqueResult();
    }
}
