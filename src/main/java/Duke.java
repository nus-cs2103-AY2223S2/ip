import entities.TaskList;
import enums.Commands;
import exceptions.DukeFileNotFoundException;
import exceptions.EmptyDescException;
import exceptions.InvalidInputException;
import storage.Storage;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws EmptyDescException, InvalidInputException, DukeFileNotFoundException {
        TaskList list = new TaskList(100);
        Storage storage = new Storage("user.home", list);
        UI ui = new UI();
        ui.showWelcome();
        storage.connect();
        storage.load();
        processInputs(ui, list, storage);
    }

    //exits the application when "exit" is inputted
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }

    //adds items into the list and prints it when "list" is the input
    //our list takes in Tasks that are marked with a boolean.
    //processes the list with inputs from the user with list and Tasks operations.
    private static void processInputs(UI ui, TaskList list, Storage storage) throws InvalidInputException, EmptyDescException {
        Scanner sc = new Scanner(System.in).useDelimiter(" ");
        String input = sc.nextLine();
        while (input.equals("bye") == false) {
            try {
                String[] split = input.split(" ");
                Commands type = Commands.valueOf(split[0].toUpperCase().strip());
                switch (type) {
                case LIST:
                    ui.processList(list);
                    break;
                case MARK:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    ui.processMark(list, Integer.parseInt(split[1]) - 1);
                    break;
                case UNMARK:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    ui.processUnmark(list, Integer.parseInt(split[1]) - 1);
                    break;
                case TODO:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String task = "";
                    for (int i = 1; i < split.length; i++) {
                        task += split[i] + " ";
                    }
                    ui.processTodo(list, task.trim());
                    break;
                case DEADLINE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String[] parseInput = input.split("/by");
                    ui.processDeadline(list, parseInput[0].replaceFirst("deadline ", ""), parseInput[1]);
                    break;
                case EVENT:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    String[] parseFirst = input.split("/from");
                    String[] parseSecond = parseFirst[1].split("/to");
                    ui.processEvent(list, parseFirst[0].replaceFirst("event ", ""), parseSecond[0], parseSecond[1]);
                    break;
                case DELETE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    ui.processDelete(list, Integer.parseInt(split[1]) - 1);
                    break;
                case DATE:
                    if (split.length < 2) {
                        throw new EmptyDescException("Sorry! you can't have empty descriptions!");
                    }
                    ui.processPrintDate(list, split[1].trim());
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








