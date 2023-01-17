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
        String command = "";
        Task[] taskList = new Task[100];
        int taskCount = 0;
        while (!command.equals("bye")) {
            command = sc.nextLine();
            // split command into each word
            String[] splitCommand = command.split(" ");
            if (splitCommand[0].equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    String taskCheckbox = taskList[i].getStatusCheckbox();
                    System.out.println(String.format("%d.%s %s", i + 1, taskCheckbox, taskList[i]));
                }
            } else if (splitCommand[0].equals("blah")) {
                System.out.println(command);
            } else if (splitCommand[0].equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            } else if (splitCommand[0].equals("mark")) {
                // taskNumber in 1-indexing
                String taskNumber = splitCommand[1];
                // index in 0-indexing
                int index = Integer.parseInt(taskNumber) - 1;
                markTask(taskList, index);
            } else if (splitCommand[0].equals("unmark")) {
                String taskNumber = splitCommand[1];
                int index = Integer.parseInt(taskNumber) - 1;
                unmarkTask(taskList, index);
            } else { // new tasks
                taskList[taskCount] = new Task(command);
                System.out.println(String.format("added: %s", command));
                taskCount++;
            }
        }
    }
}
