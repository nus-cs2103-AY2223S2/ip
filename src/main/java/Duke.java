import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        while (sc.hasNextLine()) {
            String originalString = sc.nextLine();
            try {
                parser.parseCommand(originalString);
            } catch (NeroException e) {
                System.out.println("Error encountered :((");
            }
        }
    }
}

