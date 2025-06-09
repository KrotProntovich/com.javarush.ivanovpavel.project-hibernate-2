package org.example.dao;

import org.hibernate.Session;
import java.util.List;

public abstract class EntityDAO<T> {
    private final Class<T> entityClass;


    public EntityDAO(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // получить объект по его ID
    public T getById(Session session, final Long id) {
            return session.find(entityClass, id);
    }

    // получить список объектов по заданному диапазону
    public List<T> getItems(Session session, int from, int count){
        return session
                .createQuery("from " + entityClass.getName(), entityClass)
                .setFirstResult(from).
                setMaxResults(count)
                .list();
    }

    // получить все объекты данного типа
    public List<T> getAll(Session session){
        return session
                .createQuery("from " + entityClass.getName(), entityClass)
                .list();
    }

    // узнать количество объектов
    public int getCount(Session session){
        return Math.toIntExact(session
                .createQuery("select count(*) from " + entityClass.getName(), Long.class)
                .uniqueResult());
    }

    // сохранить объект в БД
    public T persist(Session session, final T entity) {
            session.persist(entity);
            return entity;
    }

    // обновить объект в БД
    public T merge(Session session, final T entity) {
        return session.merge(entity);
    }

    // удалить объект из БД
    public void delete(Session session, final T entity) {
        session.remove(entity);
    }

    // удалить объект из БД по ID
    public void deleteById(Session session, final Long id) {
        final T entityDB = session.find(entityClass, id);
        delete(session, entityDB);
    }

}
