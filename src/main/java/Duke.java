import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke\n"
                + "What can I do for you?");
        Task[] taskArray = new Task[100];
        int arrayIndex = 0;
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if(input.equals("list")) {
                for (int i = 0; i < arrayIndex; i++) {
                    int listNumber = i + 1;
                    System.out.println(listNumber + ". " + taskArray[i].toString());
                }
            } else if(input.startsWith("mark")) {
                int taskNumber = input.charAt(5) - 48;
                Task currTask = taskArray[taskNumber - 1];
                currTask.markTask();
                System.out.println("Nice! I,ve marked this task as done:\n   "
                                    + currTask);
            } else if(input.startsWith("unmark")) {
                int taskNumber = input.charAt(7) - 48;
                Task currTask = taskArray[taskNumber - 1];
                currTask.unmarkTask();
                System.out.println("OK, I've marked thus task as not done yet:\n   "
                                    + currTask);
            } else {
                Task currTask = new Task(input);
                taskArray[arrayIndex] = currTask;
                System.out.println("added: " + currTask.getName());
                arrayIndex++;
            }
        }
    }
}
