import Storage.TaskList;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        // echo();
        handleRequest();
        exit();
    }

    /**
     * Prints response
     * @param res Duke's response(String) to print
     */
    public static void printRes(String res) {
        System.out.println("================================================================");
        System.out.println(res);
        System.out.println("================================================================\n");
    }

    /**
     * Greet the user
     */
    public static void greet() {
        final String logo = "██████   █████  ██████   █████  \n"
                    + "██   ██ ██   ██ ██   ██ ██   ██ \n"
                    + "██   ██ ██   ██ ██████  ███████ \n"
                    + "██   ██ ██   ██ ██   ██ ██   ██ \n"
                    + "██████   █████  ██   ██ ██   ██ \n\n";
        final String intro = "Hola! Soy \n";
        final String icebreaker = "What can I do for you?";
        System.out.println(intro + logo + icebreaker);
        System.out.println("________________________________________________________________");
        System.out.println();
    }

    /**
     * Echo the user's response
     */
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            printRes(input);
            input = sc.nextLine();
        }

        sc.close();
    }

    /**
     * Function to handle the user's request
     */
    public static void handleRequest() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        TaskList tasks = new TaskList();

        while(!input.equalsIgnoreCase("bye")) {
            Request request = new Request(input, tasks);
            printRes(request.toString());
            input = sc.nextLine();
        }

        sc.close();
    }

    /**
     * Exit the program with an outro
     */
    public static void exit() {
        String outro = "bella ciao";
        printRes(outro);
    }
}
