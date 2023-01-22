import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String projName = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    ArrayList<Task> taskList = new ArrayList<>();

    public boolean readInput(String input) {
//        if (input.equals("bye")) {
//            System.out.println("It was a pleasure to help, goodbye!");
//            return false;
//        } else if (input.equals("list")) {
//            for (int i = 0; i < taskList.size(); i += 1) {
//                int currItem = i + 1;
//                System.out.println(currItem + ": " + taskList.get(i));
//            }
//            return true;
//        } else {
//            taskList.add(input);
//            System.out.println("Item added: " + input);
//            return true;
//        }
        String firstInput = input.split(" ")[0];

        switch(firstInput) {
            case "list":
                System.out.println("Here are the tasks you asked for!\n");
                for (int i = 0; i < taskList.size(); i += 1) {
                    int currItem = i + 1;
                    System.out.println(currItem + ": " + taskList.get(i));
                }
                return true;

            case "bye":
                System.out.println("It was a pleasure to help, goodbye!");
                return false;

            case "mark":
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task selectedTask = taskList.get(taskIndex);
                    System.out.println("Done! I've marked this task as done :D\n");
                    selectedTask.check();
                    System.out.println(selectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops, that task number does not exist");
                    break;
                }

            case "unmark":
                try {
                    int untaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task unselectedTask = taskList.get(untaskIndex);
                    System.out.println("This task is apparently not done huh D:\n");
                    unselectedTask.unCheck();
                    System.out.println(unselectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops, that task number does not exist");
                    break;
                }

            default:
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Item added: " + input);
                return true;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Yo! The name is\n" + projName);
        System.out.println("How might I help you today?");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        boolean cont = true;

        while (cont) {
            System.out.println("\nQuery:");
            String input = scanner.nextLine();
            cont = duke.readInput(input);
        }
        scanner.close();
    }
}
