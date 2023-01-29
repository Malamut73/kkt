package com.tehnology.kkt.controllers;

import com.tehnology.kkt.models.Account;
import com.tehnology.kkt.models.Operation;
import com.tehnology.kkt.services.AccountService;
import com.tehnology.kkt.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final OperationService operationService;

    @GetMapping("/accounts")
    public String accounts(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "accounts";
    }

    @GetMapping("/accounts/create")
    public String createAccount(){
        return "create-account";
    }

    @PostMapping("/accounts/create")
    public String createAccount(@RequestParam("name") String name){
        Account account = Account.builder()
                .name(name)
                .amount(new BigDecimal(0))
                .build();
        accountService.save(account);

        return "redirect:/accounts";
    }

    @GetMapping("/accounts/{accountid}")
    public String accountInfo(@PathVariable("accountid") Account account, Model model){
        model.addAttribute("account", account);
        System.out.println(new Date(new java.util.Date().getTime()));
        for (Account acount :
                accountService.findByDate(new Date(new java.util.Date().getTime()))) {
            for (Operation operation :
                    account.getFeatureOutgoes()) {
                System.out.println(operation.getDateTransaction());

            }
        }
        return "info-account";
    }

    @PostMapping("/accounts/{accountid}/addincome")
    public String accountAddIncome(@RequestParam("amount") BigDecimal amount,
                                   @RequestParam("aimTransaction") String aimTransaction,
                                   @PathVariable("accountid") Account account){
        Operation operation = Operation.builder()
                .amount(amount)
                .aimTransaction(aimTransaction)
                .dateTransaction(new Date(new java.util.Date().getTime()))
                .build();
        account.getIncomes().add(operation);
        account.addIncome(amount);
        accountService.save(account);
        return "redirect:/accounts/{accountid}";

    }

    @PostMapping("/accounts/{accountid}/addoutgo")
    public String accountAddOutgo(@RequestParam("amount") BigDecimal amount,
                                  @RequestParam("aimTransaction") String aimTransaction,
                                  @PathVariable("accountid") Account account){
        Operation operation = Operation.builder()
                .amount(amount)
                .aimTransaction(aimTransaction)
                .dateTransaction(new Date(new java.util.Date().getTime()))
                .build();
        account.getOutgoes().add(operation);
        account.addOutgo(amount);
        accountService.save(account);
        return "redirect:/accounts/{accountid}";
    }

    @PostMapping("/accounts/{accountid}/addFeatureIncome")
    public String accountAddFeatureIncome(@RequestParam("amount") BigDecimal amount,
                                          @RequestParam("aimTransaction") String aimTransaction,
                                          @PathVariable("accountid") Account account,
                                          @RequestParam("date") Date date ){
        Operation operation = Operation.builder()
                .amount(amount)
                .aimTransaction(aimTransaction)
                .dateTransaction(date)
                .build();
        account.getFeatureIncomes().add(operation);
        accountService.save(account);
        return "redirect:/accounts/{accountid}";
    }

    @PostMapping("/accounts/{accountid}/addFeatureOutgo")
    public String accountAddFeatureOutgo(@RequestParam("amount") BigDecimal amount,
                                          @RequestParam("aimTransaction") String aimTransaction,
                                          @PathVariable("accountid") Account account,
                                          @RequestParam("date") Date date ){
        Operation operation = Operation.builder()
                .amount(amount)
                .aimTransaction(aimTransaction)
                .dateTransaction(date)
                .build();
        account.getFeatureOutgoes().add(operation);
        accountService.save(account);
        return "redirect:/accounts/{accountid}";
    }

    @GetMapping("/banks")
    public String banks(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "banks";
    }


}
