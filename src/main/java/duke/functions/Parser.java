package duke.functions;
import duke.dukeexceptions.Missing;
import duke.dukeexceptions.WrongKeyWord;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that understands and handles commands.
 */
public class Parser {

    /**
     * Returns reply string based on.
     * the command from the user.
     *
     * @param input the full command from the user.
     * @param inputArr the full command split in words.
     * @param tasks keeps track of the list of tasks.
     * @param storage stores the list of tasks.
     * @return a string upon understanding the command.
     * @throws Missing if there is missing information regarding date and time.
     * @throws WrongKeyWord if there is a missing key command.
     */
    public static String understandInput(String input, String[] inputArr, TaskList tasks, Storage storage)
            throws Missing, WrongKeyWord {
        String reply;

        Task newTask = null;
        String type = inputArr[0];

        if (inputArr[0].equals("bye")) {
            reply = "bye";
        } else if (inputArr[0].equals("find")) {
            reply = tasks.findTask(inputArr[1]);
        } else if (inputArr[0].equals("list")) {
            reply = tasks.printTasks();
        } else if (inputArr[0].equals("mark")) {
            int index = Integer.valueOf(inputArr[1]) - 1;
            Task task = tasks.getTask(index);
            reply =  task.setDone();
        } else if (inputArr[0].equals("unmark")) {
            int index = Integer.valueOf(inputArr[1]) - 1;
            Task task = tasks.getTask(index);
            reply = task.setNotDone();
        } else if (inputArr[0].equals("delete")) {
            int index = Integer.valueOf(inputArr[1]) - 1;
            Task task = tasks.deleteTask(index);
            reply = task.removeTask();

        } else if (inputArr[0].equals("todo")) {
            input = input.replaceFirst("todo", "");
            if (input.equals("")) {
                throw new Missing("");
            }
            newTask = new ToDo(input);
            type = "T";

            tasks.addTask(newTask);
            reply = newTask.toString();
            storage.writeToFile(input, type);

        } else if (inputArr[0].equals("deadline")) {

            input = input.replaceFirst("deadline", "");
            if (input.equals("")) {
                throw new Missing("");
            }
            String[] taskDate = input.split("/by ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(taskDate[1], formatter);
            String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma"));
            input = taskDate[0] + "(by:" + dateTimeString + ")";
            newTask = new Deadline(input);
            type = "D";

            tasks.addTask(newTask);
            reply = newTask.toString();
            storage.writeToFile(input, type);
        } else if (inputArr[0].equals("event")) {
            input = input.replaceFirst("event", "");
            if (input.equals("")) {
                throw new Missing("");
            }
            String[] taskDate2 = input.split("/from ");
            String[] fromTo = taskDate2[1].split(" /to ");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(fromTo[0], formatter2);
            LocalDateTime toDateTime = LocalDateTime.parse(fromTo[1], formatter2);

            String fromString = fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma"));
            String toString = toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma"));
            input = taskDate2[0] + "(from: " + fromString + " ";
            input = input + "to: " + toString + ")";

            newTask = new Event(input);
            type = "E";

            tasks.addTask(newTask);
            reply = newTask.toString();
            storage.writeToFile(input, type);
        } else {
            throw new WrongKeyWord("");
        }
        return reply;
    }
}
