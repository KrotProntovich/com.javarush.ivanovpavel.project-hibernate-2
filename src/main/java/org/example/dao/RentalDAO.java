package org.example.dao;

import org.example.entity.Rental;
import org.hibernate.Session;

import java.util.List;

public class RentalDAO extends EntityDAO<Rental> {
    public RentalDAO() {
        super(Rental.class);
    }

    public Rental findByInventoryIDAndCustomerID(Session session, List<Integer> inventoryId, Short customerID) {
        String hql = "from Rental r where r.inventory.id in(:inventoryId) and r.customer.id = :customerId and r.returnDate IS NULL";
        return session
                .createQuery(hql, Rental.class)
                .setParameter("inventoryId", inventoryId)
                .setParameter("customerId", customerID)
                .uniqueResult();
    }
}
