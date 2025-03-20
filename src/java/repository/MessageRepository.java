package repository;

import java.util.ArrayList;
import dto.MessageResponse;

public interface MessageRepository {
    public MessageResponse getMessageById(int id);

    public ArrayList<MessageResponse> getMessagesByClubId(int clubId);

    public ArrayList<MessageResponse> getMessagesByMemberId(int memberId);

    public MessageResponse addMessage(MessageResponse message);

    public MessageResponse deleteMessageById(int messageId);
}