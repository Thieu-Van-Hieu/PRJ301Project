package factory;

import repository.MessageRepository;
import repository.impl.MessageRepositoryImpl;


public class MessageFactory {
    private static final MessageRepository messageRepository = new MessageRepositoryImpl();
    
    public static MessageRepository getMessageRepository() {
        return messageRepository;
    }
}
