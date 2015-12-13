package projectPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Trip implements Serializable{
    //Atributes
    private int code;
    private int[] salesByMonth;
    private double price, duration;
    private String origin, destiny;
    private Date date;
    private ArrayList<Bus> buses;
    private ArrayList<Coment> coments;
    private ArrayList<Client> waitingList;
    private ArrayList<Reserve> reservesOfTrip;

    public Trip(int code, String origin, String destiny, double price, double duration, Date date, ArrayList<Bus> buses) {
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.buses = buses;
        this.waitingList = new ArrayList<>();
        this.coments = new ArrayList<>();
        this.reservesOfTrip = new ArrayList<>();
        this.salesByMonth = new int[12];
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

    public int[] getSalesByMonth() { return salesByMonth; }
    public void setSalesByMonth(int[] salesByMonth) { this.salesByMonth = salesByMonth; }

    public ArrayList<Bus> getBuses() { return buses; }
    public void setBuses(ArrayList<Bus> buses) { this.buses = buses; }

    public ArrayList<Coment> getComents() { return coments; }
    public void setComents(ArrayList<Coment> coments) { this.coments = coments; }

    public ArrayList<Client> getWaitingList() { return waitingList; }
    public void setWaitingList(ArrayList<Client> waitingList) { this.waitingList = waitingList; }

    public ArrayList<Reserve> getReservesOfTrip() { return reservesOfTrip; }
    public void setReservesOfTrip(ArrayList<Reserve> reservesOfTrip) { this.reservesOfTrip = reservesOfTrip; }

    @Override
    public String toString() {
        return "Trip{" +
                "code=" + code +
                ", salesByMonth=" + Arrays.toString(salesByMonth) +
                ", price=" + price +
                ", duration=" + duration +
                ", origin='" + origin + '\'' +
                ", destiny='" + destiny + '\'' +
                ", date=" + date +
                ", buses=" + buses +
                ", coments=" + coments +
                ", waitingList=" + waitingList +
                '}';
    }

    public double averageRating() {
        ArrayList<Coment> coments =  this.getComents();
        double average = 0;
        int total = 0;

        for (Coment coment : coments) {
            average += coment.getScore();
            total++;
        }
        return average/total;
    }
}
