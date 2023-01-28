package Week2.src.main;
import java.util.Scanner;



/**
 * Deals with making sense of the user command
 */
public class Parser {
    Scanner sc = new Scanner(System.in);

    /**
     * Parser constructor
     */
    public Parser() {

    }

    public String getCommand() {
        return sc.nextLine();
    }

}
