import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(formatOutput("Hey there! I'm Sirius\n\t What can I do for you today? :D"));

        String input = sc.nextLine();
        while (!isBye(input)) {
            System.out.println(formatOutput(input));
            input = sc.nextLine();
        }

        System.out.println(formatOutput("Well, I'm off! Hope to see you again soon :)"));
    }
    
    public static String formatOutput(String input) {
        String line = "\t____________________________________________________________\n";
        return line + "\t " + input + "\n" + line;
    }

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }
}
