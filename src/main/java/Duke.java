import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static void displayTasks(ArrayList<Task> taskList, boolean showMessage) {
        if (showMessage) {
            System.out.println("UwU_TaskMaster ＵｗＵ: Heww are your tasks UwU!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String details = task.getDetails();
            boolean hasCompleted = task.isCompleted();
            String completionDisplay;

            if (hasCompleted) {
                completionDisplay = "[X]";
            } else {
                completionDisplay = "[ ]";
            }

            System.out.println((i + 1) + ". " + details + " " + completionDisplay);
        }
        System.out.println("__________________________________________");
    }




    public static void main(String[] args) {

        System.out.println("Hewwo! I'm UwU_TaskMaster! How c-can I hewp you?!?");

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        String input;

        ArrayList<Task> taskList = new ArrayList<Task>();

        loop: while(true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            // checks for keywords in the user input
            switch (input) {
                case "bye":
                    System.out.println("UwU_TaskMaster ＵｗＵ: Bye bye... UwU");
                    break loop;

                case "list":

                    if (taskList.isEmpty()) {
                        System.out.println("UwU_TaskMaster ＵｗＵ: Wooks wike *screams* you do not have any OwO tasks!");
                        System.out.println("__________________________________________");
                    } else {
                        Duke.displayTasks(taskList, true);
                    }
                    break;

                default:

                    if (input.contains("mark") || input.contains("unmark")) {

                        int index;

                        try {
                            index = parser.parseCommand(input) - 1;
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nUwU_TaskMaster ＵｗＵ: Ohh nyoo I cannyot ^w^ undewstand *cries* what you mean");
                            System.out.println("__________________________________________");
                            break;
                        }

                        // check for out of bounds task request
                        if (index > taskList.size() - 1) {
                            System.out.println("\nUwU_TaskMaster ＵｗＵ: I cannyot ^w^ undewstand *cries* what t-task you w-want me t-to mark *runs away*");
                            System.out.println("__________________________________________");
                            break;
                        }

                        if (input.contains("mark")) {
                            Task taskToMark = taskList.get(index);
                            taskToMark.changeStatus(true);
                            System.out.println("\nUwU_TaskMaster ＵｗＵ: Wowww c-congwatuwations on compweting youw task!!");
                            Duke.displayTasks(taskList, false);
                        }

                        if (input.contains("unmark")) {
                            Task taskToMark = taskList.get(index);
                            taskToMark.changeStatus(false);
                            System.out.println("\nUwU_TaskMaster ＵｗＵ: omg nyoooooo why you have nyot compwete youw t-task :(");
                            Duke.displayTasks(taskList, false);

                        }

                    } else {
                        if (!input.equals("")) {
                            Task task = new Task(input);
                            taskList.add(task);
                            System.out.println("\nUwU_TaskMaster ＵｗＵ: Y-Y-Youw t-task has been successfuwwy added!!");
                            Duke.displayTasks(taskList, false);
                        }
                    }


            }

        }
        scanner.close();
    }
}
