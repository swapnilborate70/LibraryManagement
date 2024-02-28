package com.example.LibraryFinal.repository;

import com.example.LibraryFinal.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
