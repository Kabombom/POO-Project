package projectPackage;

import java.io.*;
import java.util.ArrayList;

//TODO OS FICHEIROS CRIADOS NAO ESTAO CRIADOS NO SITIO CORRETO
//CORRIGIR O PATH


public class Agency implements Ficheiro, Menu{

    public void writeOneLine(File toWrite, String line) {
        try	{
            PrintWriter writer = new PrintWriter(new FileWriter(toWrite));
            writer.println(line);
            System.out.println(line);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        }
        catch (IOException e)	{
            System.out.println("General I/O Exception " + e.getMessage());
        }
    }

    public String readLine(File toRead) {
        String line = "";
        try	{
            BufferedReader reader = new BufferedReader(new FileReader(toRead));
            if ((line = reader.readLine()) != null)
                return line;
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        }
        catch (IOException e)	{
            System.out.println("General I/O Exception " + e.getMessage());
        }
        return  line;
    }

    //Verificar se funciona assim
    public Object rObject(ObjectInputStream inputStream) {
        Object toReturn = null;
        try {
            toReturn = inputStream.readObject();
            return toReturn;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch (IOException e) {
            System.out.println("General I/O Exception " + e.getMessage());
        }
        return toReturn;
    }

    public void wObject(ObjectOutputStream outputStream, Object obj) {
        try {
            outputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println("General I/O Exception " + e.getMessage());
        }
    }

    public boolean login() {
        return false;
    }

    public void logout() {

    }

    public void adminMenu() {

    }

    public void clientMenu() {

    }

    public static void main(String[] args) {
        //ATRIBUTOS DA AGENCIA
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Trip> trips = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();
        double profit;

        //DATA
        Date date = new Date(1 ,1 ,1 ,1 ,1);

        //COMENTARIOS
        ArrayList<Coment> coments = new ArrayList<>();

        Bus bus = new Bus("1", 2);
        Bus bus2 = new Bus("2", 2);
        buses.add(bus);
        buses.add(bus2);

        //VIAGEM
        Trip trip = new Trip(1, "oi", "adeus", 1, 1, date, buses, coments);
        trips.add(trip);

        ArrayList<Reserve> reserves = new ArrayList<>();
        ArrayList<Reserve> reserves2 = new ArrayList<>();
        ArrayList<Reserve> reserves3 = new ArrayList<>();
        ArrayList<Reserve> reserves4 = new ArrayList<>();
        ArrayList<Reserve> reserves5 = new ArrayList<>();
        ArrayList<Reserve> reserves6 = new ArrayList<>();
        ArrayList<Reserve> reserves7 = new ArrayList<>();
        ArrayList<Reserve> reserves8 = new ArrayList<>();


        Admin admin = new Admin("Machado", "1", "1", "mail", "32434", "isto", 1);
        Premium premium1 = new Premium("Machado1", "2", "2", "mail2", "324342", "isto2", 2, reserves);
        Premium premium2 = new Premium("Machado2", "3", "3", "mail3", "324343", "isto3", 2, reserves2);
        Premium premium3 = new Premium("Machado3", "4", "4", "mail4", "324344", "isto4", 2, reserves3);
        Premium premium4 = new Premium("Machad4", "5", "5", "mail5", "324345", "isto5", 2, reserves4);
        Premium premium5 = new Premium("Machado5", "6", "6", "mail6", "324346", "isto6", 2, reserves5);
        Premium premium6 = new Premium("Machado6", "7", "7", "mail7", "324347", "isto7", 2, reserves6);
        Premium premium7 = new Premium("Machado7", "8", "8", "mail8", "324348", "isto8", 2, reserves7);
        Premium premium8 = new Premium("Machado8", "9", "9", "mail9", "324349", "isto9", 2, reserves8);

        premium1.reserveTrip(trips);
        premium1.reserveTrip(trips);
        premium1.listReserves();
        premium1.listAvaiableTrips(trips);
        premium1.cancelReserve();
        premium1.listAvaiableTrips(trips);

        File fp = new File("src/projectPackage/teste.txt");
        Agency agencia = new Agency();
        agencia.writeOneLine(fp, "oi");
        agencia.writeOneLine(fp, "oi2");
    }

    //------------------------------------------------------------TESTAR A PARTE HOJE A NOITE-----------------------------------------------------------------------------


}
