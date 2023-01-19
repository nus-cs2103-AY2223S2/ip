import java.util.Scanner;
import java.util.ArrayList;

import Exceptions.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int counter = 0;

        while(true) {
            String message = scanner.nextLine();

            if(message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(message.equals("list")){
                for(int i = 0; i < counter; i++) {
                    int label = i + 1;
                    System.out.println("    " + label + ". " + list.get(i).toString());
                }
            } else if(message.startsWith("mark") || message.startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(message.split(" ")[1]);
                    if (taskNumber > counter) {
                        throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
                    }

                    Task currTask = list.get(taskNumber - 1);
                    if (message.startsWith("mark")) {
                        currTask.setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + currTask);
                    } else {
                        currTask.setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("    " + currTask);
                    }
                } catch(TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else if(message.startsWith("todo") || message.startsWith("deadline") || message.startsWith("event")) {
                try {
                    Task task = addNewTask(message);
                    list.add(task);
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + task);
                    System.out.println("Now you have " + counter + " task in the list");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if(message.startsWith("delete")) {
                try {
                    int taskNumber = Integer.parseInt(message.split(" ")[1]);
                    if (taskNumber > counter) {
                        throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
                    }

                    Task removedTask = list.remove(taskNumber - 1);
                    counter--;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + removedTask);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                } catch(TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static Task addNewTask(String message) throws DukeException {
        if(message.startsWith("todo")) {
            String info = message.substring(4).trim();
            if (info.isEmpty()) {
                throw (new TaskNoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty."));
            }
            return new Todo(info);
        } else if(message.startsWith("deadline")) {
            String info = message.substring(8).trim();
            if (info.isEmpty()) {
                throw(new TaskNoDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty."));
            }

            try {
                String[] info_parts = info.split("/", 2);
                return new Deadline(info_parts[0], info_parts[1]);
            } catch(ArrayIndexOutOfBoundsException e) {
                throw(new NotEnoughArgumentsException("☹ OOPS!!! Deadline requires a date after the description."));
            }
        } else if(message.startsWith("event")) {
            String info = message.substring(5).trim();
            if (info.isEmpty()) {
                throw (new TaskNoDescriptionException("☹ OOPS!!! The description of an event cannot be empty."));
            }

            try {
                String[] info_parts = info.split("/", 3);
                return new Event(info_parts[0],info_parts[1],info_parts[2]);
            } catch(ArrayIndexOutOfBoundsException e) {
                throw(new NotEnoughArgumentsException("☹ OOPS!!! Event requires a start time and an end time."));
            }
        } else {
            // this should only happen when addNewTask is being called in the wrong place
            throw(new TaskNoDescriptionException("There is an error in the code. This message does not belong here"));
        }
    }
}
