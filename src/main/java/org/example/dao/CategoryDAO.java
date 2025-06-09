package org.example.dao;

import org.example.entity.Category;
import org.hibernate.Session;

public class CategoryDAO extends EntityDAO<Category> {
    public CategoryDAO () {
        super(Category.class);
    }

    public Category findByName(Session session, String categoryName) {
        String hql = "from Category where name = :categoryName";
        return session.createQuery(hql, Category.class)
                .setParameter("categoryName", categoryName)
                .uniqueResult();
    }
}
