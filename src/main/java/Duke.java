import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            String textInput = scan.nextLine();

            if (textInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scan.close();
                return;
            }

            if (textInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i).toString());
                }
                continue;
            }

            if (textInput.length() >= 8 &&
                    textInput.substring(0, 6).equalsIgnoreCase("delete")) {
                int i = Integer.parseInt(textInput.substring(7));
                Task t = taskList.get(i - 1);
                taskList.remove(i - 1);
                String output = String.format("Got it. I've removed this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
                System.out.println(output);
                continue;
            }

            if (textInput.length() >= 6 && 
                    textInput.substring(0, 4).equalsIgnoreCase("mark")) {
                int i = Integer.parseInt(textInput.substring(5));
                Task currTask = taskList.get(i - 1);
                currTask.markDone();
                System.out.println("Nice! I've marked this task as done\n" + currTask.toString());
                continue;
            }

            if (textInput.length() >= 8 &&
                    textInput.substring(0, 6).equalsIgnoreCase("unmark")) {
                int i = Integer.parseInt(textInput.substring(7));
                Task currTask = taskList.get(i - 1);
                currTask.markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + currTask.toString());
                continue;
            }

            if (textInput.length() >= 4 &&
                    textInput.substring(0, 4).equalsIgnoreCase("todo")) {
                try {
                    String[] parts = textInput.split(" ", 2);
                    if (parts.length == 1 || parts[1] == "") {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Task.Todo(textInput.substring(5));
                    taskList.add(t);
                    String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
                    System.out.println(output);
                    continue;                
                } catch (DukeException e) {
                    System.out.println(e);
                    continue;
                }
            }

            if (textInput.length() >= 10 && 
                    textInput.substring(0, 8).equalsIgnoreCase("deadline")) {
                String[] parts = textInput.split("/");
                Task t = new Task.Deadline(parts[0].substring(9), parts[1].substring(3));
                taskList.add(t);
                String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
                System.out.println(output);
                continue;
            }

            if (textInput.length() >= 7 && 
                    textInput.substring(0, 5).equalsIgnoreCase("event")) {
                String[] parts = textInput.split("/");
                Task t = new Task.Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
                taskList.add(t);
                String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
                System.out.println(output);
                continue;
            }
            
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}