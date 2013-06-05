/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/5/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringCalculator {
    public int result = 0;
    public int Add (String inputString) {
        if (inputString.length() == 0) {
            return result;
        }
        else {
            String regex = "[,;\\n//*]";
            String negative = "-([\\d])(.*)";
            String define = "(//)(\\[)(.*)(\\])(\\n)(.*)";

            String negativeNumbers = "";
            boolean ok = false;

            if (inputString.matches(define)) {
                regex = getMultiDefineDelimiter(inputString);
                String[] getString = inputString.split("\\n");
                inputString = getString[1];
            }
            String[] inputNumbers = inputString.split(regex);
            for (String si : inputNumbers) {
                if (si.matches(negative)) {
                    ok = true;
                    negativeNumbers += si + " ";
                    continue;
                }
                if (ok) {
                    throw new IllegalArgumentException("Negative is not allowed. " + negativeNumbers );
                }
                if ((!si.isEmpty()) && (!si.equals("[")) && (!si.equals("]"))) {
                    int number = Integer.parseInt(si);
                    if (number <= 1000) {
                        result += number;
                    }
                }
            }
            return result;
        }
    }

    public String getMultiDefineDelimiter(String inputString) {
        String result = "";
        result += "[";
        String getDefinePattern = "(//)(.*)(\\n)(.*)";
        if (inputString.matches(getDefinePattern)) {
            String[] temp1 = inputString.split("\\n");
            String[] temp2 = temp1[0].split("//");
            String delimiterString = temp2[1];
            delimiterString = delimiterString.replaceAll("\\["," ");
            delimiterString = delimiterString.replaceAll("\\]"," ");
            String[] delimiterList = delimiterString.split(" ");

            for (String di: delimiterList) {
                if (!di.isEmpty()){
                    result += "(" + di + ")";
                }
            }
            result += "]";
        }
        return result;
    }
}
