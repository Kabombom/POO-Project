package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

//TODO test
//verificar o metodo de criaçao de uma data para a viagem. Meter varios inputs ou fazer assim?
//Ao alterar tudo da trip altera se o rating?

public class Admin extends User {
    public Admin(String name, String nif, String address, String email, String phone, String password) {
        super(name, nif, address, email, phone, password);
    }
    public void createClient(ArrayList<Client> clients) {
        System.out.print("Input client name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println();
        System.out.print("Input client NIF: ");
        String nif = input.nextLine();
        System.out.println();
        System.out.print("Input client address: ");
        String address = input.nextLine();
        System.out.println();
        System.out.print("Input client email: ");
        String email = input.nextLine();
        System.out.println();
        System.out.print("Input client phone number:");
        String phone = input.nextLine();
        System.out.println();
        System.out.print("Input client password: ");
        String password = input.nextLine();
        System.out.println();
        System.out.println("Premium or Regular?");
        String type = input.nextLine();
        if (type.equals("Premium")){
            Premium client = new Premium(name,nif, address, email, phone, password);
            clients.add(client);
        } else {
            Regular client = new Regular(name,nif, address, email, phone, password);
            clients.add(client);
        }

    }
    public void deleteClient(ArrayList<Client> clients) {
        System.out.print("Name of client to delete: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println();
        System.out.print("NIF of client to delete: ");
        String nif = input.nextLine();
        System.out.println();
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
        System.out.println("[0] --> All\n" +
                           "[1] --> Name\n" +
                           "[2] --> NIF\n" +
                           "[3] --> Address\n" +
                           "[4] --> Email\n" +
                           "[5] --> PhoneNumber\n" +
                           "[6] --> Password\n");
        System.out.println("Which info of the client do you want to modify: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 0:
                System.out.print("Input new name: ");
                name = input.nextLine();
                System.out.println();
                System.out.print("Input NIF: ");
                nif = input.nextLine();
                System.out.println();
                System.out.print("Input address: ");
                address = input.nextLine();
                System.out.println();
                System.out.print("Input email: ");
                email = input.nextLine();
                System.out.println();
                System.out.print("Input phone number:");
                phone = input.nextLine();
                System.out.println();
                System.out.print("Input password: ");
                password = input.nextLine();
                System.out.println();
                client.setName(name);
                client.setNif(nif);
                client.setAddress(address);
                client.setEmail(email);
                client.setPhone(phone);
                client.setPassword(password);
                return;
            case 1:
                System.out.print("Input new name: ");
                name = input.nextLine();
                System.out.println();
                client.setName(name);
                return;
            case 2:
                System.out.print("Input new NIF: ");
                nif = input.nextLine();
                System.out.println();
                client.setNif(nif);
                return;
            case 3:
                System.out.print("Input new address: ");
                address = input.nextLine();
                client.setAddress(address);
                return;
            case 4:
                System.out.print("Input new email: ");
                email = input.nextLine();
                System.out.println();
                client.setEmail(email);
                return;
            case 5:
                System.out.print("Input new phone number:");
                phone = input.nextLine();
                System.out.println();
                client.setPhone(phone);
                return;
            case 6:
                System.out.print("Input new password: ");
                password = input.nextLine();
                System.out.println();
                client.setPassword(password);
                return;
            default:
                System.out.println("Invalid Operation");

        }
    }
    public void listClients(ArrayList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client);
        }
    }
    public void createTrip(ArrayList<Trip> trips) {
        System.out.print("Input trip code: ");
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        System.out.println();
        System.out.print("Input trip origin: ");
        String origin = input.nextLine();
        System.out.println();
        System.out.print("Input trip destiny: ");
        String destiny = input.nextLine();
        System.out.println();
        System.out.print("Input trip price: ");
        Double price = input.nextDouble();
        System.out.println();
        System.out.print("Input trip duration: ");
        Double duration = input.nextDouble();
        System.out.println();
        System.out.print("Input trip rating: ");
        Double rating = input.nextDouble();
        System.out.println();
        System.out.println("Input trip minute: ");
        int minute = input.nextInt();
        System.out.println();
        System.out.println("Input trip hour: ");
        int hour = input.nextInt();
        System.out.println();
        System.out.println("Input trip day: ");
        int day = input.nextInt();
        System.out.println();
        System.out.println("Input trip month: ");
        int month = input.nextInt();
        System.out.println();
        System.out.println("Input trip year: ");
        int year = input.nextInt();
        System.out.println();
        Date date = new Date(minute, hour, day, month, year);
        Trip trip = new Trip(code, origin, destiny, price, duration, rating, date);
        trips.add(trip);
    }
    public void deleteTrip(ArrayList<Trip> trips) {
        System.out.print("Code of Trip to delete: ");
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        System.out.println();
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getCode() == code) {
                trips.remove(i);
                System.out.println("Trip sucefully removed");
                return;
            }
        }
        System.out.println("Trip not found");
    }
    public void modifyTrip(Trip trip) {
        int code, minute, hour, day, month, year;
        double price, duration, rating;
        String origin, destiny;
        Date date;
        System.out.println("[0] --> All\n" +
                           "[1] --> Code\n" +
                           "[2] --> Origin\n" +
                           "[3] --> Destiny\n" +
                           "[4] --> Price\n" +
                           "[5] --> Duration\n" +
                           "[6] --> Date\n");
        System.out.println("Which info of the trip do you want to modify: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 0:
                System.out.print("Input new code: ");
                code = input.nextInt();
                System.out.println();
                System.out.print("Input new origin: ");
                origin = input.nextLine();
                System.out.println();
                System.out.print("Input new destiny: ");
                destiny = input.nextLine();
                System.out.println();
                System.out.print("Input new price: ");
                price = input.nextDouble();
                System.out.println();
                System.out.print("Input new duration:");
                duration = input.nextDouble();
                System.out.println();
                System.out.println("Input new minute: ");
                minute = input.nextInt();
                System.out.println();
                System.out.println("Input new hour: ");
                hour = input.nextInt();
                System.out.println();
                System.out.println("Input new day: ");
                day = input.nextInt();
                System.out.println();
                System.out.println("Input new month: ");
                month = input.nextInt();
                System.out.println();
                System.out.println("Input new year: ");
                year = input.nextInt();
                System.out.println();
                date = new Date(minute, hour, day, month, year);
                trip.setCode(code);
                trip.setOrigin(origin);
                trip.setDestiny(destiny);
                trip.setPrice(price);
                trip.setDuration(duration);
                trip.setDate(date);
                return;
            case 1:
                System.out.print("Input new code: ");
                code = input.nextInt();
                System.out.println();
                trip.setCode(code);
                return;
            case 2:
                System.out.print("Input new origin: ");
                origin = input.nextLine();
                System.out.println();
                trip.setOrigin(origin);
                return;
            case 3:
                System.out.print("Input new destiny: ");
                destiny = input.nextLine();
                trip.setDestiny(destiny);
                return;
            case 4:
                System.out.print("Input new price: ");
                price = input.nextDouble();
                System.out.println();
                trip.setPrice(price);
                return;
            case 5:
                System.out.print("Input new duration:");
                duration = input.nextDouble();
                System.out.println();
                trip.setDuration(duration);
                return;
            case 6:
                System.out.println("Input new minute: ");
                minute = input.nextInt();
                System.out.println();
                System.out.println("Input new hour: ");
                hour = input.nextInt();
                System.out.println();
                System.out.println("Input new day: ");
                day = input.nextInt();
                System.out.println();
                System.out.println("Input new month: ");
                month = input.nextInt();
                System.out.println();
                System.out.println("Input new year: ");
                year = input.nextInt();
                System.out.println();
                date = new Date(minute, hour, day, month, year);
                trip.setDate(date);
                return;
            default:
                System.out.println("Invalid Operation");

        }
    }
    public void listTrips(ArrayList<Trip> trips) {
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }
    public void createBus() {
        System.out.print("Input bus license plate: ");
        Scanner input = new Scanner(System.in);
        String licensePlate = input.nextLine();
        System.out.println();
        System.out.print("Input bus capacity: ");
        int capacity = input.nextInt();
        System.out.println();
    }
    public void deleteBus(ArrayList<Bus> buses) {
        System.out.print("License Plate of bus to delete: ");
        Scanner input = new Scanner(System.in);
        String licensePlate = input.nextLine();
        System.out.println();
        for (int i = 0; i < buses.size(); i++) {
            if (buses.get(i).getLicensePlate().equals(licensePlate)) {
                buses.remove(i);
                System.out.println("Trip sucefully removed");
                return;
            }
        }
        System.out.println("Trip not found");
    }
    public void modifyBus(Bus bus) {
        String licensePlate;
        int capacity;
        System.out.println("[0] --> All\n" +
                           "[1] --> Licese Plate\n" +
                           "[2] --> Capacity\n");
        System.out.println("Which info of the bus do you want to modify: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 0:
                System.out.print("Input new license plate: ");
                licensePlate = input.nextLine();
                System.out.println();
                System.out.print("Input capacity: ");
                capacity = input.nextInt();
                System.out.println();
                bus.setLicensePlate(licensePlate);
                bus.setCapacity(capacity);
                return;
            case 1:
                System.out.print("Input new license plate: ");
                licensePlate = input.nextLine();
                System.out.println();
                bus.setLicensePlate(licensePlate);
                return;
            case 2:
                System.out.print("Input new capacity: ");
                capacity = input.nextInt();
                System.out.println();
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
