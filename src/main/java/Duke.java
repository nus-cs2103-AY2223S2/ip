import java.util.Scanner;
import java.util.Objects;

public class Duke {
    public static final int MAX_TASK = 1000;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWelcomeMessage();


        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASK]; //ask[] taskList = new Task[100];
        //int taskCounter = 0;

        // use while loop to deal with user input through scanner
        String msgDescription;   //String line; Line // msgDescription
        request:
        while (true) {
            msgDescription = in.nextLine();
            String[] splitMessage = msgDescription.split(" ");
//            switch (splitMessage[0]) {
            if (Objects.equals(msgDescription, "list")) {
                Task.printHorizontalLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.getTotalNumberOfTask(); i++) {
                    taskList[i].printTask();
                }
                Task.printHorizontalLine();
            } else if (Objects.equals(msgDescription, "blah")) {
                Task.printHorizontalLine();
                System.out.println("     blah");
                Task.printHorizontalLine();
            } else if (Objects.equals(msgDescription, "bye")) {
                Duke.printByeMessage();
                break request;
            } else if (Objects.equals(msgDescription.split(" ")[0], "mark")) {
                int id = Integer.parseInt(msgDescription.split(" ")[1]) - 1;
                taskList[id].setDone();
                Task.printHorizontalLine();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] " + taskList[id].getDescription());
                Task.printHorizontalLine();
            } else if (Objects.equals(msgDescription.split(" ")[0], "unmark")) {
                int id = Integer.parseInt(msgDescription.split(" ")[1]) - 1;
                taskList[id].setNotDone();
                Task.printHorizontalLine();
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [] " + taskList[id].getDescription());
                Task.printHorizontalLine();
            } else {
                taskList[Task.getTotalNumberOfTask()] = new Task(msgDescription);
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + msgDescription);
                System.out.println("    ____________________________________________________________");
            }

        }

    }

    private static void printByeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Task.printHorizontalLine();
    }

    private static void printWelcomeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        Task.printHorizontalLine();
    }

    public static void printMessage(String msgDescription){
        Task.printHorizontalLine();
        System.out.println("     added: " + msgDescription);
        Task.printHorizontalLine();
    }
}
