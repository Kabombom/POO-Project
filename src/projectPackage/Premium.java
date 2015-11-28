package projectPackage;

import java.util.ArrayList;
import java.util.Scanner;

//TODO implementar o metodo payment que vai ser chamado no menu aquando da reserva

public class Premium extends Client {

    public Premium(String name, String nif, String address, String email, String phone, String password, int type, ArrayList<Reserve> clientReserves) {
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

    //TODO Listar os lugares disponiveis
    public boolean seatReserveSecurity(Bus bus, String strInput) {
        try {
            int seatNumber = Integer.parseInt(strInput);
            return !(seatNumber <= 0 || seatNumber > bus.getCapacity() || bus.getTakenSeats()[seatNumber]);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean reserveTripCodeSecurity(String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return !(code <= 0);

        } catch (NumberFormatException e) {
            return false;
        }

    }

    public void reserveTrip(ArrayList<Trip> trips) {
        Scanner input = new Scanner(System.in);
        System.out.print("Code of trip to reserve: ");
        String strInput = input.nextLine();
        while (!reserveTripCodeSecurity(strInput)) {
            System.out.print("Invalid input, code of trip to resereve: ");
            strInput = input.nextLine();
        }
        int tripCode = Integer.parseInt(strInput);
        for (Trip trip: trips) {
            if (trip.getCode() == tripCode) {
                Bus firstBus = trip.getBuses().get(0);
                System.out.print("Seat in the bus to reserve: ");
                strInput = input.nextLine();
                while(!seatReserveSecurity(firstBus, strInput)) {
                    System.out.print("Invalid input, seat number in the bus to reserve: ");
                    strInput = input.nextLine();
                }
                int seatNumber = Integer.parseInt(strInput);
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

    public boolean checksIfReserveCodeExists(int code, int[] codesOfTrips) {
        for (int codesOfTrip : codesOfTrips) {
            if (code == codesOfTrip)
                return true;
        }
        return false;
    }

    public boolean cancelReserveCodeSecurity(String strInput, int[] codesOfTrip) {
        try {
            int code = Integer.parseInt(strInput);
            return checksIfReserveCodeExists(code, codesOfTrip);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    //TODO so preciso de verificar quando recebe reembolso ou nao
    public void cancelReserve() {
        ArrayList<Reserve> reserves = this.getClientReserves();
        int[] codesOfTrip = new int[reserves.size()];
        for (int i = 0; i < reserves.size(); i++) {
            Reserve reserve = reserves.get(i);
            codesOfTrip[i] = reserve.getTrip().getCode();
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Trip code of the reserve to cancel: ");
        String strInput = input.nextLine();
        while(!cancelReserveCodeSecurity(strInput, codesOfTrip)) {
            System.out.println("Invalid input, trip code of the reserve to cancel: ");
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
}
