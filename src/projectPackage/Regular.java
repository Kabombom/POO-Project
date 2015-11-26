package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Regular extends Client{

    public Regular(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password, type, clientReserves);
    }

    public void listAvaiableTrips(ArrayList<Trip> trips) {
        for (Trip trip: trips) {
            Bus bus = trip.getBuses().get(0);
            boolean[] takenSeats = bus.getTakenSeats();
            for (boolean takenSeat : takenSeats) {
                if (!takenSeat) {
                    System.out.println(trip);
                    break;
                }
            }
        }
    }

    public int seatReserveSecurity(Bus bus) {
        System.out.print("Seat number in the bus you prefer: ");
        Scanner input = new Scanner(System.in);
        int seatNumber = input.nextInt() - 1;
        if (!bus.getTakenSeats()[seatNumber]) {
            bus.addTakenSeat(seatNumber);
            System.out.println("Operation Sucefull");
            return seatNumber;
        }
        else {
            System.out.println("Seat was already taken");
            while (true) {
                System.out.print("Choose a different seat number: ");
                seatNumber = input.nextInt() - 1;
                if (!((bus.getTakenSeats())[seatNumber])) {
                    bus.addTakenSeat(seatNumber);
                    System.out.println("Operation Sucefull");
                    return seatNumber;
                }
            }
        }
    }

    public void reserveTrip(ArrayList<Trip> trips) {
        System.out.print("Trip to reserve(code): ");
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        for (Trip trip: trips) {
            if (trip.getCode() == code) {
                Bus firstBus = trip.getBuses().get(0);
                int seatNumber = seatReserveSecurity(firstBus);
                Reserve reserve = new Reserve(this, trip, seatNumber);
                this.clientReserves.add(reserve);
                break;
            }
        }
    }

    public void listReserves() {
        for (Reserve reserve : this.getClientReserves())
            System.out.println(reserve);
    }

    //TODO so preciso de verificar quando recebe reembolso ou nao
    public void cancelReserve() {
        System.out.print("Trip code of reserve to cancel: ");
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        ArrayList<Reserve> reserves = this.getClientReserves();
        for (Reserve reserve : reserves) {
            int tripCode = reserve.getTrip().getCode();
            if (tripCode == code) {
                int seatNumber = reserve.getSeatNumber();
                reserve.getTrip().getBuses().get(0).deleteTakenSeat(seatNumber);
                reserves.remove(reserve);
                System.out.println("Operation Successful");
                return;
            }
        }

    }

    //TODO Verificar se funciona quando o user da apenas enter
    public void addCommentTrip(Trip trip) {
        System.out.print("Rating you want to give the trip: ");
        Scanner input = new Scanner(System.in);
        double rating = input.nextDouble();
        System.out.print("If you want, add a comment: ");
        String comm = input.nextLine();
        Coment coment = new Coment(comm, rating);
        ArrayList<Coment> coments = trip.getComents();
        coments.add(coment);
        trip.setComents(coments);
    }

    public void listCommentsTrip(ArrayList<Coment> coments) {
        for (Coment coment : coments)
            System.out.println(coment);
    }
}
