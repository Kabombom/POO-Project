package projectPackage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


//TODO os perguntar de novo ao professor o que era os resultados armazenados em ficheiro
public interface Ficheiro {
    public void writeOneLine(File toWrite, String line);
    public String readLine(File toRead);
    public  Object rObject(ObjectInputStream inputStream);
    public void wObject(ObjectOutputStream outputStream, Object obj) throws IOException;
}
