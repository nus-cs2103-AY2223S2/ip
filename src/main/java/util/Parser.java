package util;

public class Parser {

    String stringToParse;

    public Parser(){

    }


    //credit: https://stackabuse.com/java-check-if-string-is-a-number/
    public boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("The value is a String!");
        }
        return false;
    }
}
