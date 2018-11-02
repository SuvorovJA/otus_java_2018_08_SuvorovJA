package ru.otus.sua.L07.atm.staff;

import ru.otus.sua.L07.atm.Cartridge;

import java.util.EnumMap;

public interface WithdrawStrategy {

    /**
     *
     * @param cartridge -  ATM cartridge
     * @param nonNegativeSum - amount for withdraw
     * @return - withdrawed nominals packet
     * @throws ImpossibleWithdraw - if impossible on given available packet
     */
    EnumMap<Nominal, Long> tryWithdraw(Cartridge cartridge, long nonNegativeSum) throws NegativeSum, ImpossibleWithdraw;

}
