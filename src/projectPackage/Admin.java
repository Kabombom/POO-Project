package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

//TODO seguranças

public class Admin extends User {

    public Admin(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
    }

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

    public void deleteClient(ArrayList<Client> clients) {
        System.out.print("Name of client to delete: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.print("NIF of client to delete: ");
        String nif = input.nextLine();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getName().equals(name) && clients.get(i).getNif().equals(nif)) {
                clients.remove(i);
                System.out.println("Client sucefully removed");
                return;
            }
        }
        System.out.println("Client not found");
    }

    public void modifyClient(Client client) {
        String name, nif, address, email, phone, password;
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
        int choice = input.nextInt();
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
                type = input.nextInt();
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
                type = input.nextInt();
                client.setType(type);
                return;
            default:
                System.out.println("Invalid Operation");
        }
        System.out.println("Operation sucefull");
    }

    public void listClients(ArrayList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public Trip createTrip() {
        System.out.print("Trip code: ");
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        System.out.print("Trip origin: ");
        String origin = input.nextLine();
        System.out.print("Trip destiny: ");
        String destiny = input.nextLine();
        System.out.print("Trip price: ");
        Double price = input.nextDouble();
        System.out.print("Trip duration: ");
        Double duration = input.nextDouble();
        System.out.print("Trip minute: ");
        int minute = input.nextInt();
        System.out.print("Trip hour: ");
        int hour = input.nextInt();
        System.out.print("Trip day: ");
        int day = input.nextInt();
        System.out.print("Trip month: ");
        int month = input.nextInt();
        System.out.print("Trip year: ");
        int year = input.nextInt();
        Date date = new Date(minute, hour, day, month, year);
        System.out.print("Number of buses used: ");
        int numBuses = input.nextInt();
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
        int code = input.nextInt();
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
    public void modifyTrip(Trip trip) {
        int code, minute, hour, day, month, year;
        double price, duration;
        String origin, destiny;
        Date date;
        System.out.println("[0] --> All\n"      +
                           "[1] --> Code\n"     +
                           "[2] --> Origin\n"   +
                           "[3] --> Destiny\n"  +
                           "[4] --> Price\n"    +
                           "[5] --> Duration\n" +
                           "[6] --> Date\n"     +
                           "[7] --> Buses\n");
        System.out.println("Which info of the trip do you want to modify: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 0:
                System.out.print("Trip new code: ");
                code = input.nextInt();
                System.out.print("Trip new origin: ");
                origin = input.nextLine();
                System.out.print("Trip new destiny: ");
                destiny = input.nextLine();
                System.out.print("Trip new price: ");
                price = input.nextDouble();
                System.out.print("Trip new duration:");
                duration = input.nextDouble();
                System.out.println("Trip new minute: ");
                minute = input.nextInt();
                System.out.println("Trip new hour: ");
                hour = input.nextInt();
                System.out.println("Trip new day: ");
                day = input.nextInt();
                System.out.println("Trip new month: ");
                month = input.nextInt();
                System.out.println("Trip new year: ");
                year = input.nextInt();
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
                price = input.nextDouble();
                trip.setPrice(price);
                return;
            case 5:
                System.out.print("Trip new duration:");
                duration = input.nextDouble();
                trip.setDuration(duration);
                return;
            case 6:
                System.out.println("Trip new minute: ");
                minute = input.nextInt();
                System.out.println("Trip new hour: ");
                hour = input.nextInt();
                System.out.println("Trip new day: ");
                day = input.nextInt();
                System.out.println("Trip new month: ");
                month = input.nextInt();
                System.out.println("Trip new year: ");
                year = input.nextInt();
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
        int capacity = input.nextInt();
        System.out.println();
        boolean[] takenSeats = new boolean[capacity];
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
    public void modifyBus(Bus bus) {
        String licensePlate;
        int capacity;
        System.out.println("[0] --> All\n"          +
                           "[1] --> Licese Plate\n" +
                           "[2] --> Capacity");
        System.out.println("Which info of the bus do you want to modify: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 0:
                System.out.print("Bus new license plate: ");
                licensePlate = input.nextLine();
                System.out.print("Bus new capacity: ");
                capacity = input.nextInt();
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
                capacity = input.nextInt();
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
