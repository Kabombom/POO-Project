package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Premium extends Client {
    public Premium(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password, type, clientReserves);
    }


    public void listAvaiableTrips(ArrayList<Trip> trips) {
        for (Trip trip: trips)
            System.out.println(trip);
    }

    public void reserveTrip(ArrayList<Reserve> reserves) {

    }

    public void listReserves(ArrayList<Reserve> reserves) {
        for (Reserve reserve : reserves) {
            System.out.println(reserve);
        }
    }

    public void cancelReserve(ArrayList<Reserve> reserves) {
        System.out.println("Trip code of reserve to cancel: ");
    }

    public void addCommentTrip(ArrayList<Coment> coments) {

    }

    public void listCommentsTrip(ArrayList<Coment> coments) {

    }

    public void addScore(Trip trip) {

    }

    public void payment() {

    }
}
