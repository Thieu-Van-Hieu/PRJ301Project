package entity;

public class Member {
    private int id;
    private String name;

    public Member() {
    }

    public Member(MemberBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class MemberBuilder {
        private int id;
        private String name;

        public MemberBuilder() {
        }

        public MemberBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MemberBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
