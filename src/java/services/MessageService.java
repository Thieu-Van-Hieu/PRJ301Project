package services;

import dto.MessageResponse;
import factory.MessageFactory;
import java.util.ArrayList;
import repository.MessageRepository;

public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService() {
        messageRepository = MessageFactory.getMessageRepository();
    }

    public MessageResponse getMessageById(int id) {
        return messageRepository.getMessageById(id);
    }

    public ArrayList<MessageResponse> getMessagesByClubId(int clubId) {
        return messageRepository.getMessagesByClubId(clubId);
    }

    public ArrayList<MessageResponse> getMessageByMemberId(int memberId) {
        return messageRepository.getMessagesByMemberId(memberId);
    }

    public MessageResponse addMessage(MessageResponse messageResponse) {
        return messageRepository.addMessage(messageResponse);
    }

    public MessageResponse deleteMessageById(int messageId) {
        return messageRepository.deleteMessageById(messageId);
    }
}
