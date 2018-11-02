package ru.otus.sua.L07.atm;

import lombok.AllArgsConstructor;
import ru.otus.sua.L07.atm.staff.*;

import java.util.EnumMap;

@AllArgsConstructor
public class Atm implements AtmMachine, AtmAvailable {

    private Cartridge cartridge;
    private WithdrawStrategy strategy;

    @Override
    public EnumMap<Nominal, Long> withdraw(Multivalute valute, long nonNegativeSum) throws NegativeSum, ImpossibleWithdraw {
        if (nonNegativeSum < 0) throw new NegativeSum("Negative Sum");
        return strategy.tryWithdraw(cartridge, nonNegativeSum);
    }

    @Override
    public long deposit(Multivalute valute, EnumMap<Nominal, Long> packet) throws ImpossibleDeposit {
        long sum = 0;
        for (Nominal nominal : packet.keySet()) {
            cartridge.charging(valute, nominal, packet.get(nominal));
            sum += packet.get(nominal) * nominal.getNominal();
        }
        return sum;
    }

    @Override
    public long balanceTotal(Multivalute valute) {
        long sum = 0;
        EnumMap<Nominal, Long> available = cartridge.available(valute);
        for (Nominal n : available.keySet()) {
            sum += available.get(n) * n.getNominal();
        }
        return sum;
    }

    @Override
    public void setWithdrawStrategy(WithdrawStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public EnumMap<Nominal, Long> available(Multivalute valute) {
        return cartridge.available(valute);
    }
}
