package entity;
import java.sql.Timestamp;


public class Member {
    private int id;
    private String name;
    
    
    public Member() {
    }

    public Member(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
