import java.util.Scanner;

import exception.DukeException;
import input.Advisor;
import input.Command;
import input.Parser;

import output.Printer;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

public class Duke {
    private static boolean isValidMark(Task[] tasks, int index) {
        if (index < 0 || index >= tasks.length) {
            Printer.printWithDecorations("Index out of bounds!");
            return false;
        } else if (tasks[index] == null) {
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
        } else {
            return Command.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Printer.printWelcome();
        Task[] tasks = new Task[100];
        int numTasks = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        while (!input.equals("bye")) {
            try {
                switch (Duke.getCommand(input)) {
                    case LIST:
                        Printer.printTasks(tasks, numTasks);
                        break;
                    case DEADLINE:
                        if (numTasks >= tasks.length) {
                            Printer.printNotEnoughSpace();
                            break;
                        }

                        String[] dt = Parser.parseDeadline(input);
                        Deadline dl = new Deadline(dt[0], dt[1]);
                        tasks[numTasks] = dl;
                        numTasks++;
                        Printer.printAddedConfirmation(dl, numTasks);
                        break;
                    case EVENT:
                        if (numTasks >= tasks.length) {
                            Printer.printNotEnoughSpace();
                            break;
                        }

                        String[] et = Parser.parseEvent(input);
                        Event e = new Event(et[0], et[1], et[2]);
                        tasks[numTasks] = e;
                        numTasks++;
                        Printer.printAddedConfirmation(e, numTasks);
                        break;
                    case TODO:
                        if (numTasks >= tasks.length) {
                            Printer.printNotEnoughSpace();
                            break;
                        }

                        Todo td = new Todo(Parser.parseTodo(input));
                        tasks[numTasks] = td;
                        numTasks++;
                        Printer.printAddedConfirmation(td, numTasks);
                        break;
                    case MARK:
                        int markIndex = Parser.parseMark(input);
                        if (isValidMark(tasks, markIndex)) // Note that this check also prints out error messages if any
                            Printer.printWithDecorations(tasks[markIndex].markDone());
                        break;
                    case UNMARK:
                        int unmarkIndex = Parser.parseUnmark(input);
                        if (isValidMark(tasks, unmarkIndex)) // Note that this check also prints out error messages if any
                            Printer.printWithDecorations(tasks[unmarkIndex].unmarkDone());
                        break;
                    default:
                        Printer.printWithDecorations(Advisor.advise(input));
                }
            } catch (DukeException e) {
                Printer.printError(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
        Printer.printBye();
    }
}