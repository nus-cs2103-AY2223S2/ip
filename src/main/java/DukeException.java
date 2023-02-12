class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    public static void missingTimingException(String command) throws DukeException {
        if (command.startsWith("deadline") && !command.contains("/by")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The timing of a deadline cannot be empty." +
                    "\n\t____________________________________________________________");
        } else if (command.startsWith("event") && !command.contains("/from")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The start time of an event cannot be empty." +
                    "\n\t____________________________________________________________");
        } else if (command.startsWith("event") && !command.contains("/to")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The end time of an event cannot be empty." +
                    "\n\t____________________________________________________________");
        }
    }

    public static void missingIndexException(String command) throws DukeException {
        switch (command) {
            case "mark":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to mark a task as done cannot be empty." +
                        "\n\t____________________________________________________________");
            case "unmark":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to unmark a task as not done cannot be empty." +
                        "\n\t____________________________________________________________");
            case "delete":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to delete a task as not done cannot be empty." +
                        "\n\t____________________________________________________________");
        }
    }

    public static void invalidIndexException(String command, int taskSize) throws DukeException {
        if (command.startsWith("mark") || command.startsWith("unmark")
                ||command.startsWith("delete")) {
            String index = command.split(" ")[1];
            int index1 = Integer.parseInt(index);
            if (index1 <= 0) {
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be zero or less." +
                        "\n\t____________________________________________________________");
            } else if (index.equals("")) {
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be empty." +
                        "\n\t____________________________________________________________");
            } else if (index1 > taskSize) {
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be more than" +
                        " number of tasks." +
                        "\n\t____________________________________________________________");
            }
        }
    }

    public static void invalidCommandException(String command) throws DukeException {
        if (!command.startsWith("event") || !(command.startsWith("deadline")) ||
                !command.startsWith("todo") || command.startsWith("mark") ||
                !command.startsWith("unmark")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n\t____________________________________________________________");
        }
    }

    public static void emptyCommandException(String command) throws DukeException {
        switch (command) {
            case "todo":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The description of a todo cannot be empty." +
                        "\n\t____________________________________________________________");
            case "deadline":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The description of a deadline cannot be empty." +
                        "\n\t____________________________________________________________");
            case "event":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The description of an event cannot be empty." +
                        "\n\t____________________________________________________________");
            case "find deadlines or events on":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The date of a deadline/ event cannot be empty." +
                        "\n\t____________________________________________________________");
        }
    }
}
