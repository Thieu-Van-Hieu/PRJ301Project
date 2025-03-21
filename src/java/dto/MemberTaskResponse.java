package dto;

public class MemberTaskResponse {
    private int id;
    private int clubId;
    private String name;

    public MemberTaskResponse() {
    }

    public MemberTaskResponse(int id, int clubId, String name) {
        this.id = id;
        this.clubId = clubId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
