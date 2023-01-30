package duke;

/**
 * Parser for commands
 */
public class Parser {

    private String listALl(TaskList tl) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tl.getList().size(); i++) {
            str.append(String.format("%d: %s\n", i + 1, tl.getList().get(i)));
        }
        return str.toString();
    }

    /**
     * Parses and runs the entered command.
     *
     * @param taskList TaskList to be used for commands
     * @param line     line to be parsed
     * @return String  response
     */
    public String parse(TaskList taskList, String line) {
        String[] split = line.split(" ");
        String command = split[0];
        switch (command) {
        case "bye":
            return "Bye, hope to see you again";
        case "list":
            return listALl(taskList);
        case "mark": {
            int number = Integer.parseInt(split[1]) - 1;
            taskList.mark(number);
            return listALl(taskList);
        }
        case "unmark": {
            int number = Integer.parseInt(split[1]) - 1;
            taskList.unmark(number);
            return listALl(taskList);
        }
        case "todo": {
            taskList.addTodo(split[1]);
            return listALl(taskList);
        }
        case "deadline": {
            split = line.split(" ");
            if (split.length < 4) {
                return "Invalid format";
            }
            taskList.addDeadline(split[1], split[3]);
            return listALl(taskList);
        }
        case "event": {
            split = line.split(" ");
            if (split.length < 6) {
                return "Invalid format";
            }
            taskList.addEvent(split[1], split[3], split[5]);
            return listALl(taskList);
        }
        case "find": {
            return listALl(taskList.find(split[1]));
        }
        case "delete": {
            if (split.length != 2) {
                return "Invalid format";
            }
            int itemIndex;
            try {
                itemIndex = Integer.parseInt(split[1]);
                taskList.delete(itemIndex - 1);
                return "Removal successful. New list:\n" + listALl(taskList);
            } catch (IndexOutOfBoundsException e) {
                return ("Item does not exist");
            } catch (NumberFormatException e) {
                return split[1] + " is not a number";
            }
        }
        default:
            return "Command not found";
        }
    }

}
