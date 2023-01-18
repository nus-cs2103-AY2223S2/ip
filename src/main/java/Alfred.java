import java.util.Scanner;
import java.util.ArrayList;

public class Alfred {

    private static ArrayList<Task> itemsList;
    public static void main(String[] args) {
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        System.out.println("| Your favourite personal assistant:  |");
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        Alfred.printLogo();
        Alfred.printIntro();

        Scanner sc = new Scanner(System.in);
        String commandLine = sc.nextLine();

        itemsList = new ArrayList<>();

        while (true) {
            String[] lineArr = commandLine.split(" ");
            String command = lineArr[0];

            if (command.equals("bye") && lineArr.length == 1) { // So we can still add taskNames that start with bye
                Alfred.saysBye();
                System.exit(1);
            } else if (command.equals("list") && lineArr.length == 1) {
                Alfred.listItems();
            } else if (command.equals("mark") && lineArr.length == 2) {
                Alfred.markItem(lineArr[1]); // must be int catch error
            } else if (command.equals("unmark") && lineArr.length == 2) {
                Alfred.unmarkItem(lineArr[1]);
            } else {
                Alfred.addItem(commandLine);
            }

            commandLine = sc.nextLine();
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

    private static void addItem(String commandLine) {
        itemsList.add(new Task(commandLine));
        String command = String.format("    added: %s", commandLine);
        Alfred.echoCommand(command);
    }

    private static void markItem(String indexArg) {
        int index = Integer.parseInt(indexArg) - 1;
        Task task = itemsList.get(index);
        task.markAsDone();
        String command = "Well done! Good job " +
                "for completing your task! \n";
        command += String.format("    %s", task);
        Alfred.echoCommand(command);
    }

    private static void unmarkItem(String indexArg) {
        int index = Integer.parseInt(indexArg) - 1;
        Task task = itemsList.get(index);
        task.unmarkTask();
        String command = "I have unmark this task.Remember to complete" +
                "your task on time! \n";
        command += String.format("    %s", task);
        Alfred.echoCommand(command);
    }

    private static void listItems() {
        int itemIndex = 1;
        StringBuilder command = new StringBuilder("Here are your pending tasks: \n");
        for (Task item : itemsList) {
            command.append(String.format("    %d. %s\n", itemIndex, item));
            itemIndex++;
        }
        Alfred.echoCommand(command.toString());
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
