package org.example.dao;

import org.example.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends EntityDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

    public Address findByNameAndPhone(String name, String phone) {
        String hql = "from Address a where a.address like :name and a.phone = :phone";
        return getCurrentSession()
                .createQuery(hql, Address.class)
                .setParameter("name", name)
                .setParameter("phone", phone)
                .uniqueResult();
    }
}
