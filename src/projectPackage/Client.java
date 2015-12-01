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
    public abstract double reserveTrip(ArrayList<Trip> trips);
    public abstract void listReserves();
    public abstract void cancelReserve();
    public abstract void addCommentTrip(ArrayList<Trip> trips);
    public abstract void listCommentsTrip(ArrayList<Coment> coments);
    public abstract double payment(Trip trip);
}
