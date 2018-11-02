package ru.otus.sua.L07.atm.staff;

import java.util.EnumMap;

public interface AtmMachine extends AtmAvailable {

    EnumMap<Nominal, Long> withdraw(Multivalute valute, long nonNegativeSum) throws NegativeSum, ImpossibleWithdraw;

    long deposit(Multivalute valute, EnumMap<Nominal, Long> packet) throws ImpossibleDeposit;

    long balanceTotal(Multivalute valute);

    void setWithdrawStrategy(WithdrawStrategy strategy);

}
