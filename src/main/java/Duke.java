import java.util.*;

public class Duke {

    public static void markTask(Task[] taskList, int index) {
        Task unmarkedTask = taskList[index];
        Task markedTask = unmarkedTask.markTask();
        taskList[index] = markedTask;
    }


    public static void unmarkTask(Task[] taskList, int index) {
        Task markedTask = taskList[index];
        Task unmarkedTask = markedTask.unmarkTask();
        taskList[index] = unmarkedTask;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner (System.in);
        String input = "";
        Task[] taskList = new Task[100];
        int taskCount = 0;
        while (!input.equals("bye")) {
            input = sc.nextLine();
            // split command into each word
            String[] splitInput = input.split(" ");
            String command = splitInput[0];
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    Task task = taskList[i];
                    System.out.println(String.format("%d.%s", i + 1, task));
                }
            } else if (command.equals("blah")) {
                System.out.println(command);
            } else if (command.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            } else if (command.equals("mark")) {
                // taskNumber in 1-indexing
                String taskNumber = splitInput[1];
                // index in 0-indexing
                int index = Integer.parseInt(taskNumber) - 1;
                markTask(taskList, index);
            } else if (command.equals("unmark")) {
                String taskNumber = splitInput[1];
                int index = Integer.parseInt(taskNumber) - 1;
                unmarkTask(taskList, index);
            } else if (command.equals("todo")) {
                String description = String.join(" ",
                        Arrays.copyOfRange(splitInput, 1, splitInput.length));
                Task newTask = new Todo(description);
                taskList[taskCount] = newTask;
                taskCount++;
                System.out.println("Got it, I've added this task:");
                System.out.println(newTask);
                System.out.println(String.format("Now you have %d tasks in the list.", taskCount));
            } else if (command.equals("deadline")) {
                int byIndex = Arrays.asList(splitInput).indexOf("/by");
                String description = String.join(" ", Arrays.copyOfRange(splitInput, 1, byIndex));
                String deadline = String.join(" ", Arrays.copyOfRange(splitInput,
                        byIndex + 1, splitInput.length));
                Task newTask = new Deadline(description, deadline);
                taskList[taskCount] = newTask;
                taskCount++;
                System.out.println("Got it, I've added this task:");
                System.out.println(newTask);
                System.out.println(String.format("Now you have %d tasks in the list.", taskCount));
            } else if (command.equals("event")) {
                int fromIndex = Arrays.asList(splitInput).indexOf("/from");
                int toIndex = Arrays.asList(splitInput).indexOf("/to");
                String description = String.join(" ", Arrays.copyOfRange(splitInput, 1, fromIndex));
                String from = String.join(" ", Arrays.copyOfRange(splitInput, fromIndex + 1, toIndex));
                String to = String.join(" ", Arrays.copyOfRange(splitInput,
                        toIndex + 1, splitInput.length));
                Task newTask = new Event(description, from, to);
                taskList[taskCount] = newTask;
                taskCount++;
                System.out.println("Got it, I've added this task:");
                System.out.println(newTask);
                System.out.println(String.format("Now you have %d tasks in the list.", taskCount));
            }
        }
    }
}
