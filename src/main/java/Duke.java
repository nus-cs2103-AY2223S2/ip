import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        Pattern mark = Pattern.compile("(mark|unmark)([0-9])?");
        Pattern index = Pattern.compile("(mark|unmark)?([0-9])");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm Duke!\n" + "How can I help you?");

        while (true) {
            String input = sc.nextLine();
            Matcher matchMark = mark.matcher(input);
            Matcher matchIndex = index.matcher(input);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list");
                if (tasks.size() != 0) {
                    for (int i=0; i < tasks.size(); i++) {
                        System.out.println((i+1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
                    }
                }
            } else if (matchMark.find() && matchIndex.find()) {
                Task currTask = tasks.get(Integer.parseInt(matchIndex.group())-1);
                if (matchMark.group().equals("mark")) {
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                } else if (matchMark.group().equals("unmark")) {
                    currTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                }
            }
            else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(newTask.toString());
            }
            //System.out.println(input);
        }
    }
}
