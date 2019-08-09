package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.MileageInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageInvoiceRepository extends JpaRepository<MileageInvoice, Integer> {

}
