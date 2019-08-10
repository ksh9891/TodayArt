package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.MileageInvoice;
import com.artfactory.project01.todayart.entity.MileageStatement;
import com.artfactory.project01.todayart.repository.MileageInvoiceRepository;
import com.artfactory.project01.todayart.repository.MileageStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MileageService {

    @Autowired
    private MileageStatementRepository mileageStatementRepository;

    @Autowired
    private MileageInvoiceRepository mileageInvoiceRepository;

    @Transactional(readOnly = true)
    public List<MileageStatement> retrieveMileageStatement() {
        return mileageStatementRepository.findAll();
    }

    @Transactional
    public MileageStatement createMileageStatement(MileageStatement mileageStatement) {
        return mileageStatementRepository.save(mileageStatement);
    }

    @Transactional
    public MileageStatement deleteMileageStatement(int id, int isDelete) {
        MileageStatement mileageStatement = mileageStatementRepository.findById(id).get();
        mileageStatement.setIsDelete(isDelete);
        return mileageStatementRepository.save(mileageStatement);
    }

    @Transactional(readOnly = true)
    public List<MileageInvoice> retrieveMileageInvoice() {
        return mileageInvoiceRepository.findAll();
    }

    @Transactional
    public MileageInvoice createMileageInvoice(MileageInvoice mileageInvoice) {
        return mileageInvoiceRepository.save(mileageInvoice);
    }

    @Transactional
    public MileageInvoice deleteMileageInvoice(int id, int isDelete) {
        MileageInvoice mileageInvoice = mileageInvoiceRepository.findById(id).get();
        mileageInvoice.setIsDelete(isDelete);
        return mileageInvoiceRepository.save(mileageInvoice);
    }
}
