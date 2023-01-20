package dukes.engine;

import dukes.util.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeEngine mainEngine = new DukeEngine();
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
                    // mainEngine.handleDeadLine(command);
                } else if (splited[0].equals("event")) {
                    try {
                        mainEngine.validateEvent(command);
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
                    // mainEngine.handleEvent(command);
                } else {
                    // mainEngine.addTask(command);
                    System.out.println("Sorry, but I don't know what you means.");
                }
            }
            // mainEngine.echo(command);
            mainEngine.printLine();
            command = sc.nextLine();
        }
        mainEngine.goodbye();
    }

}
