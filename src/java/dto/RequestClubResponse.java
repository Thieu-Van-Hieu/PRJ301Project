package dto;

public class RequestClubResponse {
    private int id;
    private String clubId;
    private String name;
    private String studentId;
    private String status;

    public RequestClubResponse() {
    }

    public RequestClubResponse(int id, String clubId, String name, String studentId, String status) {
        this.id = id;
        this.clubId = clubId;
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

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
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
