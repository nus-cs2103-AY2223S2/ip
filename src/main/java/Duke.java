import command.Command;
import dukeexeption.DukeException;
import dukeexeption.InvalidArgumentException;
import parser.Request;
import storage.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    /**
     * Prints the formatted response in the console.
     * @param response  the string to be printed
     */
    private static void printFormattedResponse(String response) {
        final int INDENTS = 4;

        String lineAddedResponse = "____________________________________________________________\n"
                + " " + response.replace("\n", "\n" + " ")
                + "\n____________________________________________________________\n";
        String indentedResponse = " ".repeat(INDENTS)
                + lineAddedResponse.replace("\n", "\n" + " ".repeat(INDENTS));

        System.out.println(indentedResponse);;
    }

    private static File linkLocalDriveOnStartup(String filepath) {
        if (filepath.trim().equals("")) {
            throw new InvalidArgumentException("Filepath cannot be empty.");
        }
        File dataFile =  new File(filepath);
        dataFile.getParentFile().mkdirs();

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException error) {
                error.printStackTrace();
            }
        } else if (dataFile.exists() && dataFile.isDirectory()) {
            throw new InvalidArgumentException("Directory path provided, change filepath to a path to a file.");
        }

        return dataFile;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String INTRODUCTION_MESSAGE = "Hello! I'm Duke\n"
                + "What can I do for you?";
        final String EXITING_MESSAGE = "Bye. Hope to see you again soon!";

        File tasksFile = linkLocalDriveOnStartup("./data/tasks.txt");

        System.out.println(logo);
        printFormattedResponse(INTRODUCTION_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(tasksFile);
        while(true) {
            String request = scanner.nextLine();
            if (request.equals("bye")) {
                taskList.writeTasksToFile();
                break;
            }
            try {
                Command command = new Request(request).parse();
                String reply = command.run(taskList);
                printFormattedResponse(reply);
            } catch(DukeException error) {
                printFormattedResponse(error.toString());
            }
        }

        printFormattedResponse(EXITING_MESSAGE);
    }
}
