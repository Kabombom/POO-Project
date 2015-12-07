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

    public abstract void listAvaiableTrips(Agency agency);
    public abstract void reserveTrip(Agency agency);
    public abstract void listReserves();
    public abstract void cancelReserve(Agency agency);
    public abstract void addCommentTrip(Agency agency);
    public abstract void listCommentsTrip(Agency agency);
    public abstract void leaveWaitingList(Agency agency);
    public abstract double payment(Trip trip);

}
