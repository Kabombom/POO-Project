package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Regular extends Client{

    public Regular(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
        super(name, nif, address, email, phone, password, type, clientReserves);
    }
    public void listAvaiableTrips(ArrayList<Trip> trips) {
        System.out.println("Avaiable trips:");
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

    public boolean seatReserveSecurity(Bus bus, String strInput) {
        try {
            int seatNumber = Integer.parseInt(strInput);
            return !(seatNumber <= 0 || seatNumber > bus.getCapacity() || bus.getTakenSeats()[seatNumber - 1]);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkIfTripCodeExists(int code, ArrayList<Trip> trips) {
        for (Trip trip: trips) {
            if (trip.getCode() == code)
                return true;
        }
        return false;
    }

    public boolean reserveTripCodeSecurity(String strInput, ArrayList<Trip> trips) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checkIfTripCodeExists(code, trips));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void reserveTrip(ArrayList<Trip> trips) {
        listAvaiableTrips(trips);
        Scanner input = new Scanner(System.in);
        System.out.print("Code of trip to reserve: ");
        String strInput = input.nextLine();
        while (!reserveTripCodeSecurity(strInput, trips)) {
            System.out.print("Invalid input, code of trip to reserve: ");
            strInput = input.nextLine();
        }
        int tripCode = Integer.parseInt(strInput);

        for (Trip trip: trips) {
            if (trip.getCode() == tripCode) {
                Bus firstBus = trip.getBuses().get(0);
                System.out.println("Seats avaiable in the bus: ");
                boolean[] takenSeats = firstBus.getTakenSeats();
                for (int i = 0; i < takenSeats.length; i++) {
                    if (!takenSeats[i])
                        System.out.println(i + 1);
                }

                System.out.print("Seat in the bus to reserve: ");
                strInput = input.nextLine();
                while(!seatReserveSecurity(firstBus, strInput)) {
                    System.out.print("Invalid input, seat number in the bus to reserve: ");
                    strInput = input.nextLine();
                }
                int seatNumber = Integer.parseInt(strInput) - 1;
                firstBus.addTakenSeat(seatNumber);
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

    public boolean checksIfReserveCodeExists(int code, ArrayList<Reserve> reserves) {
        for (Reserve reserve : reserves) {
            if (code == reserve.getTrip().getCode())
                return true;
        }
        return false;
    }

    public boolean cancelReserveCodeSecurity(String strInput, ArrayList<Reserve> reserves) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checksIfReserveCodeExists(code, reserves));

        } catch (NumberFormatException e) {
            return false;
        }
    }

    //TODO so preciso de verificar quando recebe reembolso ou nao
    public void cancelReserve() {
        this.listReserves();
        ArrayList<Reserve> reserves = this.getClientReserves();
        System.out.println("Code of trips you have reserved: ");
        for (Reserve reserve : reserves) {
            int code = reserve.getTrip().getCode();
            System.out.println(code);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Trip code of the reserve to cancel: ");
        String strInput = input.nextLine();
        while(!cancelReserveCodeSecurity(strInput, reserves)) {
            System.out.print("Invalid input, trip code of the reserve to cancel: ");
            strInput = input.nextLine();
        }
        int code = Integer.parseInt(strInput);

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

    public boolean ratingSecurity(String strInput) {
        try {
            double rating = Double.parseDouble(strInput);
            return !(rating <= 0 || rating > 5);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //TODO segurança de dar filter em certas palavras nos comentarios
    public void addCommentTrip(Trip trip) {
        Scanner input = new Scanner(System.in);
        System.out.print("Rating you want to give the trip(1 to 5): ");
        String strInput = input.nextLine();
        while (!ratingSecurity(strInput)) {
            System.out.print("Invalid input, rating of trip(1 to 5: ");
            strInput = input.nextLine();
        }
        double rating = Double.parseDouble(strInput);
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

    public double payment(Trip trip) {
        return trip.getPrice();
    }

}
