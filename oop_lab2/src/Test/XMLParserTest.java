import Dance.*;
import Utility.DOMParser;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class XMLParserTest {
    ArrayList<Dance> testArray;
    @Before
    public void init(){
        DOMParser parser=new DOMParser();
        try {
             testArray = parser.parse();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void  correctTest(){
        ArrayList<Dancer> dancers=new ArrayList<>();
        Dancer dancer1=new Dancer();
        Dancer dancer2=new Dancer();
        Dancer dancer3=new Dancer();
        Dancer dancer4=new Dancer();
        dancer1.setName("Andrey"); dancer1.setAge(33); dancer1.setWorkExp(9);
        dancers.add(dancer1);
        dancer2.setName("Elya"); dancer2.setAge(29); dancer2.setWorkExp(9);
        dancers.add(dancer2);
        dancer3.setName("Boris"); dancer3.setAge(31); dancer3.setWorkExp(10);
        dancers.add(dancer3);
        dancer4.setName("Grigoriy"); dancer4.setAge(26); dancer4.setWorkExp(7);
        dancers.add(dancer4);
        Iterator i =testArray.iterator();
        Dance dance1 =new Dance(161,"National","Assembly Hall",4, Music.Phonogram,dancers,2);
        Assert.assertEquals(dance1, i.next());
    }

    @Test
    public void sortTest() {
        Collections.sort(testArray);
        if (testArray.iterator().next().compareTo(testArray.iterator().next()) < 0){
            Assert.fail("Failed");
        }
    }

}
