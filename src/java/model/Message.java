package model;

public class Message {
    private int id;
    private Member member;
    private String content;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public Message setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return member.getName();
    }

    public Message setUsername(String username) {
        this.member.setName(username);
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }
}
