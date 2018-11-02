package ru.otus.sua.L07.atm.staff;

import java.util.EnumMap;

public interface AtmMachine extends AtmAvailable {

    long withdraw(Multivalute valute, long nonNegativeSum) throws NegativeSum, ImpossibleWithdraw;

    long deposit(Multivalute valute, EnumMap<Nominal, Long> packet) throws ImpossibleDeposit;

    long balanceTotal(Multivalute valute);

    void insertWithdrawStrategy(WithdrawStrategy strategy);

}
