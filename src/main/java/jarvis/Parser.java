package jarvis;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {};

    public boolean parse(String command, ToDoList todolist, Storage storage) {

        if (command.equals("bye")) {
            try {
                storage.saveTasks(todolist);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("\tHave a nice day sir.");
            return true;

        } else if (command.equals("list")) {
            todolist.list();
        } else if (command.matches("mark(.*)")) {
            try {
                int spacer = command.indexOf(" ");
                int taskNum = Integer.parseInt(command.substring(spacer + 1));
                validateTask(todolist, taskNum);
                todolist.markDone(taskNum);
            } catch (NumberFormatException e) {
                System.out.println("Which task have you completed, sir?");
            } catch (NoTaskFoundException e) {
                System.out.println("Sir, that task does not exist.");
            }

        } else if (command.matches("unmark(.*)")) {
            try {
                int spacer = command.indexOf(" ");
                int taskNum = Integer.parseInt(command.substring(spacer + 1));
                validateTask(todolist, taskNum);
                todolist.unmark(taskNum);
            } catch (NumberFormatException e) {
                System.out.println("Which task would you like to unmark sir?");
            } catch (NoTaskFoundException e) {
                System.out.println("Sir, that task does not exist.");
            }

        } else if (command.matches("todo(.*)")) {
            try {
                addToDo(todolist, command);
            } catch (JarvisException e) {
                System.out.println("\tPlease enter in format 'todo <task>'");
            }

        } else if (command.matches("deadline(.*)")) {

            // check format
            if (!command.contains("/")) {
                System.out.println("\tPlease enter in format 'deadline <task> /<deadline>'");
            } else {
                int firstSlash = command.indexOf("/");
                String task = command.substring(9, firstSlash);
                String time = command.substring(firstSlash + 1);

                try {
                    LocalDate startTimeParsed = LocalDate.parse(time);
                    todolist.add(task, startTimeParsed);
                } catch (DateTimeParseException e) {
                    System.out.println("Wrong date format\n");
                    System.out.println("Input date format 'event <task> /<YYYY-MM-DD>'");
                }
            }

        } else if (command.matches("event(.*)")) {
            int firstSlash = command.indexOf("/");

            // check format
            if (firstSlash == -1 || !command.substring(firstSlash + 1).contains("/")) {
                System.out.println("\tPlease enter in format 'event <task> /<start>/<end>'");
            } else {
                int secondSlash = command.substring(firstSlash + 1).indexOf("/") + firstSlash + 1;
                String startTime = command.substring(firstSlash + 1, secondSlash);
                String endTime = command.substring(secondSlash + 1);
                String task = command.substring(6, firstSlash);

                try {
                    LocalDate startTimeParsed = LocalDate.parse(startTime);
                    LocalDate endTimeParsed = LocalDate.parse(endTime);
                    todolist.add(task, startTimeParsed, endTimeParsed);
                } catch (DateTimeParseException e) {
                    System.out.println("Wrong date format\n");
                    System.out.println("Input date format 'event <task> /<YYYY-MM-DD>/<YYYY-MM-DD>'");
                }
            }

        } else if (command.matches("delete(.*)")) {
            try {
                int spacer = command.indexOf(" ");
                int taskNum = Integer.parseInt(command.substring(spacer + 1));
                validateTask(todolist, taskNum);
                todolist.delete(taskNum);
            } catch (NumberFormatException e) {
                System.out.println("Which task would you like to delete, sir?");
            } catch (NoTaskFoundException e) {
                System.out.println("Sir, you may not delete nonexistent tasks.");
            }

        } else {
            System.out.println("I do not know that command, sir.");
            System.out.println("Perhaps you can add that functionality for J.A.R.V.I.S(v2.0).");
        }
        return false;
    }

    public static void validateTask(ToDoList toDo, int num) throws NoTaskFoundException {
        if (num > toDo.getCount()) {
            throw new NoTaskFoundException("");
        }
    }

    public static void addToDo(ToDoList toDo, String line) throws JarvisException {
        try {
            String task = line.substring(5);
            toDo.add(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new JarvisException("");
        }
    }
}
