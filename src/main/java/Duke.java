import java.util.Scanner;

public class Duke {
    private static final String linebreak = "_________________________________________________________";

    public static void reply(String s) {
        System.out.println(linebreak);
        System.out.println(s);
        System.out.println(linebreak);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        String bye = "Bye. Hope to see you again!";
        Scanner sc = new Scanner(System.in);
        String currentInput = "";
        String[] storedPhrases = new String[100];
        int phraseCount = 0;

        //Introduction
        reply(greeting);

        currentInput = sc.nextLine();
        while (!currentInput.equalsIgnoreCase("bye")) {
            if (currentInput.equalsIgnoreCase("list")) {
                System.out.println(linebreak);
                if (phraseCount == 0) {
                    System.out.println("List empty, add tasks!");
                }
                for (int i = 0; i < phraseCount; i++) {
                    System.out.println((i+ 1) + ". " + storedPhrases[i]);
                }
                System.out.println(linebreak);
            } else {
                storedPhrases[phraseCount] = currentInput;
                phraseCount++;
                reply("added: " + currentInput);
            }
            currentInput = sc.nextLine();
        }
        reply(bye);
    }
}
