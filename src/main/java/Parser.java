import java.io.StringBufferInputStream;

public class Parser {

    public void executeCommand(String userInput, TaskList list) throws StringIndexOutOfBoundsException {
        String[] userInputComponents = userInput.split(" ");
        String requestType = userInputComponents[0];
        try {
            Request request = Request.getRequest(requestType);

            switch (request) {
                case LIST: {
                    list.printItems();
                    list.getTaskDetails();

                }

                case MARK: {
                    int taskNumber = Integer.parseInt(userInputComponents[1]);

                    list.markDone(taskNumber);
                    list.getTaskDetails();
                }

                case UNMARK: {
                    int taskNumber = Integer.parseInt(userInputComponents[1]);
                    list.markUndone(taskNumber);
                    list.getTaskDetails();
                }

                case TODO: {
                    try {
                        list.addTask(new ToDo(userInput.substring(5)));
                        list.getTaskDetails();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("The description of todo cannot be empty!");
                        throw new StringIndexOutOfBoundsException();
                    }
                }
                case DEADLINE: {
                    String[] splitDeadline = userInput.split("/");
                    String description = splitDeadline[0].substring(9);
                    String deadline = splitDeadline[1];
                    list.addTask(new Deadline(description, deadline));
                    list.getTaskDetails();
                }
                case EVENT: {
                    String[] splitTimes = userInput.split("/");
                    String description = splitTimes[0].substring(6);
                    String startDayTime = splitTimes[1];
                    String endDayTime = splitTimes[2];
                    list.addTask(new Event(startDayTime, endDayTime, description));
                    list.getTaskDetails();
                }

                case DELETE: {
                    list.deleteTask(Integer.parseInt(userInputComponents[1]));
                }
                default: {
                    System.out.println("You may have accidentally entered in an invalid command. Please re-enter!");

                }
            }
        } catch (DukeException e) {
            System.out.println("Invalid Duke Request; please re-enter your request!");
        }
    }
}
