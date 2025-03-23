package repository;

import java.util.ArrayList;

import dto.RequestClubResponse;

public interface RequestClubRepository {
    public ArrayList<RequestClubResponse> getAllRequest(int clubId);

    public RequestClubResponse getRequest(int id);

    public boolean updateRequest(int id, String status);
<<<<<<< HEAD

    public boolean createRequest(RequestClubResponse request);
=======
>>>>>>> ae36d1b32d3a165192ee4aa9051d3f3dc01ec61d
}
