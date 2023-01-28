import java.util.ArrayList;
import java.util.Scanner;

import input.Advisor;
import input.Command;
import input.Parser;

import output.DukeException;
import output.Printer;
import output.Storage;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

public class UwUke {

    private final static int CAPACITY = 100;

    private static boolean isValidIndex(ArrayList<Task> tasks, int index) {
        if (index < 0 || index >= tasks.size()) {
            Printer.printWithDecorations("Index out of bounds!");
            return false;
        } else if (tasks.get(index) == null) {
            Printer.printWithDecorations("Task not initialised!");
            return false;
        } else {
            return true;
        }
    }

    private static Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.matches("^mark\\s\\d+$")) {
            return Command.MARK;
        } else if (input.matches("^unmark\\s\\d+$")) {
            return Command.UNMARK;
        } else if (input.matches("^deadline\\s.+/by\\s.+$")) {
            return Command.DEADLINE;
        } else if (input.matches("^event\\s.+/from\\s.+/to\\s.+$")) {
            return Command.EVENT;
        } else if (input.matches("^todo\\s.+$")) {
            return Command.TODO;
        } else if (input.matches("^delete\\s\\d+$")) {
            return Command.DELETE;
        } else {
            return Command.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Printer.uwu();
        ArrayList<Task> tasks;
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file");
            tasks = new ArrayList<Task>(CAPACITY); 
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.contains(",")) { // Can potentially cause fatal errors when trying to read files if commas were allowed.
                    throw new DukeException("Please do not use reserved character \',\'.");
                }

                switch (getCommand(input)) {
                case LIST:
                    Printer.printTasks(tasks);
                    break;
                case DEADLINE:
                    if (tasks.size() >= CAPACITY) {
                        Printer.printNotEnoughSpace();
                        break;
                    }

                    String[] dt = Parser.parseDeadline(input);
                    Deadline dl = new Deadline(dt[0], dt[1]);
                    tasks.add(dl);
                    Printer.printAddedConfirmation(dl, tasks.size());
                    break;
                case EVENT:
                    if (tasks.size() >= CAPACITY) {
                        Printer.printNotEnoughSpace();
                        break;
                    }

                    String[] et = Parser.parseEvent(input);
                    Event e = new Event(et[0], et[1], et[2]);
                    tasks.add(e);
                    Printer.printAddedConfirmation(e, tasks.size());
                    break;
                case TODO:
                    if (tasks.size() >= CAPACITY) {
                        Printer.printNotEnoughSpace();
                        break;
                    }

                    Todo td = new Todo(Parser.parseTodo(input));
                    tasks.add(td);
                    Printer.printAddedConfirmation(td, tasks.size());
                    break;
                case MARK:
                    int markIndex = Parser.parseMark(input);
                    if (isValidIndex(tasks, markIndex)) // Note that this check also prints out error messages if any
                        Printer.printWithDecorations(tasks.get(markIndex).markDone());
                    break;
                case UNMARK:
                    int unmarkIndex = Parser.parseUnmark(input);
                    if (isValidIndex(tasks, unmarkIndex)) // Note that this check also prints out error messages if any
                        Printer.printWithDecorations(tasks.get(unmarkIndex).unmarkDone());
                    break;
                case DELETE:
                    int deleteIndex = Parser.parseDelete(input);
                    if (isValidIndex(tasks, deleteIndex)) {
                        Task removedTask = tasks.get(deleteIndex);
                        tasks.remove(deleteIndex);
                        Printer.printDeleteConfirmation(removedTask, tasks.size());
                    }

                    break;
                default:
                    Printer.printWithDecorations(Advisor.advise(input));
                }
            } catch (Exception e) {
                Printer.printError(e.getMessage());
            } 

            try {
                input = sc.nextLine();
            } catch (Exception e) {
                input = "";
            }
        }

        try {
            Storage.saveTasks(tasks);
        } catch (Exception e) {
            Printer.printWithDecorations("Error occured when trying to save tasks");
        }

        sc.close();
        Printer.printBye();
    }
}