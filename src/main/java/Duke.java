import java.util.*;

public class Duke {
    public static int MAXCHAR = 60;
    public static LinkedList<String> memory;
    public static String[] phrases = {
        "I am Duke.\nHow may I be of service?",
        "Goodbye. Shutting down."
    };

    public static void chatboxFrame() {
        System.out.println("\t____________________________________________________________");
    }

    public static void chatbox(String text, boolean frame) {
        // Frame determines if chatboxFrame is run
        if (frame) {
            chatboxFrame();
        }

        for (String substr : text.split("\n", -1)) {
            while (substr.length() > MAXCHAR) {
                System.out.println("\t" + substr.substring(0, MAXCHAR));
                substr = substr.substring(MAXCHAR, substr.length());
            }
            System.out.println("\t" + substr);
        }

        if (frame) {
            chatboxFrame();
        }
    }

    public static void chatbox(String text) {
        chatbox(text, true);
    }

    public static void chatbox(LinkedList<String> input) {
        chatboxFrame();

        // Adds padding to the number to line up the items
        int tmp = memory.size();
        int n = 0;
        while (tmp != 0) {
            tmp = tmp/10;
            n++;
        }
        int i = 0;
        for (String substr : memory) {
            i++;
            chatbox(
                String.format("%" + n + "d", i).replace(' ', '0')
                + ". "
                + substr,
                false);
        }
        chatboxFrame();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        memory = new LinkedList<String>();
        String input;

        chatbox(phrases[0]);

        boolean loop = true;
        while (loop) {
            input = sc.nextLine();

            switch (input) {
                case "bye":
                    chatbox(phrases[1]);
                    loop = false;
                    break;
                case "list":
                    chatbox(memory);
                    break;
                default:
                    memory.add(input);
                    chatbox("added: " + input);
            }

        }

    }
}
