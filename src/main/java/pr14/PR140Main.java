package pr14;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PR140Main {
    public static void main(String[] args) {
        String file = "./data/persones.xml";

        // Crea una factoria de constructors de documents
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse(file);

            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();

            // Obté una llista de tots els elements "student" del document
            NodeList personaNodeList = doc.getElementsByTagName("persona");

            // Dibujo de la tabla
            String[] dataList = new String[4];
            dataList[0] = "NOM";
            dataList[1] = "COGNOM";
            dataList[2] = "EDAT";
            dataList[3] = "CIUTAT";

            String dataTable = "*".repeat(personaNodeList.getLength() * 35) + "\n";
            dataTable = Main.rellenarTabla(dataTable, dataList);
            dataTable += "*   *".repeat((personaNodeList.getLength() * 35) / 5) + "\n";

            // Bucle for per recórrer la llista d'estudiants
            for (int cnt = 0; cnt < personaNodeList.getLength(); cnt++) {
                // Obté l'estudiant actual
                Node nodeEstudiant = personaNodeList.item(cnt);
                // Comprova si l'estudiant actual és un element
                if (nodeEstudiant.getNodeType() == Node.ELEMENT_NODE) {
                    // Converteix l'estudiant actual a un element
                    Element elm = (Element) nodeEstudiant;

                    // **Obté el nom**
                    NodeList nodeList = elm.getElementsByTagName("nom");
                    String nom = nodeList.item(0).getTextContent();

                    nodeList = elm.getElementsByTagName("cognom");
                    String cognom = nodeList.item(0).getTextContent();

                    nodeList = elm.getElementsByTagName("edat");
                    String edat = nodeList.item(0).getTextContent();

                    nodeList = elm.getElementsByTagName("ciutat");
                    String ciutat = nodeList.item(0).getTextContent();

                    dataList[0] = nom;
                    dataList[1] = cognom;
                    dataList[2] = edat;
                    dataList[3] = ciutat;

                    dataTable = Main.rellenarTabla(dataTable, dataList);

                }
            }

            dataTable += "*".repeat(personaNodeList.getLength() * 35) + "\n";
            System.out.println(dataTable);

        } catch (Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }

    }
}
