package projectPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

//TODO ber o bestRatingOfTrips por causa dos ficheiros
//como notificar waiting list?
//verificar os autocarros ocupados como é quando acabar viagem. Meter seguranças de naquela data o autocarro estar ocupado por aquela viagem para o metodo criar trip do admin?
//TODO segurança do autocarro a criar viagem para nao haver conflito de datas
//TODO começar a implementar ficheiros
//TODO javadocs
//TODO relatorio

public class Agency implements Serializable, Ficheiro, Menu{
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Trip> trips = new ArrayList<>();
    private ArrayList<Bus> buses = new ArrayList<>();
    private double profit;
    private int[] stats;
    private int[] currentDaySells;

    public Agency(ArrayList<User> users, ArrayList<Trip> trips, ArrayList<Bus> buses, int[] currentDaySells) {
        this.users = users;
        this.trips = trips;
        this.buses = buses;
        this.profit = 0;
        this.stats = new int[4];
        this.currentDaySells = currentDaySells;
    }

    public ArrayList<User> getUsers() { return users; }
    public void setUsers(ArrayList<User> users) { this.users = users; }

    public ArrayList<Trip> getTrips() { return trips; }
    public void setTrips(ArrayList<Trip> trips) { this.trips = trips; }

    public ArrayList<Bus> getBuses() { return buses;}
    public void setBuses(ArrayList<Bus> buses) { this.buses = buses; }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }

    public int[] getCurrentDaySells() { return currentDaySells; }
    public void setCurrentDaySells(int[] currentDaySells) { this.currentDaySells = currentDaySells; }

    public int[] getStats() { return stats; }
    public void setStats(int[] stats) { this.stats = stats; }

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

    public boolean checkIfFileEmpty(String filename) throws  IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        return br.readLine() == null;
    }

    public Object rObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return inputStream.readObject();
    }

    public void wObject(ObjectOutputStream oS, Object obj) throws  IOException {
        oS.writeObject(obj);
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



    public User login(Agency agency) {
        ArrayList<User> users = agency.getUsers();

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to COSTA & MACHADO travel agency");
        System.out.println("To login press 1\nTo leave press 0");
        String choiceInput = input.nextLine();
        while (!choiceSecurity(choiceInput)) {
            System.out.print("Invalid input");
            choiceInput = input.nextLine();
        }
        int choice = Integer.parseInt(choiceInput);

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

    public void adminMenu(Agency agency, Admin admin) throws IOException, ClassNotFoundException {
        ArrayList<Bus> buses = agency.getBuses();
        String strInput;
        int choice;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("WELCOME TO THE ADMIN MENU\n" +
                    "[0] --> Exit\t\t\t" +
                    "[1] --> Create Client\n"   +
                    "[2] --> Modify Client\t"   +
                    "[3] --> Delete Client\n"   +
                    "[4] --> List Clients\t"    +
                    "[5] --> Create Trip\n"     +
                    "[6] --> Modify Trip\t\t"   +
                    "[7] --> Delete Trip\n"     +
                    "[8] --> List Trips\t\t"    +
                    "[9] --> Create Bus\n"      +
                    "[10] --> Modify Bus\t\t"   +
                    "[11] --> Delete Bus\n"     +
                    "[12] --> List Buses\t\t" +
                    "[13] --> Extras\n"   +
                    "What do you wish to do: ");
            strInput = input.nextLine();
            while (!optionsSecurity(strInput)) {
                System.out.print("Invalid input, What do you wish to do:");
                strInput = input.nextLine();
            }
            choice = Integer.parseInt(strInput);

            ObjectOutputStream oSU;
            ObjectOutputStream oST;
            ObjectOutputStream oSB;
            switch (choice) {
                case 0:
                    oSU = new ObjectOutputStream(new FileOutputStream("users"));
                    oST = new ObjectOutputStream(new FileOutputStream("trips"));
                    oSB = new ObjectOutputStream(new FileOutputStream("buses"));
                    agency.wObject(oSU, agency.getUsers());
                    oSU.close();
                    agency.wObject(oST, agency.getTrips());
                    oST.close();
                    agency.wObject(oSB, agency.getBuses());
                    oSB.close();
                    menu(agency);
                    return;
                case 1:
                    admin.createUser(agency);
                    admin.listUsers(agency);
                    System.out.println();
                    break;
                case 2:
                    admin.modifyUser(agency);
                    admin.listUsers(agency);
                    System.out.println();
                    break;
                case 3:
                    admin.deleteUser(agency);
                    admin.listTripCanceledReserves(agency);
                    admin.listTripReserves(agency);
                    System.out.println();
                    break;
                case 4:
                    admin.listUsers(agency);
                    System.out.println();
                    break;
                case 5:
                    admin.createTrip(agency);
                    admin.listTrips(agency);
                    System.out.println();
                    break;
                case 6:
                    admin.modifyTrip(agency);
                    admin.listTrips(agency);
                    System.out.println();
                    break;
                case 7:
                    admin.deleteTrip(agency);
                    admin.listTrips(agency);
                    System.out.println();
                    break;
                case 8:
                    admin.listTrips(agency);
                    System.out.println();
                    break;
                case 9:
                    buses.add(admin.createBus(agency));
                    agency.setBuses(buses);
                    admin.listBuses(agency);
                    oSB = new ObjectOutputStream(new FileOutputStream("buses"));
                    agency.wObject(oSB , buses);
                    oSB.close();
                    System.out.println();
                    break;
                case 10:
                    admin.modifyBus(agency);
                    admin.listBuses(agency);
                    System.out.println();
                    break;
                case 11:
                    admin.deleteBus(agency);
                    admin.listBuses(agency);
                    System.out.println();
                    break;
                case 12:
                    admin.listBuses(agency);
                    System.out.println();
                    break;
                case 13:
                    System.out.print("[0] --> Go back\t\t\t\t\t\t"                      +
                                       "[1] --> Client that most trips bought\n"        +
                                       "[2] --> Trip most sold in month\t\t"            +
                                       "[3] --> Trips not sold in a month\n"            +
                                       "[4] --> List reserves of trip\t\t"              +
                                       "[5] --> List canceled reserves of trip\n"       +
                                       "[6] --> Trip with best rating\t\t"              +
                                       "[7] --> List waiting lists of trips\n"          +
                                       "\t\t[8] --> List day with most trips sold\n"    +
                                       "What do you wish to do: ");
                    strInput = input.nextLine();
                    while (!optionsSecurity(strInput)) {
                        System.out.print("Invalid input, What do you wish to do:");
                        strInput = input.nextLine();
                    }
                    choice = Integer.parseInt(strInput);
                    switch (choice) {
                        case 0:
                            return;
                        case 1:
                            admin.clientMostTripsBoughtInMonth(agency);
                            break;
                        case 2:
                            admin.mostSoldTripInMonth(agency);
                        case 3:
                            admin.tripsNotSoldInMonth(agency);
                            break;
                        case 4:
                            admin.listTripReserves(agency);
                            break;
                        case 5:
                            admin.listTripCanceledReserves(agency);
                            break;
                        case 6:
                            admin.bestRatingOfTrips(agency);
                            break;
                        case 7:
                            admin.listWaitingClients(agency);
                            break;
                        case 8:
                            admin.listDayWithMostSells(agency);
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                default:
                    System.out.println("Invalid operation");
                }
            }
    }

    public void clientMenu(Agency agency, Client client) throws IOException, ClassNotFoundException {
        String strInput;
        int choice;

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("WELCOME TO THE CLIENT MENU\n" +
                    "[0] --> Exit\t\t\t\t\t\t\t\t\t" +
                    "[1] --> List Avaiable Trips\n" +
                    "[2] --> Reserve Trip\t\t\t\t\t\t\t" +
                    "[3] --> Cancel Reserve\n" +
                    "[4] --> List Your Reserves\t\t\t\t\t\t" +
                    "[5] --> Rate and/or comment trip\n" +
                    "[6] --> Reserve trip and leave waiting list\n" +
                    "What do you wish to do: ");
            strInput = input.nextLine();
            while (!optionsSecurity(strInput)) {
                System.out.print("Invalid input, What do you wish to do:");
                strInput = input.nextLine();
            }
            choice = Integer.parseInt(strInput);

            switch (choice) {
                case 0:
                    ObjectOutputStream oSU = new ObjectOutputStream(new FileOutputStream("users"));
                    ObjectOutputStream oST = new ObjectOutputStream(new FileOutputStream("trips"));
                    ObjectOutputStream oSB = new ObjectOutputStream(new FileOutputStream("buses"));
                    agency.wObject(oSU, agency.getUsers());
                    oSU.close();
                    agency.wObject(oST, agency.getTrips());
                    oST.close();
                    agency.wObject(oSB, agency.getBuses());
                    oSB.close();
                    menu(agency);
                    return;
                case 1:
                    client.listAvaiableTrips(agency);
                    System.out.println();
                    break;
                case 2:
                    client.reserveTrip(agency);
                    System.out.println(agency.getStats()[3]);
                    System.out.println();
                    break;
                case 3:
                    client.cancelReserve(agency);
                    System.out.println();
                    break;
                case 4:
                    client.listReserves();
                    System.out.println();
                    break;
                case 5:
                    client.addCommentTrip(agency);
                    System.out.println();
                    break;
                case 6:
                    client.leaveWaitingList(agency);
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
    }

    public void menu(Agency agency) throws IOException, ClassNotFoundException {
        ArrayList<User> users = agency.getUsers();
        ArrayList<Trip> trips = agency.getTrips();
        ArrayList<Bus> buses = agency.getBuses();
        Client client;
        Admin admin;
        User user = login(agency);

        if (user == null)
            return;

        try {
            if (user.getType() == 2 || user.getType() == 3) {
                client = (Client) user;
                while (true) {
                    clientMenu(agency, client);
                }
            } else {
                admin = (Admin) user;
                while (true) {
                    adminMenu(agency, admin);
                }
            }
        } catch (InterruptedIOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Trip> trips = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();

        //Get current date
        Calendar  rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int[] actualDate = new int[4];
        actualDate[0] = year;
        actualDate[1] = month;
        actualDate[2] = day;


        Agency agencia = new Agency(users, trips, buses, actualDate);
        Admin admin = new Admin("Machado", "1", "1", "mail", "32434", "isto", 1);

        if (!agencia.checkIfFileEmpty("users")) {
            ObjectInputStream iS = new ObjectInputStream(new FileInputStream("users"));
            try {
                users = (ArrayList<User>) agencia.rObject(iS);
                iS.close();
            } catch (EOFException e) {
                System.out.println(e.toString());
            }
        }
        if (!agencia.checkIfFileEmpty("trips")) {
            ObjectInputStream iS = new ObjectInputStream(new FileInputStream("trips"));
            try {
                trips = (ArrayList<Trip>) agencia.rObject(iS);
                iS.close();
            } catch (EOFException e) {
                e.printStackTrace();
            }
        }
        if (!agencia.checkIfFileEmpty("buses")) {
            ObjectInputStream iS = new ObjectInputStream(new FileInputStream("buses"));
            try {
                buses = (ArrayList<Bus>) agencia.rObject(iS);
                iS.close();
            } catch (EOFException e) {
                System.out.println(e.toString());
            }
        }

        if (!agencia.checkIfFileEmpty("stats.txt")) {
            File file = new File("stats.txt");
            String line = agencia.readLine(file);
            System.out.println("costa gay");
            String[] separatedLines = line.split(",");
            int[] stats = new int[4];
            for (int i = 0; i < separatedLines.length; i++) {
                stats[i] = Integer.parseInt(separatedLines[i]);
                System.out.println(stats[i]);
            }
            agencia.setStats(stats);
        } else {
           agencia.setStats(actualDate);
        }

        users.add(admin);
        agencia.setUsers(users);
        agencia.setTrips(trips);
        agencia.setBuses(buses);
        agencia.menu(agencia);
        admin.listDayWithMostSells(agencia);
    }


}