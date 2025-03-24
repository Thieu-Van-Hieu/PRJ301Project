package services;

import java.util.ArrayList;

import dto.RequestClubResponse;
import factory.ReqeustClubFactory;
import repository.RequestClubRepository;

public class RequestClubService {
    private RequestClubRepository requestClubRepository;

    public RequestClubService() {
        this.requestClubRepository = ReqeustClubFactory.getRequestClubRepository();
    }

    public ArrayList<RequestClubResponse> getAllRequest(int clubId) {
        return requestClubRepository.getAllRequest(clubId);
    }

    public RequestClubResponse getRequest(int id) {
        return requestClubRepository.getRequest(id);
    }

    public boolean updateRequest(int id, String status) {
        return requestClubRepository.updateRequest(id, status);
    }

    public boolean createRequest(RequestClubResponse request) {
        return requestClubRepository.createRequest(request);
    }
}
