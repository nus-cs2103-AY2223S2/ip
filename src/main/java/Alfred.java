import java.util.Scanner;
import java.util.ArrayList;

public class Alfred {

    private static ArrayList<String> itemsList;
    public static void main(String[] args) {
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        System.out.println("| Your favourite personal assistant:  |");
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        Alfred.printLogo();
        Alfred.printIntro();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        itemsList = new ArrayList<>();
        while (true) {
            if (command.equals("bye")) {
                Alfred.saysBye();
                System.exit(1);
            } else if (command.equals("list")) {
                Alfred.listItems();
            } else {
                itemsList.add(command);
                Alfred.echoCommand("added: " + command);
            }
            command = sc.nextLine();
        }
    }

    private static void echoCommand(String command) {
        Alfred.printLines();
        command = "    " + command;
        System.out.println(command);
        Alfred.printLines();

    }

    private static void saysBye() {
        String command = "Bye. Hope to see you again soon!";
        Alfred.echoCommand(command);
    }

    private static void listItems() {
        int itemIndex = 1;
        Alfred.printLines();
        for (String item : itemsList) {
            System.out.printf("    %d. %s\n", itemIndex, item);
            itemIndex++;
        }
        Alfred.printLines();
    }

    private static void printLogo() {
        System.out.println(" _____ __     ______ _____ ____ ___ ");
        System.out.println("|  -  |  |   |  ____|  _  |  __| _ \\     ");
        System.out.println("| | | |  |   | |___ | |_|_| |__|| | |  ");
        System.out.println("|  -  |  |___|  ___||  _ \\  |__||_| |");
        System.out.println("|_| |_| ____ |__|   |_| \\_|____|__ /   ");

    }

    private static void printIntro() {
        String intro = "Hello! I'm Alfred :>\n"
                + "How can I help you today?";
        Alfred.printLines();
        System.out.println(intro);
        Alfred.printLines();
    }

    private static void printLines() {
        System.out.println("    ____________________________________________________________");
    }
}
