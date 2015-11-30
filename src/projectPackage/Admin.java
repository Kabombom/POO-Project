package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

//TODO permitir o admin dar delete a outros admins?d

public class Admin extends User {

    public Admin(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
    }

    public boolean optionsSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //TODO Mudar o input do type
    public Client createClient() {
        Client client;
        System.out.print("Client name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.print("Client NIF: ");
        String nif = input.nextLine();
        System.out.print("Client address: ");
        String address = input.nextLine();
        System.out.print("Client email: ");
        String email = input.nextLine();
        System.out.print("Client phone number:");
        String phone = input.nextLine();
        System.out.print("Client password: ");
        String password = input.nextLine();
        System.out.println("Premium or Regular?");
        String type = input.nextLine();
        ArrayList<Reserve> reserves = new ArrayList<>();
        if (type.equals("Premium")){
            client = new Premium(name,nif, address, email, phone, password, 2, reserves);
        } else {
            client = new Regular(name,nif, address, email, phone, password, 3, reserves);
        }
        System.out.println("Operation sucefull");
        return  client;
    }

    public void deleteClient(ArrayList<User> users) {
        Scanner input = new Scanner(System.in);
        System.out.print("NIF of client to delete: ");
        String nif = input.nextLine();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNif().equals(nif)) {
                users.remove(i);
                System.out.println("Client sucefully removed");
                return;
            }
        }
        System.out.println("Client not found");
    }

    public boolean typeSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 3);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void modifyClient(Client client) {
        String name, nif, address, email, phone, password, typeInput;
        int type;
        System.out.println("[0] --> All\n"          +
                           "[1] --> Name\n"         +
                           "[2] --> NIF\n"          +
                           "[3] --> Address\n"      +
                           "[4] --> Email\n"        +
                           "[5] --> PhoneNumber\n"  +
                           "[6] --> Password\n"     +
                           "[7] --> Type\n");
        System.out.println("Which info of the client do you want to modify: ");
        Scanner input = new Scanner(System.in);
        String strInput = input.nextLine();
        while (!optionsSecurity(strInput)) {
            System.out.print("Invalid input, info you want to modify: ");
            strInput = input.nextLine();
        }
        int choice = Integer.parseInt(strInput);
        switch (choice) {
            case 0:
                System.out.print("Client's new name: ");
                name = input.nextLine();
                System.out.print("Client's NIF: ");
                nif = input.nextLine();
                System.out.print("Client's address: ");
                address = input.nextLine();
                System.out.print("Client's email: ");
                email = input.nextLine();
                System.out.print("Client's phone number:");
                phone = input.nextLine();
                System.out.print("Client's password: ");
                password = input.nextLine();
                System.out.print("Client's type: ");
                typeInput = input.nextLine();
                while (!typeSecurity(typeInput)) {
                    System.out.print("Invalid Input, client's type: ");
                    typeInput = input.nextLine();
                }
                type = Integer.parseInt(typeInput);
                client.setName(name);
                client.setNif(nif);
                client.setAddress(address);
                client.setEmail(email);
                client.setPhone(phone);
                client.setPassword(password);
                client.setType(type);
                return;
            case 1:
                System.out.print("Client's new name: ");
                name = input.nextLine();
                client.setName(name);
                return;
            case 2:
                System.out.print("Client's new NIF: ");
                nif = input.nextLine();
                client.setNif(nif);
                return;
            case 3:
                System.out.print("Client's new address: ");
                address = input.nextLine();
                client.setAddress(address);
                return;
            case 4:
                System.out.print("Client's new email: ");
                email = input.nextLine();
                client.setEmail(email);
                return;
            case 5:
                System.out.print("Client's new phone number:");
                phone = input.nextLine();
                client.setPhone(phone);
                return;
            case 6:
                System.out.print("Client's new password: ");
                password = input.nextLine();
                client.setPassword(password);
                return;
            case 7:
                System.out.print("Client's type: ");
                typeInput = input.nextLine();
                while (!typeSecurity(typeInput)) {
                    System.out.print("Invalid Input, client's type: ");
                    typeInput = input.nextLine();
                }
                type = Integer.parseInt(typeInput);
                client.setType(type);
                return;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public void listClients(ArrayList<User> users) {
        for (User user : users) {
            Client client = (Client) user;
            if (client.getType() == 2 || client.getType() == 3)
                System.out.println(client);
        }
    }

    public boolean checkIfTripCodeExists(int code, ArrayList<Trip> trips) {
        for (Trip trip: trips) {
            if (trip.getCode() == code)
                return true;
        }
        return false;
    }

    public boolean tripCodeSecurity(String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checkIfTripCodeExists(code, t));
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

    public boolean minuteSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 59);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean hourSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 23);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean daySecurity(String strInput, int month) {
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

    public boolean monthSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 0 || input > 12);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean yearSecurity(String strInput) {
        try {
            int input = Integer.parseInt(strInput);
            return !(input <= 2015);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Trip createTrip() {
        System.out.print("Trip code: ");
        Scanner input = new Scanner(System.in);
        String strInput = input.nextLine();
        while (!tripCodeSecurity(strInput)) {
            System.out.print("Invalid input, trip code: ");
            strInput = input.nextLine();
        }
        int code = Integer.parseInt(strInput);
        System.out.print("Trip origin: ");
        String origin = input.nextLine();
        System.out.print("Trip destiny: ");
        String destiny = input.nextLine();
        System.out.print("Trip price: ");
        String priceInput = input.nextLine();
        while (!tripPriceSecurity(priceInput)) {
            System.out.print("Invalid input, trip price: ");
            priceInput = input.nextLine();
        }
        double price = Double.parseDouble(priceInput);
        System.out.print("Trip duration: ");
        String durationInput = input.nextLine();
        while (!tripDurationSecurity(durationInput)) {
            System.out.print("Invalid input, trip duration: ");
            durationInput = input.nextLine();
        }
        double duration = Double.parseDouble(durationInput);
        System.out.print("Trip new year: ");
        String yearInput = input.nextLine();
        while (!yearSecurity(yearInput)) {
            System.out.print("Invalid input, trip new year: ");
            yearInput = input.nextLine();
        }
        int year = Integer.parseInt(yearInput);
        System.out.print("Trip new month: ");
        String monthInput = input.nextLine();
        while (!monthSecurity(monthInput)) {
            System.out.print("Invalid input, trip new month: ");
            monthInput = input.nextLine();
        }
        int month = Integer.parseInt(monthInput);
        System.out.print("Trip new day: ");
        String dayInput = input.nextLine();
        while (!daySecurity(dayInput, month)) {
            System.out.print("Invalid input, trip new day: ");
            dayInput = input.nextLine();
        }
        int day = Integer.parseInt(dayInput);
        System.out.print("Trip new hour: ");
        String hourInput = input.nextLine();
        while (!hourSecurity(hourInput)) {
            System.out.print("Invalid input, trip new hour: ");
            hourInput = input.nextLine();
        }
        int hour = Integer.parseInt(hourInput);
        System.out.print("Trip new minute: ");
        String minuteInput = input.nextLine();
        while (!minuteSecurity(minuteInput)) {
            System.out.print("Invalid input, trip new minute: ");
            minuteInput = input.nextLine();
        }
        int minute = Integer.parseInt(minuteInput);
        Date date = new Date(minute, hour, day, month, year);
        System.out.print("Number of buses used: ");
        String numBusesInput = input.nextLine();
        while (!optionsSecurity(numBusesInput)) {
            System.out.print("Invalid input, number of buses used: ");
            numBusesInput = input.nextLine();
        }
        int numBuses = Integer.parseInt(numBusesInput);
        ArrayList<Bus> buses = new ArrayList<>(numBuses);
        for (int i = 0; i < numBuses; i++) {
            buses.add(createBus());
        }
        ArrayList<Coment> coments = new ArrayList<>();
        Trip trip = new Trip(code, origin, destiny, price, duration, date, buses, coments);
        System.out.println("Operation sucefull");
        return trip;
    }

    public void deleteTrip(ArrayList<Trip> trips) {
        System.out.print("Code of Trip to delete: ");
        Scanner input = new Scanner(System.in);
        String strInput = input.nextLine();
        while (!tripCodeSecurity(strInput)) {
            System.out.print("Invalid input, code of trip do delete: ");
            strInput = input.nextLine();
        }
        int code = Integer.parseInt(strInput);
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getCode() == code) {
                trips.remove(i);
                System.out.println("Trip sucefully removed");
                return;
            }
        }
        System.out.println("Trip not found");
    }

    //Permite se alterar tambem os comentarios?
    //Mudar capacidade do autocarro?
    //TODO Permitir alterar apenas uma parte da data
    public void modifyTrip(Trip trip) {
        int code, minute, hour, day, month, year;
        double price, duration;
        String codeInput, priceInput, durationInput, origin, destiny, yearInput, monthInput, dayInput, hourInput, minuteInput;
        Date date;
        Scanner input = new Scanner(System.in);
        System.out.println("[0] --> All\n"      +
                           "[1] --> Code\n"     +
                           "[2] --> Origin\n"   +
                           "[3] --> Destiny\n"  +
                           "[4] --> Price\n"    +
                           "[5] --> Duration\n" +
                           "[6] --> Date\n"     +
                           "[7] --> Buses\n");
        System.out.print("Info of the trip you want to modify: ");
        String strInput = input.nextLine();
        while(!optionsSecurity(strInput)) {
            System.out.println("Invalid input, info of the trip you want to modify: ");
            strInput = input.nextLine();
        }
        int choice = Integer.parseInt(strInput);
        switch (choice) {
            case 0:
                System.out.print("Trip new code: ");
                codeInput = input.nextLine();
                while (!tripCodeSecurity(codeInput)) {
                    System.out.print("Invalid input, trip code: ");
                    codeInput = input.nextLine();
                }
                code = Integer.parseInt(codeInput);
                System.out.print("Trip new origin: ");
                origin = input.nextLine();
                System.out.print("Trip new destiny: ");
                destiny = input.nextLine();
                System.out.print("Trip new price: ");
                priceInput = input.nextLine();
                while (!tripPriceSecurity(priceInput)) {
                    System.out.print("Invalid input, trip price: ");
                    priceInput = input.nextLine();
                }
                price = Double.parseDouble(priceInput);
                System.out.print("Trip new duration:");
                durationInput = input.nextLine();
                while (!tripDurationSecurity(durationInput)) {
                    System.out.print("Invalid input, trip duration: ");
                    durationInput = input.nextLine();
                }
                duration = Double.parseDouble(durationInput);
                System.out.print("Trip new year: ");
                yearInput = input.nextLine();
                while (!yearSecurity(yearInput)) {
                    System.out.print("Invalid input, trip new year: ");
                    yearInput = input.nextLine();
                }
                year = Integer.parseInt(yearInput);
                System.out.print("Trip new month: ");
                monthInput = input.nextLine();
                while (!monthSecurity(monthInput)) {
                    System.out.print("Invalid input, trip new month: ");
                    monthInput = input.nextLine();
                }
                month = Integer.parseInt(monthInput);
                System.out.print("Trip new day: ");
                dayInput = input.nextLine();
                while (!daySecurity(dayInput, month)) {
                    System.out.print("Invalid input, trip new day: ");
                    dayInput = input.nextLine();
                }
                day = Integer.parseInt(dayInput);
                System.out.print("Trip new hour: ");
                hourInput = input.nextLine();
                while (!hourSecurity(hourInput)) {
                    System.out.print("Invalid input, trip new hour: ");
                    hourInput = input.nextLine();
                }
                hour = Integer.parseInt(hourInput);
                System.out.print("Trip new minute: ");
                minuteInput = input.nextLine();
                while (!minuteSecurity(minuteInput)) {
                    System.out.print("Invalid input, trip new minute: ");
                    minuteInput = input.nextLine();
                }
                minute = Integer.parseInt(minuteInput);
                date = new Date(minute, hour, day, month, year);
                trip.setCode(code);
                trip.setOrigin(origin);
                trip.setDestiny(destiny);
                trip.setPrice(price);
                trip.setDuration(duration);
                trip.setDate(date);
                return;
            case 1:
                System.out.print("Trip new code: ");
                codeInput = input.nextLine();
                code = input.nextInt();
                trip.setCode(code);
                return;
            case 2:
                System.out.print("Trip new origin: ");
                origin = input.nextLine();
                trip.setOrigin(origin);
                return;
            case 3:
                System.out.print("Trip new destiny: ");
                destiny = input.nextLine();
                trip.setDestiny(destiny);
                return;
            case 4:
                System.out.print("Trip new price: ");
                priceInput = input.nextLine();
                while (!tripPriceSecurity(priceInput)) {
                    System.out.print("Invalid input, trip price: ");
                    priceInput = input.nextLine();
                }
                price = Double.parseDouble(priceInput);
                trip.setPrice(price);
                return;
            case 5:
                System.out.print("Trip new duration:");
                durationInput = input.nextLine();
                while (!tripDurationSecurity(durationInput)) {
                    System.out.print("Invalid input, trip duration: ");
                    durationInput = input.nextLine();
                }
                duration = Double.parseDouble(durationInput);
                trip.setDuration(duration);
                return;
            case 6:
                System.out.print("Trip new year: ");
                yearInput = input.nextLine();
                while (!yearSecurity(yearInput)) {
                    System.out.print("Invalid input, trip new year: ");
                    yearInput = input.nextLine();
                }
                year = Integer.parseInt(yearInput);
                System.out.print("Trip new month: ");
                monthInput = input.nextLine();
                while (!monthSecurity(monthInput)) {
                    System.out.print("Invalid input, trip new month: ");
                    monthInput = input.nextLine();
                }
                month = Integer.parseInt(monthInput);
                System.out.print("Trip new day: ");
                dayInput = input.nextLine();
                while (!daySecurity(dayInput, month)) {
                    System.out.print("Invalid input, trip new day: ");
                    dayInput = input.nextLine();
                }
                day = Integer.parseInt(dayInput);
                System.out.print("Trip new hour: ");
                hourInput = input.nextLine();
                while (!hourSecurity(hourInput)) {
                    System.out.print("Invalid input, trip new hour: ");
                    hourInput = input.nextLine();
                }
                hour = Integer.parseInt(hourInput);
                System.out.print("Trip new minute: ");
                minuteInput = input.nextLine();
                while (!minuteSecurity(minuteInput)) {
                    System.out.print("Invalid input, trip new minute: ");
                    minuteInput = input.nextLine();
                }
                minute = Integer.parseInt(minuteInput);
                date = new Date(minute, hour, day, month, year);
                trip.setDate(date);
                return;
            case 7:
                System.out.print("License Plate of bus to modify: ");
                String licensePlate = input.nextLine();
                for (Bus bus : trip.getBuses()) {
                    if (bus.getLicensePlate().equals(licensePlate)) {
                        modifyBus(bus);
                        break;
                    }
                }
                return;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public void listTrips(ArrayList<Trip> trips) {
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    public Bus createBus() {
        System.out.print("Bus license plate: ");
        Scanner input = new Scanner(System.in);
        String licensePlate = input.nextLine();
        System.out.print("Bus capacity: ");
        String capacityInput = input.nextLine();
        while (!optionsSecurity(capacityInput)) {
            System.out.print("Invalid input, bus capacity: ");
            capacityInput = input.nextLine();
        }
        int capacity = Integer.parseInt(capacityInput);
        Bus bus = new Bus(licensePlate, capacity);
        return bus;
    }

    public void deleteBus(ArrayList<Bus> buses) {
        System.out.print("License Plate of bus to delete: ");
        Scanner input = new Scanner(System.in);
        String licensePlate = input.nextLine();
        for (int i = 0; i < buses.size(); i++) {
            if (buses.get(i).getLicensePlate().equals(licensePlate)) {
                buses.remove(i);
                System.out.println("Bus sucefully removed");
                return;
            }
        }
        System.out.println("Bus not found");
    }

    //Acrescenta a opçao de poder mudar os lugares ocupados?
    public void modifyBus(ArrayList<Bus> buses) {
        Scanner input = new Scanner(System.in);
        String strInput;
        System.out.print("License ");
        for (:
             ) {

        }
        String licensePlate, capacityInput;
        int capacity;
        System.out.println("[0] --> All\n"          +
                           "[1] --> License Plate\n" +
                           "[2] --> Capacity");
        System.out.println("Which info of the bus do you want to modify: ");
        strInput = input.nextLine();
        while (!optionsSecurity(strInput)) {
            System.out.print("Invalid input, info you want to modify: ");
            strInput = input.nextLine();
        }
        int choice = Integer.parseInt(strInput);
        switch (choice) {
            case 0:
                System.out.print("Bus new license plate: ");
                licensePlate = input.nextLine();
                System.out.print("Bus new capacity: ");
                capacityInput = input.nextLine();
                while (!optionsSecurity(capacityInput)) {
                    System.out.print("Invalid input, bus capacity: ");
                    capacityInput = input.nextLine();
                }
                capacity = Integer.parseInt(capacityInput);
                bus.setLicensePlate(licensePlate);
                bus.setCapacity(capacity);
                return;
            case 1:
                System.out.print("Bus new license plate: ");
                licensePlate = input.nextLine();
                bus.setLicensePlate(licensePlate);
                return;
            case 2:
                System.out.print("Bus new capacity: ");
                capacityInput = input.nextLine();
                while (!optionsSecurity(capacityInput)) {
                    System.out.print("Invalid input, bus capacity: ");
                    capacityInput = input.nextLine();
                }
                capacity = Integer.parseInt(capacityInput);
                bus.setCapacity(capacity);
                return;
            default:
                System.out.println("Invalid Operation");
        }
    }

    public void listBuses(ArrayList<Bus> buses) {
        for (Bus bus: buses) {
            System.out.println(bus);
        }
    }
}
