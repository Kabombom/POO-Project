package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

//TODO test

public class Premium extends Client {

    public Premium(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password, type, clientReserves);
    }

    //Ver se itera
    public void listAvaiableTrips(ArrayList<Trip> trips) {
        for (Trip trip: trips) {
            for (Bus bus : trip.getBuses()) {
                boolean[] takenSeats = bus.getTakenSeats();
                boolean toBreakLoop = false;
                for (boolean takenSeat : takenSeats) {
                    if (!takenSeat) {
                        System.out.println(trip);
                        toBreakLoop = true;
                        break;
                    }
                }
                if (toBreakLoop) {
                    break;
                }
            }
        }
    }

    public int seatReserveSecurity(ArrayList<Bus> buses) {
        System.out.print("Seat number in the bus you prefer: ");
        Scanner input = new Scanner(System.in);
        System.out.println();
        int seatNumber = input.nextInt();
        for (Bus bus : buses) {
            if (!((bus.getTakenSeats())[seatNumber])) {
                bus.setTakenSeats(seatNumber);
                System.out.println("Operation Sucefull");
                return seatNumber;
            }
            else {
                System.out.println("Seat was already taken");
                while (true) {
                    System.out.print("Input a different seat number: ");
                    seatNumber = input.nextInt();
                    if (!((bus.getTakenSeats())[seatNumber])) {
                        bus.setTakenSeats(seatNumber);
                        System.out.println("Operation Sucefull");
                        return seatNumber;
                    }
                }
            }
        }
        return seatNumber;
    }

    //TODO Quando se reserva pagar
    public void reserveTrip(ArrayList<Trip> trips) {
        System.out.print("Trip to reserve(code): ");
        System.out.println();
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        for (Trip trip: trips) {
            if (trip.getCode() == code) {
                int seatNumber = seatReserveSecurity(trip.getBuses());
                Reserve reserve = new Reserve(this, trip, seatNumber);
                this.clientReserves.add(reserve);
                break;
            }
        }
    }

    public void listReserves(ArrayList<Reserve> reserves) {
        for (Reserve reserve : reserves)
            System.out.println(reserve);
    }

    //TODO verificar quando recebe reembolso ou nao
    public void cancelReserve() {
        System.out.print("Trip code of reserve to cancel: ");
        System.out.println();
        Scanner input = new Scanner(System.in);
        int code = input.nextInt();
        ArrayList<Reserve> reserves = this.getClientReserves();
        for (Reserve reserve: reserves) {
            if (reserve.getTrip().getCode() == code) {
                reserves.remove(reserve.getTrip().getCode());
                System.out.println("Operation Successful");
                return;
            }
        }

    }

    //Verificar se funciona quando o user da apenas enter
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
