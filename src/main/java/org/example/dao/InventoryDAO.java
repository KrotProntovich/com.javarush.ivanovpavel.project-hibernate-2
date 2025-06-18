package org.example.dao;

import org.example.entity.Inventory;
import org.hibernate.SessionFactory;

import java.util.List;

public class InventoryDAO extends EntityDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public List<Integer> findInventoryIdByFilmID(Short filmId) {
        String hqlInventory = "select i.id from Inventory i where i.film.id = :filmId";
        return getCurrentSession()
                .createQuery(hqlInventory, Integer.class)
                .setParameter("filmId", filmId)
                .list();
    }

    public List<Integer> findInventoryIDByFilmIDAndStoreID(Short filmId, Integer storeId) {
        String hqlInventory = "select i.id from Inventory i where i.film.id = :filmId and i.store.id = :storeId";
        return getCurrentSession()
                .createQuery(hqlInventory, Integer.class)
                .setParameter("filmId", filmId)
                .setParameter("storeId", storeId)
                .list();
    }
}
