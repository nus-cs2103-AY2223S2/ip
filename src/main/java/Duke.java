import java.util.*;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws InvalidInputException, ToDoDescriptionException {

        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello hello! I'm Panav");
        System.out.println("Whatsup bro");
        System.out.println("____________________________________________________________");

        ArrayList<Task> list = new ArrayList<>();
        String command = sc.nextLine();
        int counter = 0;
        while(true) {
            String[] temp = command.split(" ");
            String first = temp[0];
            //Task t = new Task(command);
            int len = command.length();
            switch(first) {
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i+1) + ". " + list.get(i));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "todo":
                    try {
                        String todomessage = command.replace("todo", "").trim();
                        Task todo = new ToDo(todomessage);
                        list.add(todo);
                        counter++;
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo);
                        System.out.println("Now you have " + counter + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch(ToDoDescriptionException e) {
                        System.out.println(e);
                    }

                    break;
                case "deadline":
                    String by = command.substring(22);
                    String deadlinemessage = command.substring(9, 20);
                    Task deadline = new Deadline(deadlinemessage, by);
                    list.add(deadline);
                    counter++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "event":
                    int fromindex = 0, toindex = 0;
                    String eventmessage = "", from = "", to = "";
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j].compareTo("/from") == 0) {
                            fromindex = j;
                        }
                        if (temp[j].compareTo("/to") == 0) {
                            toindex = j;
                        }
                    }
                    for (int j = 0; j < temp.length; j++) {
                        if (j < fromindex && j > 0) {
                            eventmessage += temp[j] + " ";
                        }
                        if (j > fromindex && j < toindex) {
                            from += temp[j] + " ";
                        }
                        if (j > toindex) {
                            to += temp[j];
                        }
                    }

                    Task event = new Event(eventmessage, from, to);
                    list.add(event);
                    counter++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "mark":

                case "unmark":
                    int num = Integer.parseInt(String.valueOf(command.charAt(len-1)));
                    Task cur = list.get(num-1);
                    System.out.println("____________________________________________________________");
                    if (first.compareTo("mark") == 0) {
                        System.out.println("Nice! I've marked this task as done:");
                        cur.markAsDone();
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:");
                        cur.markAsNotDone();
                    }
                    System.out.println(cur);
                    System.out.println("____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    System.exit(0);
                    break;
                case "delete":
                    int index = Integer.parseInt(String.valueOf(command.charAt(len-1)));
                    Task removed = list.remove(index-1);
                    counter--;
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removed);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    try {
                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch(InvalidInputException e) {
                        System.out.println(e);
                }



            }

            command = sc.nextLine();
        }

    }
}
