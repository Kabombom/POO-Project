package projectPackage;

public class Reserve {
    private int seatNumber;
    private boolean state;
    private Trip trip;
    private Client  client;

    public Reserve(Client client, Trip trip, int seatNumber) {
        this.client = client;
        this.trip = trip;
        this.seatNumber = seatNumber;
        this.state = true;
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

    public boolean getState() { return state; }
    public void setState(boolean state) { this.state = state; }

    @Override
    public String toString() {
        return "Reserve{" +
                "client=" + client +
                ", trip=" + trip +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
