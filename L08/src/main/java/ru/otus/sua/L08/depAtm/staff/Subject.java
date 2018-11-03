package ru.otus.sua.L08.depAtm.staff;

import ru.otus.sua.L08.atm.staff.Event;
import ru.otus.sua.L08.atm.staff.Observer;

public interface Subject {

    void register(Observer observer);

    void unregister(Observer observer);

    void notify(Event event);

}
