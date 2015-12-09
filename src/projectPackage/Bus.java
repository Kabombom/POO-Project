package projectPackage;

import java.io.Serializable;

public class Bus implements Serializable{
    private String licensePlate;
    private int capacity;
    private boolean[] takenSeats;

    public Bus(String licensePlate, int capacity) {
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.takenSeats = new boolean[capacity];
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean[] getTakenSeats() { return takenSeats; }

    public void setTakenSeats(boolean[] takenSeats) { this.takenSeats = takenSeats; }

    public void addTakenSeat(int seat) { this.takenSeats[seat] = true; }
    public void deleteTakenSeat(int seat) { this.takenSeats[seat] = false; }

    @Override
    public String toString() {
        return "Bus{" +
                "licensePlate='" + licensePlate + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
