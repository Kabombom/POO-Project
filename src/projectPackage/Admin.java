package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

//permitir o admin dar delete ou alterar outros admins?

public class Admin extends User {

    public Admin(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
    }

    public boolean generalSecurity(String strInput) {
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

    public boolean checkIfNifExists(ArrayList<User> users, String nif) {
        for (User user : users) {
            if (user.getType() == 2 || user.getType() == 3) {
                if (user.getNif().equals(nif))
                    return true;
            }
        }
        return false;
    }

    public int indexOfClient(ArrayList<User> users, String nif) {
        int i;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getNif().equals(nif) && users.get(i).getType() != 1)
                return i;
        }
        return i;
    }

    public void modifyClient(ArrayList<User> users) {
        Scanner input = new Scanner(System.in);
        String strInput, name, nif, address, email, phone, password, typeInput;
        int type, index;

        System.out.print("NIF of client you want to modify: ");
        strInput = input.nextLine();
        while(!checkIfNifExists(users, strInput)) {
            System.out.print("Invalid input, NIF of client you want to  modify: ");
            strInput = input.nextLine();
        }
        index = indexOfClient(users, strInput);

        System.out.print("[0] --> All\n"            +
                         "[1] --> Name\n"           +
                         "[2] --> NIF\n"            +
                         "[3] --> Address\n"        +
                         "[4] --> Email\n"          +
                         "[5] --> PhoneNumber\n"    +
                         "[6] --> Password\n"       +
                         "[7] --> Type\n"           +
                         "Which info of the client do you want to modify: ");
        strInput = input.nextLine();
        while (!generalSecurity(strInput)) {
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
                users.get(index).setName(name);
                users.get(index).setNif(nif);
                users.get(index).setAddress(address);
                users.get(index).setEmail(email);
                users.get(index).setPhone(phone);
                users.get(index).setPassword(password);
                users.get(index).setType(type);
                break;
            case 1:
                System.out.print("Client's new name: ");
                name = input.nextLine();
                users.get(index).setName(name);
                break;
            case 2:
                System.out.print("Client's new NIF: ");
                nif = input.nextLine();
                users.get(index).setNif(nif);
                break;
            case 3:
                System.out.print("Client's new address: ");
                address = input.nextLine();
                users.get(index).setAddress(address);
                break;
            case 4:
                System.out.print("Client's new email: ");
                email = input.nextLine();
                users.get(index).setEmail(email);
                break;
            case 5:
                System.out.print("Client's new phone number:");
                phone = input.nextLine();
                users.get(index).setPhone(phone);
                break;
            case 6:
                System.out.print("Client's new password: ");
                password = input.nextLine();
                users.get(index).setPassword(password);
                break;
            case 7:
                System.out.print("Client's type: ");
                typeInput = input.nextLine();
                while (!typeSecurity(typeInput)) {
                    System.out.print("Invalid Input, client's type: ");
                    typeInput = input.nextLine();
                }
                type = Integer.parseInt(typeInput);
                users.get(index).setType(type);
                break;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public void listClients(ArrayList<User> users) {
        for (User user : users) {
            if (user.getType() == 2 || user.getType() == 3) {
                Client client = (Client) user;
                System.out.println(client);
            }
            else {
                System.out.println(user);
            }
        }
    }

    public boolean checkIfTripCodeExists(int code, ArrayList<Trip> trips) {
        for (Trip trip: trips) {
            if (trip.getCode() == code)
                return true;
        }
        return false;
    }

    public boolean tripCodeSecurity(String strInput, ArrayList<Trip> trips) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checkIfTripCodeExists(code, trips));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int indexOfTrip(ArrayList<Trip> trips, int code) {
        int i;
        for (i = 0; i < trips.size(); i++) {
            if (trips.get(i).getCode() == code)
                return i;
        }
        return i;
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

    public void listTrips(ArrayList<Trip> trips) {
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    public Trip createTrip(ArrayList<Bus> buses) {
        System.out.print("Trip code: ");
        Scanner input = new Scanner(System.in);
        String strInput = input.nextLine();
        while (!generalSecurity(strInput)) {
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
        while (!generalSecurity(numBusesInput)) {
            System.out.print("Invalid input, number of buses used: ");
            numBusesInput = input.nextLine();
        }
        int numBuses = Integer.parseInt(numBusesInput);
        ArrayList<Bus> newTripBuses = new ArrayList<>(numBuses);
        for (int i = 0; i < numBuses; i++) {
            int choice;
            System.out.print("[1] --> Add a existing bus to the trip\t" +
                             "[2] --> Create a new bus\n"               +
                             "What do you wish to do: ");
            strInput = input.nextLine();
            while(!generalSecurity(strInput)) {
                System.out.println("Invalid input, what do you wish to do: ");
                strInput = input.nextLine();
            }
            choice = Integer.parseInt(strInput);

            switch (choice) {
                case 1:
                    listBuses(buses);
                    System.out.print("License plate of the bus: ");
                    strInput = input.nextLine();
                    while (!licensePlateSecurity(buses, strInput)) {
                        System.out.print("Invalid input, license plate of the bus: ");
                        strInput = input.nextLine();
                    }
                    int index = indexOfBus(buses, strInput);
                    newTripBuses.add(buses.get(index));
                    break;
                case 2:
                    newTripBuses.add(createBus());
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
        ArrayList<Coment> coments = new ArrayList<>();
        Trip trip = new Trip(code, origin, destiny, price, duration, date, newTripBuses, coments);
        System.out.println(trip);
        System.out.println("Operation sucefull");
        return trip;
    }

    public void deleteTrip(ArrayList<Trip> trips) {
        listTrips(trips);
        System.out.print("Code of Trip to delete: ");
        Scanner input = new Scanner(System.in);
        String strInput = input.nextLine();
        while (!tripCodeSecurity(strInput, trips)) {
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
    public void modifyTrip(ArrayList<Trip> trips, ArrayList<Bus> buses) {
        int code, index, minute, hour, day, month, year;
        double price, duration;
        String strInput, origin, destiny;
        Date date;
        Scanner input = new Scanner(System.in);

        System.out.print("Code of trip to modify: ");
        strInput = input.nextLine();
        while(!tripCodeSecurity(strInput, trips)) {
            System.out.print("Invalid input, code of trip to modify: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);
        index = indexOfTrip(trips, code);

        System.out.println("[0] --> All\n"      +
                           "[1] --> Code\n"     +
                           "[2] --> Origin\n"   +
                           "[3] --> Destiny\n"  +
                           "[4] --> Price\n"    +
                           "[5] --> Duration\n" +
                           "[6] --> Date\n"     +
                           "[7] --> Buses\n");
        System.out.print("Info of the trip you want to modify: ");
        strInput = input.nextLine();
        while(!generalSecurity(strInput)) {
            System.out.println("Invalid input, info of the trip you want to modify: ");
            strInput = input.nextLine();
        }

        int choice = Integer.parseInt(strInput);
        switch (choice) {
            case 0:
                System.out.print("Trip new code: ");
                strInput = input.nextLine();
                while (!generalSecurity(strInput)) {
                    System.out.print("Invalid input, trip code: ");
                    strInput = input.nextLine();
                }
                code = Integer.parseInt(strInput);

                System.out.print("Trip new origin: ");
                origin = input.nextLine();

                System.out.print("Trip new destiny: ");
                destiny = input.nextLine();

                System.out.print("Trip new price: ");
                strInput = input.nextLine();
                while (!tripPriceSecurity(strInput)) {
                    System.out.print("Invalid input, trip price: ");
                    strInput = input.nextLine();
                }
                price = Double.parseDouble(strInput);

                System.out.print("Trip new duration:");
                strInput = input.nextLine();
                while (!tripDurationSecurity(strInput)) {
                    System.out.print("Invalid input, trip duration: ");
                    strInput = input.nextLine();
                }
                duration = Double.parseDouble(strInput);

                System.out.print("Trip new year: ");
                strInput = input.nextLine();
                while (!yearSecurity(strInput)) {
                    System.out.print("Invalid input, trip new year: ");
                    strInput = input.nextLine();
                }
                year = Integer.parseInt(strInput);

                System.out.print("Trip new month: ");
                strInput = input.nextLine();
                while (!monthSecurity(strInput)) {
                    System.out.print("Invalid input, trip new month: ");
                    strInput = input.nextLine();
                }
                month = Integer.parseInt(strInput);

                System.out.print("Trip new day: ");
                strInput = input.nextLine();
                while (!daySecurity(strInput, month)) {
                    System.out.print("Invalid input, trip new day: ");
                    strInput = input.nextLine();
                }
                day = Integer.parseInt(strInput);

                System.out.print("Trip new hour: ");
                strInput = input.nextLine();
                while (!hourSecurity(strInput)) {
                    System.out.print("Invalid input, trip new hour: ");
                    strInput = input.nextLine();
                }
                hour = Integer.parseInt(strInput);

                System.out.print("Trip new minute: ");
                strInput = input.nextLine();
                while (!minuteSecurity(strInput)) {
                    System.out.print("Invalid input, trip new minute: ");
                    strInput = input.nextLine();
                }
                minute = Integer.parseInt(strInput);

                date = new Date(minute, hour, day, month, year);
                trips.get(index).setCode(code);
                trips.get(index).setOrigin(origin);
                trips.get(index).setDestiny(destiny);
                trips.get(index).setPrice(price);
                trips.get(index).setDuration(duration);
                trips.get(index).setDate(date);
                break;
            case 1:
                System.out.print("Trip new code: ");
                strInput = input.nextLine();
                while (!generalSecurity(strInput)) {
                    System.out.print("Invalid input, trip new code: ");
                    strInput = input.nextLine();
                }
                code = Integer.parseInt(strInput);
                trips.get(index).setCode(code);
                break;
            case 2:
                System.out.print("Trip new origin: ");
                strInput = input.nextLine();
                trips.get(index).setOrigin(strInput);
                break;
            case 3:
                System.out.print("Trip new destiny: ");
                strInput = input.nextLine();
                trips.get(index).setDestiny(strInput);
                break;
            case 4:
                System.out.print("Trip new price: ");
                strInput = input.nextLine();
                while (!tripPriceSecurity(strInput)) {
                    System.out.print("Invalid input, trip price: ");
                    strInput = input.nextLine();
                }
                price = Double.parseDouble(strInput);
                trips.get(index).setPrice(price);
                break;
            case 5:
                System.out.print("Trip new duration:");
                strInput = input.nextLine();
                while (!tripDurationSecurity(strInput)) {
                    System.out.print("Invalid input, trip duration: ");
                    strInput = input.nextLine();
                }
                duration = Double.parseDouble(strInput);
                trips.get(index).setDuration(duration);
                break;
            case 6:
                System.out.print("Trip new year: ");
                strInput = input.nextLine();
                while (!yearSecurity(strInput)) {
                    System.out.print("Invalid input, trip new year: ");
                    strInput = input.nextLine();
                }
                year = Integer.parseInt(strInput);
                System.out.print("Trip new month: ");
                strInput = input.nextLine();
                while (!monthSecurity(strInput)) {
                    System.out.print("Invalid input, trip new month: ");
                    strInput = input.nextLine();
                }
                month = Integer.parseInt(strInput);

                System.out.print("Trip new day: ");
                strInput = input.nextLine();
                while (!daySecurity(strInput, month)) {
                    System.out.print("Invalid input, trip new day: ");
                    strInput = input.nextLine();
                }
                day = Integer.parseInt(strInput);

                System.out.print("Trip new hour: ");
                strInput = input.nextLine();
                while (!hourSecurity(strInput)) {
                    System.out.print("Invalid input, trip new hour: ");
                    strInput = input.nextLine();
                }
                hour = Integer.parseInt(strInput);

                System.out.print("Trip new minute: ");
                strInput = input.nextLine();
                while (!minuteSecurity(strInput)) {
                    System.out.print("Invalid input, trip new minute: ");
                    strInput = input.nextLine();
                }
                minute = Integer.parseInt(strInput);

                date = new Date(minute, hour, day, month, year);
                trips.get(index).setDate(date);
                break;
            case 7:
                System.out.print("License Plate of bus to modify: ");
                strInput = input.nextLine();
                for (Bus bus : trips.get(index).getBuses()) {
                    if (bus.getLicensePlate().equals(strInput)) {
                        modifyBus(buses);
                        break;
                    }
                }
                break;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public Bus createBus() {
        System.out.print("Bus license plate: ");
        Scanner input = new Scanner(System.in);
        String licensePlate = input.nextLine();
        System.out.print("Bus capacity: ");
        String capacityInput = input.nextLine();
        while (!generalSecurity(capacityInput)) {
            System.out.print("Invalid input, bus capacity: ");
            capacityInput = input.nextLine();
        }
        int capacity = Integer.parseInt(capacityInput);
        Bus bus = new Bus(licensePlate, capacity);
        return bus;
    }

    public int indexOfBus(ArrayList<Bus> buses, String licensePlate) {
        int i;
        for (i = 0; i < buses.size(); i++) {
            if (buses.get(i).getLicensePlate().equals(licensePlate))
                return i;
        }
        return i;
    }

    public boolean licensePlateSecurity(ArrayList<Bus> buses, String strInput) {
        for (Bus bus : buses) {
            if (bus.getLicensePlate().equals(strInput))
                return true;
        }
        return false;
    }

    public void deleteBus(ArrayList<Bus> buses) {
        Scanner input = new Scanner(System.in);
        String licensePlate;

        System.out.print("License Plate of bus to delete: ");
        licensePlate = input.nextLine();
        while(!licensePlateSecurity(buses, licensePlate)) {
            System.out.print("Invalid input, license plate of bus to modify: ");
            licensePlate = input.nextLine();
        }

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
        String strInput, newLicensePlate, capacityInput;
        int choice, newCapacity, index;
        listBuses(buses);

        System.out.print("License plate of bus to modify: ");
        strInput = input.nextLine();
        while (!licensePlateSecurity(buses, strInput)) {
            System.out.print("Invalid input, license plate of bus to modify: ");
            strInput = input.nextLine();
        }
        index = indexOfBus(buses, strInput);

        System.out.println("[0] --> All\n"          +
                           "[1] --> License Plate\n" +
                           "[2] --> Capacity");
        System.out.println("Which info of the bus do you want to modify: ");
        strInput = input.nextLine();
        while (!generalSecurity(strInput)) {
            System.out.print("Invalid input, info you want to modify: ");
            strInput = input.nextLine();
        }
        choice = Integer.parseInt(strInput);

        switch (choice) {
            case 0:
                System.out.print("Bus new license plate: ");
                newLicensePlate = input.nextLine();
                System.out.print("Bus new capacity: ");
                capacityInput = input.nextLine();
                while (!generalSecurity(capacityInput)) {
                    System.out.print("Invalid input, bus capacity: ");
                    capacityInput = input.nextLine();
                }
                newCapacity = Integer.parseInt(capacityInput);
                buses.get(index).setLicensePlate(newLicensePlate);
                buses.get(index).setCapacity(newCapacity);
                break;
            case 1:
                System.out.print("Bus new license plate: ");
                newLicensePlate = input.nextLine();
                buses.get(index).setLicensePlate(newLicensePlate);
                return;
            case 2:
                System.out.print("Bus new capacity: ");
                capacityInput = input.nextLine();
                while (!generalSecurity(capacityInput)) {
                    System.out.print("Invalid input, bus capacity: ");
                    capacityInput = input.nextLine();
                }
                newCapacity = Integer.parseInt(capacityInput);
                buses.get(index).setCapacity(newCapacity);
                break;
            default:
                System.out.println("Invalid Operation");
        }
    }

    public void listBuses(ArrayList<Bus> buses) {
        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }
}
