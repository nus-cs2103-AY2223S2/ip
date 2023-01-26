import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);

    private Storage storage;
    private TaskList allTasks;

    public Duke(String[] memoryPathArray) {
        storage = new Storage(memoryPathArray);
        allTasks = new TaskList();
        try {
            storage.loadTasks(allTasks);
        } catch (MemoryFailedException e) {
            Ui.showLoadingError();
            allTasks = new TaskList();
        }
    }

    public void run() {

    }

    public static void main(String[] args) {
        String[] memoryPathArray = {".", "memory.txt"};
        new Duke(memoryPathArray).run();
    }

//    public static void main(String[] args) {
//        loadTasks();
//
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        printLine();
//        System.out.println(logo);
//        System.out.println("Hope you are doing great!");
//        System.out.println("What can I do for you?");
//        printLine();
//
//        boolean promptAgain = true;
//        while (promptAgain) {
//            System.out.println("Enter your prompt below:");
//            String command = sc.nextLine();
//            try {
//                promptAgain = handleCommands(command);
//            } catch (DukeException e) {
//                System.out.println(e.toString());
//            }
//            printLine();
//        }
//
//        saveTasks();
//    }
}
