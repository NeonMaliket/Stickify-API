package com.farumazula.stickifyapi.service;

import com.farumazula.stickifyapi.dto.invoce.InvoiceLinkResponse;
import com.farumazula.stickifyapi.dto.invoce.SmallInvoiceRequest;

import java.util.Optional;

/**
 * @author Ma1iket
 **/

public interface InvoiceService {

    Optional<InvoiceLinkResponse> createInvoiceLink(SmallInvoiceRequest invoiceRequest);

}
