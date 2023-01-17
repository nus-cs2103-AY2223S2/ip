import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String name = "C4PO-Echo";
        String line = "-----------------------------------------------";
        String quote = "Hello. I don’t believe we have been introduced. A pleasure to meet you. I am " + name + " Human-Computer Relations.";
        System.out.println(quote);
        System.out.println(name + ": Master, please type your response below.");
        System.out.println(line);
        System.out.println(line);

        Scanner newScan = new Scanner(System.in);

        while (true) {
            String receive = newScan.nextLine(); //reads user input
            if ("bye".equalsIgnoreCase(receive)) {
                System.out.println(name + ": Bye! I'll miss all of you.");
                break;
            }
            System.out.println(name + ": " + receive);
            System.out.println(line);
        }
    }
}
