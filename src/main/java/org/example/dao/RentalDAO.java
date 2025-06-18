package org.example.dao;

import org.example.entity.Rental;
import org.hibernate.SessionFactory;

import java.util.List;

public class RentalDAO extends EntityDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class,  sessionFactory);
    }

    public Rental findByInventoryIDAndCustomerID(List<Integer> inventoryId, Short customerID) {
        String hql = "from Rental r where r.inventory.id in(:inventoryId) and r.customer.id = :customerId and r.returnDate IS NULL";
        return getCurrentSession()
                .createQuery(hql, Rental.class)
                .setParameter("inventoryId", inventoryId)
                .setParameter("customerId", customerID)
                .uniqueResult();
    }
}
