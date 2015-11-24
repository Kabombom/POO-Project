package projectPackage;

public class Bus {
    private String licensePlate;
    private int capacity;
    private boolean[] takenSeats;

    public Bus(String licensePlate, int capacity, boolean[] takenSeats) {
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.takenSeats = takenSeats;
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
    public void setTakenSeats(int seat) { this.takenSeats[seat] = true; }

    @Override
    public String toString() {
        return "Bus{" +
                "licensePlate='" + licensePlate + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
