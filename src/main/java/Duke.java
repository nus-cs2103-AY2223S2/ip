import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }

    public static void printRes(String res) {
        System.out.println("================================================================");
        System.out.println(res);
        System.out.println("================================================================\n");
    }
    public static void greet() {
        final String logo = "██████   █████  ██████   █████  \n"
                    + "██   ██ ██   ██ ██   ██ ██   ██ \n"
                    + "██   ██ ██   ██ ██████  ███████ \n"
                    + "██   ██ ██   ██ ██   ██ ██   ██ \n"
                    + "██████   █████  ██   ██ ██   ██ \n\n";
        final String intro = "Hola! Soy \n";
        final String icebreaker = "What can I do for you?";
        System.out.println(intro + logo + icebreaker);
        System.out.println("____________________________________________________________");
        System.out.println();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            printRes(input);
            input = sc.nextLine();
        }
        sc.close();
    }

    public static void exit() {
        String outro = "bella ciao";
        printRes(outro);
    }
}
