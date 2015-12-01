package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

//O cliente ao reservar se a trip nao estiver disponivel faz se a lista de espera, na segurança so preciso de verificar se o codigo existee

public class Premium extends Client {

    public Premium(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
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

    public boolean checkIfTripCodeExists(ArrayList<Trip> trips, int code) {
        for (Trip trip: trips) {
            if (trip.getCode() == code)
                return true;
        }
        return false;
    }

    public boolean tripCodeSecurity(ArrayList<Trip> trips, String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0 || !checkIfTripCodeExists(trips, code));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double reserveTrip(ArrayList<Trip> trips) {
        listAvaiableTrips(trips);
        Scanner input = new Scanner(System.in);
        System.out.print("Code of trip to reserve: ");
        String strInput = input.nextLine();
        while (!tripCodeSecurity(trips, strInput)) {
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
                return payment(trip);
            }
        }
        return 0;
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
            return checksIfReserveCodeExists(code, reserves);

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

        for (int i = 0; i < reserves.size(); i++) {
            Reserve reserve = reserves.get(i);
            int tripCode = reserve.getTrip().getCode();
            Bus firstBus = reserve.getTrip().getBuses().get(0);
            if (tripCode == code) {
                int seatNumber = reserve.getSeatNumber();
                firstBus.deleteTakenSeat(seatNumber);
                reserves.remove(i);
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

    public int indexOfTrip(ArrayList<Trip> trips, int code) {
        int i;
        for (i = 0; i < trips.size(); i++) {
            if (trips.get(i).getCode() == code)
                return i;
        }
        return i;
    }

    public void addCommentTrip(ArrayList<Trip> trips) {
        String strInput, comm;
        double rating;
        int code, index;
        Scanner input = new Scanner(System.in);

        System.out.print("Code of trip to rate and/or comment: ");
        strInput = input.nextLine();
        while (!tripCodeSecurity(trips, strInput)) {
            System.out.print("Invalid input, trip to rate and/or comment: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);
        index = indexOfTrip(trips, code);

        System.out.print("Rating you want to give the trip(1 to 5): ");
        strInput = input.nextLine();
        while (!ratingSecurity(strInput)) {
            System.out.print("Invalid input, rating of trip(1 to 5: ");
            strInput = input.nextLine();
        }
        rating = Double.parseDouble(strInput);
        System.out.print("If you want, add a comment: ");
        comm = input.nextLine();
        Coment coment = new Coment(comm, rating);
        ArrayList<Coment> coments = trips.get(index).getComents();
        coments.add(coment);
        trips.get(index).setComents(coments);
    }

    public void listCommentsTrip(ArrayList<Coment> coments) {
        for (Coment coment : coments)
            System.out.println(coment);
    }

    public double payment(Trip trip) {
        return (trip.getPrice() - (trip.getPrice() * 0.1));
    }
}
