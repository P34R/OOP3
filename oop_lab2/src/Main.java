import Dance.Dance;
import Utility.DOMParser;
import Utility.XMLValidator;

import javax.xml.transform.dom.DOMSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        DOMParser parser=new DOMParser();
        ArrayList<Dance> dances=null;
        try {
            dances=parser.parse();
        }catch(Exception e){
            e.printStackTrace();
        }

        for (Dance dance : dances)
            System.out.println(dance.toString());

        XMLValidator validator=new XMLValidator("resources\\Dance.xml","resources\\Dance.xsd");
        if (validator.validateXML())
            System.out.println("Fine");
        else System.out.println("Not fine... Sadge");

        Collections.sort(dances);

        for (Dance dance : dances)
            System.out.println(dance.toString());
        parser.createXML(dances);
    }
}