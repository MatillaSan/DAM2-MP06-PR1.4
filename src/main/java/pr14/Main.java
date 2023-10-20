package pr14;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class Main {
    static Scanner in = new Scanner(System.in); // System.in és global

    // Main
    public static void main(String[] args) throws InterruptedException, IOException {
        boolean running = true;
        while (running) {
            String menu = "Escull una opció:";
            menu = menu + "\n 0) PR140LlegirIMostrar";
            menu = menu + "\n 1) PR141CrearXML";
            menu = menu + "\n 2) PR142CursosXML";
            // Adapta aquí les altres classes de l’exercici (PR122cat…)
            menu = menu + "\n 100) Sortir";
            System.out.println(menu);

            int opcio = Integer.valueOf(llegirLinia("Opció:"));
            try {
                switch (opcio) {
                    case 0:
                        PR140Main.main(args);
                        break;
                    case 1:
                        PR141Main.main(args);
                        break;
                    case 2:
                        PR142Main.main(args);
                        break;
                    // Adapta aquí les altres classes de l’exercici (PR122cat…)
                    case 100:
                        running = false;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        in.close();
    }

    static public String llegirLinia(String text) {
        System.out.print(text);
        return in.nextLine();
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    public static String rellenarTabla(String tabla, String[] lista) {
        for (String data : lista) {
            tabla += padRight("|", 2) + padRight(data, 31) + padLeft("|", 2);
        }
        tabla += "\n";
        return tabla;
    }

    // Save a Document into an XML file
    static public void write(String path, Document doc) throws TransformerException, IOException {
        if (!new File(path).exists()) {
            new File(path).createNewFile();
        }
        // Crea una factoria de transformadors XSLT
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // Crea un transformador XSLT
        Transformer transformer = transformerFactory.newTransformer();
        // Estableix la propietat OMIT_XML_DECLARATION a "no" per no ometre la
        // declaració XML del document XML resultant
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        // Estableix la propietat INDENT a "yes" per indentar el document XML resultant
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Crea una instància de DOMSource a partir del document XML
        DOMSource source = new DOMSource(doc);
        // Crea una instància de StreamResult a partir del camí del fitxer XML
        StreamResult result = new StreamResult(new File(path));
        // Transforma el document XML especificat per source i escriu el document XML
        // resultant a l'objecte especificat per result
        transformer.transform(source, result);
    }

}
