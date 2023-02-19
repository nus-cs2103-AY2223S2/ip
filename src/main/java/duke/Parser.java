package duke;

import duke.DukeException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;


import java.io.IOException;

public class Parser {
    Storage storage;

    public Parser(Storage storage) {
        this.storage = storage;
    }


    public void parseAndExecute(String userInput, TaskList list) throws IOException {


            String[] userInputComponents = userInput.split(" ");

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
                System.out.println("Invalid duke.Duke duke.Request; please re-enter your request!");
            }


        try {
            storage.updateTasksInFile(list);
        } catch (IOException e) {
            System.out.println("Unable to open storage file");
        }





    }





}


