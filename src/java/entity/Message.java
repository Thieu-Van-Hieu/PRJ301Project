package entity;

public class Message {
    private int id;
    private int clubId;
    private int memberId;
    private String content;

    public Message() {
    }

    public Message(Builder builder) {
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.memberId = builder.memberId;
        this.content = builder.content;
    }

    public int getId() {
        return id;
    }

    public int getClubId() {
        return clubId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }

    public static class Builder {
        private int id;
        private int clubId;
        private int memberId;
        private String content;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setClubId(int clubId) {
            this.clubId = clubId;
            return this;
        }

        public Builder setMemberId(int memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
