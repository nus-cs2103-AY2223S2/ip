package duke;

import exceptions.EmptyContentException;
import exceptions.InvalidTaskAccessException;
import exceptions.UnknownInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String formatDateString(String str) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String displayDate = date.format(dateFormat);
        return displayDate;
    }

    /**
     * Changes the format of the date and time for display.
     * @param str String object of the saved date and time.
     * @return String object of the date and time used for display.
     */
    public static String retrieveDate(String str) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String displayDate = date.format(dateFormat);
        return displayDate;
    }
    public static LocalDateTime getTime(String str) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        return date;
    }

    public static String[] extractArgsFromInput(String input) {
        String[] splitCommand = input.split(" ", 2);
        String type = splitCommand[0];
        if(type.equals("todo") || type.equals("find")) {
            return splitCommand;
        } else {
            String[] segments = splitCommand[1].split("/");
            return segments;
        }
    }

    public static int findIndex(String input) {
        String[] splitCommand = input.split(" ", 2);
        int index = Integer.parseInt(splitCommand[1]) - 1;
        return index;
    }

    public static String execute(String input, TaskHandler handler, UIText ui, Storage storage)
            throws UnknownInputException, EmptyContentException, InvalidTaskAccessException {
//        String input = ui.getInput();
//        System.out.println(ui.separate());
        if (input.equals("bye")) {
            storage.saveTasks();
            return ui.exit();
        } else if (input.equals("list")) {
            return handler.display();
        } else if (input.startsWith("mark")) {
            storage.saveTasks();
            return handler.markAsDone(input);
        } else if (input.startsWith("unmark")) {
            storage.saveTasks();
            return handler.markAsUndone(input);
        } else if (input.startsWith("event")) {
            storage.saveTasks();
            return handler.eventHandler(input);
        } else if (input.startsWith("todo")) {
            storage.saveTasks();
            return handler.todoHandler(input);
        } else if (input.startsWith("deadline")) {
            storage.saveTasks();
            return handler.deadlineHandler(input);
        } else if (input.startsWith("delete")) {
            storage.saveTasks();
            return handler.deleteHandler(input);
        } else if (input.startsWith("find")) {
            storage.saveTasks();
            return handler.findHandler(input);
        } else if (input.startsWith("remind")) {
            storage.saveTasks();
            return handler.remindHandler();
        } else {
            throw new UnknownInputException();
        }
    }
}