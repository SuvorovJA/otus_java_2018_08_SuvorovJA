package ru.otus.sua.L16.sts.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.otus.sua.L16.sts.abstractions.Msg;

// for passthrough using in messagesystem

@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends Msg {
    private String destination;
    private String payload;
    private String sender;
    private String dialogUid;
    private boolean isSupplier;


    public Message() {
        super(Message.class);
    }

    public Message(String destination, String payload, String sender, String dialogUid, boolean isSupplier) {
        super(Message.class);
        this.destination = destination;
        this.payload = payload;
        this.sender = sender;
        this.isSupplier = isSupplier;
        this.dialogUid = dialogUid;
    }

    public Message getResponseMessage(String payload, String sender, boolean isSupplier) {
        Message message = new Message();
        message.setDialogUid(this.dialogUid);
        message.setDestination(this.destination);
        message.setPayload(payload);
        message.setSender(sender);
        message.setSupplier(isSupplier);
        return message;
    }
}
