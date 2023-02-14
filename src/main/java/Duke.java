import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
public class Duke {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        Storage storage = new Storage();
        String home = System.getProperty("user.home");
        File Task_Data = new File("Task Data.txt");
        try {
          Task_Data.createNewFile();
          list.loadTaskData(Task_Data);
        } catch (FileAlreadyExistsException e){ // nothing should be done if the file already exists
            System.out.println("The file already exists");
        } catch (IOException e) {
            System.out.println("File creation was unsuccessful");

        }



        horizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello there! I am: \n" + logo + "\nWhat can I help you with!");
        System.out.println("You can let me know by typing it below, please!");
        horizontalLine();

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

    public static void horizontalLine() {


        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }


        System.out.print("\n");

    }






}
