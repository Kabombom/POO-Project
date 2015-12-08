package projectPackage;

import java.util.ArrayList;
import java.util.Calendar;

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

    public boolean generalSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean typeSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 3);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean createTripCodeSecurity(ArrayList<Trip> trips, String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || checkIfTripCodeExists(trips, code));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean acessTripCodeSecurity(ArrayList<Trip> trips, String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checkIfTripCodeExists(trips, code));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tripPriceSecurity(String strInput) {
        try {
            double price = Double.parseDouble(strInput);
            return !(price <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tripDurationSecurity(String strInput) {
        try {
            double duration = Double.parseDouble(strInput);
            return !(duration <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean dateMinuteSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 59);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean dateHourSecurity(String strInput) {
        try {
            int hour = Integer.parseInt(strInput);
            return !(hour <= 0 || hour > 23);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean dateDaySecurity(String strInput, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentYear = calendar.get(Calendar.DAY_OF_MONTH);
        try {
            int day = Integer.parseInt(strInput);
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                return !(day <= 0 || day > 31 || (currentYear == year && day <= currentDay));
            else if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11 )
                return  !((day <= 0 || day > 30) || (currentYear == year && day <= currentDay));
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public boolean monthSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !((input <= 0 || input > 12));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean dateMonthSecurity(String strInput, int year) {
        Calendar calendar = Calendar.getInstance();
        //Calendar month indexes like an array
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        try {
            int input = Integer.parseInt(strInput);
            return !((input <= 0 || input > 12) || (currentYear == year && input < currentMonth));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean dateYearSecurity(String strInput) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        try {
            int input = Integer.parseInt(strInput);
            return !(input < currentYear);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean seatReserveSecurity(Bus bus, String strInput) {
        try {
            int seatNumber = Integer.parseInt(strInput);
            return !(seatNumber <= 0 || seatNumber > bus.getCapacity() || bus.getTakenSeats()[seatNumber - 1]);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean ratingSecurity(String strInput) {
        try {
            double rating = Double.parseDouble(strInput);
            return !(rating <= 0 || rating > 5);
        } catch (NumberFormatException e) {
            return false;
        }
    }

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

    public boolean checkIfTripCodeExists(ArrayList<Trip> trips, int code) {
        for (Trip trip: trips) {
            if (trip.getCode() == code)
                return true;
        }
        return false;
    }

    public boolean checkIfTripFull(Bus bus) {
        for (boolean takenSeat : bus.getTakenSeats()) {
            if (!takenSeat) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfLicensePlateExists(ArrayList<Bus> buses, String strInput) {
        for (Bus bus : buses) {
            if (bus.getLicensePlate().equals(strInput))
                return true;
        }
        return false;
    }

    public int indexOfClient(ArrayList<User> users, String nif) {
        int i;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getNif().equals(nif))
                return i;
        }
        return i;
    }

    public int indexOfTrip(ArrayList<Trip> trips, int code) {
        int i;
        for (i = 0; i < trips.size(); i++) {
            if (trips.get(i).getCode() == code)
                return i;
        }
        return i;
    }

    public int indexOfBus(ArrayList<Bus> buses, String licensePlate) {
        int i;
        for (i = 0; i < buses.size(); i++) {
            if (buses.get(i).getLicensePlate().equals(licensePlate))
                return i;
        }
        return i;
    }

    public int compareDates(Calendar calendar, Date date) {
        int currentYear = calendar.get(Calendar.YEAR);
        //Months of class calendar are indexed array like
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int tripYear = date.getYear();
        int tripMonth = date.getMonth();
        int tripDay = date.getDay();

        int currentDate = currentYear * 10000 + currentMonth *  100 + currentDay;
        int tripDate = tripYear * 10000 + tripMonth *  100 + tripDay;

        return tripDate - currentDate;
    }
}
