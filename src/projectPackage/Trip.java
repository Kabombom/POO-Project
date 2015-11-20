package projectPackage;

public class Trip {
    //Atributes
    private int code;
    private String origin, destiny;
    private double price, duration, rating;
    private Date date;

    //Constructors
    public Trip(){}
    public Trip(int code,String origin,String destiny,double price, double duration, double rating,Date date){
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.price = price;
        this.duration = duration;
        this.rating = rating;
        this.date = date;
    }

    //Getters && Setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getDestiny() { return destiny; }
    public void setDestiny(String destiny) { this.destiny = destiny; }

    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }

    //
}
