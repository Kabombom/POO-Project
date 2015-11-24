package projectPackage;

import java.util.ArrayList;

public class Regular extends Client{

    public Regular(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password, type, clientReserves);
    }

    @Override
    public void listAvaiableTrips(ArrayList<Trip> trips) {

    }

    @Override
    public void reserveTrip(ArrayList<Trip> trips) {

    }

    @Override
    public void listReserves(ArrayList<Reserve> reserves) {

    }

    @Override
    public void cancelReserve() {

    }

    @Override
    public void addCommentTrip(Trip trip) {

    }

    @Override
    public void listCommentsTrip(ArrayList<Coment> coments) {

    }


}
