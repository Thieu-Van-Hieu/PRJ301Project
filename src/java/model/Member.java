package model;

public class Member {
    private int id;
    private String name;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public Member setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }
}
