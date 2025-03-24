package dto;

public class RequestClubResponse {
    private int id;
    private int clubId;
    private int userId;
    private String name;
    private String studentId;
    private String status;

    public RequestClubResponse() {
    }

    public RequestClubResponse(int id, int clubId, int userId, String name, String studentId, String status) {
        this.id = id;
        this.clubId = clubId;
        this.userId = userId;
        this.name = name;
        this.studentId = studentId;
        this.status = status;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
