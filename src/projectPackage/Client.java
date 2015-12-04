package projectPackage;

import java.util.ArrayList;

public abstract class Client extends User{
    private int[] tripsBoughtByMonth;
    ArrayList<Reserve> clientReserves = new ArrayList<>();

    public Client(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
        this.clientReserves = new ArrayList<>();
        this.tripsBoughtByMonth = new int[12];
    }

    public ArrayList<Reserve> getClientReserves() {
        return clientReserves;
    }
    public void setClientReserves(ArrayList<Reserve> clientReserves) { this.clientReserves = clientReserves; }

    public int[] getTripsBoughtByMonth() { return tripsBoughtByMonth; }
    public void setTripsBoughtByMonth(int[] tripsBoughtByMonth) { this.tripsBoughtByMonth = tripsBoughtByMonth; }

    public abstract void listAvaiableTrips(ArrayList<Trip> trips);
    public abstract double reserveTrip(ArrayList<Trip> trips);
    public abstract void listReserves();
    public abstract double cancelReserve();
    public abstract void addCommentTrip(ArrayList<Trip> trips);
    public abstract void listCommentsTrip(ArrayList<Coment> coments);
    public abstract double leaveWaitingList(ArrayList<Trip> trips);
    public abstract double payment(Trip trip);

}
