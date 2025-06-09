package org.example.dao;

import org.example.entity.Payment;
import org.hibernate.Session;

public class PaymentDAO extends EntityDAO<Payment> {
    public PaymentDAO() {
        super(Payment.class);
    }
}
