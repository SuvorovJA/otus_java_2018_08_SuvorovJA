package ru.otus.sua.L16.sts.entities;

import lombok.Data;
import ru.otus.sua.L16.sts.abstractions.Msg;

// for passthrough using in messagesystem

@Data
public class Message extends Msg {
    private String destination;
    private String payload;
    private String sender;
    private String dialogUid;

    protected Message() {
        super(Message.class);
    }

    public Message getResponseMessage(String payload, String sender) {
        Message message = new Message();
        message.setDialogUid(this.dialogUid);
        message.setDestination(this.destination);
        return message;
    }
}
