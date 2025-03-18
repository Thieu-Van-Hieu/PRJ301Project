package entity;

public class Message {
    private int id;
    private int memberId;
    private String content;

    public Message() {
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

    public static class Builder {
        private Message message;

        public Builder() {
            message = new Message();
        }

        public Builder setId(int id) {
            message.id = id;
            return this;
        }

        public Builder setMemberId(int memberId) {
            message.memberId = memberId;
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
