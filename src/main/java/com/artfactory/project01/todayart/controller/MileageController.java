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

    @GetMapping(value = "/statement", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MileageStatement> retrieveMileageStatement() {
        return mileageService.retrieveMileageStatement();
    }

    @PostMapping(value = "/statement", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageStatement createMileageStatement (@RequestBody MileageStatement mileageStatement) {
        return mileageService.createMileageStatement(mileageStatement);
    }

    @DeleteMapping(value = "/statement/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageStatement deleteMileageStatement(@PathVariable("id") int id, @RequestParam int isDelete) {
        return mileageService.deleteMileageStatement(id, isDelete);
    }

    @GetMapping(value = "/invoice", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MileageInvoice> retrieveMileageInvoice() {
        return mileageService.retrieveMileageInvoice();
    }

    @PostMapping(value = "/invoice", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageInvoice createMileageInvoice (@RequestBody MileageInvoice mileageInvoice) {
        return mileageService.createMileageInvoice(mileageInvoice);
    }

    @DeleteMapping(value = "/invoice/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MileageInvoice deleteMileageInvoice(@PathVariable("id") int id, @RequestParam int isDelete) {
        return mileageService.deleteMileageInvoice(id, isDelete);
    }
}