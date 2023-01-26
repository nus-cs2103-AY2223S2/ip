public class Parser {
    public static Command parse(String str) {
        Command command;
        boolean todoCheck, deadlineCheck, eventCheck, deleteCheck, markCheck, unMarkCheck, listCheck, exitCheck, nothingCheck;
        todoCheck = str.startsWith("todo ");
        deadlineCheck = str.startsWith("deadline ");
        eventCheck = str.startsWith("event ");
        deleteCheck = str.startsWith("delete ");
        markCheck = str.startsWith("mark ");
        unMarkCheck = str.startsWith("unmark ");
        listCheck = str.equals("list");
        exitCheck = str.equals("bye");
        nothingCheck = str.equals("");
        if (str.equals("todo") || str.equals("deadline") || str.equals("event") || str.equals("delete") || str.equals("mark") || str.equals("unmark")) {
            throw new RuntimeException("This command's field cannot be left blank!");
        } else if (todoCheck || deadlineCheck || eventCheck) {
            if (deadlineCheck) {
                String target = " /by ";
                if (!str.contains(target)) {
                    throw new RuntimeException("Unable to create Deadline! Deadline commands need a /by field!");
                }
                int index = str.indexOf(target);
                String description = str.substring(0, index);
                if (description.equals("")) {
                    throw new RuntimeException("Unable to create Deadline! Description for deadline cannot be left blank!");
                }
                String deadline = str.substring(index + 5);
                int dateTimeLength = deadline.length();
                if (!(dateTimeLength > 12 && dateTimeLength < 16)) {
                    throw new RuntimeException("Unable to create Deadline! Check your date and time. They have to be in the format of dd/mm/yyyy hhmm");
                }
                int firstSlash = deadline.indexOf("/");
                int secondSlash = deadline.indexOf("/", firstSlash + 1);
                if (firstSlash == -1 || secondSlash == -1) {
                    throw new RuntimeException("Unable to create Deadline! Check your date format. Use / to separate day, month and year.");
                }
            } else if (eventCheck) {
                String target1 = " /from ";
                String target2 = " /to ";
                if (!(str.contains(target1) && str.contains(target2))) {
                    throw new RuntimeException("Unable to create Event! Event commands need a /from and /to field!");
                }
                int index1 = str.indexOf(target1);
                int index2 = str.indexOf(target2);
                if (index2 - index1 < 0) {
                    throw new RuntimeException("Unable to create event! The /from field has to be before the /to field.");
                } else if (index2 - index1 < 20) {
                    throw new RuntimeException("Unable to create event! Please enter a valid /from field.");
                }
                String description = str.substring(0, index1);
                if (description.equals("")) {
                    throw new RuntimeException("Unable to create event! Description for event cannot be left blank");
                }
                String start = str.substring(index1 + 7, index2);
                String end = str.substring(index2 + 5);
                int startFirstSlash = start.indexOf("/");
                int startSecondSlash = start.indexOf("/", startFirstSlash + 1);
                int endFirstSlash = end.indexOf("/");
                int endSecondSlash = end.indexOf("/", endFirstSlash + 1);
                if (startFirstSlash == -1 || startSecondSlash == -1 || endFirstSlash == -1 || endSecondSlash == -1) {
                    throw new RuntimeException("Unable to create Deadline! Check your date and time format!");
                }
            }
            command = new AddCommand(str);
        } else if (deleteCheck) {
            try {
                String deleted_index = str.substring(7);
                int index = Integer.parseInt(deleted_index) - 1;
                command = new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Target was not a number!");
            }
        } else if (markCheck) {
            try {
                String marked_index = str.substring(5);
                int index = Integer.parseInt(marked_index) - 1;
                command = new MarkCommand(index);
            } catch (NumberFormatException e) {
                command = null;
                throw new RuntimeException("Target was not a number!");
            }
        } else if (unMarkCheck) {
            try {
                String unmarked_index = str.substring(7);
                int index = Integer.parseInt(unmarked_index) - 1;
                command = new UnMarkCommand(index);
            } catch (NumberFormatException e) {
                command = null;
                throw new RuntimeException("Target was not a number!");
            }
        } else if (listCheck) {
            command = new ListCommand();
        } else if (exitCheck) {
            command = new ExitCommand();
        } else if (nothingCheck) {
            throw new RuntimeException("Please enter a command!");
        } else {
            command = null;
            throw new RuntimeException("Huh? I don't know what that means :(");
        }
        return command;
    }
}
