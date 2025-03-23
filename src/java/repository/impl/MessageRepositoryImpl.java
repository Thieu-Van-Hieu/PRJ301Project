package repository.impl;

import dto.MessageResponse;
import java.util.ArrayList;
import java.sql.*;
import repository.MessageRepository;
import util.DBContext;

public class MessageRepositoryImpl implements MessageRepository {

    @Override
    public MessageResponse getMessageById(int id) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select *
                    from messages as m
                    where id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                MessageResponse message = new MessageResponse();
                message.setId(rs.getInt("id"));
                message.setClubId(rs.getInt("clubId"));
                message.setMemberId(rs.getInt("memberId"));
                message.setMessage(rs.getString("content"));
                return message;
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }

        }

        return null;
    }

    @Override
    public ArrayList<MessageResponse> getMessagesByClubId(int clubId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select m.id, m.clubId, m.memberId, m.content, ui.firstName
                    from messages as m
                    join members as mb on mb.id = m.memberId
                    join user_informations as ui on ui.userId = mb.userId
                    where m.clubId = ?
                    order by m.id
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();
            ArrayList<MessageResponse> messages = new ArrayList<>();
            while (rs.next()) {
                MessageResponse message = new MessageResponse();
                message.setId(rs.getInt("id"));
                message.setClubId(rs.getInt("clubId"));
                message.setMemberId(rs.getInt("memberId"));
                message.setMemberName(rs.getString("firstName"));
                message.setMessage(rs.getString("content"));
                messages.add(message);
                System.out.println("Add");
            }
            return messages;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }

            return null;
        }
    }

    @Override
    public ArrayList<MessageResponse> getMessagesByMemberId(int memberId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select m.id, m.clubId, m.memberId, m.content, ui.firstName
                    from messages as m
                    join members as mb on mb.id = m.memberId
                    join user_informations as ui on ui.userId = mb.userId
                    where memberId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, memberId);
            ResultSet rs = statement.executeQuery();
            ArrayList<MessageResponse> messages = new ArrayList<>();
            while (rs.next()) {
                MessageResponse message = new MessageResponse();
                message.setId(rs.getInt("id"));
                message.setClubId(rs.getInt("clubId"));
                message.setMemberId(rs.getInt("memberId"));
                message.setMemberName(rs.getString("firstName"));
                message.setMessage(rs.getString("content"));
                messages.add(message);
            }
            return messages;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }

            return null;
        }
    }

    @Override
    public MessageResponse addMessage(MessageResponse message) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    insert into messages (clubId, memberId, content)
                    values (?, ?, ?)
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, message.getClubId());
            statement.setInt(2, message.getMemberId());
            statement.setString(3, message.getMessage());
            int rs = statement.executeUpdate();
            System.out.println(rs);
            if (rs > 0) {
                return message;
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return null;
    }

    @Override
    public MessageResponse deleteMessageById(int messageId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    update messages
                    set content = ''
                    where id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, messageId);
            int rs = statement.executeUpdate();
            if (rs > 0) {
                return getMessageById(messageId);
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return null;
    }

}
