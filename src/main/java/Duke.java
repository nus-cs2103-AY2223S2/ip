import enums.Commands;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws EmptyDescException, InvalidInputException {
        ArrayList<Task> list = new ArrayList<>(100);
        greet();
        processInputs(list);
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("---------------------------------------");
    }

    // echoes strings inputted by the user


    //exits the application when "exit" is inputted
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }

    //adds items into the list and prints it when "list" is the input
    //our list takes in Tasks that are marked with a boolean.
    //processes the list with inputs from the user with list and Tasks operations.
    private static void processInputs(ArrayList<Task> list) throws InvalidInputException, EmptyDescException {
        Scanner sc = new Scanner(System.in).useDelimiter(" ");
        String input = sc.nextLine();
        while (input.equals("bye") == false) {
            try {
                String[] split = input.split(" ");
                Commands type = Commands.valueOf(split[0].toUpperCase().strip());
                switch (type) {
                case LIST:
                    for (int i = 0; i < list.size(); i++) {
                        Task element = list.get(i);
                        System.out.println(String.format("%d.%s", i + 1, element.toString()));
                    }
                    System.out.println("---------------------------------------");
                    break;
                case MARK:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    int item = Integer.parseInt(split[1]);
                    Task curr = list.get(item - 1);
                    curr.setDone();
                    System.out.println(String.format("Nice, this task has been marked as done:\n %s", curr.toString()));
                    System.out.println("---------------------------------------");
                    break;
                case UNMARK:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    item = Integer.parseInt(split[1]);
                    curr = list.get(item - 1);
                    curr.setUndone();
                    System.out.println(String.format("ok, this task has been marked as not done yet:\n %s", curr.toString()));
                    System.out.println("---------------------------------------");
                    break;
                case TODO:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String task = "";
                    for (int i = 1; i < split.length; i++) {
                        task += split[i] + " ";
                    }
                    Todo stuff = new Todo(task.trim());
                    list.add(stuff);
                    System.out.println(String.format("alright, I've added the following task:\n %s", stuff.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
                    System.out.println("---------------------------------------");
                    break;
                case DEADLINE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String[] parseInput = input.split("/by");
                    Deadline deadline = new Deadline(parseInput[0].replaceFirst("deadline ", ""), parseInput[1]);
                    list.add(deadline);
                    System.out.println(String.format("Received, I've added the following deadlines:\n %s", deadline.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
                    System.out.println("---------------------------------------");
                    break;
                case EVENT:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String[] parseFirst = input.split("/from");
                    String[] parseSecond = parseFirst[1].split("/to");
                    Event event = new Event(parseFirst[0].replaceFirst("event ", ""), parseSecond[0], parseSecond[1]);
                    list.add(event);
                    System.out.println(String.format("Sure!, I've added the following events:\n %s", event.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
                    System.out.println("---------------------------------------");
                    break;
                case DELETE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    item = Integer.parseInt(split[1]);
                    curr = list.remove(item - 1);
                    System.out.println(String.format("ok, this task has been removed:\n %s", curr.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                    System.out.println("---------------------------------------");
                    break;
                default:
                    throw new InvalidInputException("Sorry! I have no idea what that means ??? >:c");
                }
            } catch (InvalidInputException e) {
                System.out.println(e);
                System.out.println("---------------------------------------");
            }
            if (!sc.hasNextLine()) {
                break;
            }
            input = sc.nextLine();
        }
        exit();

    }
}








