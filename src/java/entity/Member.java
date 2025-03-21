package entity;
import java.sql.Timestamp;


public class Member {
    private int id;
    private int clubId;
    private String role; // Thêm trường mới

    public Member() {
    }

    public Member(Builder builder) {
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.role = builder.role; // Gán giá trị từ Builder
    }

    public int getId() {
        return id;
    }

    public int getClubId() {
        return clubId;
    }

    public String getRole() {
        return role;
    }  

    public static class Builder {
        private int id;
        private int clubId;
        private String role; // Thêm trường mới

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setClubId(int clubId) {
            this.clubId = clubId;
            return this;
        }

        public Builder setRole(String role) { // Setter cho memberRoleId
            this.role = role;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
