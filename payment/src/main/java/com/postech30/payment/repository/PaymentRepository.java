package com.postech30.payment.repository;

import com.postech30.payment.entity.Card;
import com.postech30.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
