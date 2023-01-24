import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int index = 1;
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            String textInput = scan.nextLine();
            Task t = new Task(textInput);

            if (textInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scan.close();
                return;
            }

            if (textInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < index; i++) {
                    System.out.println(i + ". " + taskList[i-1].toString());
                }
                continue;
            }

            if (textInput.substring(0, 4).equalsIgnoreCase("mark")) {
                int i = Integer.parseInt(textInput.substring(5));
                Task currTask = taskList[i-1];
                currTask.markDone();
                System.out.println("Nice! I've marked this task as done\n" + currTask.toString());
                continue;
            }

            if (textInput.substring(0, 6).equalsIgnoreCase("unmark")) {
                int i = Integer.parseInt(textInput.substring(7));
                Task currTask = taskList[i-1];
                currTask.markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + currTask.toString());
                continue;
            }

            taskList[index-1] = t;
            index++;
            System.out.println("added: " + textInput);
        }
    }
}