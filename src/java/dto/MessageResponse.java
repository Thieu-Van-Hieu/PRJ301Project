package dto;

public class MessageResponse {
    private int id;
    private int clubId;
    private int memberId;
    private String memberName;
    private String message;

    public MessageResponse() {
    }

    public MessageResponse(int id, int clubId, int memberId, String memberName, String message) {
        this.id = id;
        this.clubId = clubId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.message = message;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
