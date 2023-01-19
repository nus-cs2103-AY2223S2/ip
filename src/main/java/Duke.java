import java.util.*;

public class Duke {
    public static int MAXCHAR = 60;
    public static LinkedList<Task> memory;
    public static String[] phrases = {
        "I am Duke.\nHow may I be of service?",
        "Goodbye. Shutting down.",
        "Here are the tasks in your list:",
        "Nice, I've marked this task as done:",
        "OK, I've marked this task as not done yet:"
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

    public static void chatbox(LinkedList<Task> input) {
        chatboxFrame();
        chatbox(phrases[2], false);
        int tmp = memory.size();
        int n = 0;
        while (tmp != 0) {
            tmp = tmp/10;
            n++;
        }
        int i = 0;
        for (Task item : input) {
            i++;
            chatbox(
                String.format("%" + n + "d", i).replace(' ', '0')
                + ". "
                + item.toString(),
                false);
        }
        chatboxFrame();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        memory = new LinkedList<Task>();
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
                    if (input.length() > 5 &&
                        input.substring(0, 5).equals("mark ")) {

                        Task foo = memory.get(Integer.parseInt(input.substring(5, input.length())) - 1);
                        foo.setDone(true);

                        chatboxFrame();
                        chatbox(phrases[3], false);
                        chatbox(foo.toString(), false);
                        chatboxFrame();

                    } else if (input.length() > 7 &&
                        input.substring(0, 7).equals("unmark ")) {

                        Task foo = memory.get(Integer.parseInt(input.substring(7, input.length())) - 1);

                        chatboxFrame();
                        chatbox(phrases[4], false);
                        chatbox(foo.toString(), false);
                        chatboxFrame();

                    } else {
                        memory.add(new Task(input));
                        chatbox("added: " + input);
                    }
            }

        }

    }
}
