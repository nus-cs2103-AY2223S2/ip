package dukes.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import dukes.util.*;
import java.util.*;

public class Duke {

    public static final String FILE_PATH = "data/duke.txt";

    public static void main(String[] args) throws FileNotFoundException {
        // You should read and load the task at the START of the Duke Engine!!!
        DukeEngine mainEngine = new DukeEngine();
        try {
            File f = new File(FILE_PATH);
            Scanner fileScan = new Scanner(f);
            List<Task> taskList = new ArrayList<>();
            while (fileScan.hasNext()) {
                Task currTask = DukeEngine.fetchTask(fileScan.nextLine());
                taskList.add(currTask);
            }
            mainEngine.setTaskList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in location data/duke.txt");
        }
        // Then read input per normal
        Scanner sc = new Scanner(System.in);
        mainEngine.greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            mainEngine.printLine();
            if (command.equals("list")) {
                mainEngine.listTask();
            } else {
                String[] splited = command.split(" ");
                // Here in input.txt, empty line is seen as last situation
                if (splited.length == 0) {
                    System.out.println("Oops, you have key in nothing!");
                } else if (splited[0].equals("mark")) {
                    // 0 = markDone, 1 = markUnDone, 2 = delete
                    try {
                        mainEngine.validateMark(command, 0);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                } else if (splited[0].equals("unmark")) {
                    try {
                        mainEngine.validateMark(command, 1);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                } else if (splited[0].equals("delete")) {
                    try {
                        mainEngine.validateMark(command, 2);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                } else if (splited[0].equals("todo")) {
                    try {
                        mainEngine.validateToDo(command);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                } else if (splited[0].equals("deadline")) {
                    try {
                        mainEngine.validateDeadLine(command);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                } else if (splited[0].equals("event")) {
                    try {
                        mainEngine.validateEvent(command);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                } else {
                    System.out.println("Sorry, but I don't know what you means.");
                }
                // Here need to write into hard disk
                String currWriteIn = mainEngine.generateTaskList();
                File directory = new File("data/");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                File writeFile = new File(FILE_PATH);
                try {
                    FileWriter fw = new FileWriter(FILE_PATH);
                    fw.write(currWriteIn);
                    fw.close();
                } catch(IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            // mainEngine.echo(command);
            mainEngine.printLine();
            command = sc.nextLine();
        }
        mainEngine.goodbye();
        sc.close();
    }

}
