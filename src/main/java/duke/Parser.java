package duke;


import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * A Parser has the methods that decode the requests entered by the user and executes the relevant methods that
 * facilitate the requests of the user
 */
public class Parser {
    private Storage storage;

    /**
     * Constructor for the Parser, which takes in a Storage object to facilitate the updating of the storage
     * file each time there is a task executed
     *
     * @param storage The storage object the handles storing of the tasks to a file so that it could be retrieved
     *                for subsequent uses of the bot
     */
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * This method interprets the user input and calls the relevant methods to facilitate the request of the user
     *
     * @param userInput the String entered by the user specifying their request
     * @param list      the TaskList object that stores all the user's tasks
     * @return A String that is to be displayed to the user upon completion of their request
     * @throws IOException
     */
    public String parseAndExecute(String userInput, TaskList list) throws IOException {

        String[] userInputComponents = userInput.split(" ");
        if (userInputComponents.length == 0) {
            return "Your request cannot be empty! Please re-enter your request";
        }

        String requestType = userInputComponents[0];
        try {
            Request request = Request.getRequest(requestType);

            switch (request) {
            case LIST: {
                String firstOutput = list.printItems();
                String secondOutput = list.getTaskDetails();
                updateStorage(list);
                return firstOutput + "\n" + secondOutput;
            }

            case MARK: {
                if (userInputComponents.length != 2) {
                    return "This request requires exactly one task number as the second argument!";
                } try {
                    int taskNumber = Integer.parseInt(userInputComponents[1]);
                    assert taskNumber < list.getNumberOfTasks();
                    String firstOutput = list.markDone(taskNumber);
                    String secondOutput = list.getTaskDetails();
                    updateStorage(list);
                    return firstOutput + "\n" + secondOutput;
                } catch (NumberFormatException e) {
                    return "You have to specify a number representing the task number!";
                }

            }

            case UNMARK: {
                if (userInputComponents.length != 2) {
                    return "This request requires exactly one task number as the second argument!";
                }

                try {
                    int taskNumber = Integer.parseInt(userInputComponents[1]);
                    assert taskNumber < list.getNumberOfTasks();
                    String firstOutput = list.markUndone(taskNumber);
                    String secondOutput = list.getTaskDetails();
                    updateStorage(list);
                    return firstOutput + "\n" + secondOutput;
                } catch (NumberFormatException e) {
                    return "You have to specify a number representing the task number!";
                }
            }

            case TODO: {
                try {
                    assert userInput.length() > 5;
                    String firstOutput = list.addTask(new ToDo(userInput.substring(5).strip()));
                    String secondOutput = list.getTaskDetails();
                    updateStorage(list);
                    return firstOutput + "\n" + secondOutput;
                } catch (StringIndexOutOfBoundsException e) {
                    return "The description of todo cannot be empty!";
                }
            }
            case DEADLINE: {

                String[] splitDeadline = userInput.split("/");
                if (splitDeadline.length != 2) {
                    return "You have to enter a deadline!";
                }
                try {
                    String description = splitDeadline[0].substring(9).strip();
                    String deadline = splitDeadline[1];
                    String firstOutput = list.addTask(new Deadline(description, deadline));
                    String secondOutput = list.getTaskDetails();
                    updateStorage(list);
                    return firstOutput + "\n" + secondOutput;
                } catch (InvalidDateFormatException e) {
                    return "You have to enter the deadline date in this format: dd-Mmm-yyyy";
                }
            }
            case EVENT: {
                String[] splitTimes = userInput.split("/");
                if (splitTimes.length != 3) {
                    return "You have to enter a start and a finish date and time!";
                }
                String description = splitTimes[0].substring(6);
                String startDayTime = splitTimes[1];
                String endDayTime = splitTimes[2];
                try {
                    String firstOutput = list.addTask(new Event(startDayTime, endDayTime, description));
                    String secondOutput = list.getTaskDetails();
                    updateStorage(list);
                    return firstOutput + "\n" + secondOutput;
                } catch (DateTimeParseException e) {
                    return "The date needs to be in this format: dd-Mmm-yyyy" +
                            " and the time needs to be in this format: HHmm";
                }

            }

            case DELETE: {
                if (userInputComponents.length != 2) {
                    return "You have to specify a task number to be deleted!";
                }
                try {
                    String firstOutput = list.deleteTask(Integer.parseInt(userInputComponents[1]));
                    String secondOutput = list.getTaskDetails();
                    updateStorage(list);
                    return firstOutput + "\n" + secondOutput;
                } catch (NumberFormatException e) {
                    return "You have to specify a number representing the task number!";
                }
            }

            case FIND: {
                if (userInputComponents.length != 2) {
                    return "You have to enter a keyword to find a task with a match!";
                }
                String keyword = userInputComponents[1].strip();
                String firstOutput = list.findTask(keyword);
                return firstOutput;
            }

            case HELP: {
               return UI.printAvailableCommands();
            }
            default: {
                return "You may have accidentally entered in an invalid command. Please re-enter!";
            }

            }
        } catch (DukeException e) {
            return "Invalid Duke Request; please re-enter your request!";

        }
    }

    /**
     * Updates the storage file each time the task list is altered
     * @param list The list that stores all the tasks of the user
     */
    private void updateStorage(TaskList list) {
        try {
            storage.updateTasksInFile(list);
        } catch (IOException e) {
            System.out.println("Unable to open storage file");
        }

    }
}


