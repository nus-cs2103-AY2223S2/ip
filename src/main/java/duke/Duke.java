package duke;

import java.util.Scanner;

/**
 * Duke is a program that helps you keep track of tasks
 */
public class Duke {
    /**
     * Initializes the Duke program and calls start function to start the program
     * @param args
     */
    public static void main(String[] args) {
        TasksList list = new TasksList(100);
        UI ui = new UI();
        Storage storage = new Storage(list);
        Parser parser = new Parser();
        ui.start();
        storage.findData();
        storage.loadData();
        start(ui, storage, parser, list);
    }

    /**
     * Starts the duke program and checks for various commands
     * @param ui UI onject responsible for User interactions
     * @param storage Stores your tasks in the program
     * @param parser formats your commands that helps Duke read
     * @param list list of Tasks
     */
    public static void start(UI ui, Storage storage, Parser parser, TasksList list) {
        Scanner input = new Scanner(System.in);
        String command;
        while (true) {
            command = input.nextLine();
            String[] commandArr = command.split(" ");
            try {
                DukeExceptions.checkCommand(commandArr);
                if (commandArr[0].equals("todo")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.addTodo(list, parser.getTodoDescription(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("deadline")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.addDeadline(list, parser.getDeadlineDescription(command), parser.getDeadlineby(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("event")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.addEvent(list, parser.getEventDescription(command),
                            parser.getEventFrom(command), parser.getEventEnd(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("mark")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.mark(list, parser.getMarkNum(command, true));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("unmark")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.mark(list, parser.getMarkNum(command, false));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("delete")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.removeTask(list, parser.getMarkNum(command, false));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("list")) {
                    ui.showList(list);
                } else if (commandArr[0].equals("find")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.findTask(list, parser.getKeyword(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("bye")) {
                    storage.saveData();
                    ui.showExit();
                    return;
                } else {
                    System.out.println("Should not reach this condition");
                }
            } catch (DontKnowWhatThatMeansException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
