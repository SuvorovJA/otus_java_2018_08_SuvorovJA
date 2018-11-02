package ru.otus.sua.L07.atm;

import ru.otus.sua.L07.atm.staff.*;

import java.util.EnumMap;

public class TrivialWithdrawStrategyImpl implements WithdrawStrategy {


    @Override
    public EnumMap<Nominal, Long> tryWithdraw(Cartridge cartridge, long nonNegativeSumForWithdraw) throws NegativeSum, ImpossibleWithdraw {

        EnumMap<Nominal, Long> packetOut = new EnumMap<>(Nominal.class);
        EnumMap<Nominal, Long> packetIn = new EnumMap<>(Nominal.class);
        packetIn.putAll(cartridge.available(Multivalute.RUR));

        if (nonNegativeSumForWithdraw < 0) throw new NegativeSum("Negative Sum");
        if (summarize(cartridge.available(Multivalute.RUR)) < nonNegativeSumForWithdraw)
            throw new ImpossibleWithdraw("Impossible Withdraw. Big sum.");



        long minimalAvailableNominal = 0;
        Nominal[] nominals = Nominal.values();
        for (int i = nominals.length - 1; i >= 0 ; i--) {
            if (packetIn.get(nominals[i]) <= 0) continue;
            minimalAvailableNominal = nominals[i].getNominal();
            break;
        }
        if (minimalAvailableNominal == 0) throw new ImpossibleWithdraw("Impossible Withdraw. Empty cartridge.");


        long left = nonNegativeSumForWithdraw;
        while (left > 0) {

            for (Nominal nominal : packetIn.keySet()) {
                if (left < minimalAvailableNominal) throw new ImpossibleWithdraw("Impossible Withdraw. Not a round number or small sum.");
                if (left < nominal.getNominal()) continue;
                if (packetIn.get(nominal) == null || packetIn.get(nominal) <= 0) continue;
                left -= nominal.getNominal();
                discharging(packetIn, nominal, 1);
                charging(packetOut, nominal, 1);
                break;
            }

        }

        if (left < 0 || summarize(packetOut) != nonNegativeSumForWithdraw)
            throw new ImpossibleWithdraw("Impossible Withdraw. Err in algorithm.");

        // apply packetOut to available (discharge main cartridge)
        for (Nominal nominal : packetOut.keySet()) {
            try {
                cartridge.discharging(Multivalute.RUR, nominal, packetOut.get(nominal));
            } catch (ImpossibleDischarging e) {
                throw new ImpossibleWithdraw("Err when discharge cartridge. Atm broken state.");
            }
        }

        return packetOut;
    }


    private void charging(EnumMap<Nominal, Long> packet, Nominal nominal, long amount) {
        long oldAmount = 0;
        if (packet.containsKey(nominal)) oldAmount = packet.get(nominal);
        packet.put(nominal, oldAmount + amount);
    }

    private void discharging(EnumMap<Nominal, Long> packet, Nominal nominal, long amount) {
        long oldAmount = 0;
        if (packet.containsKey(nominal)) oldAmount = packet.get(nominal);
        if (oldAmount - amount < 0) return;
        packet.put(nominal, oldAmount - amount);
    }

    private long summarize(EnumMap<Nominal, Long> packet) {
        long sum = 0;
        for (Nominal n : packet.keySet()) {
            sum += packet.get(n) * n.getNominal();
        }
        return sum;
    }

}
