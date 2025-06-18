package org.example.dao;

import org.example.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends EntityDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
