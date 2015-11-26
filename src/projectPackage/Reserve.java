package projectPackage;

import java.util.ArrayList;

public class Reserve {
    private Client  client;
    private Trip trip;
    private int seatNumber;

    public Reserve(Client client, Trip trip, int seatNumber) {
        this.client = client;
        this.trip = trip;
        this.seatNumber = seatNumber;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    @Override
    public String toString() {
        return "Reserve{" +
                "client=" + client +
                ", trip=" + trip +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
