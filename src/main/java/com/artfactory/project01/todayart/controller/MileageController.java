package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.MileageInvoice;
import com.artfactory.project01.todayart.entity.MileageStatement;
import com.artfactory.project01.todayart.service.MileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 판매대금 현황/명세
 */
@RestController
@RequestMapping("/artists/mileage")
public class MileageController {
    @Autowired
    private MileageService mileageService;

    @GetMapping(path = "/statement", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MileageStatement> findAllMileageStatement() {
        return mileageService.findAllMileageStatement();
    }

    @PostMapping(path = "/statement", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageStatement createMileageStatement (@RequestBody MileageStatement mileageStatement) {
        return mileageService.createMileageStatement(mileageStatement);
    }

    @DeleteMapping(path = "/statement/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageStatement deleteMileageStatement(@PathVariable("id") int id, @RequestParam int isDelete) {
        return mileageService.deleteMileageStatement(id, isDelete);
    }

    @GetMapping(path = "/invoice", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MileageInvoice> findAllMileageInvoice() {
        return mileageService.findAllMileageInvoice();
    }

    @PostMapping(path = "/invoice", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageInvoice createMileageInvoice (@RequestBody MileageInvoice mileageInvoice) {
        return mileageService.createMileageInvoice(mileageInvoice);
    }

    @DeleteMapping(path = "/invoice/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageInvoice deleteMileageInvoice(@PathVariable("id") int id, @RequestParam int isDelete) {
        return mileageService.deleteMileageInvoice(id, isDelete);
    }
}