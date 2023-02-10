package duke;


import duke.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n" + LOGO;
    private static final String LIST = "Duke presents tasks in your list: ";
    private static final String BYE = "Duke says bye bye ~~";
    private static final String MATCHING = "Duke finds these items containg your keyword: ";

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

    public String greet() {
        System.out.println(GREETING);
        return "Hello from Duke! Nice to see you";
    }

    public String goodBye() {
        lineUI();
        System.out.println(BYE);
        lineUI();

        return BYE;
    }

    public String listCommand() {
        ArrayList<Task> arr = TaskList.getList();
        if (arr.size() == 0) {
            System.out.println("Duke sees no tasks in your list");
            return "Duke sees no tasks in your list";
        }
        return listTasks(arr);
    }

     static String todoCommand(String str, Storage storage) throws IOException {
        Task toDo;
        try {
            toDo = new ToDo(Parser.getName(str, 5));
        } catch (MissingArgumentsException | MissingNameException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        TaskList.addTask(toDo);
        storage.load();
        lineUI();
        String res = "Got it. I've added this task:\n" + toDo
                        + "\n" + "Now you have " + TaskList.getList().size() + " tasks in the list.";
        System.out.println(res);
        lineUI();
        return res;

    }

     static String eventCommand(String str, Storage storage) throws IOException {
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
            String res = "Got it. I've added this task:\n" + toAdd + "\n"
                        + "Now you have " + TaskList.getList().size() + " tasks in the list.";
            System.out.println(res);
            lineUI();
            return res;
        } catch (MissingArgumentsException | MissingNameException | MissingTimeException | InvalidSyntaxException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

    }

     static String deadlineCommand(String str, Storage storage) throws IOException {
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
            String res = "Got it. I've added this task:\n" + toAdd + "\n"
                        + "Now you have " + TaskList.getList().size() + " tasks in the list.";
            System.out.println(res);
            lineUI();
            return res;
        } catch (MissingArgumentsException | MissingTimeException | MissingNameException | InvalidSyntaxException |
                 InvalidDateFormatException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }
    }

     static String deleteCommand(String str, Storage storage) throws IOException {
        int num;
        try {
            num = Parser.getTaskNumber(str, 7);
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        Task removed = TaskList.remove(num - 1);
        storage.load();
        lineUI();
        String res = "Noted. I've removed this task:\n" + removed + "\n"
                    + "Now you have " + TaskList.getList().size() + " tasks in the list.";
        System.out.println(res);
        lineUI();
        return res;

    }

     String markCommand(String str, Storage storage) throws IOException {
        int index;
        try {
            index = Parser.getTaskNumber(str,5) - 1;
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        String name = TaskList.markDone(index);
        storage.load();
        lineUI();
        String res = "Nice! duke.Duke has marked this task as done:\n" + "[X] " + name;
        System.out.println(res);
        lineUI();
        return res;
    }

     String unmarkCommand(String str, Storage storage) throws IOException {
        int index;
        try {
            index = Parser.getTaskNumber(str,7) - 1;
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        String name = TaskList.markUndone(index);
        storage.load();
        lineUI();
        String res = "OK, duke.Duke has marked this task as not done yet:\n" +  "[O] " + name;
        System.out.println(res);
        lineUI();
        return res;
    }

    String findCommand(String str) {
        String keyword;
        try {
            keyword = Parser.getName(str, 5);
        } catch (MissingNameException | MissingArgumentsException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        ArrayList<Task> matchedTasks = TaskList.findMatch(keyword);
        if (matchedTasks.size() == 0) {
            System.out.println("Duke finds no tasks containing the keyword given");
            return "Duke finds no tasks containing the keyword given";
        } else {
            lineUI();
            String res = MATCHING + "\n" + listTasks(matchedTasks);
            System.out.println(res);
            lineUI();
            return res;
        }
    }

    String listTasks(ArrayList<Task> arr) {
        String res = "";
        int cnt = 1;
        while (cnt <= arr.size()) {
            Task item = arr.get(cnt - 1);
            res += cnt + "." + item.toString() + "\n";
            System.out.println(cnt + "." + item.toString());
            cnt++;
        }
        return res;
    }

     static void lineUI() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
