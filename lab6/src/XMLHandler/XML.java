package XMLHandler;

import general.Single;
import memento.Memento;
import states.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XML {
    private int xmlCounter = 0;
    public XML() {
    }

    public void saveFile(Memento memento){
        try {
            File file = new File("./memento" + xmlCounter + ".xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element pop = document.createElement("population");
            document.appendChild(pop);

            for(Single person : memento.getPeople()) {

                Element personEl = document.createElement("person");
                pop.appendChild(personEl);
                Element x = document.createElement("x");
                x.appendChild(document.createTextNode(String.valueOf(person.getX())));
                personEl.appendChild(x);
                Element y = document.createElement("y");
                y.appendChild(document.createTextNode(String.valueOf(person.getY())));
                personEl.appendChild(y);
                Element status = document.createElement("status");
                status.appendChild(document.createTextNode(person.getStatus().getClass().getName()));
                personEl.appendChild(status);
                if(person.getStatus() instanceof ISick || person.getStatus() instanceof Healthy){
                    Element numberOf = document.createElement("numberOf");
                    if(person.getStatus() instanceof ISick){
                        int counterInt = ((ISick) person.getStatus()).getNumberOf();
                        numberOf.appendChild(document.createTextNode(String.valueOf(counterInt)));
                    } else{
                        int counterInt = ((Healthy) person.getStatus()).getNumberOf();
                        numberOf.appendChild(document.createTextNode(String.valueOf(counterInt)));
                    }
                    status.appendChild(numberOf);
                }
            }

            StreamResult res = new StreamResult(file);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer trans = transFactory.newTransformer();
            DOMSource src = new DOMSource(document);
            trans.transform(src, res);
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();}
        xmlCounter++;
    }

    public void openFile(String path){
        path = "./" + path;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            document.getDocumentElement().normalize();
            new XMLParser(document).createPopulation();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Nie znaleziono pliku xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
