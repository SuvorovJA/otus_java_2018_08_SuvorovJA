package ru.otus.sua.L16.sts.entities;

import lombok.Getter;
import lombok.ToString;
import ru.otus.sua.L16.sts.abstractions.Msg;

@Getter
@ToString
public class PingMsg extends Msg {
    private final long time;
    private final String label;

    public PingMsg(String label) {
        super(PingMsg.class);
        this.label = label;
        time = System.currentTimeMillis();
    }
}
