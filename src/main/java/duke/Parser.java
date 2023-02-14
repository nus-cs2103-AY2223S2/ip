package duke;

public class Parser {

    public Command parseInput(String input) throws DukeException {
        if (input.equals("bye")) {
            return new Command("bye", null);
        } else if (input.equals("list")) {
            return new Command("list", null);
        } else if (input.startsWith("mark")) {
            String index = input.split(" ", 2)[1];
            String[] markData = { index };
            return new Command("mark", markData);
        } else if (input.startsWith("unmark")) {
            String index = input.split(" ", 2)[1];
            String[] markData = { index };
            return new Command("unmark", markData);
        } else if (input.startsWith("todo")) {
            return createTodo(input);
        } else if (input.startsWith("deadline")) {
            return createDeadline(input);
        } else if (input.startsWith("event")) {
            return createEvent(input);
        } else if (input.startsWith("fixed")) {
            return createFixed(input);
        } else if (input.startsWith("delete")) {
            return deleteTask(input);
        } else if (input.startsWith("find")) {
            return findTasks(input);
        } else {
            throw new DukeException("I DON'T UNDERSTAND THAT!");
        }
    }

    private Command createTodo(String input) throws DukeException {
        // Error handling
        if (input.length() <= 5) {
            throw new DukeException("TODO NEEDS A DESCRIPTION!");
        }

        // Get to-do name
        String descrip = input.split(" ", 2)[1];

        // Error handling
        if (descrip.length() == 0) {
            throw new DukeException("TODO DESCRIPTION CANNOT BE EMPTY!");
        }

        String[] todoData = { descrip };

        return new Command("addTodo", todoData);
    }

    private Command createDeadline(String input) throws DukeException {
        // Error handling
        if (input.length() <= 9) {
            throw new DukeException("DEADLINE NEEDS A DESCRIPTION!");
        }

        // Get by String
        String inputInfo = input.split(" ", 2)[1];
        String[] deadlineData = inputInfo.split(" /by ");

        // Error handling (description or by date cannot be empty)
        if (deadlineData[0].length() == 0 || deadlineData[1].length() == 0) {
            throw new DukeException("DEADLINE DESCRIPTION AND BY DATE CANNOT BE EMPTY!");
        }

        return new Command("addDeadline", deadlineData);
    }

    private Command createEvent(String input) throws DukeException {
        // Error handling
        if (input.length() <= 6) {
            throw new DukeException("EVENT NEEDS A DESCRIPTION!");
        }

        // Get from and to Strings
        String inputInfo = input.split(" ", 2)[1];
        String[] inputs1 = inputInfo.split(" /from ");
        String taskName = inputs1[0];
        String[] inputs2 = inputs1[1].split(" /to ");
        String from = inputs2[0];
        String to = inputs2[1];

        // Error handling
        if (taskName.length() == 0 || from.length() == 0 || to.length() == 0) {
            throw new DukeException("EVENT DESCRIPTION AND FROM/TO DATE CANNOT BE EMPTY!");
        }

        String[] eventData = { taskName, from, to };

        return new Command("addEvent", eventData);
    }

    private Command createFixed(String input) throws DukeException {
        // Error handling
        if (input.length() <= 6) {
            throw new DukeException("FIXED TASK NEEDS A DESCRIPTION!");
        }

        // Get by String
        String inputInfo = input.split(" ", 2)[1];
        String[] fixedData = inputInfo.split(" /time ");

        // Error handling (description or time needed cannot be empty)
        if (fixedData[0].length() == 0 || fixedData[1].length() == 0) {
            throw new DukeException("FIXED TASK DESCRIPTION AND TIME NEEDED CANNOT BE EMPTY!");
        }

        return new Command("addFixed", fixedData);
    }

    private Command deleteTask(String input) throws DukeException {
        // Error handling
        if (input.length() <= 7) {
            throw new DukeException("DELETE NEEDS A TASK NUMBER!");
        }

        // Get delete task number
        String index = input.split(" ", 2)[1];
        String[] deleteData = { index };

        return new Command("deleteTask", deleteData);
    }

    private Command findTasks(String input) throws DukeException {
        // Error handling
        if (input.length() <= 5) {
            throw new DukeException("FIND NEEDS A TASK NUMBER!");
        }

        // Get find keyword
        String keyword = input.split(" ", 2)[1];
        String[] findData = { keyword };

        return new Command("find", findData);
    }

}
