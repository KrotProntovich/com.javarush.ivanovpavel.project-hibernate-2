package org.example.dao;

import org.example.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends EntityDAO<Category> {
    public CategoryDAO (SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    public Category findByName(String categoryName) {
        String hql = "from Category where name = :categoryName";
        return getCurrentSession().createQuery(hql, Category.class)
                .setParameter("categoryName", categoryName)
                .uniqueResult();
    }
}
