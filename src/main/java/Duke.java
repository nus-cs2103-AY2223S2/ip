import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Tasks[] list = new Tasks[100];
        int counter = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(true) {
            String command = input.nextLine();
            String stringArr[] = command.split(" ");
            if(command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are your tasks in your list:");
                for(int i = 1; i < counter + 1; i++) {
                    System.out.println(i + ". " + list[i-1].toString());
                }
            } else if (stringArr[0].equals("todo")) {
                try {
                    DukeExceptions.checkEmptyDescription(stringArr);
                    String commandArr[] = command.split(" ", 2);
                    Todo todo = new Todo(commandArr[1]);
                    list[counter] = todo;
                    counter++;
                    System.out.println("Got it. I've added this task:\n" + todo.toString() 
                    + "\nNow you have " + counter + " tasks in the list");
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (stringArr[0].equals("deadline")) {
                try {
                    DukeExceptions.checkEmptyDescription(stringArr);
                    int starting = 0;
                    for (int i = 0; i < command.length(); i++) {
                        if (command.substring(i,i+3).equals("/by")){
                            starting = i + 4;
                            break;
                        }
                    }
                    if (starting == 0) {
                        System.out.println("☹ OOPS!!! You must indicate ur deadline using /by");
                    } else {
                        String by = command.substring(starting);
                        Deadline deadline = new Deadline(command.substring(0,starting-5), by);
                        list[counter] = deadline;
                        counter++;
                        System.out.println("Got it. I've added this task:\n" + deadline.toString() 
                        + "\nNow you have " + counter + " tasks in the list");
                    }
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (stringArr[0].equals("event")) {
                try {
                    DukeExceptions.checkEmptyDescription(stringArr);
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
                    if (fromStart == 0 || toStart == 0) {
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
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (stringArr[0].equals("mark")) {
                if (command.length() < 5) {
                    System.out.println("☹ OOPS!!! The index of task cannot be empty.");
                } else {
                    int index = Character.getNumericValue(command.charAt(5));
                    list[index-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + list[index-1].toString());
                }
            } else if (stringArr[0].equals("unmark")) {
                if (command.length() < 7) {
                    System.out.println("☹ OOPS!!! The index of task cannot be empty.");
                } else {
                    int index = Character.getNumericValue(command.charAt(7));
                    list[index-1].markAsUndone();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + list[index-1].toString());
                }
            } else if (stringArr[0].equals("delete")) {
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
                DontKnowWhatThatMeansException ex = new DontKnowWhatThatMeansException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(ex.getMessage());
                
            }
        }
    }
}