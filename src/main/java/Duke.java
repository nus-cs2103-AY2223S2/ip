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
                if (command.length() == 4) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(command.substring(5));
                list[counter] = todo;
                counter++;
                System.out.println("Got it. I've added this task:\n" + todo.toString() 
                + "\nNow you have " + counter + " tasks in the list");
                }
            } else if (command.length() >= 8 && command.substring(0,8).equals("deadline")) {
                int starting = 0;
                for (int i = 0; i < command.length(); i++) {
                    if (command.substring(i,i+3).equals("/by")){
                        starting = i + 4;
                        break;
                    }
                }
                if(command.length() == 8) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (starting == 0) {
                    System.out.println("☹ OOPS!!! You must indicate ur deadline using /by");
                } else {
                    String by = command.substring(starting);
                    Deadline deadline = new Deadline(command.substring(0,starting-5), by);
                    list[counter] = deadline;
                    counter++;
                    System.out.println("Got it. I've added this task:\n" + deadline.toString() 
                    + "\nNow you have " + counter + " tasks in the list");
                }
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
                if(command.length() == 5) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (fromStart == 0 || toStart == 0) {
                    System.out.println("☹ OOPS!!! You must indicate ur event duration using /from and /to");
                } else {
                    String from = command.substring(fromStart, toStart-4);
                    String to = command.substring(toStart);
                    Event event = new Event(command.substring(0,fromStart-7), from, to);
                    list[counter] = event;
                    counter++;
                    System.out.println("Got it. I've added this task:\n" + event.toString() 
                    + "\nNow you have " + counter + " tasks in the list");
                }
            } else if (command.length() >= 4 && command.substring(0, 4).equals("mark")){
                if (command.length() < 5) {
                    System.out.println("☹ OOPS!!! The index of task cannot be empty.");
                } else {
                    int index = Character.getNumericValue(command.charAt(5));
                    list[index-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + list[index-1].toString());
                }
            } else if(command.length() >= 6 && command.substring(0,6).equals("unmark")) {
                if (command.length() < 7) {
                    System.out.println("☹ OOPS!!! The index of task cannot be empty.");
                } else {
                    int index = Character.getNumericValue(command.charAt(7));
                    list[index-1].markAsUndone();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + list[index-1].toString());
                }
            
            } else if (command.length() >= 6 && command.substring(0,6).equals("delete")) {
                if (command.length() < 7) {
                    System.out.println("☹ OOPS!!! The index of task cannot be empty.");
                } else {
                    int index = Character.getNumericValue(command.charAt(7));
                    System.out.println("Noted. I've removed this task:\n"+list[index-1]);
                    for(int i = index; i < counter; i++) {
                        list[i-1] = list[i];
                    }
                    counter--;
                    System.out.println("Now you have " + counter + " tasks in the list");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        input.close();
    }
}
