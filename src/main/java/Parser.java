import java.util.ArrayList;
import java.util.List;

public class Parser {
    Parser() {}
    
    public Command parseCommand(String input) {
        List<String> arguments = new ArrayList<>();
        if (input.equals("bye")) {
            return new Command("bye", arguments);
        } else if (input.equals("list")) {
            return new Command("list", arguments);
        } else if (input.startsWith("mark")) {
            if (! input.matches("^mark \\d+$")) {
                arguments.add("Please enter one task which you would like to mark as done.\n");
                return new Command("invalid", arguments);
            } else {
                String taskIndex = input.split(" ")[1];
                arguments.add(taskIndex);
                return new Command("mark", arguments);
            }
        } else if (input.startsWith("unmark")) {
            if (! input.matches("^unmark \\d+$")) {
                arguments.add("Please enter one task which you would like to mark as undone.\n");
                return new Command("invalid", arguments);
            } else {
                String taskIndex = input.split(" ")[1];
                arguments.add(taskIndex);
                return new Command("unmark", arguments);
            }
        } else if (input.startsWith("todo")) {
            if (! input.matches("^todo .+$")) {
                arguments.add("Please enter the task you would like to do in the format \n>> todo [task]\n");
                return new Command("invalid", arguments);
            } else {
                String taskDescription = input.split(" ", 2)[1];
                arguments.add(taskDescription);
                return new Command("todo", arguments);
            }
        } else if (input.startsWith("deadline")) {
            if (! input.matches("^deadline .+ /by .+$")) {
                arguments.add("Sorry, that command is invalid. Specify a deadline task with \n >> deadline [description] /by [time]\n");
                return new Command("invalid", arguments);
            } else {
                String delimiter = "/by ";
                String taskDescription = input.substring("deadline ".length(), input.indexOf(delimiter) - 1);
                String taskBy = input.substring(input.indexOf(delimiter) + delimiter.length());
                arguments.add(taskDescription);
                arguments.add(taskBy);
                return new Command("deadline", arguments);
            }
        } else if (input.startsWith("event")) {
            if (! input.matches("^event .+ /from .+ /to .+$")) {
                arguments.add("Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]\n");
                return new Command("invalid", arguments);
            } else {
                String startDelimiter = "/from ";
                String endDelimiter = "/to ";
                String taskDescription = input.substring("event ".length(), input.indexOf(startDelimiter) - 1);
                String startTime = input.substring(
                        input.indexOf(startDelimiter) + startDelimiter.length(),
                        input.indexOf(endDelimiter) - 1);
                String endTime = input.substring(
                        input.indexOf(endDelimiter) + endDelimiter.length());
                arguments.add(taskDescription);
                arguments.add(startTime);
                arguments.add(endTime);
                return new Command("event", arguments);
            }
        } else if (input.startsWith("delete")) {
            if (! input.matches("^delete \\d+$")) {
                arguments.add("Tell me the index of the event you want to delete! Type >>list to view your events again.\n");
                return new Command("invalid", arguments);
            } else {
                String taskIndex = input.split(" ")[1];
                arguments.add(taskIndex);
                return new Command("delete", arguments);
            }
        } else {
            return new Command("noMatch", arguments);
        }
    }
}
