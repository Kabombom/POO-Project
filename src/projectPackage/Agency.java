package projectPackage;

import java.util.ArrayList;

public class Agency implements Menu {

    public static void main(String[] args) {
        //ATRIBUTOS DA AGENCIA
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Trip> trips = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();
        double profit;

        //DATA
        Date date = new Date(1 ,1 ,1 ,1 ,1);

        //COMENTARIOS
        ArrayList<Coment> coments = new ArrayList<>();

        //MUDAR OS AUTOCARROS NO CONSTRUTOR PORQUE O ARRAY TEM DE SER INICIALIZADO
        boolean[] takenSeats = new boolean[2];
        Bus bus = new Bus("1", 2);
        Bus bus2 = new Bus("2", 2);
        buses.add(bus);
        buses.add(bus2);

        //VIAGEM
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

        premium1.reserveTrip(trips);
        premium1.reserveTrip(trips);
        premium1.listReserves();
        premium1.cancelReserve();
        premium1.listReserves();
    }

}
