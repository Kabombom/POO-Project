package projectPackage;

import java.util.ArrayList;

public interface Menu{
    public User login(ArrayList<User> users);
    public void adminMenu(ArrayList<User> users, ArrayList<Trip> trips, ArrayList<Bus> buses, Admin admin);
    public void clientMenu(ArrayList<Trip> trips, Client client);
}
