import java.util.Scanner;

public class Duke {
    static String NAME = "Half";
    static String HORIZONTAL = "+=".repeat(20);
    static String INDENT = "> ";

    public static void main(String[] args) {
        String greeting = "Hello! I'm %s.";
        Duke.greet(greeting);

        String question = "What do you want?";
        Duke.say(question, false);

        String response = "";

        Scanner scan = new Scanner(System.in);

        while (!response.equals("bye")) {
            response = scan.nextLine();
            Duke.say(response, true);
        }

        Duke.say("Bye. HAND.", true);
    }

    public static void greet(String greeting) {
        /**
         * @param greeting what needs to be said, with placeholder for the name.
         */
        System.out.println(String.format(greeting, Duke.NAME));
    }

    public static void say(String sentence, boolean addLine) {
        /**
         * @param sentence what needs to be said.
         * @param addLine whether to add a horizontal line.
         * @return void
         */
        System.out.println(Duke.INDENT + sentence);

        if (addLine) {
            System.out.println(Duke.HORIZONTAL);
        }
    }
}
