package foi.air.szokpt.transfetch.controllers;

import foi.air.szokpt.transfetch.dtos.responses.ApiResponse;
import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.entities.Transaction;
import foi.air.szokpt.transfetch.services.MerchantService;
import foi.air.szokpt.transfetch.services.TransactionService;
import foi.air.szokpt.transfetch.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RawDataController {
    private final MerchantService merchantService;
    private final TransactionService transactionService;

    @Autowired
    public RawDataController(MerchantService merchantService, TransactionService transactionService) {
        this.merchantService = merchantService;
        this.transactionService = transactionService;
    }

    @GetMapping("/raw-data")
    public ResponseEntity<ApiResponse<Merchant>> getFilteredRawData() {
        List<Merchant> merchants = merchantService.getMerchants();

        merchants.forEach(merchant -> merchant.getMids().forEach(mid ->
                mid.getTids().forEach(tid -> {
                    List<Transaction> filteredTransactions = transactionService.getTransactionsForYesterday(tid);
                    tid.setTransactions(filteredTransactions);
                })
        ));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("Filtered transactions successfully fetched", merchants));
    }
}
