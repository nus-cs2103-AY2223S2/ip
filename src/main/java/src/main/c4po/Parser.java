package src.main.c4po;


import java.util.ArrayList;
import java.util.List;

/**
 * Parser class allows the bot to understand user commands and provides
 * the right commands for execution in main loop
 */
public class Parser {

    /**
     * Instantiates an instance of Parser
     * Parser helps make sense of user commands and creates an instance of executable command
     * for the bot to execute
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
        } else if (receive.length() > 7 && "priority".equalsIgnoreCase(receive.strip())) {
            return new GetPrioritySortedCommand();
        }else if (receive.length() > 3 && "mark".equalsIgnoreCase(receive.substring(0,4))) {
            Integer index = Integer.valueOf(receive.substring(4).strip());
            return new MarkCommand(index);
        } else if (receive.length() > 5 && "unmark".equalsIgnoreCase(receive.substring(0,6))) {
            Integer index = Integer.valueOf(receive.substring(6).strip());
            return new UnmarkCommand(index);
        } else if (receive.length() > 3 && "todo".equalsIgnoreCase(receive.substring(0,4))) {

            String desc = receive.substring(4).strip();
            String errToDo = "Sir!!! The description of a todo or priority cannot be empty.";
            if (desc.isBlank()) {
                throw new BotException(errToDo);
            }

            try {
                String[] arr = desc.split(" /priority ");
                Integer priority;
                if (arr[1].equals("")) {
                    priority = 0;
                } else {
                    priority = Integer.valueOf(arr[1]);
                }


                ToDo newTodo = new ToDo(arr[0], priority);
                return new AddTaskCommand(newTodo, true);
            } catch (Exception e) {
                throw new BotException("Priority cannot be empty");
            }


        } else if (receive.length() > 7 && "deadline".equalsIgnoreCase(receive.substring(0,8))) {
            String desc = receive.substring(8).strip();
            String errDeadline = "Sir!!! The description of a deadline cannot be empty.";
            if (desc.isBlank()) {
                throw new BotException(errDeadline);
            }
            String[] stringArr = desc.split(" /by ");

            String[] strArrPriority = stringArr[1].split(" /priority ");
            ArrayList<String> arr = new ArrayList<>(List.of(stringArr));
            String description = arr.get(0);
            arr.remove(0);
            Integer priority;
            if (strArrPriority[1].equals("")) {
                priority = 0;
            } else {
                priority = Integer.valueOf(strArrPriority[1]);
            }

            String dateTimeString = strArrPriority[0];
            try {
                Deadline newDeadline = new Deadline(description, dateTimeString, priority);
                return new AddTaskCommand(newDeadline, true);
            } catch (BotException e) {
                Ui.print(e.getMessage());
                throw new BotException(e.getMessage() + " - Note, priority cannot be empty");
            }


        } else if (receive.length() > 4 && "event".equalsIgnoreCase(receive.substring(0,5))) {
            String desc = receive.substring(5).strip();
            String errEvent = "Sir!!! The description of a event cannot be empty.";
            if (desc.isBlank()) {
                throw new BotException(errEvent);
            }
            String[] stringArr = desc.split(" /from ");
            String[] strArr = stringArr[1].split(" /to ");
            try {
                String[] strArrPriority = strArr[1].split(" /priority ");
                Integer priority;
                if (strArrPriority[1].equals("")) {
                    priority = 0;
                } else {
                    priority = Integer.valueOf(strArrPriority[1]);
                }
                Event newEvent = new Event(stringArr[0], strArr[0],
                        strArrPriority[0],priority );

                return new AddTaskCommand(newEvent, true);
            } catch (Exception e) {
                throw new BotException("Note, description and priority of event cannot be empty.");
            }
        } else if (receive.length() > 5 && "delete".equalsIgnoreCase(receive.substring(0,6))) {
            Integer index = Integer.valueOf(receive.substring(6).strip());
            return new DeleteCommand(index);
        } else if (receive.length() > 4 && "/help".equalsIgnoreCase(receive.substring(0,5))) {
            return new HelpCommand();
        } else if (receive.length() > 3 && "find".equalsIgnoreCase(receive.substring(0,4))) {
            String keywords = receive.substring(4).strip();

            String errKeyword = "Sir!!! The keywords to find cannot be empty.";
            if (keywords.isBlank()) {
                throw new BotException(errKeyword);
            }
            String[] stringArr = keywords.split(" ");
            return new FindCommand( new ArrayList<>(List.of(stringArr)));

        } else {
            throw new BotException(Ui.UNABLE_TO_UNDERSTAND_QUOTE);
        }
    }
}
