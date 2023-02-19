package duke;

public class Parser {
    Parser() { }


    /**
     * Parses a given input string command. Determines its validity, and returns a corresponding Command object.
     * @param input The input command to be parsed, given as a String.
     * @return The parsed command as a Command object.
     */
    public Command parseCommand(String input) {
        if (input.equals("bye")) {
            return new Command("bye");

        } else if (input.equals("list")) {
            return new Command("list");

        } else if (input.startsWith("mark")) {
            if (!input.matches("^mark \\d+$")) {
                return new Command("invalid",
                        "Please enter one task which you would like to mark as done.\n");
            }
            String taskIndex = input.split(" ")[1];
            return new Command("mark", taskIndex);

        } else if (input.startsWith("unmark")) {
            if (!input.matches("^unmark \\d+$")) {
                return new Command("invalid",
                        "Please enter one task which you would like to mark as undone.\n");
            }
            String taskIndex = input.split(" ")[1];
            return new Command("unmark", taskIndex);


        } else if (input.startsWith("todo")) {
            if (!input.matches("^todo .+$")) {
                return new Command("invalid",
                        "Please enter the task you would like to do in the format \n>> todo [task]\n");
            }
            String taskDescription = input.split(" ", 2)[1];
            return new Command("todo", taskDescription);

        } else if (input.startsWith("deadline")) {
            if (!input.matches("^deadline .+ /by .+$")) {
                return new Command("invalid",
                        "Sorry, that command is invalid. Specify a deadline task with \n" +
                                ">> deadline [description] /by [time]\n");
            }
            String delimiter = "/by ";
            String taskDescription = input.substring("deadline ".length(), input.indexOf(delimiter) - 1);
            String taskBy = input.substring(input.indexOf(delimiter) + delimiter.length());
            return new Command("deadline", taskDescription, taskBy);

        } else if (input.startsWith("event")) {
            if (!input.matches("^event .+ /from .+ /to .+$")) {
                return new Command("invalid",
                        "Sorry, that command is invalid. Specify an event task with \n" +
                        ">> event [description] /from [start time] /to [end time]\n");
            }
            String startDelimiter = "/from ";
            String endDelimiter = "/to ";
            String taskDescription = input.substring("event ".length(), input.indexOf(startDelimiter) - 1);
            String startTime = input.substring(
                    input.indexOf(startDelimiter) + startDelimiter.length(),
                    input.indexOf(endDelimiter) - 1);
            String endTime = input.substring(
                    input.indexOf(endDelimiter) + endDelimiter.length());
            return new Command("event", taskDescription, startTime, endTime);

        } else if (input.startsWith("delete")) {
            if (!input.matches("^delete \\d+$")) {
                return new Command("invalid",
                        "Tell me the index of the event you want to delete! " +
                        "Type >>list to view your events again.\n");
            }
            String taskIndex = input.split(" ")[1];
            return new Command("delete", taskIndex);

        } else if (input.startsWith("find")) {
            if (!input.matches("^find .+$")) {
                return new Command("invalid", "What task are you looking for?\n");
            }
            String taskToFind = input.split(" ", 2)[1];
            return new Command("find", taskToFind);

        } else {
            return new Command("noMatch");
        }
    }

    /**
     * Parses a saved task into a Task object.
     * @param data The saved task read from the database, read as a string.
     * @return The parsed task corresponding to the input data string.
     */
    public Task parseTask(String data) {
        String[] arguments = data.split("~-~-~");
        assert arguments[0].matches("(todo|deadline|event)");
        if (arguments[0].equals("todo")) {
            assert arguments.length == 3 : "Todo has incorrect arguments";
            return new Todo(arguments[1], arguments[2].equals("X"));
        } else if (arguments[0].equals("deadline")) {
            assert arguments.length == 4;
            return new Deadline(arguments[1], arguments[3].equals("X"), arguments[2]);
        } else if (arguments[0].equals("event")) {
            assert arguments.length == 5;
            return new Event(arguments[1], arguments[4].equals("X"), arguments[2], arguments[3]);
        } else {
            throw new RuntimeException("Unable to parse tasks from saved file!");
        }
    }
}