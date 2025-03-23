package repository;

import java.util.ArrayList;

import dto.RequestClubResponse;

public interface RequestClubRepository {
    public ArrayList<RequestClubResponse> getAllRequest(int clubId);

    public RequestClubResponse getRequest(int id);

    public boolean updateRequest(int id, String status);
}
