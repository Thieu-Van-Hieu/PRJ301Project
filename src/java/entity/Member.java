package entity;

public class Member {
    private int id;
    private String name;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private Member member;

        public Builder() {
            member = new Member();
        }

        public Builder setId(int id) {
            member.id = id;
            return this;
        }

        public Builder setName(String name) {
            member.name = name;
            return this;
        }

        public Member build() {
            return member;
        }
    }
}
