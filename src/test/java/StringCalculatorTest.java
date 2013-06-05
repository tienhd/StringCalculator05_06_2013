import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/5/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringCalculatorTest {

    @Test
    public void testWithNoInputNumber() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(0,stringCalculator.Add(""));
    }

    @Test
    public void testWithTwoInputNumber () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(1,stringCalculator.Add("1,0"));
    }

    @Test
    public void testWithMoreInputNumber () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("1,3,2"));
    }

    @Test
    public void testEndLineInInputString () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6,stringCalculator.Add("1\n2,3"));
    }

    @Test
    public void testWithDifferentDelimiter () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(5,stringCalculator.Add("//;\n1;2;2"));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testNegativeExceptionInInputString() {
        StringCalculator stringCalculator = new StringCalculator();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Negative is not allowed. -1 -2");
        stringCalculator.Add("//;\n,-1;-2,3");
    }

    @Test
    public void testWithInputNumberOverThanOneThousand () {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(3,stringCalculator.Add("1,2;1001"));
    }

    @Test
    public void testWithDefineStringDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("//[***]\n1***2***3"));
    }

    @Test
    public void testGetDefineDelimiterModule() {
        StringCalculator scModule = new StringCalculator();
        Assert.assertEquals("***",scModule.getDefineDelimiter("//[***]\n1***2***3)"));
    }

    @Test
    public void testGetMultiDefineDelimiterModule() {
        StringCalculator scModule = new StringCalculator();
        Assert.assertEquals("[(***)(;)]",scModule.getMultiDefineDelimiter("//[***][;]\n1***2;3"));
    }

    @Test
    public void testWithMultiDefineDelimiter(){
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(6, stringCalculator.Add("//[*][;]\n1*2;3"));
    }
}
