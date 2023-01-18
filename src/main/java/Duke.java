import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<Task>();
        String input = reader.nextLine();
        String[] splitInput = input.split(" ");

        while (true) {
            String command = splitInput[0];

            System.out.println("________________________________");

            if (command == "bye") {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________");
                break;
            }
            switch (command) {

                case "list":
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.print(i + 1);
                        System.out.println(toDoList.get(i).toString());
                    }
                    break;
                case "mark":
                    String taskNumMark = splitInput[1];

                    Task taskToMark = toDoList.get(Integer.parseInt(taskNumMark));

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToMark.mark());

                    break;
                case "unmark":
                    String taskNumUnmark = splitInput[1];

                    Task taskToUnmark = toDoList.get(Integer.parseInt(taskNumUnmark));

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskToUnmark);

                    break;
                default:
                    Task newTask = new Task(input);
                    toDoList.add(newTask);
                    System.out.println("added: " + input);
                    break;

            }

            System.out.println("________________________________");

        }
        reader.close();

    }

}
