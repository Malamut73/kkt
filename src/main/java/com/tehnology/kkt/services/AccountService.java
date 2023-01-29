package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Account;
import com.tehnology.kkt.repositories.AccountDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountDAO accountDAO;


    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    public void save(Account account) {
        accountDAO.save(account);
    }

    public Account findById(Long accountid) {
        return accountDAO.getReferenceById(accountid);
    }

    public List<Account> findByDate(Date date) {
        return accountDAO.findDistinctAccountByFeatureOutgoes_DateTransactionAfter(date);
    }
}
