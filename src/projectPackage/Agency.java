package projectPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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
    private int[] currentDaySells = new int[2];
    private int[] dayWithMostSells = new int[2];

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

    public int[] getCurrentDaySells() { return currentDaySells; }
    public void setCurrentDaySells(int[] currentDaySells) { this.currentDaySells = currentDaySells; }

    public int[] getDayWithMostSells() { return dayWithMostSells; }
    public void setDayWithMostSells(int[] dayWithMostSells) { this.dayWithMostSells = dayWithMostSells; }



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

    public void daySecurity(Agency agency) {
        int[] currentDaySells = agency.getCurrentDaySells();
        int[] dayWithMostSells = agency.getDayWithMostSells();
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(currentDay);
        if (currentDaySells[0] == currentDay)
            currentDaySells[1]++;
        else {
            if (currentDaySells[1] > dayWithMostSells[1]) {
                dayWithMostSells[0] = currentDaySells[0];
                dayWithMostSells[1] = currentDaySells[1];
                currentDaySells[0] = currentDay;
                currentDaySells[1] = 0;
                agency.setCurrentDaySells(currentDaySells);
                agency.setDayWithMostSells(dayWithMostSells);
            } else {
                currentDaySells[0] = currentDay;
                currentDaySells[1] = 0;
                agency.setCurrentDaySells(currentDaySells);
            }
        }
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
                    "[1] --> Create Client\n" +
                    "[2] --> Modify Client\t" +
                    "[3] --> Delete Client\n" +
                    "[4] --> List Clients\t" +
                    "[5] --> Create Trip\n" +
                    "[6] --> Modify Trip\t\t" +
                    "[7] --> Delete Trip\n" +
                    "[8] --> List Trips\t\t" +
                    "[9] --> Create Bus\n" +
                    "[10] --> Modify Bus\t\t" +
                    "[11] --> Delete Bus\n" +
                    "[12] --> List Buses\t\t" +
                    "What do you wish to do: ");
            strInput = input.nextLine();
            while (!optionsSecurity(strInput)) {
                System.out.print("Invalid input, What do you wish to do:");
                strInput = input.nextLine();
            }
            choice = Integer.parseInt(strInput);

            ObjectOutputStream oSU = null;
            ObjectOutputStream oST = null;
            ObjectOutputStream oSB = null;
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
                    oSB = new ObjectOutputStream(new FileOutputStream("buses"));
                    buses.add(admin.createBus(agency));
                    agency.setBuses(buses);
                    admin.listBuses(agency);
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
                default:
                    System.out.println("Invalid operation");
                }
            }
    }

    public void clientMenu(Agency agency, Client client) throws IOException, ClassNotFoundException {
        String strInput;
        int choice;

        double profitToAdd = this.getProfit();
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
                    daySecurity(agency);
                    client.reserveTrip(agency);
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
                    daySecurity(agency);
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
        double profit = 0;
        Agency agencia = new Agency(users, trips, buses, profit);
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
        users.add(admin);
        agencia.setUsers(users);
        agencia.setTrips(trips);
        agencia.setBuses(buses);
        agencia.menu(agencia);
    }
}
