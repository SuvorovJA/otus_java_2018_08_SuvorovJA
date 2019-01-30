package ru.otus.sua.L16.frontendservice;

import lombok.Data;
import ru.otus.sua.L16.sts.abstractions.Msg;

@Data
public class MessageMsg extends Msg {

    private Message message;

    public MessageMsg(Message message) {
        super(MessageMsg.class);
        this.message = message;
    }
}
