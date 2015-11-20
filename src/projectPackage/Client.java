package projectPackage;

import java.util.ArrayList;

public abstract class Client extends User{
    ArrayList<Reserve> clientReserves = new ArrayList<>();

    Client() {}
    public Client(String name, String nif, String address, String email, String phone, String password, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password);
        this.clientReserves = clientReserves;
    }

    public ArrayList<Reserve> getClientReserves() {
        return clientReserves;
    }

    public void setClientReserves(ArrayList<Reserve> clientReserves) {
        this.clientReserves = clientReserves;
    }

    public abstract void listAvaiableTrips();
    public abstract void reserveTrip();
    public abstract void listReserves();
    public abstract void cancelReserve();
    public abstract void addCommentTrip();
    public abstract void listCommentsTrip();
    public abstract void addScore();
    public abstract void payment();
}
