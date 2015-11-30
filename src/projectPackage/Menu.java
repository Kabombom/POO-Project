package projectPackage;

import java.util.ArrayList;

public interface Menu{
    public User login(ArrayList<User> users);
    public void adminMenu(Admin admin, ArrayList<User> users, ArrayList<Trip> trips, ArrayList<Bus> buses);
    public void clientMenu();
}
