package aufgabe1;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Sensor {
    private String id;
    private HashMap<LocalDateTime, Double> measurements;
    private String file;

    public Sensor() {
        this.measurements = new HashMap<>();
        this.setFile("sensor.xml");
    }

    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        sensor.createSensorByXML();
        System.out.println(sensor.getId());
        sensor.printMeasurements();
        sensor.createXml();
        ArrayListe<Messung> test = sensor.toListe();
        try {
            System.out.println(test.gibKleinstesElement().get_wert());

        } catch (Exception e) {

        }
    }

    /**
     * fill sensor with measurements
     */
    public void createSensorByXML() {
        File file = new File(this.getFile());

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //documentBuilderFactory.setValidating(true);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            String rootId =  document.getDocumentElement().getAttribute("id");
            NodeList nList = document.getElementsByTagName("Messung");

            this.setId(rootId);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    LocalDateTime readTimestamp = LocalDateTime.parse(eElement.getAttribute("zeitstempel"));
                    Double readMeasure = Double.parseDouble(eElement.getAttribute("wert"));
                    this.setMeasurements(readTimestamp, readMeasure);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createXml() {
        this.setFile("sensor_output.xml");
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Sensor");
            document.appendChild(root);
            // set an attribute to staff element
            Attr attrId = document.createAttribute("id");
            attrId.setValue(this.getId());
            root.setAttributeNode(attrId);

            if(this.measurements.size() > 0) {
                for (LocalDateTime i : measurements.keySet()) {
                    Element measurement = document.createElement("Messung");

                    Attr attrMeasureValue = document.createAttribute("wert");
                    attrMeasureValue.setValue(measurements.get(i).toString());
                    measurement.setAttributeNode(attrMeasureValue);

                    Attr attrTimestamp = document.createAttribute("zeitstempel");
                    attrTimestamp.setValue(i.toString());
                    measurement.setAttributeNode(attrTimestamp);

                    root.appendChild(measurement);
                }
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.getFile()));

            transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMeasurements() {
        // Print keys and values
        for (LocalDateTime i : measurements.keySet()) {
            System.out.println("Zeitstempel: " + i + " Wert: " + measurements.get(i));
        }
    }

    public ArrayListe<Messung> toListe() {
        ArrayListe<Messung> liste = new ArrayListe<>();

        for (LocalDateTime i : measurements.keySet()) {
            Messung tempMessung = new Messung();
            tempMessung.set_date(i);
            tempMessung.set_wert(measurements.get(i));
            liste.hinzufuegen(tempMessung);
        }

        return liste;
    }

    public void setMeasurements(LocalDateTime date, Double measure) {
        measurements.put(date, measure);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Double getMeasurements(LocalDateTime date) {
        return measurements.get(date);
    }

    public String getId() {
        return id;
    }

    public String getFile() {
        return file;
    }
}
