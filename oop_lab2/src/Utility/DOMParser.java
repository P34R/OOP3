package Utility;

import Dance.Dance;
import Dance.Dancer;
import Dance.Music;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DOMParser {
    private String filePath="resources\\Dance.xml";
    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
    Document doc=null;
    private final String rootTag="Dance";
    private final String eventTag="DanceEvent";
    private final String idTag="Id";
    private final String typeTag="Type";
    private final String sceneTag="Scene";
    private final String numDTag="NumOfDancers";
    private final String musicTag="Music";
    private final String dancersTag="Dancers";
    private final String dancerTag="Dancer";
    private final String nameTag="Name";
    private final String ageTag="Age";
    private final String expTag="WorkExp";
    private final String numTag="Number";

        public void initialize() {
            File file = new File(filePath);
            try {
                doc = dbf.newDocumentBuilder().parse(file);
            }catch (Exception e){
                System.out.println("File initialization error");
                e.printStackTrace();
            }
        }
        public ArrayList<Dance> parse() throws Exception {
            this.initialize();
            Node rootNode = doc.getFirstChild();
            NodeList eventNodes=rootNode.getChildNodes();

            ArrayList<Dance> dances=new ArrayList<>();


            for (int i = 0;i<eventNodes.getLength();i++){
                NodeList danceChildNodes=eventNodes.item(i).getChildNodes();
                if (eventNodes.item(i).getNodeType()!= Node.ELEMENT_NODE){
                    continue;
                }

                Dance dance =new Dance();
                for (int j = 0; j < danceChildNodes.getLength();j++){
                    if (danceChildNodes.item(j).getNodeType()!= Node.ELEMENT_NODE){
                        continue;
                    }

                    switch (danceChildNodes.item(j).getNodeName()){
                        case idTag -> {
                            dance.setId(Integer.parseInt(danceChildNodes.item(j).getTextContent()));
                        }
                        case typeTag -> {
                            dance.setType(danceChildNodes.item(j).getTextContent());
                        }
                        case sceneTag -> {
                            dance.setScene(danceChildNodes.item(j).getTextContent());
                        }
                        case numDTag -> {
                            dance.setDancersNumber(Integer.parseInt(danceChildNodes.item(j).getTextContent()));
                        }
                        case musicTag -> {
                            dance.setMusic(Music.valueOf(danceChildNodes.item(j).getTextContent()));
                        }
                        case dancersTag -> {
                            NodeList dancersNode=danceChildNodes.item(j).getChildNodes();
                            for (int k=0;k<dancersNode.getLength();k++){
                                Dancer dancer = new Dancer();
                                if (dancersNode.item(k).getNodeType() != Node.ELEMENT_NODE){
                                    continue;
                                }
                                NodeList dancersChildNodes = dancersNode.item(k).getChildNodes();

                                for (int child=0;child<dancersChildNodes.getLength();child++){
                                    if (dancersChildNodes.item(child).getNodeType()!= Node.ELEMENT_NODE){
                                        continue;
                                    }
                                    //System.out.println(dancersChildNodes.item(child).getNodeName());
                                    switch(dancersChildNodes.item(child).getNodeName()){
                                        case nameTag -> {
                                            dancer.setName(dancersChildNodes.item(child).getTextContent());
                                        }
                                        case ageTag -> {
                                            dancer.setAge(Integer.parseInt(dancersChildNodes.item(child).getTextContent()));
                                        }
                                        case expTag -> {
                                            dancer.setWorkExp(Integer.parseInt(dancersChildNodes.item(child).getTextContent()));
                                        }
                                    }
                                }
                                dance.addDancer(dancer);
                            }
                        }
                        case numTag -> {
                            dance.setNumber(Integer.parseInt(danceChildNodes.item(j).getTextContent()));
                        }
                    }


                }
                dances.add(dance);
            }
            return dances;
        }
        public void createXML(ArrayList<Dance> dancesList){
            try {
                File file = new File(filePath);
                if (file.createNewFile()){
                    System.out.println("File created successfully");
                } else{
                    System.out.println("File already exists.");
                }
            }catch (IOException e) {
                e.printStackTrace();
            }



            try{
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                Document doc=docBuilder.newDocument();
                Element root=doc.createElement(rootTag);
                doc.appendChild(root);
                for (Dance dance : dancesList){
                    Element eventElement=doc.createElement(eventTag);
                    root.appendChild(eventElement);

                    Element id = doc.createElement(idTag);
                    id.appendChild(doc.createTextNode(String.valueOf(dance.getId())));
                    eventElement.appendChild(id);

                    Element type = doc.createElement(typeTag);
                    type.appendChild(doc.createTextNode(dance.getType()));
                    eventElement.appendChild(type);

                    Element scene =doc.createElement(sceneTag);
                    scene.appendChild(doc.createTextNode(dance.getScene()));
                    eventElement.appendChild(scene);

                    Element dancersNumber = doc.createElement(numDTag);
                    dancersNumber.appendChild(doc.createTextNode(String.valueOf(dance.getDancersNumber())));
                    eventElement.appendChild(dancersNumber);

                    Element music = doc.createElement(musicTag);
                    music.appendChild(doc.createTextNode(dance.getMusic().toString()));
                    eventElement.appendChild(music);

                    Element dancers=doc.createElement(dancersTag);
                    for (Dancer danceDancer: dance.getDancers()){
                        Element dancer=doc.createElement(dancerTag);

                        Element name=doc.createElement(nameTag);
                        name.appendChild(doc.createTextNode(danceDancer.getName()));
                        dancer.appendChild(name);

                        Element age=doc.createElement(ageTag);
                        age.appendChild(doc.createTextNode(String.valueOf(danceDancer.getAge())));
                        dancer.appendChild(age);

                        Element exp=doc.createElement(expTag);
                        exp.appendChild(doc.createTextNode(String.valueOf(danceDancer.getWorkExp())));
                        dancer.appendChild(exp);

                        dancers.appendChild(dancer);

                    }
                    eventElement.appendChild(dancers);

                    Element number=doc.createElement(numTag);
                    number.appendChild(doc.createTextNode(String.valueOf(dance.getNumber())));
                    eventElement.appendChild(number);
                }
                TransformerFactory tFactory= TransformerFactory.newInstance();
                Transformer transformer = tFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT,"yes");
                DOMSource dom=new DOMSource(doc);
                StreamResult result=new StreamResult(new File(filePath));
                transformer.transform(dom,result);
            } catch (ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }
        }
}
