package projectPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//meter todas as seguranças no user?
//TODO METER SEGURANÇAS NOS INPUTS AO CRIAR CLIENT
//TODO Trips com mesmo codigo. Buses com mesma license plate.
//TODO METER NAS SEGURANÇAS UMA FORMA DE SAIR DOS INPUTS
//TODO pagamentos no cancelamento da reserva
//TODO meter mais linhas brancas entre menus
//TODO estatisticas
//TODO melhorar codigo, meter vars declaradas no inicio

//admins podem mudar admins? Pode fazer tudo
//verificar os autocarros ocupados como é quando acabar viagem. Meter seguranças de naquela data o autocarro estar ocupado por aquela viagem para o metodo criar trip do admin?

public class Agency implements Ficheiro, Menu{
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Trip> trips = new ArrayList<>();
    private ArrayList<Bus> buses = new ArrayList<>();
    private double profit;

    public Agency(ArrayList<User> users, ArrayList<Trip> trips, ArrayList<Bus> buses, double profit) {
        this.users = users;
        this.trips = trips;
        this.buses = buses;
        this.profit = profit;
    }

    public ArrayList<User> getUsers() { return users; }
    public void setUsers(ArrayList<User> users) { this.users = users; }

    public ArrayList<Trip> getTrips() { return trips; }
    public void setTrips(ArrayList<Trip> trips) { this.trips = trips; }

