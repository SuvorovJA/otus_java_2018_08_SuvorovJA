package ru.otus.sua.L07.atm.staff;

public interface AtmMachine {

    long withdraw(Multivalute valute, long nonNegativeSum) throws ImpossibleWithdraw;

    long deposit(Multivalute valute, long nonNegativeSum);

    long balanceTotal(Multivalute valute);

    void insertCartridge(AtmCartridge cartgidge);

    void insertWithdrawStrategy(WithdrawStrategy strategy);

}
