import Utility.XMLValidator;
import org.junit.Assert;
import org.junit.Test;


public class XMLValidatorTest {

    @Test
    public void validTest(){
        Assert.assertTrue(XMLValidator.validateXML("resources/Dance.xml", "resources/Dance.xsd"));
    }

}
