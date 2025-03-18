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

    public String getUsername() {
        return member.getName();
    }

    public String getContent() {
        return content;
    }

    public static class Builder {
        private Message message;

        public Builder() {
            message = new Message();
        }

        public Builder setId(int id) {
            message.id = id;
            return this;
        }

        public Builder setMember(Member member) {
            message.member = member;
            return this;
        }

        public Builder setContent(String content) {
            message.content = content;
            return this;
        }

        public Message build() {
            return message;
        }
    }
}
