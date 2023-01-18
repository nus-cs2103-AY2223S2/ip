import java.util.*;

import javafx.concurrent.Task;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner input = new Scanner(System.in);
        //List<Task> list = new ArrayList<>();
        Tasks[] list = new Tasks[100];
        int counter = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are your tasks in your list:");
                for(int i = 1; i < counter + 1; i++) {
                    System.out.println(i + ". " + list[i-1].toString());
                }
            } else if (command.length() >= 4 && command.substring(0,4).equals("todo")) {
                Todo todo = new Todo(command.substring(5));
                list[counter] = todo;
                counter++;
                System.out.println("Got it. I've added this task:\n" + todo.toString() 
                + "\nNow you have " + counter + " tasks in the list");
            } else if (command.length() >= 8 && command.substring(0,8).equals("deadline")) {
                int starting = 0;
                for (int i = 0; i < command.length(); i++) {
                    if (command.substring(i,i+3).equals("/by")){
                        starting = i + 4;
                        break;
                    }
                }
                String by = command.substring(starting);
                Deadline deadline = new Deadline(command.substring(0,starting-5), by);
                list[counter] = deadline;
                counter++;
                System.out.println("Got it. I've added this task:\n" + deadline.toString() 
                + "\nNow you have " + counter + " tasks in the list");
            } else if (command.length() >= 5 && command.substring(0,5).equals("event")) {
                int fromStart = 0;
                int toStart = 0;
                for(int i = 0; i < command.length(); i++) {
                    if(command.substring(i,i+5).equals("/from")) {
                        fromStart = i+6;
                    } else if (command.substring(i,i+3).equals("/to")) {
                        toStart = i+4;
                        break;
                    }
                }
                String from = command.substring(fromStart, toStart-4);
                String to = command.substring(toStart);
                Event event = new Event(command.substring(0,fromStart-7), from, to);
                list[counter] = event;
                counter++;
                System.out.println("Got it. I've added this task:\n" + event.toString() 
                + "\nNow you have " + counter + " tasks in the list");
            } else if (command.substring(0, 4).equals("mark")){
                int index = Character.getNumericValue(command.charAt(5));
                list[index-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list[index-1].toString());
            } else if(command.substring(0,6).equals("unmark")) {
                int index = Character.getNumericValue(command.charAt(7));
                list[index-1].markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:\n" + list[index-1].toString());
            } else {
                Tasks newTask = new Tasks(command);
                list[counter] = newTask;
                counter++;
                System.out.println("added: " + command);
            }
        }
        input.close();
    }
}
