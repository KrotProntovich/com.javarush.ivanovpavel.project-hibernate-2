package org.example.dao;

import org.example.entity.Inventory;
import org.example.entity.Rental;
import org.hibernate.Session;

import java.util.List;

public class InventoryDAO extends EntityDAO<Inventory> {
    public InventoryDAO() {
        super(Inventory.class);
    }

    public List<Integer> findInventoryIdByFilmID(Session session, Short filmId) {
        String hqlInventory = "select i.id from Inventory i where i.film.id = :filmId";
        return session
                .createQuery(hqlInventory, Integer.class)
                .setParameter("filmId", filmId)
                .list();
    }

    public List<Integer> findInventoryIDByFilmIDAndStoreID(Session session, Short filmId, Integer storeId) {
        String hqlInventory = "select i.id from Inventory i where i.film.id = :filmId and i.store.id = :storeId";
        return session
                .createQuery(hqlInventory, Integer.class)
                .setParameter("filmId", filmId)
                .setParameter("storeId", storeId)
                .list();
    }
}
