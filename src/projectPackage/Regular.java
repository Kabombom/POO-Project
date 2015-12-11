package projectPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Regular extends Client{

    public Regular(String name, String nif, String address, String email, String phone, String password, int type) {
        super(name, nif, address, email, phone, password, type);
        super.clientReserves = new ArrayList<>();
    }

    public void listAvaiableTrips(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();

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

    public void reserveTrip(Agency agency) throws IOException {
        ArrayList<Trip> trips = agency.getTrips();
        Scanner input = new Scanner(System.in);
        String strInput;
        int tripCode, choice;
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);

        listAvaiableTrips(agency);
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
                            return;
                        case 1:
                            trip.getWaitingList().add(this);
                            System.out.print("Operation Sucefull");
                            return;
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

                for (Bus bus : trip.getBuses())
                    bus.addTakenSeat(seatNumber);

                agency.getCurrentDaySells()[3]++;
                if (agency.getCurrentDaySells()[3] > agency.getStats()[3]) {
                    agency.setStats(agency.getCurrentDaySells());
                    String year = Integer.toString(agency.getCurrentDaySells()[0]);
                    String month = Integer.toString(agency.getCurrentDaySells()[1]);
                    String day = Integer.toString(agency.getCurrentDaySells()[2]);
                    String sells = Integer.toString(agency.getCurrentDaySells()[3]);
                    String line = year + "," + month + "," + day + "," +  sells;
                    File file = new File("stats.txt");
                    agency.writeOneLine(file, line);
                } else {
                    String year = Integer.toString(agency.getCurrentDaySells()[0]);
                    String month = Integer.toString(agency.getCurrentDaySells()[1]);
                    String day = Integer.toString(agency.getCurrentDaySells()[2]);
                    String sells = Integer.toString(agency.getCurrentDaySells()[3]);
                    String line = year + "," + month + "," + day + "," +  sells;
                    File file = new File("stats.txt");
                    agency.writeOneLine(file, line);
                }

                trip.getSalesByMonth()[currentMonth]++;
                this.getTripsBoughtByMonth()[currentMonth]++;

                Reserve reserve = new Reserve(this, trip, seatNumber);
                this.clientReserves.add(reserve);
                trip.getReservesOfTrip().add(reserve);
                double profit = agency.getProfit() + payment(trip);
                agency.setProfit(profit);
                ObjectOutputStream oSU = new ObjectOutputStream(new FileOutputStream("users"));
                ObjectOutputStream oST = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oST, agency.getTrips());
                oST.close();
                agency.wObject(oSU, agency.getUsers());
                oSU.close();
            }
        }
    }

    public void listReserves() {
        for (Reserve reserve : this.getClientReserves())
            System.out.println(reserve);
    }

    public boolean checksIfReserveCodeExists(ArrayList<Reserve> reserves, int code) {
        for (Reserve reserve : reserves) {
            if (code == reserve.getTrip().getCode())
                return true;
        }
        return false;
    }

    public boolean cancelReserveCodeSecurity(ArrayList<Reserve> reserves, String strInput) {
        try {
            int code = Integer.parseInt(strInput);
            return checksIfReserveCodeExists(reserves, code);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void cancelReserve(Agency agency) throws IOException {
        int code, tripCode, differenceOfDates;
        double profit = agency.getProfit();
        //Getting current date
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        String strInput;
        Scanner input = new Scanner(System.in);
        Reserve reserve;
        Trip trip;
        Bus firstBus;
        ArrayList<Reserve> reserves = this.getClientReserves();

        this.listReserves();
        System.out.print("Trip code of the reserve to cancel: ");
        strInput = input.nextLine();
        while (!cancelReserveCodeSecurity(reserves, strInput)) {
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
                differenceOfDates = compareWithCurrentDate(calendar, trip.getDate());

                if (differenceOfDates > 7)
                    profit -= payment(trip) * 0.5;

                if (checkIfTripFull(firstBus))
                        reserve.getTrip().notifyWaitingList();

                agency.getCurrentDaySells()[3]--;
                trip.getSalesByMonth()[currentMonth]--;
                this.getTripsBoughtByMonth()[currentMonth]--;

                ArrayList<Reserve> reservesOfTrip = trip.getReservesOfTrip();
                for (Reserve reserveOfTrip : reservesOfTrip) {
                    if (reserveOfTrip.getClient() == this)
                        reserveOfTrip.setState(false);
                }

                firstBus.deleteTakenSeat(reserve.getSeatNumber());
                reserves.remove(i);
                System.out.println("Operation Successful");
                System.out.println(profit);
                agency.setProfit(profit);
                ObjectOutputStream oSU = new ObjectOutputStream(new FileOutputStream("users"));
                ObjectOutputStream oST = new ObjectOutputStream(new FileOutputStream("trips"));
                agency.wObject(oST, agency.getTrips());
                oST.close();
                agency.wObject(oSU, agency.getUsers());
                oSU.close();

            }
        }
    }


    public void addCommentTrip(Agency agency) throws IOException {
        ArrayList<Trip> trips = agency.getTrips();
        int code, index;
        double rating;
        Coment coment;
        ArrayList<Coment> coments;
        String strInput, comm;
        Scanner input = new Scanner(System.in);

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
        ObjectOutputStream oST = new ObjectOutputStream(new FileOutputStream("trips"));
        agency.wObject(oST, agency.getTrips());
        oST.close();
    }

    public void listCommentsTrip(Agency agency) {
        ArrayList<Trip> trips = agency.getTrips();
        ArrayList<Coment> coments;
        int code, index;
        Scanner input = new Scanner(System.in);
        String strInput;

        System.out.print("Code of trip to list coments: ");
        strInput = input.nextLine();
        while (!acessTripCodeSecurity(trips, strInput)) {
            System.out.print("Invalid input, code of trip to list coments: ");
            strInput = input.nextLine();
        }
        code = Integer.parseInt(strInput);

        index = indexOfTrip(trips, code);
        coments = trips.get(index).getComents();

        for (Coment coment : coments)
            System.out.println(coment);

    }

    public void leaveWaitingList(Agency agency) throws IOException {
        ArrayList<Trip> trips = agency.getTrips();

        for (Trip trip : trips) {
            ArrayList<User> waitingList = trip.getWaitingList();

            for (int i = 0; i < waitingList.size(); i++) {
                Client toCompare = (Client) waitingList.get(i);
                if (toCompare == this) {
                    waitingList.remove(i);
                    trip.setWaitingList(waitingList);
                    reserveTrip(agency);
                }
            }
        }
    }

    public double payment(Trip trip) {
        return trip.getPrice();
    }
}
