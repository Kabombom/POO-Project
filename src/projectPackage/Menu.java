package projectPackage;

public interface Menu{
    User login(Agency agency);
    void adminMenu(Agency agency, Admin admin);
    void clientMenu(Agency agency, Client client);
}
