import java.util.Scanner;

import Exceptions.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;

        while(true) {
            String message = scanner.nextLine();

            if(message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(message.equals("list")){
                for(int i = 0; i < counter; i++) {
                    int label = i + 1;
                    System.out.println(label + ". " + list[i].toString());
                }
            } else if(message.startsWith("mark") || message.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(message.split(" ")[1]);
                if(taskNumber > counter) {
                    System.out.println("Task " + taskNumber + " does not exist");
                    continue;
                }

                Task currTask = list[taskNumber - 1];
                if(message.startsWith("mark")) {
                    currTask.setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask);
                } else {
                    currTask.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currTask.toString());
                }
            } else if(message.startsWith("todo")) {
                try {
                    Task todo = addNewTask(message);
                    list[counter] = todo;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + counter + " task in the list");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if(message.startsWith("deadline")) {
                try {
                    Task deadline = addNewTask(message);
                    list[counter] = deadline;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + counter + " task in the list");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if(message.startsWith("event")) {
                try {
                    Task event = addNewTask(message);
                    list[counter] = event;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + counter + " task in the list");
                } catch(DukeException e) {
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
                throw (new TaskNotFoundException("☹ OOPS!!! The description of a todo cannot be empty."));
            }
            return new Todo(info);
        } else if(message.startsWith("deadline")) {
            String info = message.substring(8).trim();
            if (info.isEmpty()) {
                throw(new TaskNotFoundException("☹ OOPS!!! The description of a deadline cannot be empty."));
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
                throw (new TaskNotFoundException("☹ OOPS!!! The description of an event cannot be empty."));
            }

            try {
                String[] info_parts = info.split("/", 3);
                return new Event(info_parts[0],info_parts[1],info_parts[2]);
            } catch(ArrayIndexOutOfBoundsException e) {
                throw(new NotEnoughArgumentsException("☹ OOPS!!! Event requires a start time and an end time."));
            }
        } else {
            // this should only happen when addNewTask is being called in the wrong place
            throw(new TaskNotFoundException("There is an error in the code. This message does not belong here"));
        }
    }
}
