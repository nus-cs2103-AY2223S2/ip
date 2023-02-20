package duke;


import java.io.IOException;

/**
 * A Parser has the methods that decode the requests entered by the user and executes the relevant methods that
 * facilitate the requests of the user
 */
public class Parser {
    Storage storage;

    /**
     * Constructor for the Parser, which takes in a Storage object to facilitate the updating of the storage
     * file each time there is a task executed
     * @param storage The storage object the handles storing of the tasks to a file so that it could be retrieved
     *                for subsequent uses of the bot
     */
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * This method interprets the user input and calls the relevant methods to facilitate the request of the user
     * @param userInput the String entered by the user specifying their request
     * @param list the TaskList object that stores all the user's tasks
     * @throws IOException
     */
    public void parseAndExecute(String userInput, TaskList list) throws IOException {


            String[] userInputComponents = userInput.split(" ");
            if (userInputComponents.length == 0 || userInput ==) {
                System.out.println("Your request cannot be empty! Please re-enter your request");
                return;
            }

            String requestType = userInputComponents[0];
            try {
                Request request = Request.getRequest(requestType);

                switch (request) {
                    case LIST: {
                        UI.horizontalLine();
                        list.printItems();
                        list.getTaskDetails();
                        UI.horizontalLine();
                        break;
                    }

                    case MARK: {
                        if (userInputComponents.length != 2) {
                            System.out.println("This request requires exactly one task number as the second argument!");
                            return;
                        }
                        int taskNumber = Integer.parseInt(userInputComponents[1]);
                        UI.horizontalLine();
                        list.markDone(taskNumber);
                        list.getTaskDetails();
                        UI.horizontalLine();
                        break;
                    }

                    case UNMARK: {
                        if (userInputComponents.length != 2) {
                            System.out.println("This request requires exactly one task number as the second argument!");
                            return;
                        }
                        int taskNumber = Integer.parseInt(userInputComponents[1]);
                        UI.horizontalLine();
                        list.markUndone(taskNumber);
                        list.getTaskDetails();
                        UI.horizontalLine();
                        break;
                    }

                    case TODO: {
                        try {
                            UI.horizontalLine();
                            list.addTask(new ToDo(userInput.substring(5).strip()));
                            list.getTaskDetails();
                            UI.horizontalLine();
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("The description of todo cannot be empty!");
                        }
                        break;
                    }
                    case DEADLINE: {
                        String[] splitDeadline = userInput.split("/");
                        String description = splitDeadline[0].substring(9).strip();
                        String deadline = splitDeadline[1];
                        UI.horizontalLine();
                        list.addTask(new Deadline(description, deadline));
                        list.getTaskDetails();
                        UI.horizontalLine();
                        break;
                    }
                    case EVENT: {
                        String[] splitTimes = userInput.split("/");
                        String description = splitTimes[0].substring(6);
                        String startDayTime = splitTimes[1];
                        String endDayTime = splitTimes[2];
                        UI.horizontalLine();
                        list.addTask(new Event(startDayTime, endDayTime, description));
                        list.getTaskDetails();
                        UI.horizontalLine();
                        break;
                    }

                    case DELETE: {
                        if (userInputComponents.length != 2) {
                            System.out.println("You have to specify a task number to be deleted!");
                            return;
                        }
                        UI.horizontalLine();
                        list.deleteTask(Integer.parseInt(userInputComponents[1]));
                        UI.horizontalLine();
                        break;
                    }
                    default: {
                        System.out.println("You may have accidentally entered in an invalid command. Please re-enter!");
                    }

                }
            } catch (DukeException e) {
                System.out.println("Invalid Duke Request; please re-enter your request!");
            }


        try {
            storage.updateTasksInFile(list);
        } catch (IOException e) {
            System.out.println("Unable to open storage file");
        }





    }





}


