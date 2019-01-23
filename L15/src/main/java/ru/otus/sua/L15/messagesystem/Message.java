package ru.otus.sua.L15.messagesystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Destination destination;
    private String message;
    private String senderName = "anonymous";
    private UUID dialogId;
    private MSMember sender;


    public Message getResponseMessage() {
        Message message = new Message();
        message.setDialogId(this.dialogId);
        message.setDestination(this.destination);
        return message;
    }
}
