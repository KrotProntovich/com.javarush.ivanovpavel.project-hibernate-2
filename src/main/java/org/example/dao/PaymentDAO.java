package org.example.dao;

import org.example.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends EntityDAO<Payment> {
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
