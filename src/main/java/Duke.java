import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    public static void main(String[] args)
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        ArrayList<Task> userTasks = new ArrayList<Task>();
        while (!userInput.equals("bye"))
        {
            Pattern mark = Pattern.compile("mark [0-9]+");
            Pattern unmark = Pattern.compile("unmark [0-9]+");
            Matcher matchMark = mark.matcher(userInput);
            Matcher matchUnmark = unmark.matcher(userInput);

            if (userInput.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= userTasks.size(); i++)
                {
                    System.out.println(i + ".[" + userTasks.get(i - 1).getStatusIcon() + "] " + userTasks.get(i - 1));
                }
            }
            else if (matchMark.matches())
            {
                System.out.println("Nice! I've marked this task as done:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                userTasks.get(idx - 1).markTask();
                System.out.println("    " + idx + ".[" + userTasks.get(idx - 1).getStatusIcon() + "] " + userTasks.get(idx - 1));
            }
            else if (matchUnmark.matches())
            {
                System.out.println("OK, I've marked this task as not done yet:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                userTasks.get(idx - 1).unmarkTask();
                System.out.println("    " + idx + ".[" + userTasks.get(idx - 1).getStatusIcon() + "] " + userTasks.get(idx - 1));
            }
            else
            {
                Task userTask = new Task(userInput);
                userTasks.add(userTask);
                System.out.println("added: " + userInput);
            }
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
