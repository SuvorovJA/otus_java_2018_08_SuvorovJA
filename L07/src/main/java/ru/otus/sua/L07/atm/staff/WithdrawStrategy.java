package ru.otus.sua.L07.atm.staff;

import java.util.EnumMap;

public interface WithdrawStrategy {

    /**
     *
     * @param available -  available nominals packet
     * @param nonNegativeSum - amount for withdraw
     * @return - withdrawed nominals packet
     * @throws ImpossibleWithdraw - if impossible on given available packet
     */
    EnumMap<Nominal, Long> tryWithdraw(EnumMap<Nominal, Long> available, long nonNegativeSum) throws ImpossibleWithdraw;

}
