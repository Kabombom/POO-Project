package projectPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
    }

    public void createUser(Agency agency) throws IOException {
        ArrayList<User> users = agency.getUsers();
        int type;
        String name, nif, address, email, phone, password, typeInput;
        Scanner input = new Scanner(System.in);

        System.out.print("Client name: ");
        name = input.nextLine();

        System.out.print("Client NIF: ");
        nif = input.nextLine();

        while (checkIfNifExists(users, nif)) {
            System.out.print("NIF already exists, client nif: ");
            nif = input.nextLine();
        }

        System.out.print("Client address: ");
        address = input.nextLine();

        System.out.print("Client email: ");
        email = input.nextLine();
        while (checkIfEmailExists(users, email)) {
            System.out.print("Email already exists, client email: ");
            email = input.nextLine();
        }

        System.out.print("Client phone number:");
        phone = input.nextLine();

        System.out.print("Client password: ");
        password = input.nextLine();

        System.out.print("[1] --> Admin\n" +
                         "[2] --> Premium\n" +
                         "[3] --> Regular\n" +
                         "Client type: ");
        typeInput = input.nextLine();
        while (!typeSecurity(typeInput)) {
            System.out.print("Invalid type, ");
            typeInput = input.nextLine();
        }
        type = Integer.parseInt(typeInput);

        if (type == 1) {
            ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream("users"));
            Admin admin = new Admin(name, nif, address, email, phone, password, type);
            users.add(admin);
            agency.setUsers(users);
            agency.wObject(oS, users);
            oS.close();
            System.out.println("Operation sucefull");
        }
        else if (type == 2) {
            ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream("users"));
            Premium premium = new Premium(name, nif, address, email, phone, password, type);
            users.add(premium);
            agency.setUsers(users);
            agency.wObject(oS, users);
            oS.close();
            System.out.println("Operation sucefull");
        }
        else if (type == 3) {
            ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream("users"));
            Regular regular = new Regular(name, nif, address, email, phone, password, type);
            users.add(regular);
            agency.setUsers(users);
            agency.wObject(oS, users);
            oS.close();
            System.out.println("Operation sucefull");
        }
    }

    public void deleteUser(Agency agency) throws IOException {
        ArrayList<User> users = agency.getUsers();
        ArrayList<Trip> trips = agency.getTrips();
        int index, indexTrip;
        Scanner input = new Scanner(System.in);
        String nif;

        System.out.print("NIF of user to delete: ");
        nif = input.nextLine();
        while (!checkIfNifExists(users, nif)) {
            System.out.println("NIF  doesn't exist, NIF of user to delete:");
        }

        index = indexOfClient(users, nif);
        User user = users.get(index);

        ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream("users"));
        if (user.getType() == 2 || user.getType() == 3) {
            Client client = (Client) users.get(index);

            for (Reserve clientReserve : client.getClientReserves()) {
                Trip tripOfReserve = clientReserve.getTrip();
                indexTrip = indexOfTrip(trips, tripOfReserve.getCode());
                int tripCode = tripOfReserve.getCode();
                ArrayList<Reserve> tripReserves = trips.get(indexTrip).getReservesOfTrip();

                for (Reserve reserveTrip : tripReserves) {
                    int reserveTripCode = reserveTrip.getTrip().getCode();
                    String reserveClientNif = reserveTrip.getClient().getNif();

                    if (reserveTripCode == tripCode && reserveClientNif.equals(client.getNif()))
                        reserveTrip.setState(false);
                }

            }
            users.remove(index);
            agency.setUsers(users);
            agency.wObject(oS, users);
            System.out.println("Client sucefully removed");
        }
        else {
            users.remove(index);
            agency.setUsers(users);
            agency.wObject(oS, users);
            System.out.println("Client sucefully removed");
        }
        oS.close();
    }

    public void modifyUser(Agency agency) throws IOException {
        Scanner input = new Scanner(System.in);
        String strInput, name, nif, address, email, phone, password;
        int choice, type, index;
        ArrayList<User> users = agency.getUsers();


        System.out.print("NIF of client you want to modify: ");
        strInput = input.nextLine();
        while(!checkIfNifExists(users, strInput)) {
            System.out.print("Invalid Input, NIF of client you want to  modify: ");
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
        while (!optionsSecurity(strInput)) {
            System.out.print("Invalid input, info you want to modify: ");
            strInput = input.nextLine();
        }
        choice = Integer.parseInt(strInput);

        ObjectOutputStream oS;
        switch (choice) {
            case 0:
                System.out.print("Client's new name: ");
                name = input.nextLine();

                System.out.print("Client's new NIF: ");
                nif = input.nextLine();
                while (checkIfNifExists(users, nif)) {
                    System.out.print("Invalid input, NIF of client you want to  modify: ");
                    nif = input.nextLine();
                }

                System.out.print("Client's new address: ");
                address = input.nextLine();

                System.out.print("Client's new email: ");
                email = input.nextLine();
                while (checkIfEmailExists(users, email)) {
                    System.out.println("Email already exists, client email: ");
                    email = input.nextLine();
                }

                System.out.print("Client's new phone number:");
                phone = input.nextLine();

                System.out.print("Client's new password: ");
                password = input.nextLine();

                System.out.print("Client's new type: ");
                strInput = input.nextLine();
                while (!typeSecurity(strInput)) {
                    System.out.print("Invalid Input, client's type: ");
                    strInput = input.nextLine();
                }
                type = Integer.parseInt(strInput);
                users.get(index).setName(name);
                users.get(index).setNif(nif);
                users.get(index).setAddress(address);
                users.get(index).setEmail(email);
                users.get(index).setPhone(phone);
                users.get(index).setPassword(password);
                users.get(index).setType(type);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 1:
                System.out.print("Client's new name: ");
                name = input.nextLine();
                users.get(index).setName(name);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 2:
                System.out.print("Client's new NIF: ");
                nif = input.nextLine();
                while (checkIfNifExists(users, nif)) {
                    System.out.print("Invalid input, NIF of client you want to  modify: ");
                    nif = input.nextLine();
                }
                users.get(index).setNif(nif);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 3:
                System.out.print("Client's new address: ");
                address = input.nextLine();
                users.get(index).setAddress(address);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 4:
                System.out.print("Client's new email: ");
                email = input.nextLine();
                while (checkIfEmailExists(users, email)) {
                    System.out.println("Email already exists, client email: ");
                    email = input.nextLine();
                }
                users.get(index).setEmail(email);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 5:
                System.out.print("Client's new phone number:");
                phone = input.nextLine();
                users.get(index).setPhone(phone);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 6:
                System.out.print("Client's new password: ");
                password = input.nextLine();
                users.get(index).setPassword(password);
                agency.setUsers(users);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            case 7:
                System.out.print("Client's type: ");
                strInput = input.nextLine();
                while (!typeSecurity(strInput)) {
                    System.out.print("Invalid Input, client's type: ");
                    strInput = input.nextLine();
                }
                type = Integer.parseInt(strInput);
                users.get(index).setType(type);
                oS = new ObjectOutputStream(new FileOutputStream("users"));
                agency.wObject(oS, users);
                oS.close();
                break;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public void listUsers(Agency agency) {
        ArrayList<User> users = agency.getUsers();

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

    public void listTrips(Agency agency) {
        for (Trip trip : agency.getTrips()) {
            System.out.println(trip);
        }
    }

    public void createTrip(Agency agency) throws IOException {
        ArrayList<Trip> trips = agency.getTrips();
        ArrayList<Bus> buses = agency.getBuses();

        int code, year, month, day, hour, minute, numBuses;
        double price, duration;
        Scanner input = new Scanner(System.in);
        String strInput;

        System.out.print("Trip code: ");
        strInput = input.nextLine();
        while (!createTripCodeSecurity(trips ,strInput)) {
            System.out.print("Invalid input, trip code: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);

        System.out.print("Trip origin: ");
        String origin = input.nextLine();

        System.out.print("Trip destiny: ");
        String destiny = input.nextLine();

        System.out.print("Trip price: ");
        strInput = input.nextLine();
        while (!tripPriceSecurity(strInput)) {
            System.out.print("Invalid input, trip price: ");
            strInput = input.nextLine();
        }
        price = Double.parseDouble(strInput);

        System.out.print("Trip duration: ");
        strInput = input.nextLine();
        while (!tripPriceSecurity(strInput)) {
            System.out.print("Invalid input, trip duration: ");
            strInput = input.nextLine();
        }
        duration = Double.parseDouble(strInput);

        System.out.print("Trip new year: ");
        strInput = input.nextLine();
        while (!dateYearSecurity(strInput)) {
            System.out.print("Invalid input, trip year: ");
            strInput = input.nextLine();
        }
        year = Integer.parseInt(strInput);

        System.out.print("Trip new month: ");
        strInput = input.nextLine();
        while (!dateMonthSecurity(strInput, year)) {
            System.out.print("Invalid input, trip month: ");
            strInput = input.nextLine();
        }
        month = Integer.parseInt(strInput);

        System.out.print("Trip new day: ");
        strInput = input.nextLine();
        while (!dateDaySecurity(strInput, month, year)) {
            System.out.print("Invalid input, trip day: ");
            strInput = input.nextLine();
        }
        day = Integer.parseInt(strInput);

        System.out.print("Trip new hour: ");
        strInput = input.nextLine();
        while (!dateHourSecurity(strInput)) {
            System.out.print("Invalid input, trip hour: ");
            strInput = input.nextLine();
        }
        hour = Integer.parseInt(strInput);

        System.out.print("Trip new minute: ");
        strInput = input.nextLine();
        while (!dateMinuteSecurity(strInput)) {
            System.out.print("Invalid input, tripr minute: ");
            strInput = input.nextLine();
        }
        minute = Integer.parseInt(strInput);

        Date date = new Date(minute, hour, day, month, year);

        System.out.print("Number of buses used: ");
        strInput = input.nextLine();
        while (!generalSecurity(strInput)) {
            System.out.print("Invalid input, number of buses used: ");
            strInput = input.nextLine();
        }
        numBuses = Integer.parseInt(strInput);
        ArrayList<Bus> newTripBuses = new ArrayList<>(numBuses);

        for (int i = 0; i < numBuses; i++) {
            int choice, index;
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
                    listBuses(agency);
                    System.out.print("License plate of the bus: ");
                    strInput = input.nextLine();
                    while (!checkIfLicensePlateExists(buses, strInput)) {
                        System.out.print("License plate not found, license plate of the bus: ");
                        strInput = input.nextLine();
                    }
                    index = indexOfBus(buses, strInput);

                    newTripBuses.add(buses.get(index));
                    break;
                case 2:
                    Bus newBus = createBus(agency);
                    buses.add(newBus);
                    newTripBuses.add(newBus);
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }

        Trip trip = new Trip(code, origin, destiny, price, duration, date, newTripBuses);
        trips.add(trip);
        agency.setTrips(trips);
        ObjectOutputStream oST = new ObjectOutputStream(new FileOutputStream("trips"));
        ObjectOutputStream oSB = new ObjectOutputStream(new FileOutputStream("buses"));
        agency.wObject(oST, trips);
        oST.close();
        agency.wObject(oSB, buses);
        oSB.close();
        System.out.println("Operation sucefull");
    }

    public void deleteTrip(Agency agency) throws IOException {
        ArrayList<Trip> trips = agency.getTrips();
        int code, index;
        Scanner input = new Scanner(System.in);
        String strInput;


        listTrips(agency);
        System.out.print("Code of Trip to delete: ");
        strInput = input.nextLine();
        while (!acessTripCodeSecurity(trips, strInput)) {
            System.out.print("Invalid input, code of trip do delete: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);

        index = indexOfTrip(trips, code);

        ArrayList<Reserve> reserves = trips.get(index).getReservesOfTrip();

        for (Reserve reserve : reserves) {
            if (reserve.getState()) {
                System.out.println("Trips that have active reservations can't be deleted");
                return;
            }
        }
        trips.remove(index);
        agency.setTrips(trips);
        ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream("trips"));
        agency.wObject(oS, trips);
        oS.close();
    }

    public void modifyTrip(Agency agency) throws IOException {
        int code, index, minute, hour, day, month, year;
        double price, duration;
        String strInput, origin, destiny;
        Date date;
        Scanner input = new Scanner(System.in);
        ArrayList<Trip> trips = agency.getTrips();

        System.out.print("Code of trip to modify: ");
        strInput = input.nextLine();
        while(!acessTripCodeSecurity(trips, strInput)) {
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
                           "[6] --> Date\n");
        System.out.print("Info of the trip you want to modify: ");
        strInput = input.nextLine();
        while(!optionsSecurity(strInput)) {
            System.out.println("Invalid input, info of the trip you want to modify: ");
            strInput = input.nextLine();
        }
        int choice = Integer.parseInt(strInput);

        ObjectOutputStream oS;
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
                while (!dateYearSecurity(strInput)) {
                    System.out.print("Invalid input, trip new year: ");
                    strInput = input.nextLine();
                }
                year = Integer.parseInt(strInput);

                System.out.print("Trip new month: ");
                strInput = input.nextLine();
                while (!dateMonthSecurity(strInput, year)) {
                    System.out.print("Invalid input, trip new month: ");
                    strInput = input.nextLine();
                }
                month = Integer.parseInt(strInput);

                System.out.print("Trip new day: ");
                strInput = input.nextLine();
                while (!dateDaySecurity(strInput, month, year)) {
                    System.out.print("Invalid input, trip new day: ");
                    strInput = input.nextLine();
                }
                day = Integer.parseInt(strInput);

                System.out.print("Trip new hour: ");
                strInput = input.nextLine();
                while (!dateHourSecurity(strInput)) {
                    System.out.print("Invalid input, trip new hour: ");
                    strInput = input.nextLine();
                }
                hour = Integer.parseInt(strInput);

                System.out.print("Trip new minute: ");
                strInput = input.nextLine();
                while (!dateMinuteSecurity(strInput)) {
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
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
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
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
                break;
            case 2:
                System.out.print("Trip new origin: ");
                strInput = input.nextLine();
                trips.get(index).setOrigin(strInput);
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
                break;
            case 3:
                System.out.print("Trip new destiny: ");
                strInput = input.nextLine();
                trips.get(index).setDestiny(strInput);
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
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
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
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
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
                break;
            case 6:
                System.out.print("Trip new year: ");
                strInput = input.nextLine();
                while (!dateYearSecurity(strInput)) {
                    System.out.print("Invalid input, trip new year: ");
                    strInput = input.nextLine();
                }
                year = Integer.parseInt(strInput);
                System.out.print("Trip new month: ");
                strInput = input.nextLine();
                while (!dateMonthSecurity(strInput, year)) {
                    System.out.print("Invalid input, trip new month: ");
                    strInput = input.nextLine();
                }
                month = Integer.parseInt(strInput);

                System.out.print("Trip new day: ");
                strInput = input.nextLine();
                while (!dateDaySecurity(strInput, month, year)) {
                    System.out.print("Invalid input, trip new day: ");
                    strInput = input.nextLine();
                }
                day = Integer.parseInt(strInput);

                System.out.print("Trip new hour: ");
                strInput = input.nextLine();
                while (!dateHourSecurity(strInput)) {
                    System.out.print("Invalid input, trip new hour: ");
                    strInput = input.nextLine();
                }
                hour = Integer.parseInt(strInput);

                System.out.print("Trip new minute: ");
                strInput = input.nextLine();
                while (!dateMinuteSecurity(strInput)) {
                    System.out.print("Invalid input, trip new minute: ");
                    strInput = input.nextLine();
                }
                minute = Integer.parseInt(strInput);

                date = new Date(minute, hour, day, month, year);
                trips.get(index).setDate(date);
                agency.setTrips(trips);
                oS = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oS, trips);
                oS.close();
                break;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public Bus createBus(Agency agency) throws IOException{
        ArrayList<Bus> buses = agency.getBuses();
        Scanner input = new Scanner(System.in);
        String licensePlate, capacityInput;
        Bus bus;

        System.out.print("Bus license plate: ");
        licensePlate = input.nextLine();
        while (checkIfLicensePlateExists(buses, licensePlate)) {
            System.out.print("License plate already exists, bus license plate: ");
            licensePlate = input.nextLine();
        }

        System.out.print("Bus capacity: ");
        capacityInput = input.nextLine();
        while (!generalSecurity(capacityInput)) {
            System.out.print("Invalid input, bus capacity: ");
            capacityInput = input.nextLine();
        }
        int capacity = Integer.parseInt(capacityInput);

        bus = new Bus(licensePlate, capacity);
        return bus;
    }

    public void deleteBus(Agency agency) throws IOException {
        Scanner input = new Scanner(System.in);
        String licensePlate;
        ArrayList<Bus> buses = agency.getBuses();

        listBuses(agency);
        System.out.print("License plate of bus to delete: ");
        licensePlate = input.nextLine();
        while(!checkIfLicensePlateExists(buses, licensePlate)) {
            System.out.print("License plate not found, license plate of bus to modify: ");
            licensePlate = input.nextLine();
        }
        int index = indexOfBus(buses, licensePlate);

        Bus bus = buses.get(index);
        for (boolean seat : bus.getTakenSeats()) {
            if (seat) {
                System.out.println("Bus is reserved by a client");
                return;
            }
        }
        buses.remove(index);
        agency.setBuses(buses);
        ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream("buses"));
        agency.wObject(oS, buses);
        oS.close();
    }

    public void modifyBus(Agency agency) throws IOException {
        int choice, newCapacity, index;
        String strInput, newLicensePlate;
        Scanner input = new Scanner(System.in);
        ArrayList<Bus> buses = agency.getBuses();

        listBuses(agency);
        System.out.print("License plate of bus to modify: ");
        strInput = input.nextLine();
        while (!checkIfLicensePlateExists(buses, strInput)) {
            System.out.print("License plate not found, license plate of bus to modify: ");
            strInput = input.nextLine();
        }
        index = indexOfBus(buses, strInput);

        System.out.println("[0] --> All\n"          +
                           "[1] --> License Plate\n" +
                           "[2] --> Capacity");
        System.out.println("Which info of the bus do you want to modify: ");
        strInput = input.nextLine();
        while (!optionsSecurity(strInput)) {
            System.out.print("Invalid input, info you want to modify: ");
            strInput = input.nextLine();
        }
        choice = Integer.parseInt(strInput);

        ObjectOutputStream oS;
        switch (choice) {
            case 0:
                System.out.print("Bus new license plate: ");
                newLicensePlate = input.nextLine();
                System.out.print("Bus new capacity: ");
                strInput = input.nextLine();
                while (!generalSecurity(strInput)) {
                    System.out.print("Invalid input, bus capacity: ");
                    strInput = input.nextLine();
                }
                newCapacity = Integer.parseInt(strInput);
                buses.get(index).setLicensePlate(newLicensePlate);
                buses.get(index).setCapacity(newCapacity);
                agency.setBuses(buses);
                oS = new ObjectOutputStream(new FileOutputStream("buses"));
                agency.wObject(oS, buses);
                oS.close();
                break;
            case 1:
                System.out.print("Bus new license plate: ");
                newLicensePlate = input.nextLine();
                buses.get(index).setLicensePlate(newLicensePlate);
                agency.setBuses(buses);
                oS = new ObjectOutputStream(new FileOutputStream("buses"));
                agency.wObject(oS, buses);
                oS.close();
                return;
            case 2:
                System.out.print("Bus new capacity: ");
                strInput = input.nextLine();
                while (!generalSecurity(strInput)) {
                    System.out.print("Invalid input, bus capacity: ");
                    strInput = input.nextLine();
                }
                newCapacity = Integer.parseInt(strInput);
                buses.get(index).setCapacity(newCapacity);
                agency.setBuses(buses);
                oS = new ObjectOutputStream(new FileOutputStream("buses"));
                agency.wObject(oS, buses);
                oS.close();
                break;
            default:
                System.out.println("Invalid Operation");
        }
    }

    public void listBuses(Agency agency) {
        for (Bus bus : agency.getBuses()) {
            System.out.println(bus);
        }
    }

    public void mostSoldTripInMonth(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        int month;
        int mostSells = 0;
        String strInput;
        Scanner input = new Scanner(System.in);
        Trip mostSold = null;

        System.out.print("Month: ");
        strInput = input.nextLine();
        while (!monthSecurity(strInput)) {
            System.out.print("Invalid input, month:");
            strInput = input.nextLine();
        }
        month = Integer.parseInt(strInput) - 1;

        for (Trip trip : trips) {
            int salesOfMonth = trip.getSalesByMonth()[month];
            if (salesOfMonth > mostSells) {
                mostSells = salesOfMonth;
                mostSold = trip;
            }
        }
        if (mostSold != null)
            System.out.println("The most sold trip in the month " + month +
                               " was " + mostSold + " with " + mostSells + " trips sold");
    }

    public void clientMostTripsBoughtInMonth(Agency agency) {
        ArrayList<User> users = agency.getUsers();
        int month;
        int mostBought = 0;
        String strInput;
        Scanner input = new Scanner(System.in);
        Client mostTripsBought = null;

        System.out.print("Month: ");
        strInput = input.nextLine();
        while (!monthSecurity(strInput)) {
            System.out.print("Invalid input, month:");
            strInput = input.nextLine();
        }
        month = Integer.parseInt(strInput) - 1;

        for (User user : users) {
            if (user.getType() == 2 || user.getType() == 3) {
                Client client = (Client) user;
                int tripsBought = client.getTripsBoughtByMonth()[month];

                if (tripsBought > mostBought) {
                    mostTripsBought = client;
                    mostBought = tripsBought;
                }
            }
        }

        if (mostTripsBought != null)
            System.out.println("The client that most trips bought in the month " + month + " is " +
                               mostTripsBought + " with " + mostBought + " trips bought");
    }

    public void tripsNotSoldInMonth(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        int month;
        String strInput;
        Scanner input = new Scanner(System.in);

        System.out.print("Month: ");
        strInput = input.nextLine();
        while (!monthSecurity(strInput)) {
            System.out.print("Invalid input, month:");
            strInput = input.nextLine();
        }
        month = Integer.parseInt(strInput) - 1;

        for (Trip trip : trips) {
            if (trip.getSalesByMonth()[month] == 0)
                System.out.println(trip);
        }
    }

    public void listTripReserves(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        Trip trip;
        int code, index;
        String strInput;
        Scanner input = new Scanner(System.in);

        System.out.print("Code of trip to list reserves: ");
        strInput = input.nextLine();
        while (!acessTripCodeSecurity(trips, strInput)) {
            System.out.print("Invalid input, code of trip: to list reserves: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);

        index = indexOfTrip(trips, code);
        trip = trips.get(index);

        for (Reserve reserve : trip.getReservesOfTrip()) {
            if (reserve.getState())
                System.out.println(reserve);
        }
    }

    public void listTripCanceledReserves(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        Trip trip;
        int code, index;
        String strInput;
        Scanner input = new Scanner(System.in);

        System.out.print("Code of trip to list reserves: ");
        strInput = input.nextLine();
        while (!acessTripCodeSecurity(trips, strInput)) {
            System.out.print("Invalid input, code of trip: to list reserves: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);

        index = indexOfTrip(trips, code);
        trip = trips.get(index);

        for (Reserve reserve : trip.getReservesOfTrip()) {
            if (!reserve.getState())
                System.out.println(reserve);
        }
    }

    public void bestRatingOfTrips(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        int month , tripMonth;
        double bestRating = 0;
        Trip bestTrip = null;
        String strInput;
        Scanner input = new Scanner(System.in);
        File file = new File("results.txt");

        System.out.print("Month: ");
        strInput = input.nextLine();
        while (!monthSecurity(strInput)) {
            System.out.print("Invalid input, month: ");
            strInput = input.nextLine();
        }
        month = Integer.parseInt(strInput);

        for (Trip trip : trips) {
            tripMonth = trip.getDate().getMonth();

            if (month == tripMonth) {
                double tripRating = trip.averageRating();

                if (tripRating > bestRating) {
                    bestRating = tripRating;
                    bestTrip = trip;
                }
            }
        }

        if (bestTrip != null) {
            System.out.println("The trip with the best rating in the month " + month + " is" +
                    bestTrip + " with " + bestRating + " of rating");
            String line = Double.toString(bestRating);
            agency.writeOneLine(file, line);
        }

    }

    public void listWaitingClients(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        for (Trip trip : trips) {
            Bus firstBus = trip.getBuses().get(0);
            if (!checkIfTripFull(firstBus))
                continue;

            ArrayList<User> waitingList = trip.getWaitingList();
            System.out.println("Users in waiting list for " + trip + ":");
            for (User user : waitingList) {
                Client client = (Client) user;
                System.out.println(client);
            }
        }
    }

    public void listDayWithMostSells(Agency agency) {
        int[] stats = agency.getStats();
        System.out.println("There were mosts sells was in " + stats[0] + "/" + stats[1] + "/" + stats[2] + " with " + stats[3] + "trips sold");
    }
}
