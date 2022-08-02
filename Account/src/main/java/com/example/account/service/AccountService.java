package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.dto.AccountDto;
import com.example.account.exceoption.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

import static com.example.account.type.AccountStatus.*;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    @Transactional
    public AccountDto createAccount(Long userId, Long initialBalance) {
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(ErrorCode.USER_NOT_FOUND));

        validateCreateAccount(accountUser);

        String newAccountNumber = getNewAccountNumber();
        while (accountRepository.findByAccountNumber(newAccountNumber) != null) {
            System.out.println(accountRepository.findByAccountNumber(newAccountNumber));
            newAccountNumber = getNewAccountNumber();
        }

        return AccountDto.fromEntity(
                accountRepository.save(Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(IN_USE)
                        .balance(initialBalance)
                        .accountNumber(newAccountNumber)
                        .registeredAt(LocalDateTime.now())
                        .build()));
    }

    private String getNewAccountNumber() {
        StringBuilder newAccountNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            newAccountNumber.append(random.nextInt(10));
        }
        return newAccountNumber.toString();
    }

    private void validateCreateAccount(AccountUser accountUser) {
        if (accountRepository.countByAccountUser(accountUser) >= 10) {
            throw new AccountException(ErrorCode.MAX_ACCOUNT_PER_USER_10);
        }
    }

    @Transactional
    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }
}
