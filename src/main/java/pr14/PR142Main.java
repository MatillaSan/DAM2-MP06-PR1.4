package pr14;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class PR142Main {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse("./data/cursos.xml");

            XPath xPath = XPathFactory.newInstance().newXPath();

            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();

            // Obté una llista de tots els elements "cursos" del document
            NodeList listCursos = doc.getElementsByTagName("curs");
            String[] listaIds = new String[listCursos.getLength()];

            for (int i = 0; i < listCursos.getLength(); i++) {
                Node nodeCurso = listCursos.item(i);
                if (nodeCurso.getNodeType() == Node.ELEMENT_NODE) {
                    Element elm = (Element) nodeCurso;
                    listaIds[i] = elm.getAttribute("id");
                }
            }

            boolean running = true;
            String id = "";
            while (running) {
                String menu = "Escull una opció:";
                menu = menu + "\n 0) Mostar cursos mas info";
                menu = menu + "\n 1) Mostrar modulos de un curso";
                menu = menu + "\n 2) Listar alumnos de un curso";
                menu = menu + "\n 3) Anyadir alumno a un curso";
                menu = menu + "\n 4) Eliminar alumno de un curso";
                // Adapta aquí les altres classes de l’exercici (PR122cat…)
                menu = menu + "\n 5) Sortir";
                System.out.println(menu);

                int opcio = Integer.valueOf(Main.llegirLinia("Opció:"));
                try {
                    switch (opcio) {
                        case 0:
                            part0(doc, xPath);
                            break;
                        case 1:
                            id = Main.llegirLinia("ID: ");
                            if (!comprobarCurso(id, listaIds))
                                break;
                            part1(doc, xPath, id);
                            break;
                        case 2:
                            id = Main.llegirLinia("ID: ");
                            if (!comprobarCurso(id, listaIds))
                                break;
                            part2(doc, xPath, id);
                            break;
                        case 3:
                            id = Main.llegirLinia("ID: ");
                            if (!comprobarCurso(id, listaIds))
                                break;
                            break;
                        case 4:
                            id = Main.llegirLinia("ID: ");
                            if (!comprobarCurso(id, listaIds))
                                break;
                            break;
                        // Adapta aquí les altres classes de l’exercici (PR122cat…)
                        case 5:
                            running = false;
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean comprobarCurso(String idComp, String[] idList) {

        for (String id : idList) {
            if (id.equals(idComp))
                return true;
        }

        return false;
    }

    public static void part0(Document doc, XPath xPath) {
        String expression = "/cursos/curs";
        String[] titleList = new String[3];
        titleList[0] = "CURS";
        titleList[1] = "TUTOR";
        titleList[2] = "NUM ALUMNES";

        try {
            NodeList listCurs = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
            System.out.println(listCurs.getLength());

            String dataTable = "*".repeat(105) + "\n";
            dataTable = Main.rellenarTabla(dataTable, titleList);
            dataTable += "*   *".repeat(105 / 5) + "\n";

            for (int i = 0; i < listCurs.getLength(); i++) {
                Element elmCurso = (Element) listCurs.item(i);
                String id = elmCurso.getAttribute("id");
                String tutor = elmCurso.getElementsByTagName("tutor").item(0).getTextContent();
                int numAlumnos = elmCurso.getElementsByTagName("alumne").getLength();

                titleList[0] = id;
                titleList[1] = tutor;
                titleList[2] = ((Integer) numAlumnos).toString();
                dataTable = Main.rellenarTabla(dataTable, titleList);

            }
            System.out.println(dataTable + "*".repeat(105));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void part1(Document doc, XPath xPath, String id) {
        String expression = "/cursos/curs[@id='" + id + "']/moduls/modul";
        String[] titleList = new String[2];
        titleList[0] = "MODUL";
        titleList[1] = "TITOL";

        try {
            NodeList listModuls = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            String dataTable = "*".repeat(70) + "\n";
            dataTable = Main.rellenarTabla(dataTable, titleList);
            dataTable += "*   *".repeat(70 / 5) + "\n";

            for (int i = 0; i < listModuls.getLength(); i++) {
                Element modulElement = (Element) listModuls.item(i);
                String modulID = modulElement.getAttribute("id");
                String modulTitol = modulElement.getElementsByTagName("titol").item(0).getTextContent();

                titleList[0] = modulID;
                titleList[1] = modulTitol;

                dataTable = Main.rellenarTabla(dataTable, titleList);

            }
            System.out.println(dataTable + "*".repeat(70));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void part2(Document doc, XPath xPath, String id) {
        String expression = "/cursos/curs[@id='" + id + "']/alumnes/alumne";
        String[] titleList = new String[1];
        titleList[0] = "ALUMNES DE " + id;

        try {
            NodeList listAlumnes = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            String dataTable = "*".repeat(35) + "\n";
            dataTable = Main.rellenarTabla(dataTable, titleList);
            dataTable += "*   *".repeat(35 / 5) + "\n";

            for (int i = 0; i < listAlumnes.getLength(); i++) {
                String nom = listAlumnes.item(i).getTextContent();

                titleList[0] = nom;

                dataTable = Main.rellenarTabla(dataTable, titleList);
            }

            System.out.println(dataTable + "*".repeat(35));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
