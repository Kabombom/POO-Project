package projectPackage;

import java.util.ArrayList;

public abstract class Client extends User{
    ArrayList<Reserve> clientReserves = new ArrayList<>();

    public Client(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password, type);
        this.clientReserves = clientReserves;
    }

    public ArrayList<Reserve> getClientReserves() {
        return clientReserves;
    }
    public void setClientReserves(ArrayList<Reserve> clientReserves) { this.clientReserves = clientReserves; }

    public abstract void listAvaiableTrips(ArrayList<Trip> trips);
    public abstract void reserveTrip(ArrayList<Trip> trips);
    public abstract void listReserves(ArrayList<Reserve> reserves);
    public abstract void cancelReserve();
    public abstract void addCommentTrip(Trip trip);
    public abstract void listCommentsTrip(ArrayList<Coment> coments);
}
