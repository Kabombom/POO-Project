package projectPackage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


//Ver enunciado tudo o que e do 1 ao 8 e para ficheiros de texto
public interface Ficheiro {
    public void writeOneLine(File toWrite, String line);
    public String readLine(File toRead);
    public  Object rObject(ObjectInputStream inputStream);
    public void wObject(ObjectOutputStream outputStream, Object obj) throws IOException;
}
