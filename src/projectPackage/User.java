package projectPackage;

import java.util.ArrayList;

public class User {
    private String name;
    private String nif;
    private String address;
    private String email;
    private String phone;
    private String password;
    private int type;

    public User(String name, String nif, String address, String email, String phone, String password, int type) {
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.type = type;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getType() { return type; }
    public void setType(int type) { this.type = type; }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }


    public boolean optionsSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input < 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Security for switches used, verifies if the input given is a integer
    public boolean generalSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the user type given by the user from bad inputs (char, string, etc) and invalid types
    public boolean typeSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 3);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the trip code given by the user from bad inputs (char, string, etc) and invalid trip codes (already existing ones)
    public boolean tripCodeSecurity(ArrayList<Trip> trips, String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checkIfTripCodeExists(trips, code));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the trip price given by the user from bad inputs (char, string, etc)
    public boolean tripPriceSecurity(String strInput) {
        try {
            double price = Double.parseDouble(strInput);
            return !(price <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the trip duration given by the user from bad inputs (char, string, etc)
    public boolean tripDurationSecurity(String strInput) {
        try {
            double duration = Double.parseDouble(strInput);
            return !(duration <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the minute of date given by the user from bad inputs (char, string, etc) and invalid minutes
    public boolean dateMinuteSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 59);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the hourd of date given by the user from bad inputs (char, string, etc) and invalid hours
    public boolean dateHourSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 23);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the day of date given by the user from bad inputs (char, string, etc) and invalid days
    public boolean dateDaySecurity(String strInput, int month) {
        try {
            int input = Integer.parseInt(strInput);
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                return !(input <= 0 || input > 31);
            else if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11 )
                return  !(input <= 0 || input > 30);
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    //Protects the month of date given by the user from bad inputs (char, string, etc) and invalid months
    public boolean dateMonthSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 12);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the year of date given by the user from bad inputs (char, string, etc)
    public boolean dateYearSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 2015);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Checks if the license plate given by the user already exists in the buses of the Travel Agency.
    public boolean licensePlateSecurity(ArrayList<Bus> buses, String strInput) {
        for (Bus bus : buses) {
            if (bus.getLicensePlate().equals(strInput))
                return true;
        }
        return false;
    }

    //Protects the seat of date from bad inputs (char, string, etc)
    public boolean seatReserveSecurity(Bus bus, String strInput) {
        try {
            int seatNumber = Integer.parseInt(strInput);
            return !(seatNumber <= 0 || seatNumber > bus.getCapacity() || bus.getTakenSeats()[seatNumber - 1]);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Protects the trip rating given by the user from bad inputs (char, string, etc) and invalid ratings.
    public boolean ratingSecurity(String strInput) {
        try {
            double rating = Double.parseDouble(strInput);
            return !(rating <= 0 || rating > 5);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //When searching a user or creating a new one we need to check if the nif already exists
    public boolean checkIfNifExists(ArrayList<User> users, String nif) {
        for (User user : users) {
            if (user.getNif().equals(nif))
                return true;
        }
        return false;
    }

    public boolean checkIfEmailExists(ArrayList<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    //When searching a trip or creating a new one  we need to check if the trip code already exists
    public boolean checkIfTripCodeExists(ArrayList<Trip> trips, int code) {
        for (Trip trip: trips) {
            if (trip.getCode() == code)
                return true;
        }
        return false;
    }

    //Checks if a trip is full or not. Returns false if is't full
    public boolean checkIfTripFull(Bus bus) {
        for (boolean takenSeat : bus.getTakenSeats()) {
            if (!takenSeat) {
                return false;
            }
        }
        return true;
    }

    //Returns the index of a client in an ArrayList
    public int indexOfClient(ArrayList<User> users, String nif) {
        int i;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getNif().equals(nif) && users.get(i).getType() != 1)
                return i;
        }
        return i;
    }

    //Returns the index of a trip in an ArrayList
    public int indexOfTrip(ArrayList<Trip> trips, int code) {
        int i;
        for (i = 0; i < trips.size(); i++) {
            if (trips.get(i).getCode() == code)
                return i;
        }
        return i;
    }

    //Returns the index of a bus in an ArrayList
    public int indexOfBus(ArrayList<Bus> buses, String licensePlate) {
        int i;
        for (i = 0; i < buses.size(); i++) {
            if (buses.get(i).getLicensePlate().equals(licensePlate))
                return i;
        }
        return i;
    }
}
