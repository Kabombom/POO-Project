package projectPackage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

//O cliente ao reservar se a trip nao estiver disponivel faz se a lista de espera, na segurança so preciso de verificar se o codigo existee

public class Premium extends Client {

    public Premium(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
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

    public double reserveTrip(ArrayList<Trip> trips) {
        Scanner input = new Scanner(System.in);
        String strInput;
        int tripCode, choice;
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);

        listAvaiableTrips(trips);
        System.out.print("Code of trip to reserve: ");
        strInput = input.nextLine();
        while (!acessTripCodeSecurity(trips, strInput)) {
            System.out.print("Invalid input, code of trip to reserve: ");
            strInput = input.nextLine();
        }
        tripCode = Integer.parseInt(strInput);


        for (Trip trip: trips) {
            if (trip.getCode() == tripCode) {
                if (checkIfTripFull(trip.getBuses().get(0))) {
                    System.out.print("The trip you want to reserve is full\n"   +
                                     "[0] --> Leave\t\t"                        +
                                     "[1] --> Be put on a waiting list\n"       +
                                     "What do you wish to do: ");
                    strInput = input.nextLine();
                    while (!optionsSecurity(strInput)) {
                        System.out.print("Invalid input, do you want to be put on waiting list: ");
                        strInput = input.nextLine();
                    }
                    choice = Integer.parseInt(strInput);

                    switch (choice) {
                        case 0:
                            return 0;
                        case 1:
                            trip.getWaitingList().add(this);
                            System.out.print("Operation Sucefull");
                            return 0;
                        default:
                            System.out.println("Invalid operation");
                    }
                }

                Bus firstBus = trip.getBuses().get(0);
                System.out.println("Seats avaiable in the bus: ");
                boolean[] takenSeats = firstBus.getTakenSeats();
                for (int i = 0; i < takenSeats.length; i++) {
                    if (!takenSeats[i])
                        System.out.println("Seat number: " + (i + 1));
                }

                System.out.print("Seat in the bus to reserve: ");
                strInput = input.nextLine();
                while(!seatReserveSecurity(firstBus, strInput)) {
                    System.out.print("Invalid input, seat number in the bus to reserve: ");
                    strInput = input.nextLine();
                }
                int seatNumber = Integer.parseInt(strInput) - 1;
                firstBus.addTakenSeat(seatNumber);

                trip.getSalesByMonth()[currentMonth]++;
                this.getTripsBoughtByMonth()[currentMonth]++;

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

    public double cancelReserve() {
        int code, tripCode, differenceOfDates;
        double profit;
        //Getting current date
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);

        String strInput;
        Scanner input = new Scanner(System.in);
        Reserve reserve;
        Trip trip;
        Bus firstBus;
        ArrayList<Reserve> reserves = this.getClientReserves();
        ArrayList<User> waitingList;

        this.listReserves();
        System.out.print("Trip code of the reserve to cancel: ");
        strInput = input.nextLine();
        while(!cancelReserveCodeSecurity(strInput, reserves)) {
            System.out.print("Invalid input, trip code of the reserve to cancel: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);

        for (int i = 0; i < reserves.size(); i++) {
            reserve = reserves.get(i);
            trip = reserve.getTrip();
            tripCode = trip.getCode();

            if (tripCode == code) {
                firstBus = trip.getBuses().get(0);
                waitingList = trip.getWaitingList();
                differenceOfDates = compareDates(calendar, trip.getDate());

                if (differenceOfDates < 2) {
                    profit = payment(trip);
                } else {
                    profit = 0;
                }

                if (checkIfTripFull(firstBus)) {
                    for (User user : waitingList)
                        reserve.getTrip().notifyWaitingList();
                }

                trip.getSalesByMonth()[currentMonth]--;
                this.getTripsBoughtByMonth()[currentMonth]--;

                firstBus.deleteTakenSeat(reserve.getSeatNumber());
                reserves.remove(i);
                System.out.println("Operation Successful");
                return profit;
            }
        }
        return 0;
    }

    public void addCommentTrip(ArrayList<Trip> trips) {
        String strInput, comm;
        double rating;
        int code, index;
        Scanner input = new Scanner(System.in);
        Coment coment;
        ArrayList<Coment> coments;

        System.out.print("Code of trip to rate and/or comment: ");
        strInput = input.nextLine();
        while (!acessTripCodeSecurity(trips, strInput)) {
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
        coment = new Coment(comm, rating);
        coments = trips.get(index).getComents();
        coments.add(coment);
        trips.get(index).setComents(coments);
    }

    public void listCommentsTrip(ArrayList<Coment> coments) {
        for (Coment coment : coments)
            System.out.println(coment);
    }

    public double leaveWaitingList(ArrayList<Trip> trips) {
        for (Trip trip : trips) {
            ArrayList<User> waitingList = trip.getWaitingList();

            for (int i = 0; i < waitingList.size(); i++) {
                Client toCompare = (Client) waitingList.get(i);
                if (toCompare == this) {
                    waitingList.remove(i);
                    trip.setWaitingList(waitingList);
                }
            }
        }
        return reserveTrip(trips);
    }

    public double payment(Trip trip) {
        return (trip.getPrice() - (trip.getPrice() * 0.1));
    }
}
