import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static boolean handleCommands(String rawCommand, TaskList allTasks) throws DukeException {
        int commandIndex = rawCommand.indexOf(' ');
        String command = rawCommand;
        String arguments = "";
        if (commandIndex != -1) {
            // There is a space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex + 1).trim();
        }

        switch (command) {
        case "bye":
            Ui.printGoodbye();
//            System.out.println("Sad...Alright bye!");
            return false;
        case "list":
            Ui.printList(allTasks);
            break;
        case "mark":
            try {
                int markIndex = Integer.parseInt(arguments) - 1;
                allTasks.changeTaskCompletionStatus(markIndex, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.MARK);
            }
            break;
        case "unmark":
            try {
                int unmarkIndex = Integer.parseInt(arguments) - 1;
                allTasks.changeTaskCompletionStatus(unmarkIndex, false);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.UNMARK);
            }
            break;
        case "todo":
            if (arguments.trim().equals("")) {
                throw new IlegalCommandException(Commands.TODO);
            }
            allTasks.addToList(arguments, TaskType.TODO, null, null, false, true);
            break;
        case "deadline":
            try {
                int slashIndex = arguments.indexOf('/');
                String dateByString = arguments.substring(slashIndex + 4);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateBy = LocalDateTime.parse(dateByString, dateFormat);
                allTasks.addToList(arguments.substring(0, slashIndex - 1), TaskType.DEADLINE, null, dateBy, false, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.DEADLINE);
            }
            break;
        case "event":
            try {
                int firstSlashIndex = arguments.indexOf('/');
                String startAndEnd = arguments.substring(firstSlashIndex + 6);
                int secondSlashIndex = startAndEnd.indexOf('/');
                String startString = startAndEnd.substring(0, secondSlashIndex - 1);
                String endString = startAndEnd.substring(secondSlashIndex + 4);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(startString, dateFormat);
                LocalDateTime end = LocalDateTime.parse(endString, dateFormat);
                // TODO: Check if start date is after end date
                allTasks.addToList(arguments.substring(0, firstSlashIndex - 1), TaskType.EVENT, start, end, false, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.EVENT);
            }
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(arguments) - 1;
                allTasks.deleteTask(deleteIndex);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.DELETE);
            }
            break;
        default:
            throw new IlegalCommandException(Commands.UNRECOGNIZED);
        }
        return true;
    }

}
