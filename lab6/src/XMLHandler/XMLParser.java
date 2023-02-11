package XMLHandler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import general.*;
import states.*;

public class XMLParser {
    private Document document;

    public XMLParser(Document document) {
        this.document = document;
    }
    public void createPopulation() throws Exception {
        if (document == null) {
            throw new Exception("Data is null");
        }
        NodeList records = document.getElementsByTagName("person");
        if(records.getLength() == 0){
            throw new Exception("Nie ma takiego tagu jak <person>.");
        }

        Population pop = Simulation.getPeople();

        for(int i = 0; i < records.getLength(); i++){
            Node record = records.item(i);
            Element el = (Element) record;
            try{
                double x = Double.parseDouble(el.getElementsByTagName("x").item(0).getTextContent());
                double y = Double.parseDouble(el.getElementsByTagName("y").item(0).getTextContent());
                String state = el.getElementsByTagName("state").item(0).getTextContent();
                IState statusPerson = new Resistant();
                if(state.contains("SickWithSymptoms")){
                    statusPerson = new SickWithSymptoms();
                    int counter = Integer.parseInt(el.getElementsByTagName("counter").item(0).getTextContent());
                    ((SickWithSymptoms) statusPerson).setNumberOf(counter);
                } else if (state.contains("SickWithNoSymptoms")){
                    statusPerson = new SickWIthNoSymptoms();
                    int counter = Integer.parseInt(el.getElementsByTagName("counter").item(0).getTextContent());
                    ((SickWIthNoSymptoms) statusPerson).setNumberOf(counter);
                } else if (state.contains("Healthy")){
                    statusPerson = new Healthy();
                    int counter = Integer.parseInt(el.getElementsByTagName("counter").item(0).getTextContent());
                    ((Healthy) statusPerson).setNumberOf(counter);
                }

                Single person = new Single( x, y, statusPerson);
                pop.addPerson(person);
            }catch(NullPointerException e){
                System.out.println("Nie udało się dodać tej osoby");
            }
        }
    }

}
