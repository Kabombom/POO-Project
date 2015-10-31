package projectPackage;

public class Reserve {
    private Client  client;
    private Trip trip;
    private Bus bus;
    private int seatNumber;

    public Reserve(Client client, Trip trip, Bus bus, int seatNumber) {
        this.client = client;
        this.trip = trip;
        this.bus = bus;
        this.seatNumber = seatNumber;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Trip getTrip() {
        return trip;
    }
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Bus getBus() {
        return bus;
    }
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
