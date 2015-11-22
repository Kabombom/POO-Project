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
    public abstract void reserveTrip(ArrayList<Reserve> reserves);
    public abstract void listReserves(ArrayList<Reserve> reserves);
    public abstract void cancelReserve(ArrayList<Reserve> reserves);
    public abstract void addCommentTrip(ArrayList<Coment> coments);
    public abstract void listCommentsTrip(ArrayList<Coment> coments);
    public abstract void addScore(Trip trip);
    public abstract void payment();
}
