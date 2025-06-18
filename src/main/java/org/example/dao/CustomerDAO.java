package org.example.dao;

import org.example.entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends EntityDAO<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }

    public Customer findByFirstLastNameAndEmailAndStore(String firstName, String lastName, String email, Integer storeId) {
        String hql = "from Customer c where c.firstName like :firstName and c.lastName like :lastName and c.email like :email and c.store.id = :storeId";
        return getCurrentSession()
                .createQuery(hql, Customer.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("email", email)
                .setParameter("storeId", storeId)
                .uniqueResult();
    }
}
