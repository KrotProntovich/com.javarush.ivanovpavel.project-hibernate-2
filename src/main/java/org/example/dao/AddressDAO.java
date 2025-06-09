package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Address;

public class AddressDAO extends EntityDAO<Address> {
    public AddressDAO() {
        super(Address.class);
    }

    public Address findByNameAndPhone(Session session, String name, String phone) {
        String hql = "from Address a where a.address like :name and a.phone = :phone";
        return session
                .createQuery(hql, Address.class)
                .setParameter("name", name)
                .setParameter("phone", phone)
                .uniqueResult();
    }
}
