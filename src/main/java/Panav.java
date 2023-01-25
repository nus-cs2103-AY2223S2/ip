import java.util.*;
import java.util.ArrayList;
public class Panav {
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
            try {
                String[] temp = command.split(" ");
                String first = temp[0];
                //Task t = new Task(command);
                int len = command.length();
                switch (first) {
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "todo":
                    String todomessage = command.replace("todo", "").trim();
                    Task todo = new ToDo(todomessage);
                    list.add(todo);
                    counter++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                    break;
                case "deadline":
                    int index_by = command.indexOf("/by");
                    String by = command.substring(index_by + 4);
                    String deadlinemessage = command.substring(9, index_by - 1);
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
                    //Fallthrough
                case "unmark":
                    int num = readNumber(command, counter);
                    Task cur = list.get(num - 1);
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
                    int deleteIndex = readNumber(command, counter);
                    Task removed = list.remove(deleteIndex - 1);
                    counter--;
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removed);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                default:

                    throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");



                }
            } catch (InvalidInputException e) {
                System.out.println(e);
            } catch (ToDoDescriptionException e) {
                System.out.println(e);
            } catch(InvalidNumberException e) {
                System.out.println(e);
            }

            command = sc.nextLine();
        }

    }

    /**
     * Returns the index number for commands which manipulate the list.
     *
     * @param command The command which is manipulating list.
     * @param counter The number of elements in the list.
     * @return Index number in command.
     * @throws InvalidNumberException If the index doesn't exist.
     */
    public static int readNumber(String command, int counter) throws InvalidNumberException{
        int number = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));
        if (number > counter || number < 1) {
            throw new InvalidNumberException("Oops!! There is no such index number in your list");
        } else {
            return number;
        }
    }
}
