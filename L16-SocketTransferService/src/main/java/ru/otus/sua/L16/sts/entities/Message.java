package ru.otus.sua.L16.sts.entities;

import lombok.AllArgsConstructor;
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

    public Message() {
        super(Message.class);
    }

    public Message(String destination, String payload, String sender, String dialogUid) {
        super(Message.class);
        this.destination = destination;
        this.payload = payload;
        this.sender = sender;
        this.dialogUid = dialogUid;
    }

    public Message getResponseMessage(String payload, String sender) {
        Message message = new Message();
        message.setDialogUid(this.dialogUid);
        message.setDestination(this.destination);
        return message;
    }
}
