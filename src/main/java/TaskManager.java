import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskManager {

    private static final String TASKS_FOLDER_PATH = "data";
    private static final String TASKS_FILE_NAME = "duke_tasks.txt";
    
    private FileManager tasksFileManager = new FileManager(TASKS_FOLDER_PATH, TASKS_FILE_NAME);

    private ArrayList<Task> tasks = tasksFileManager.readTasksFromFile();

    private LocalDateTime parseDateTime(String dateTimeText) throws DukeException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kkmm");
        LocalDateTime dateTime = null;
        boolean isInvalid = false;

        try {
            dateTime = LocalDateTime.parse(dateTimeText, dateTimeFormatter);

        } catch (DateTimeParseException e) {
            isInvalid = true;

        } finally {

            if (isInvalid) {
                throw new DukeInvalidCommandException(
                        "Oops! Invalid date-time format. It should be DD-MM-YYYY hhmm (24-hrs format)");
            }
            
            return dateTime;
        }
    }

    private void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("You do not have any tasks added to the list.");
        } else {
            System.out.println("Listing all tasks...");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + ") " + this.tasks.get(i));
            }
        }
    }

    private void processEmptyCommand() throws DukeException {
        throw new DukeInvalidCommandException("You have not entered anything...");
    }

    private void processList(String[] cmdParts) throws DukeException {

        if (cmdParts.length == 1) {
            this.listTasks();
        } else {
            throw new DukeInvalidCommandException("Sorry. That is an invalid command :/");
        }

    }

    private void processDelete(String[] cmdParts) throws DukeException {

        if (cmdParts.length != 2) {
            throw new DukeInvalidCommandException("Sorry... That is an invalid command :/");
        }

        try {
            int taskNumber = Integer.parseInt(cmdParts[1]);
            boolean isValidTaskNumber =
                    (taskNumber > 0 && taskNumber <= this.tasks.size());

            if (isValidTaskNumber) {
                Task removedTask = this.tasks.remove(taskNumber - 1);
                tasksFileManager.writeTasksToFile(this.tasks);
                System.out.println("I have removed Task " + taskNumber + " from the list.");
                System.out.println(removedTask);
                System.out.println("You now have " + this.tasks.size() + " task(s) in the list.");

            } else {
                throw new DukeInvalidCommandException("Sorry... That is an invalid task number :/");
            }

        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("Sorry... That is an invalid task number :/");
        }

    }

    private void processMarkUnmark(String cmdHeader, String[] cmdParts) throws DukeException {

        if (cmdParts.length != 2) {
            throw new DukeInvalidCommandException("Sorry... That is an invalid command :/");
        }

        try {
            int taskNumber = Integer.parseInt(cmdParts[1]);
            boolean isValidTaskNumber =
                    (taskNumber > 0 && taskNumber <= this.tasks.size());

            if (isValidTaskNumber) {
                if (cmdHeader.equals("mark")) {
                    this.tasks.get(taskNumber - 1).mark();
                    System.out.println("I have marked Task " + taskNumber + " as done.");
                    System.out.println(this.tasks.get(taskNumber - 1));

                } else {
                    this.tasks.get(taskNumber - 1).unmark();
                    System.out.println("I have marked Task " + taskNumber + " as undone.");
                    System.out.println(this.tasks.get(taskNumber - 1));

                }

                tasksFileManager.writeTasksToFile(this.tasks);

            } else {
                throw new DukeInvalidCommandException("Sorry... That is an invalid task number :/");
            }

        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("Sorry... That is an invalid task number :/");
        }

    }

    private void processToDo(String[] cmdParts) throws DukeException {

        if (cmdParts.length == 1) {
            throw new DukeEmptyArgumentException("Uh-oh. The description of the to-do is empty :/");
        }

        String[] descWords = Arrays.copyOfRange(cmdParts, 1, cmdParts.length);
        String description = String.join(" ", descWords);

        Task toDo = new ToDo(description);
        this.tasks.add(toDo);
        tasksFileManager.writeTasksToFile(this.tasks);
        System.out.println("I have added the to-do to the list.");
        System.out.println(toDo);

    }

    private void processDeadline(String[] cmdParts) throws DukeException {

        if (cmdParts.length == 1) {
            throw new DukeInvalidCommandException("Uh-oh. The description of the deadline is empty :/");
        }

        String[] cmdRest = Arrays.copyOfRange(cmdParts, 1, cmdParts.length);
        List<String> descList = Arrays.asList(cmdRest);

        int byIndex = descList.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no 'by' given for the deadline :/");
        }

        if (byIndex == 0) {
            throw new DukeEmptyArgumentException("Uh-oh. The description of the deadline is empty :/");
        }

        if (cmdRest.length - 1 == byIndex) {
            throw new DukeEmptyArgumentException("Uh-oh. The 'by' of the deadline is empty :/");
        }

        String[] descWords = Arrays.copyOfRange(cmdRest, 0, byIndex);
        String description = String.join(" ", descWords);

        String[] byArray = Arrays.copyOfRange(cmdRest, byIndex + 1, cmdRest.length);
        String by = String.join(" ", byArray);

        LocalDateTime byDateTime = this.parseDateTime(by);

        if (byDateTime != null) {
            Task deadline = new Deadline(description, byDateTime);
            this.tasks.add(deadline);
            tasksFileManager.writeTasksToFile(this.tasks);
            System.out.println("I have added the deadline to the list.");
            System.out.println(deadline);

        }
    }

    private void processEvent(String[] cmdParts) throws DukeException {

        if (cmdParts.length == 1) {
            throw new DukeInvalidCommandException("Uh-oh. The description of the event is empty :/");
        }

        String[] cmdRest = Arrays.copyOfRange(cmdParts, 1, cmdParts.length);
        List<String> descList = Arrays.asList(cmdRest);

        int fromIndex = descList.indexOf("/from");
        if (fromIndex == -1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no 'from' given for the event :/");
        }

        int toIndex = descList.indexOf("/to");
        if (toIndex == -1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no 'to' given for the event :/");
        }

        if (Math.min(fromIndex, toIndex) == 0) {
            throw new DukeEmptyArgumentException("Uh-oh. The description of the event is empty :/");
        }

        if (toIndex - fromIndex == 1 || cmdRest.length - 1 == fromIndex) {
            throw new DukeEmptyArgumentException("Uh-oh. The 'from' of the deadline is empty :/");
        }

        if (fromIndex - toIndex == 1 || cmdRest.length - 1 == toIndex) {
            throw new DukeEmptyArgumentException("Uh-oh. The 'to' of the event is empty :/");
        }

        String[] descWords =
                Arrays.copyOfRange(cmdRest, 0, Math.min(fromIndex, toIndex));
        String description = String.join(" ", descWords);

        String[] fromArray;
        String[] toArray;

        if (fromIndex > toIndex) {
            fromArray =
                    Arrays.copyOfRange(cmdRest, fromIndex + 1, cmdRest.length);
            toArray =
                    Arrays.copyOfRange(cmdRest, toIndex + 1, fromIndex);

        } else {
            fromArray =
                    Arrays.copyOfRange(cmdRest, fromIndex + 1, toIndex);
            toArray =
                    Arrays.copyOfRange(cmdRest, toIndex + 1, cmdRest.length);
        }

        String from = String.join(" ", fromArray);
        String to = String.join(" ", toArray);

        LocalDateTime fromDateTime = this.parseDateTime(from);
        LocalDateTime toDateTime = this.parseDateTime(to);

        if (fromDateTime != null && toDateTime != null) {
            if (toDateTime.isBefore(fromDateTime)) {
                throw new DukeInvalidCommandException(
                        "Uh-oh, those are incompatible dates and times for 'from' and 'to' :/");
            }

            Task event = new Event(description, fromDateTime, toDateTime);
            this.tasks.add(event);
            tasksFileManager.writeTasksToFile(this.tasks);
            System.out.println("I have added the event to the list.");
            System.out.println(event);

        }

    }

    private void processUnknownCommand() throws DukeException {
        throw new DukeInvalidCommandException("Sorry... I did not understand that :/");
    }

    public void processCommand(String command) throws DukeException {
        if (command.equals("")) {
            processEmptyCommand();
        }

        String[] cmdParts = command.split(" ");
        String cmdHeader = cmdParts[0].toLowerCase();

        if (cmdHeader.equals("list")) {
            processList(cmdParts);

        } else if (cmdHeader.equals("delete")) {
            processDelete(cmdParts);

        } else if (cmdHeader.equals("mark") || cmdHeader.equals("unmark")) {
            processMarkUnmark(cmdHeader, cmdParts);

        } else if (cmdHeader.equals("todo")) {
            processToDo(cmdParts);

        } else if (cmdHeader.equals("deadline")) {
            processDeadline(cmdParts);

        } else if (cmdHeader.equals("event")) {
            processEvent(cmdParts);

        } else {
            processUnknownCommand();

        }

    }
    
}
