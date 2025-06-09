package org.example.dao;

import org.example.entity.Store;
import org.hibernate.Session;

import java.util.List;

public class StoreDAO extends EntityDAO<Store> {
    public StoreDAO() {
        super(Store.class);
    }
}
