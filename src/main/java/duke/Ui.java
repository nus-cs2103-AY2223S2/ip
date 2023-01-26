package duke;

import duke.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greeting = "Hello from\n" + logo;
    private static final String list = "duke.Duke presents tasks in your list: ";
    private static final String bye = "duke.Duke says bye bye ~~";

    private BufferedReader bf;
    public Ui() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getLine() {
        try {
            return bf.readLine();
        } catch (IOException err) {
            System.out.println("Error in analyzing inputs");
            return "";
        }
    }

    public void close() throws IOException {
        bf.close();
    }

    public void greet() {
        System.out.println(greeting);
    }

    public void goodBye() {
        lineUI();
        System.out.println(bye);
        lineUI();
    }

    public void listCommand() {
        ArrayList<Task> arr = TaskList.getList();
        if (arr.size() == 0) {
            System.out.println("duke.Duke sees no tasks in your list");
        }
        int cnt = 1;
        while (cnt <= arr.size()) {
            Task item = arr.get(cnt - 1);
            System.out.println(cnt + "." + item.toString());
            cnt++;
        }
    }

     static void todoCommand(String str, Storage storage) throws IOException {
        try {
            Task toDo = new ToDo(Parser.getName(str, 5));
            TaskList.addTask(toDo);
            storage.load();
            lineUI();
            System.out.println("Got it. I've added this task:");
            System.out.println(toDo);
            System.out.println("Now you have " + TaskList.getList().size() + " tasks in the list.");
            lineUI();
        } catch (MissingArgumentsException | MissingNameException err) {
            System.out.println(err.getMessage());
        }
    }

     static void eventCommand(String str, Storage storage) throws IOException {
        try {
            String[] foo;
            try {
                foo = Parser.separate(str.substring(6));
            } catch (IndexOutOfBoundsException err) {
                throw new MissingArgumentsException();
            }
            String[] bar = Parser.getDuration(foo[1]);
            Task toAdd = new Event(foo[0], bar[0], bar[1]);
            TaskList.addTask(toAdd);
            storage.load();
            lineUI();
            System.out.println("Got it. I've added this task:");
            System.out.println(toAdd);
            System.out.println("Now you have " + TaskList.getList().size() + " tasks in the list.");
            lineUI();
        } catch (MissingArgumentsException | MissingNameException | MissingTimeException | InvalidSyntaxException err) {
            System.out.println(err.getMessage());
        }

    }

     static void deadlineCommand(String str, Storage storage) throws IOException {
        try {
            String[] foo;
            try {
                foo = Parser.separate(str.substring(9));
            } catch (IndexOutOfBoundsException err) {
                throw new MissingArgumentsException();
            }

            Task toAdd = new Deadline(foo[0], Parser.getDeadline(foo[1]));
            TaskList.addTask(toAdd);
            storage.load();
            lineUI();
            System.out.println("Got it. I've added this task:");
            System.out.println(toAdd);
            System.out.println("Now you have " + TaskList.getList().size() + " tasks in the list.");
            lineUI();


        } catch (MissingArgumentsException | MissingTimeException | MissingNameException | InvalidSyntaxException |
                 InvalidDateFormatException err) {
            System.out.println(err.getMessage());
        }
    }

     static void deleteCommand(String str, Storage storage) throws IOException {
        try {
            int num = Parser.getTaskNumber(str, 7);
            Task removed = TaskList.remove(num - 1);
            storage.load();
            lineUI();
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed.toString());
            System.out.println("Now you have " + TaskList.getList().size() + " tasks in the list.");
            lineUI();

        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
        }
    }

     void markCommand(String str, Storage storage) throws IOException {
        try {
            int index = Parser.getTaskNumber(str,5) - 1;
            String name = TaskList.markDone(index);
            storage.load();
            lineUI();
            System.out.println("Nice! duke.Duke has marked this task as done:");
            System.out.println("[X] " + name);
            lineUI();
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
        }
    }

     void unmarkCommand(String str, Storage storage) throws IOException {
        try {
            int index = Parser.getTaskNumber(str,7) - 1;
            String name = TaskList.markUndone(index);
            storage.load();
            lineUI();
            System.out.println("OK, duke.Duke has marked this task as not done yet:");
            System.out.println("[O] " + name);
            lineUI();
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
        }
    }

     static void lineUI() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
