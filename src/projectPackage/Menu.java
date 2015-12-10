package projectPackage;

import java.io.IOException;

public interface Menu{
    User login(Agency agency);
    void adminMenu(Agency agency, Admin admin) throws IOException, ClassNotFoundException;
    void clientMenu(Agency agency, Client client) throws IOException, ClassNotFoundException;
}
