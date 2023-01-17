import java.util.Scanner;

public class Duke {
    public static void reply(String s) {
        String linebreak = "_________________________________________________________\n";
        System.out.println(linebreak);
        System.out.println(s);
        System.out.println(linebreak);
    }
    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        String bye = "Bye. Hope to see you again!";
        Scanner sc = new Scanner(System.in);
        String currentInput = "";

        //Introduction
        reply(greeting);

        currentInput = sc.nextLine();
        while (!currentInput.equalsIgnoreCase("bye")) {
            reply(currentInput);
            currentInput = sc.nextLine();
        }
        reply(bye);
    }
}
