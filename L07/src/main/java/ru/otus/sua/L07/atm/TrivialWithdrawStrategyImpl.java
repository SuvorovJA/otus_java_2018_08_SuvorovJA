package ru.otus.sua.L07.atm;

import ru.otus.sua.L07.atm.staff.*;

import java.util.EnumMap;

public class TrivialWithdrawStrategyImpl implements WithdrawStrategy {


    @Override
    public EnumMap<Nominal, Long> tryWithdraw(Cartridge cartridge, long nonNegativeSumForWithdraw) throws NegativeSum, ImpossibleWithdraw {

        Cartridge packetOut = new Cartridge();
        Cartridge packetIn = new Cartridge();
        packetIn.chargingByCartridge(cartridge);

        if (nonNegativeSumForWithdraw < 0) throw new NegativeSum("Negative Sum");

        if (summarize(cartridge.available(Multivalute.RUR)) < nonNegativeSumForWithdraw)
            throw new ImpossibleWithdraw("Impossible Withdraw. SumForWithdraw > Cartridge capacity.");

        if (packetIn.getMinimalAvailableNominal() == null)
            throw new ImpossibleWithdraw("Impossible Withdraw. Empty cartridge.");

        long minimalAvailableNominalBanknote = packetIn.getMinimalAvailableNominal().getNominal();
        if (minimalAvailableNominalBanknote == 0)
            throw new ImpossibleWithdraw("Impossible Withdraw. SumForWithdraw < Minimal available banknote.");

        long left = nonNegativeSumForWithdraw;
        while (left > 0) {

            for (Nominal nominal : packetIn.available(Multivalute.RUR).keySet()) {

                if (left < minimalAvailableNominalBanknote)
                    throw new ImpossibleWithdraw("Impossible Withdraw. Not a round number or small sum.");

                if (left < nominal.getNominal()) continue;
                if (packetIn.available(Multivalute.RUR).get(nominal) == null) continue;
                if (packetIn.available(Multivalute.RUR).get(nominal) <= 0) continue;

                left -= nominal.getNominal();

                try {
                    packetIn.discharging(Multivalute.RUR, nominal, 1);
                    packetOut.charging(Multivalute.RUR, nominal, 1);
                } catch (ImpossibleDischarging e) {
                    throw new ImpossibleWithdraw("Err when discharge subcartridge. Atm broken state. (" + e.getMessage() + ")");
                }
                break;
            }
        }

        if (left < 0 || summarize(packetOut.available(Multivalute.RUR)) != nonNegativeSumForWithdraw)
            throw new ImpossibleWithdraw("Impossible Withdraw. Err in algorithm.");

        try {
            cartridge.dischargingByCartridge(packetOut);
        } catch (ImpossibleDischarging e) {
            throw new ImpossibleWithdraw("Err when discharge main cartridge. Atm broken state.(" + e.getMessage() + ")");
        }

        return packetOut.available(Multivalute.RUR);
    }

    private long summarize(EnumMap<Nominal, Long> packet) {
        long sum = 0;
        for (Nominal n : packet.keySet()) {
            sum += packet.get(n) * n.getNominal();
        }
        return sum;
    }

}
