package duke;


import java.io.IOException;

public class Parser {
    private Storage storage;

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
                UI.printHorizontalLine();
                list.printItems();
                list.getTaskDetails();
                UI.printHorizontalLine();
                break;
            }

            case MARK: {
                if (userInputComponents.length != 2) {
                    System.out.println("This request requires exactly one task number as the second argument!");
                    return;
                }
                int taskNumber = Integer.parseInt(userInputComponents[1]);
                UI.printHorizontalLine();
                list.markDone(taskNumber);
                list.getTaskDetails();
                UI.printHorizontalLine();
                break;
            }

            case UNMARK: {
                if (userInputComponents.length != 2) {
                    System.out.println("This request requires exactly one task number as the second argument!");
                    return;
                }
                int taskNumber = Integer.parseInt(userInputComponents[1]);
                UI.printHorizontalLine();
                list.markUndone(taskNumber);
                list.getTaskDetails();
                UI.printHorizontalLine();
                break;
            }

            case TODO: {
                try {
                    UI.printHorizontalLine();
                    list.addTask(new ToDo(userInput.substring(5).strip()));
                    list.getTaskDetails();
                    UI.printHorizontalLine();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("The description of todo cannot be empty!");
                }
                break;
            }
            case DEADLINE: {
                String[] splitDeadline = userInput.split("/");
                String description = splitDeadline[0].substring(9).strip();
                String deadline = splitDeadline[1];
                UI.printHorizontalLine();
                list.addTask(new Deadline(description, deadline));
                list.getTaskDetails();
                UI.printHorizontalLine();
                break;
            }
            case EVENT: {
                String[] splitTimes = userInput.split("/");
                String description = splitTimes[0].substring(6);
                String startDayTime = splitTimes[1];
                String endDayTime = splitTimes[2];
                UI.printHorizontalLine();
                list.addTask(new Event(startDayTime, endDayTime, description));
                list.getTaskDetails();
                UI.printHorizontalLine();
                break;
            }

            case DELETE: {
                if (userInputComponents.length != 2) {
                    System.out.println("You have to specify a task number to be deleted!");
                    return;
                }
                UI.printHorizontalLine();
                list.deleteTask(Integer.parseInt(userInputComponents[1]));
                UI.printHorizontalLine();
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


