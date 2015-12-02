package projectPackage;

import java.util.ArrayList;

public class Trip {
    //Atributes
    private int code;
    private String origin, destiny;
    private double price, duration;
    private Date date;
    private ArrayList<Bus> buses;
    private ArrayList<Coment> coments;
    private ArrayList<User> waitingList;

    public Trip(int code, String origin, String destiny, double price, double duration, Date date, ArrayList<Bus> buses, ArrayList<Coment> coments) {
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.buses = buses;
        this.coments = coments;
        this.waitingList = new ArrayList<>();
    }

    //Getters and Setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDestiny() { return destiny; }
    public void setDestiny(String destiny) { this.destiny = destiny; }

    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }


    public ArrayList<Bus> getBuses() { return buses; }
    public void setBuses(ArrayList<Bus> buses) { this.buses = buses; }

    public ArrayList<Coment> getComents() { return coments; }
    public void setComents(ArrayList<Coment> coments) { this.coments = coments; }

    public ArrayList<User> getWaitingList() { return waitingList; }
    public void setWaitingList(ArrayList<User> waitingList) { this.waitingList = waitingList; }

    @Override
    public String toString() {
        return "Trip{" +
                "code=" + code +
                ", origin='" + origin + '\'' +
                ", destiny='" + destiny + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", date=" + date +
                ", buses=" + buses +
                ", coments=" + coments +
                '}';
    }

    public void notifyWaitingList() {
        System.out.println("Dear clients of Costa & Machado the " + this + " is now avaiable\n" +
                           "If you wish to complete your reservation proceed with the payment, the first client to pay gets the reservation.");
    }
}
