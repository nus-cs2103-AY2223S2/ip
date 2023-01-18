import java.util.Scanner;

public class Duke {
    static String greetings() {
        String greetingsMessage = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        return String.format("%s%s%s", horizontalLine(), greetingsMessage, horizontalLine());
    }

    static String goodbye() {
        String goodbyeMessage = "\tBye. Hope to see you soon!\n";
        return String.format("%s%s%s", horizontalLine(), goodbyeMessage, horizontalLine());
    }

    static String respond(String command) {
        String response = String.format("\t%s\n", command);
        return String.format("%s%s%s", horizontalLine(), response, horizontalLine());
    }
    static String horizontalLine() {
        return "____________________________________\n";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greetings());
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            System.out.println(respond(command));
        }
        System.out.println(goodbye());
    }
}