    public ArrayList<Bus> getBuses() { return buses;}
    public void setBuses(ArrayList<Bus> buses) { this.buses = buses; }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }



    public void writeOneLine(File toWrite, String line) {
        try	{
            PrintWriter writer = new PrintWriter(new FileWriter(toWrite));
            writer.println(line);
            System.out.println(line);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        }
        catch (IOException e)	{
            System.out.println("General I/O Exception " + e.getMessage());
        }
    }

    public String readLine(File toRead) {
        String line = "";
        try	{
            BufferedReader reader = new BufferedReader(new FileReader(toRead));
            if ((line = reader.readLine()) != null)
                return line;
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        }
        catch (IOException e)	{
            System.out.println("General I/O Exception " + e.getMessage());
        }
        return  line;
    }

    public Object rObject(ObjectInputStream inputStream) {
        Object toReturn = null;
        try {
            toReturn = inputStream.readObject();
            return toReturn;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch (IOException e) {
            System.out.println("General I/O Exception " + e.getMessage());
        }
        return toReturn;
    }

    public void wObject(ObjectOutputStream outputStream, Object obj) {
        try {
            outputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println("General I/O Exception " + e.getMessage());
        }
    }

    public boolean choiceSecurity(String choiceInput) {
        try {
            int choice = Integer.parseInt(choiceInput);
            return !(choice < 0 || choice > 1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public User checkIfUserExists(ArrayList<User> users, String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public User login(ArrayList<User> users) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to COSTA & MACHADO travel agency");
        System.out.println("To login press 1\nTo leave press 0");
        String choiceInput = input.nextLine();
        while (!choiceSecurity(choiceInput)) {
            System.out.print("Invalid input");
            choiceInput = input.nextLine();
        }
        int choice = Integer.parseInt(choiceInput);
        if (choice == 0)
            return null;

        switch (choice) {
            case 0:
                return null;
            case 1:
                System.out.print("EMAIL: ");
                String email = input.nextLine();
                System.out.print("PASSWORD: ");
                String password = input.nextLine();
                while(checkIfUserExists(users, email, password) == null) {
                    System.out.println("User email or password invalid");
                    System.out.print("EMAIL: ");
                    email = input.nextLine();
                    System.out.print("PASSWORD: ");
                    password = input.nextLine();
                }
                return checkIfUserExists(users, email, password);
         }
        return null;
    }

    public boolean optionsSecurity(String  strInput) {
        try {
            int choice = Integer.parseInt(strInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void adminMenu(ArrayList<User> users, ArrayList<Trip> trips, ArrayList<Bus> buses, Admin admin) {
        String strInput;
        int choice;
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.print("WELCOME TO THE ADMIN MENU\n"  +
                    "[0] --> Exit\t\t\t"           +
                    "[1] --> Create Client\n"      +
                    "[2] --> Modify Client\t"      +
                    "[3] --> Delete Client\n"      +
                    "[4] --> List Clients\t"       +
                    "[5] --> Create Trip\n"        +
                    "[6] --> Modify Trip\t\t"      +
                    "[7] --> Delete Trip\n"        +
                    "[8] --> List Trips\t\t"       +
                    "[9] --> Create Bus\n"         +
                    "[10] --> Modify Bus\t\t"      +
                    "[11] --> Delete Bus\n"        +
                    "\t\t[12] --> List Buses\n"    +
                    "What do you wish to do: ");
            strInput = input.nextLine();
            while(!optionsSecurity(strInput)) {
                System.out.print("Invalid input, What do you wish to do:");
                strInput = input.nextLine();
            }
            choice = Integer.parseInt(strInput);

            switch (choice) {
                case 0:
                    return;
                case 1:
                    users.add(admin.createClient());
                    admin.listClients(users);
                    break;
                case 2:
                    admin.modifyClient(users);
                    admin.listClients(users);
                    break;
                case 3:
                    admin.deleteClient(users);
                    admin.listClients(users);
                    break;
                case 4:
                    admin.listClients(users);
                    break;
                case 5:
                    trips.add(admin.createTrip(trips, buses));
                    admin.listTrips(trips);
                    break;
                case 6:
                    admin.modifyTrip(trips, buses);
                    admin.listTrips(trips);
                    break;
                case 7:
                    admin.deleteTrip(trips);
                    admin.listTrips(trips);
                    break;
                case 8:
                    admin.listTrips(trips);
                    break;
                case 9:
                    buses.add(admin.createBus());
                    admin.listBuses(buses);
                    break;
                case 10:
                    admin.modifyBus(buses);
                    admin.listBuses(buses);
                    break;
                case 11:
                    admin.deleteBus(buses);
                    admin.listBuses(buses);
                    break;
                case 12:
                    admin.listBuses(buses);
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
    }

    public void clientMenu(ArrayList<Trip> trips, Client client) {
        String strInput;
        int choice;
        double profitToAdd = this.getProfit();
        Scanner input = new Scanner(System.in);
        Trip tripWaitingList;

        while (true) {
            System.out.print("WELCOME TO THE CLIENT MENU\n"                +
                    "[0] --> Leave Menu\t\t\t\t"                           +
                    "[1] --> List Avaiable Trips\n"                        +
                    "[2] --> Reserve Trip\t\t\t"                           +
                    "[3] --> Cancel Reserve\n"                             +
                    "[4] --> List Your Reserves\t\t"                       +
                    "[5] --> Rate and/or comment trip\n"                   +
                    "\t\t[6] --> Reserve trip and leave waiting list\n"    +
                    "What do you wish to do: ");
            strInput = input.nextLine();
            while(!optionsSecurity(strInput)) {
                System.out.print("Invalid input, What do you wish to do:");
                strInput = input.nextLine();
            }
            choice = Integer.parseInt(strInput);

            switch (choice) {
                case 0:
                    return;
                case 1:
                    client.listAvaiableTrips(trips);
                    break;
                case 2:
                    profitToAdd += client.reserveTrip(trips);
                    this.setProfit(profitToAdd);
                    break;
                case 3:
                    client.cancelReserve();
                    break;
                case 4:
                    client.listReserves();
                    break;
                case 5:
                    client.addCommentTrip(trips);
                    break;
                case 6:
                    client.leaveWaitingList(trips);
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
    }

    public void menu(ArrayList<User> users, ArrayList<Trip> trips, ArrayList<Bus> buses) {
        Client client;
        Admin admin;
        User user = login(users);

        if (user.getType() == 2 || user.getType() == 3) {
            client = (Client) user;
            while(true) {
                clientMenu(trips, client);
            }
        }
        else {
            admin = (Admin) user;
            while (true) {
                adminMenu(users, trips, buses, admin);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Trip> trips = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();
        double profit = 0;
        Agency agencia = new Agency(users, trips, buses, profit);

        Date date = new Date(1 ,1 ,1 ,1 ,1);
        ArrayList<Coment> coments = new ArrayList<>();
        Bus bus = new Bus("1", 2);
        Bus bus2 = new Bus("2", 2);
        buses.add(bus);
        buses.add(bus2);
        Trip trip = new Trip(1, "oi", "adeus", 1, 1, date, buses, coments);
        trips.add(trip);
        ArrayList<Reserve> reserves = new ArrayList<>();
        ArrayList<Reserve> reserves2 = new ArrayList<>();
        ArrayList<Reserve> reserves3 = new ArrayList<>();
        ArrayList<Reserve> reserves4 = new ArrayList<>();
        ArrayList<Reserve> reserves5 = new ArrayList<>();
        ArrayList<Reserve> reserves6 = new ArrayList<>();
        ArrayList<Reserve> reserves7 = new ArrayList<>();
        ArrayList<Reserve> reserves8 = new ArrayList<>();
        Admin admin = new Admin("Machado", "1", "1", "mail", "32434", "isto", 1);
        Premium premium1 = new Premium("Machado1", "2", "2", "mail2", "324342", "isto2", 2, reserves);
        Premium premium2 = new Premium("Machado2", "3", "3", "mail3", "324343", "isto3", 2, reserves2);
        Premium premium3 = new Premium("Machado3", "4", "4", "mail4", "324344", "isto4", 2, reserves3);
        Premium premium4 = new Premium("Machad4", "5", "5", "mail5", "324345", "isto5", 2, reserves4);
        Premium premium5 = new Premium("Machado5", "6", "6", "mail6", "324346", "isto6", 2, reserves5);
        Premium premium6 = new Premium("Machado6", "7", "7", "mail7", "324347", "isto7", 2, reserves6);
        Premium premium7 = new Premium("Machado7", "8", "8", "mail8", "324348", "isto8", 2, reserves7);
        Premium premium8 = new Premium("Machado8", "9", "9", "mail9", "324349", "isto9", 2, reserves8);

        users.add(admin);
        users.add(premium1);
        users.add(premium2);
        users.add(premium3);
        users.add(premium4);
        users.add(premium5);
        users.add(premium6);
        users.add(premium7);
        users.add(premium8);
        agencia.menu(users, trips, buses);
    }
}
