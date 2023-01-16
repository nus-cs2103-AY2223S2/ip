import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }

    public static void greet() {
        final String logo = "██████   █████  ██████   █████  \n"
                    + "██   ██ ██   ██ ██   ██ ██   ██ \n"
                    + "██   ██ ██   ██ ██████  ███████ \n"
                    + "██   ██ ██   ██ ██   ██ ██   ██ \n"
                    + "██████   █████  ██   ██ ██   ██ \n";
        final String intro = "Hola! Soy \n";
        final String icebreaker = "What can I do for you? \n";
        System.out.println(intro + logo);

        System.out.println("____________________________________________________________");
        System.out.println();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println("================================================================");
            System.out.println(input);
            System.out.println("================================================================");
            System.out.println();
            input = sc.nextLine();
        }
        sc.close();
    }

    public static void exit() {
        String outro = "bella ciao";

        System.out.println("================================================================");
        System.out.println(outro);
        System.out.println("================================================================");
    }
}
