import java.util.ArrayList;
import java.util.List;

/**
 * Parser class allows the bot to understand user commands and provides
 * the right commands for execution in main loop
 */
public class Parser {

    /**
     * Instantiates a instance of Parser
     */
    public Parser() {

    }

    /**
     * Makes sense of user input and returns an appropriate executable command
     * @param receive is a String of user input
     * @return a Command which is executable
     * @throws BotException
     */
    public Command parse(String receive) throws BotException {

        if (receive.length() > 2 && "bye".equalsIgnoreCase(receive.strip())) {
            return new QuitCommand();
        } else if (receive.length() > 3 && "list".equalsIgnoreCase(receive.strip())) {
            return new ListCommand();
        } else if (receive.length() > 3 && "mark".equalsIgnoreCase(receive.substring(0,4))) {
            Integer index = Integer.valueOf(receive.substring(4).strip());
            return new MarkCommand(index);
        } else if (receive.length() > 5 && "unmark".equalsIgnoreCase(receive.substring(0,6))) {
            Integer index = Integer.valueOf(receive.substring(6).strip());
            return new UnmarkCommand(index);
        } else if (receive.length() > 3 && "todo".equalsIgnoreCase(receive.substring(0,4))) {
            String desc = receive.substring(4).strip();
            String errToDo = "Sir!!! The description of a todo cannot be empty.";
            if (desc.isBlank()) {
                throw new BotException(errToDo);
            }
            ToDo newTodo = new ToDo(desc);
            return new AddTaskCommand(newTodo, true);

        } else if (receive.length() > 7 && "deadline".equalsIgnoreCase(receive.substring(0,8))) {
            String desc = receive.substring(8).strip();
            String errDeadline = "Sir!!! The description of a deadline cannot be empty.";
            if (desc.isBlank()) {
                throw new BotException(errDeadline);
            }
            String[] stringArr = desc.split(" /by ");

            ArrayList<String> arr = new ArrayList<>(List.of(stringArr));
            String description = arr.get(0);
            arr.remove(0);

            String dateTimeString = String.join(" ", arr);
            try {
                Deadline newDeadline = new Deadline(description, dateTimeString);
                return new AddTaskCommand(newDeadline, true);
            } catch (BotException e) {
                Ui.print(e.getMessage());
                throw new BotException(e.getMessage());
            }


        } else if (receive.length() > 4 && "event".equalsIgnoreCase(receive.substring(0,5))) {
            String desc = receive.substring(5).strip();
            String errEvent = "Sir!!! The description of a event cannot be empty.";
            if (desc.isBlank()) {
                throw new BotException(errEvent);
            }
            String[] stringarr = desc.split(" /from ");
            String[] strarr = stringarr[1].split(" /to ");
            Event newEvent = new Event(stringarr[0], strarr[0], strarr[1]);

            return new AddTaskCommand(newEvent, true);

        } else if (receive.length() > 5 && "delete".equalsIgnoreCase(receive.substring(0,6))) {
            Integer index = Integer.valueOf(receive.substring(6).strip());
            return new DeleteCommand(index);
        } else {
            String takFaham = Ui.UNABLE_TO_UNDERSTAND_QUOTE;
            throw new BotException(takFaham);
        }
    }
}
