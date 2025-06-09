package org.example.dao;

import org.example.entity.Staff;
import org.hibernate.Session;

public class StaffDAO extends EntityDAO<Staff> {
    public StaffDAO() {
        super(Staff.class);
    }
}
