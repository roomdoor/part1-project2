package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.dto.AccountDto;
import com.example.account.exceoption.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.type.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUserRepository accountUserRepository;

    @InjectMocks
    private AccountService accountService;

    @DisplayName("1. create account success")
    @Test
    public void test_1() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .id(12L)
                .name("Pobi").build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(null);

        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(accountUser)
                        .accountNumber("1000000015").build());

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        //when
        AccountDto accountDto = accountService.createAccount(1L, 10000L);

        //then
        verify(accountRepository, times(1)).save(captor.capture());

        assertEquals(12L, accountDto.getUserId());
    }

    @DisplayName("2. create account user not found")
    @Test
    public void test_2() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .id(12L)
                .name("Pobi").build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        //when
        AccountException e = assertThrows(AccountException.class,
                () -> accountService.createAccount(1L, 10000L));

        //then
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }

    @DisplayName("3. create account maxAccount 10")
    @Test
    public void test_3() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .id(12L)
                .name("Pobi").build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));
        given(accountRepository.countByAccountUser(accountUser))
                .willReturn(10);

        //when

        AccountException e = assertThrows(AccountException.class,
                () -> accountService.createAccount(1L, 10000L));

        //then
        assertEquals(ErrorCode.MAX_ACCOUNT_PER_USER_10, e.getErrorCode());
    }

    @DisplayName("4. create account random 10")
    @Test
    public void test_4() {
        //given
        AccountUser accountUser = AccountUser.builder()
                .id(1L)
                .name("Pobi").build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(accountUser));
        given(accountRepository.findByAccountNumber(any()))
                .willReturn(null);
        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(accountUser)
                        .accountNumber("1000000015").build());

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        //when
        AccountDto accountDto = accountService.createAccount(1L, 10000L);
        AccountDto accountDto2 = accountService.createAccount(1L, 10000L);
        //then
        verify(accountRepository, times(2)).save(captor.capture());

        assertNotEquals(captor.getAllValues().get(0).getAccountNumber()
                , captor.getAllValues().get(1).getAccountNumber());
        assertEquals(1L, accountDto.getUserId());
    }
}