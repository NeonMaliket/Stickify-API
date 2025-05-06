package com.farumazula.stickifyapi.controller;

import com.farumazula.stickifyapi.dto.invoce.InvoiceLinkResponse;
import com.farumazula.stickifyapi.dto.invoce.SmallInvoiceRequest;
import com.farumazula.stickifyapi.exception.InvoiceException;
import com.farumazula.stickifyapi.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ma1iket
 **/

@Slf4j
@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {


    private final InvoiceService invoiceService;

    @PostMapping("/link")
    public ResponseEntity<InvoiceLinkResponse> invoiceLink(@Valid @RequestBody SmallInvoiceRequest request) {
        return ResponseEntity.ok(invoiceService.createInvoiceLink(request)
                .orElseThrow(() -> new InvoiceException("Invoice link creation failed")));
    }
}
