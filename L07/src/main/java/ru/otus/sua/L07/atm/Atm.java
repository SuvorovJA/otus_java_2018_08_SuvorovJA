package ru.otus.sua.L07.atm;

import lombok.NoArgsConstructor;
import ru.otus.sua.L07.atm.staff.*;

@NoArgsConstructor
public class Atm implements AtmMachine {
    @Override
    public long withdraw(Multivalute valute, long nonNegativeSum) throws ImpossibleWithdraw {
        return 0;
    }

    @Override
    public long deposit(Multivalute valute, long nonNegativeSum) {
        return 0;
    }

    @Override
    public long balanceTotal(Multivalute valute) {
        return 0;
    }

    @Override
    public void insertCartridge(AtmCartridge cartgidge) {

    }

    @Override
    public void insertWithdrawStrategy(WithdrawStrategy strategy) {

    }
}
