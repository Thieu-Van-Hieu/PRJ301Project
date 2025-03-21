package entity;
import java.sql.Timestamp;


public class Member {
    private int id;
    private int clubId;
    private int memberRoleId; // Thêm trường mới

    public Member() {
    }

    public Member(Builder builder) {
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.memberRoleId = builder.memberRoleId; // Gán giá trị từ Builder
    }

    public int getId() {
        return id;
    }

    public int getClubId() {
        return clubId;
    }

    public int getMemberRoleId() { // Getter cho memberRoleId
        return memberRoleId;
    }

    public static class Builder {
        private int id;
        private int clubId;
        private int memberRoleId; // Thêm trường mới

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setClubId(int clubId) {
            this.clubId = clubId;
            return this;
        }

        public Builder setMemberRoleId(int memberRoleId) { // Setter cho memberRoleId
            this.memberRoleId = memberRoleId;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
