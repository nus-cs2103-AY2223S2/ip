import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String LOGO = "\n ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n\n";

    public static String input(Scanner sc) {
        System.out.print("> ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> texts = new ArrayList<>();

        System.out.println("Hello I am" + LOGO + "What Can I do for you?");
        boolean exit = false;
        while(!exit) {
            String cmd = input(sc);
            switch(cmd) {
                case "bye":
                    exit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    for (int i = 0; i < texts.size(); i++) {
                        System.out.printf("%d. %s\n", i+1, texts.get(i));
                    }
                    break;
                default:
                    texts.add(cmd);
                    System.out.printf("added: %s\n", cmd);
            }
        }
        sc.close();
    }
}
