import java.util.Scanner;

public class Duke {
    public static int MAXCHAR = 60;
    public static String[] phrases = {
        "I am Duke.\nHow may I be of service?",
        "Goodbye. Shutting down."
    };

    public static void chatbox(String text) {
        System.out.println("\t____________________________________________________________");
        for (String substr : text.split("\n", -1)) {
            while (substr.length() > MAXCHAR) {
                System.out.println("\t" + substr.substring(0, MAXCHAR));
                substr = substr.substring(MAXCHAR, substr.length());
            }
            System.out.println("\t" + substr);
        }
        System.out.println("\t____________________________________________________________");
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
