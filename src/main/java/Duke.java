import java.util.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Happie \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();


        while(!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("Here are some tasks in your list:");
                for (int i = 1; i < taskList.size() + 1; i++) {
                    System.out.println(i + "." + (taskList.get(i - 1)).toString());
                }

            } else if(input.length() > 4 && (input.substring(0, 4)).equals("mark")) {
                String taskStr = input.substring(5);
                int taskNum = Integer.parseInt(taskStr) - 1;
                Task originalTask =  taskList.get(taskNum);
                originalTask.markTask();
                System.out.println("Nice! I've marked this task as done: \n  " + originalTask.toString());

            } else if(input.length() > 6 && (input.substring(0, 6)).equals("unmark")) {
                String taskStr = input.substring(7);
                int taskNum = Integer.parseInt(taskStr) - 1;
                Task originalTask =  taskList.get(taskNum);
                originalTask.unmarkTask();
                System.out.println("Ok, I've marked this task as not done yet: \n  " + originalTask.toString());

            }  else if(input.length() > 4 && (input.substring(0, 4)).equals("todo")) {
                String taskStr = input.substring(5);
                ToDo task = new ToDo(taskStr);
                taskList.add(task);
                System.out.println("Got it. I've added this task: \n  " + task.toString() + "\nNow you have " + taskList.size()
                        + " tasks in the list.");

            } else if(input.length() > 8 &&(input.substring(0, 8)).equals("deadline")) {
                int dateIndex = input.indexOf("/");
                String taskStr = input.substring(9, dateIndex - 1) + " (by: " + input.substring(dateIndex + 4) + ")";
                Deadline task = new Deadline(taskStr);
                taskList.add(task);
                System.out.println("Got it. I've added this task: \n  " + task.toString() + "\nNow you have " + taskList.size()
                        + " tasks in the list.");

            } else if(input.length() > 5 && (input.substring(0, 5)).equals("event")) {
                int fromIndex = input.indexOf("/");
                int toIndex = input.lastIndexOf("/");
                String taskStr = input.substring(6, fromIndex - 1) +
                        " (from: " + input.substring(fromIndex + 6, toIndex - 1) + " to: " + input.substring(toIndex + 4) + ")";
                Event task = new Event(taskStr);
                taskList.add(task);
                System.out.println("Got it. I've added this task: \n  " + task.toString() + "\nNow you have " + taskList.size()
                        + " tasks in the list.");

            } else if(input.length() > 6 && (input.substring(0, 6)).equals("delete")) {
                String taskStr = input.substring(7);
                int taskNum = Integer.parseInt(taskStr) - 1;
                Task taskToRemove = taskList.get(taskNum);
                String removedTaskStr = taskToRemove.toString();
                taskList.remove(taskNum);
                System.out.println("Noted. I've removed this task: \n  " + removedTaskStr + "\nNow you have " + taskList.size()
                        + " tasks in the list.");
            }
            else {
                    try {
                        if ((input.length() == 4 && input.substring(0, 4).equals("todo")) || (input.length() == 8 && input.substring(0, 8).equals("deadline")) ||
                                (input.length() == 6 && input.substring(0, 6).equals("event"))) {
                            throw new EmptyDescriptionException();
                        } else {
                            throw new WrongCommandException();
                        }
                    } catch (EmptyDescriptionException e){
                        System.out.println(e.getMessage());
                    } catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
            }
            /*
            else {
                Task task = new Task(input);
                taskList.add(task);
                System.out.println(task.toString());
            }

             */
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
