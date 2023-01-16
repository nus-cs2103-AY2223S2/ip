import java.util.Scanner;

public class Duke {

    public static void printWithLines(String text) {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    " + text);
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }
    public static void main(String[] args) {
        printWithLines("Hello! I'm Echoey!\n    I'll echo you until you say \"bye\".");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if (line.equalsIgnoreCase("bye")) break;

            printWithLines(line);
        }
        printWithLines("Bye! Hope to see you again soon!");
    }
}

