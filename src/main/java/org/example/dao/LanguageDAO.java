package org.example.dao;

import org.example.entity.Language;
import org.hibernate.Session;

public class LanguageDAO extends EntityDAO<Language> {
    public LanguageDAO() {
        super(Language.class);
    }

    public Language findLanguageByName(Session session, String languageName) {
        String hql = "from Language where name=:languageName";
        return session.createQuery(hql, Language.class)
                .setParameter("languageName", languageName)
                .uniqueResult();
    }
}
