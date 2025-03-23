package entity;

import java.sql.Timestamp;

public class Member {

    private int id;
    private int clubId;
    private String role; // Thêm trường mới
    private int userId;
    private int deptId;

    public Member() {
    }

    public Member(Builder builder) {
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.role = builder.role; // Gán giá trị từ Builder
        this.userId = builder.userId;
        this.deptId = builder.deptId;
    }

    public int getUserId() {
        return userId;
    }

    public int getDeptId() {
        return deptId;
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
        private int userId;
        private int deptId;

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
        
        public Builder setUserId(int userId){
            this.userId = userId;
            return this;
        }

        public Builder setDeptId(int deptId) {
            this.deptId = deptId;
            return this;
        }
        
        public Member build() {
            return new Member(this);
        }
    }
}
