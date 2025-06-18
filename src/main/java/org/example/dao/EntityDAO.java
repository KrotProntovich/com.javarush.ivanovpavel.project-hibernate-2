package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class EntityDAO<T> {
    private final Class<T> entityClass;
    private final SessionFactory sessionFactory;


    public EntityDAO(final Class<T> entityClass, final SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // получить объект по его ID
    public T getById(final Long id) {
            return getCurrentSession().find(entityClass, id);
    }

    // получить список объектов по заданному диапазону
    public List<T> getItems(int from, int count){
        return getCurrentSession()
                .createQuery("from " + entityClass.getName(), entityClass)
                .setFirstResult(from).
                setMaxResults(count)
                .list();
    }

    // получить все объекты данного типа
    public List<T> getAll(){
        return getCurrentSession()
                .createQuery("from " + entityClass.getName(), entityClass)
                .list();
    }

    // узнать количество объектов
    public int getCount(){
        return Math.toIntExact(getCurrentSession()
                .createQuery("select count(*) from " + entityClass.getName(), Long.class)
                .uniqueResult());
    }

    // сохранить объект в БД
    public T persist(final T entity) {
            getCurrentSession().persist(entity);
            return entity;
    }

    // обновить объект в БД
    public T merge(final T entity) {
        return getCurrentSession().merge(entity);
    }

    // удалить объект из БД
    public void delete(final T entity) {
        getCurrentSession().remove(entity);
    }

    // удалить объект из БД по ID
    public void deleteById(final Long id) {
        final T entityDB = getById(id);
        delete(entityDB);
    }
}
