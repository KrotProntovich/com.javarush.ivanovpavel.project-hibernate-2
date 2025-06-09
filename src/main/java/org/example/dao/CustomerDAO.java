package org.example.dao;

import org.example.entity.Customer;
import org.hibernate.Session;

public class CustomerDAO extends EntityDAO<Customer> {
    public CustomerDAO() {
        super(Customer.class);
    }

    public Customer findByFirstLastNameAndEmailAndStore(Session session, String firstName, String lastName, String email, Integer storeId) {
        String hql = "from Customer c where c.firstName like :firstName and c.lastName like :lastName and c.email like :email and c.store.id = :storeId";
        return session
                .createQuery(hql, Customer.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("email", email)
                .setParameter("storeId", storeId)
                .uniqueResult();
    }
}
