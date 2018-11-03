package ru.otus.sua.L08.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.sua.L07.atm.Atm;
import ru.otus.sua.L07.atm.Cartridge;
import ru.otus.sua.L07.atm.staff.WithdrawStrategy;
import ru.otus.sua.L08.atm.staff.AdvancedAtmMachine;
import ru.otus.sua.L08.atm.staff.Event;
import ru.otus.sua.L08.atm.staff.Observer;

public class AdvancedAtm extends Atm implements AdvancedAtmMachine, Observer {

    private static Logger log = LoggerFactory.getLogger(AdvancedAtm.class);
    private Cartridge backup = new Cartridge();

    public AdvancedAtm(Cartridge cartridge, WithdrawStrategy strategy) {
        super(cartridge, strategy);
        this.backup.chargingByCartridge(cartridge);
    }

    @Override
    public void reset() {
        Cartridge clone = new Cartridge();
        clone.chargingByCartridge(backup);
        this.setCartridge(clone);
        log.info("ATM reset done.");
    }

    @Override
    public void notify(Event event) {
        if (event == Event.RESET) reset();
    }
}
