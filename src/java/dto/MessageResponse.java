package dto;

public class MessageResponse {
    private int id;
    private int clubId;
    private int memberId;
    private String memberName;
    private String content;

    public MessageResponse() {
    }

    public MessageResponse(int id, int clubId, int memberId, String memberName, String content) {
        this.id = id;
        this.clubId = clubId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
