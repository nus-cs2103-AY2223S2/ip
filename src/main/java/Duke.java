import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Path;
import java.util.Scanner;

public class Duke {
    static String processCommand(String command, TaskList taskList) throws DukeException {
        String[] commandArr = command.split(" ");
        switch (commandArr[0]) {
            case "list":
                return taskList.toString();
            case "mark":
                return taskList.markTask(Integer.parseInt(commandArr[1]));
            case "unmark":
                return taskList.unmarkTask(Integer.parseInt(commandArr[1]));
            case "delete":
                return taskList.deleteTask(Integer.parseInt(commandArr[1]));
        }
        return taskList.parseCommand(command);
    }

    static String greetings() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    static String goodbye() {
        return "Bye. Hope to see you soon!";
    }

    static TaskList getTaskList(String filePath, TaskList taskList) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String task = sc.nextLine().trim();
            taskList.addTask(parseTask(task));
        }
        return taskList;
    }

    static Task parseTask(String task) {
        String[] taskArray = task.split("\\|");
        String taskType = taskArray[0].trim();
        if (taskType.equals("T")) {
            return new ToDo(taskArray[2].trim(), taskArray[1].trim().equals("X"));
        } else if (taskType.equals("D")) {
            return new Deadline(taskArray[2].trim(), taskArray[1].trim().equals("X"),
                    taskArray[3].trim().substring(4));
        } else {
            return new Event(taskArray[2].trim(), taskArray[1].trim().equals("X"),
                    taskArray[3].trim().substring(6), taskArray[4].trim().substring(4));
        }
    }

    static void saveTasksInFile(String filePath, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.getListOfTasks());
        fw.close();
    }

    static String display(String response) {
        String horizontalLine = "\t______________________________________\n";
        String[] responseArr = response.split("\\r?\\n");
        StringBuilder responseFinal = new StringBuilder();
        //add indentation
        for (String r: responseArr) {
            responseFinal.append("\t").append(r).append("\n");
        }
        return String.format("%s%s%s", horizontalLine, responseFinal, horizontalLine);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(display(greetings()));
        String command = "", printable = "";
        TaskList taskList = new TaskList();

        String directoryPath = "../../../data";
        String filePath = "../../../data/duke.txt";

        if (!Files.exists(Path.of(directoryPath))) {
            new File(directoryPath).mkdirs();
        }

        try {
            taskList = getTaskList(filePath, taskList);
        } catch (FileNotFoundException e) {
            new File(filePath);
        }

        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                printable = processCommand(command, taskList);
            } catch (Exception e) {
                printable = "OOPS! " + e.getMessage();
            } finally {
                System.out.println(display(printable));
            }
        }
        try {
            saveTasksInFile(filePath, taskList);
        } catch (IOException e) {
            System.out.println("Error saving task list!");
        }
        System.out.println(display(goodbye()));
    }
}
