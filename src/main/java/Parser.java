import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Parser {

    public static LocalDateTime dateFormatter(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static Task translateTaskLogToTask(String taskLog) {
        Task taskToReturn = new Task();
        String[] taskLogCommands = taskLog.split(" \\| ");
        String taskType = taskLogCommands[0];
        String taskStatus = taskLogCommands[1];
        String taskName = taskLogCommands[2];
        switch(taskType) {
        case "T":
            taskToReturn = new ToDo(taskName);
            break;
        case "D":
            String deadline = taskLogCommands[3];
            LocalDateTime formattedDeadline = Parser.dateFormatter(deadline);
            taskToReturn = new Deadline(taskName, formattedDeadline);
            break;
        case "E":
            String[] duration = taskLogCommands[3].split(" - ");
            String startTime = duration[0];
            String endTime = duration[1];
            LocalDateTime formattedStartTime = Parser.dateFormatter(startTime);
            LocalDateTime formattedEndTime = Parser.dateFormatter(endTime);
            taskToReturn = new Event(taskName, formattedStartTime, formattedEndTime);
            break;
        }
        if (taskStatus.equals("1")) {
            taskToReturn.markTask();
        }
        return taskToReturn;
    }

    public static Task translateUserInputToTask(String commandLine) {
        boolean isAbleToReturn = true;
        Task taskToReturn = new Task();
        String taskType = commandLine.split(" ")[0];
        switch (taskType) {
        case "todo":
            try {
                String toDoName = commandLine.substring(5);
                taskToReturn = new ToDo(toDoName);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Please enter a valid todo task format.\n");
                isAbleToReturn = false;
            }
            break;
        case "deadline":
            try {
                String deadlineName = commandLine.split(" /by ")[0].substring(9);
                String deadlineTime = commandLine.split(" /by ")[1];
                taskToReturn = new Deadline(deadlineName, dateFormatter(deadlineTime));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Please enter a valid deadline task format.\n");
                isAbleToReturn = false;
            } catch (DateTimeParseException e) {
                System.out.println("Oops! Please enter deadline according to a valid 'DD/MM/YYYY HH:mm' format.\n");
                isAbleToReturn = false;
            }
            break;
        case "event":
            try {
                String eventName = commandLine.split(" /from ")[0].substring(6);
                String startTime = commandLine.split(" /from ")[1].split(" /to ")[0];
                String endTime = commandLine.split(" /to ")[1];
                taskToReturn = new Event(eventName, dateFormatter(startTime), dateFormatter(endTime));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Please enter a valid deadline task format.\n");
                isAbleToReturn = false;
            } catch (DateTimeParseException e) {
                System.out.println("Oops! Please enter deadline according to a valid 'DD/MM/YYYY HH:mm' format.\n");
                isAbleToReturn = false;
                break;
            }
        }
        if (isAbleToReturn) {
            return taskToReturn;
        } else {
            return null;
        }
    }
}
