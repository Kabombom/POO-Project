package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;


//TODO Ver como a criaçao de cliente e feita.
//TODO Decidir o que os metodos recebem e devolvem.
//TODO Testar os inputs no createClient

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
        int i = 0;
        while(i < clients.size()) {
            i++;
            if (clients.get(i) == )
        }
    }
    public void modifyClient(Client client) {
        System.out.println("[0] --> All\n" +
                           "[1] --> Name\n" +
                           "[2] --> NIF\n" +
                           "[3] --> Address\n" +
                           "[4] --> Email\n" +
                           "[5] --> PhoneNumber\n" +
                           "[6] --> Password\n");
        System.out.println("Which info of the client do you want to modify: ");
        Scanner mInput = new Scanner(System.in);
        int choice = mInput.nextInt();
        switch (choice) {
            case 0:


        }
    }
    public void listClients(ArrayList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client);
        }
    }
    public void createTrip() {

    }
    public void deleteTrip() {

    }
    public void modifyTrip() {

    }
    public void listTrips() {

    }
    public void createBus() {

    }
    public void deleteBus() {

    }
    public void modifyBus() {

    }
    public void listBuses() {

    }

}
