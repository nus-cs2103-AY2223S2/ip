import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        String userInput = null;
        while(!(userInput = scan.nextLine()).equals("bye")) {
            String[] splitCheck = userInput.split(" ");
            if (userInput.equals("list")) {
                String message = "Here are the tasks in your list:";
                for (int i = 0; i < taskList.size(); i++) {
//                    message += i != 0 ? "\n" : "";
                    Task oneTask = taskList.get(i);
                    message += "\n" + (i+1) + ". " + oneTask.getTaskItem();
                }
                dukeSpeak(message);
            } else if (splitCheck[0].equals("mark")){
                int taskNum = Integer.parseInt(splitCheck[1]);
                Task oneTask = taskList.get(taskNum-1);
                oneTask.markTask();
                String message = "Nice! I've marked this task as done:\n " + oneTask.getTaskItem();
                dukeSpeak(message);
            } else if (splitCheck[0].equals("unmark")) {
                int taskNum = Integer.parseInt(splitCheck[1]);
                Task oneTask = taskList.get(taskNum-1);
                oneTask.unmarkTask();
                String message = "OK! I've marked this task as not done yet:\n " + oneTask.getTaskItem();
                dukeSpeak(message);
            } else {
                taskList.add(new Task(userInput));
                dukeSpeak("added: " + userInput);
            }
        }

        dukeSpeak("Bye. Hope to see you again soon!");

    }

    public static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
