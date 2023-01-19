import java.util.Scanner;

public class Duke {
    public static String[] phrases = {
        "Hi I'm Duke.\nHow may I help?",
        "Goodbye. Shutting down."
    };

    public static void chatbox(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        chatbox(phrases[0]);

        boolean loop = true;
        while (loop) {
            input = sc.nextLine();
            if (input.equals("bye")){
                chatbox(phrases[1]);
                break;
            } else {
                chatbox(input);
            }

        }
    }
}
