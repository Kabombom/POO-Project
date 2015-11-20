package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;


//TODO Ver como a criaçao de cliente e feita.
//TODO Decidir o que os metodos recebem e devolvem.

public class Admin extends User {
    public Admin(String name, String nif, String address, String email, String phone, String password) {
        super(name, nif, address, email, phone, password);
    }

    public void createClient() {

    }
    public void deleteClient(ArrayList<Client> clients) {
        int i = 0;
        while(i < clients.size()) {
            i++;
            if (clients.get(i) ==  )
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
