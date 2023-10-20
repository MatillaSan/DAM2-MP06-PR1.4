package pr14;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class PR141Main {
    public static void main(String[] args) {
        try {
            // Crea una factoria de constructors de documents
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Crea un constructor de documents
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Crea un nou document XML
            Document doc = db.newDocument();
            // Crea l'element root del document XML
            Element elmRoot = doc.createElement("biblioteca");
            // Afegeix l'element root al document XML
            doc.appendChild(elmRoot);
            // Crea l'element "to"
            Element elmLlibre = doc.createElement("llibre");
            Element elmTitle = doc.createElement("titol");
            Element elmAutor = doc.createElement("autor");
            Element elmAnyPub = doc.createElement("anyPublicacio");
            Element elmEditorial = doc.createElement("editorial");
            Element elmGenere = doc.createElement("genere");
            Element elmPagines = doc.createElement("pagines");
            Element elmDisponible = doc.createElement("disponible");
            // Afegeix els sub elements
            elmLlibre.appendChild(elmTitle);
            elmLlibre.appendChild(elmAutor);
            elmLlibre.appendChild(elmAnyPub);
            elmLlibre.appendChild(elmEditorial);
            elmLlibre.appendChild(elmGenere);
            elmLlibre.appendChild(elmPagines);
            elmLlibre.appendChild(elmDisponible);
            // Afegeix text
            Text nodeTextTitol = doc.createTextNode("El viatge dels venturons");
            elmTitle.appendChild(nodeTextTitol);
            Text nodeTextAutor = doc.createTextNode("Joan Pla");
            elmAutor.appendChild(nodeTextAutor);
            Text nodeTextAnyPub = doc.createTextNode("1998");
            elmAnyPub.appendChild(nodeTextAnyPub);
            Text nodeTextEditorial = doc.createTextNode("Edicions Mar");
            elmEditorial.appendChild(nodeTextEditorial);
            Text nodeTextGenere = doc.createTextNode("Aventura");
            elmGenere.appendChild(nodeTextGenere);
            Text nodeTextPagines = doc.createTextNode("320");
            elmPagines.appendChild(nodeTextPagines);
            Text nodeTextDisponible = doc.createTextNode("true");
            elmDisponible.appendChild(nodeTextDisponible);
            // Afegeix l'element "to" a l'element root
            elmRoot.appendChild(elmLlibre);
            // Crea un atribut "id"
            Attr attrId = doc.createAttribute("id");
            // Estableix el valor de l'atribut "id"
            attrId.setValue("001");
            // Afegeix l'atribut "id" a l'element "to"
            elmLlibre.setAttributeNode(attrId);
            // Escriu l'arxiu
            Main.write("./data/biblioteca2.xml", doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
