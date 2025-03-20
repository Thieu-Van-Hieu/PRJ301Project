package entity;

public class Member {
    private int id;
    private int clubId;
    private int memberId;

    public Member() {
    }

    public Member(Builder builder) {
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.memberId = builder.memberId;
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

    public static class Builder {
        private int id;
        private int clubId;
        private int memberId;

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

        public Member build() {
            return new Member(this);
        }
    }
}
