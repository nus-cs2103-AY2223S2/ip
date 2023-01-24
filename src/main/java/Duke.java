import entities.TaskList;
import enums.Commands;
import exceptions.DukeFileNotFoundException;
import exceptions.EmptyDescException;
import exceptions.InvalidInputException;
import storage.Storage;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeFileNotFoundException {
        TaskList list = new TaskList(100);
        greet();
        Storage storage = new Storage("user.home", list);
        storage.connect();
        storage.load();
        processInputs(list, storage);
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


    //exits the application when "exit" is inputted
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }

    //adds items into the list and prints it when "list" is the input
    //our list takes in Tasks that are marked with a boolean.
    //processes the list with inputs from the user with list and Tasks operations.
    private static void processInputs(TaskList list, Storage storage) {
        ProcessCommands processes = new ProcessCommands(list);
        Scanner sc = new Scanner(System.in).useDelimiter(" ");
        String input = sc.nextLine();
        while (input.equals("bye") == false) {
            try {
                String[] split = input.split(" ");
                Commands type = Commands.valueOf(split[0].toUpperCase().strip());
                switch (type) {
                case LIST:
                    processes.processList();
                    break;
                case MARK:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    processes.processMark(Integer.parseInt(split[1]) - 1);
                    break;
                case UNMARK:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    processes.processUnmark(Integer.parseInt(split[1]) - 1);
                    break;
                case TODO:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String task = "";
                    for (int i = 1; i < split.length; i++) {
                        task += split[i] + " ";
                    }
                    processes.processTodo(task.trim());
                    break;
                case DEADLINE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String[] parseInput = input.split("/by");
                    processes.processDeadline(parseInput[0].replaceFirst("deadline ", ""), parseInput[1]);
                    break;
                case EVENT:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String[] parseFirst = input.split("/from");
                    String[] parseSecond = parseFirst[1].split("/to");
                    processes.processEvent(parseFirst[0].replaceFirst("event ", ""), parseSecond[0], parseSecond[1]);
                    break;
                case DELETE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    processes.processDelete(Integer.parseInt(split[1]) - 1);
                    break;
                default:
                    throw new InvalidInputException("Sorry! I have no idea what that means ??? >:c");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry! I have no idea what that means ??? >:c");
                System.out.println("---------------------------------------");
            } catch (InvalidInputException e) {
                System.out.println(e);
                System.out.println("---------------------------------------");
            }
            if (!sc.hasNextLine()) {
                break;
            }
            input = sc.nextLine();
        }
        storage.save();
        exit();
    }
}








