package entity;

public class Message {
    private int id;
    private int memberId;
    private String content;

    public Message() {
    }

    public Message(MessageBuilder builder) {
        this.id = builder.id;
        this.memberId = builder.memberId;
        this.content = builder.content;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }

    public static class MessageBuilder {
        private int id;
        private int memberId;
        private String content;

        public MessageBuilder() {
        }

        public MessageBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MessageBuilder setMemberId(int memberId) {
            this.memberId = memberId;
            return this;
        }

        public MessageBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
