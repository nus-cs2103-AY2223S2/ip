import java.util.Scanner;

public class Parser {


    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();
    while (userInput != "bye") {
        String[] userInputComponents = userInput.split(" ");
        String requestType = userInputComponents[0];
        try {
            Request request = Request.getRequest(requestType);

            switch (request) {
                case LIST: {
                    horizontalLine();
                    list.printItems();
                    list.getTaskDetails();
                    horizontalLine();
                    userInput = scanner.nextLine();
                    logTaskData(list);
                    continue;
                }

                case MARK: {
                    int taskNumber = Integer.parseInt(userInputComponents[1]);
                    horizontalLine();
                    list.markDone(taskNumber);
                    list.getTaskDetails();
                    horizontalLine();
                    userInput = scanner.nextLine();
                    logTaskData(list);
                    continue;
                }

                case UNMARK: {
                    int taskNumber = Integer.parseInt(userInputComponents[1]);
                    horizontalLine();
                    list.markUndone(taskNumber);
                    list.getTaskDetails();
                    horizontalLine();
                    userInput = scanner.nextLine();
                    logTaskData(list);
                    continue;

                }

                case TODO: {
                    try {
                        horizontalLine();
                        list.addTask(new ToDo(userInput.substring(5)));
                        list.getTaskDetails();
                        horizontalLine();
                        logTaskData(list);
                        userInput = scanner.nextLine();

                        continue;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("The description of todo cannot be empty!");
                        userInput = scanner.nextLine();
                        continue;
                    }
                }
                case DEADLINE: {
                    String[] splitDeadline = userInput.split("/");
                    String description = splitDeadline[0].substring(9);
                    String deadline = splitDeadline[1];
                    horizontalLine();
                    list.addTask(new Deadline(description, deadline));
                    list.getTaskDetails();
                    horizontalLine();
                    logTaskData(list);
                    userInput = scanner.nextLine();

                    continue;
                }
                case EVENT: {
                    String[] splitTimes = userInput.split("/");
                    String description = splitTimes[0].substring(6);
                    String startDayTime = splitTimes[1];
                    String endDayTime = splitTimes[2];
                    horizontalLine();
                    list.addTask(new Event(startDayTime, endDayTime, description));
                    list.getTaskDetails();
                    horizontalLine();
                    logTaskData(list);
                    userInput = scanner.nextLine();

                    continue;
                }

                case DELETE: {
                    horizontalLine();
                    list.deleteTask(Integer.parseInt(userInputComponents[1]));
                    horizontalLine();
                    logTaskData(list);
                    userInput = scanner.nextLine();

                    continue;
                }
                default: {
                    System.out.println("You may have accidentally entered in an invalid command. Please re-enter!");
                    userInput = scanner.nextLine();
                }

            }
        } catch (DukeException e){
            System.out.println("Invalid Duke Request; please re-enter your request!");
        }







    }

            System.out.println("Bye for now! Hope to see you again!");

}

}
