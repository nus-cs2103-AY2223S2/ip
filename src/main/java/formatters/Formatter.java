package formatters;

/**
 * This class is a utility class used to standardise the formatting of Sebastian
 */
public class Formatter {
    public static void lineBreak(){
        String line = "-";
        String res = space() + line.repeat(80);
        System.out.println(res);
    }

    /**
     * Used to standardise the space before each line of Sebastian's response
     * @return a standardised space
     */
    public static String space() {
        String space = " ";
        return space.repeat(5);
    }

    /**
     * Used to wrap Sebastian's response which a starting and an ending line
     * @param str the response which needs to be wrapped with lines.
     */
    public static void printFormattedString(String str) {
        lineBreak();
        System.out.println(str);
        lineBreak();
    }
}
