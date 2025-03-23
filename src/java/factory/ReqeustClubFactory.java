package factory;

import repository.RequestClubRepository;
import repository.impl.RequestClubRepositoryImpl;

public class ReqeustClubFactory {
    private final static RequestClubRepository requestClubRepository = new RequestClubRepositoryImpl();

    public static RequestClubRepository getRequestClubRepository() {
        return requestClubRepository;
    }
}
