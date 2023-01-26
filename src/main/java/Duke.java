import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String dataDir = System.getProperty("user.dir") + File.separator + "data";
    private static String dataPath = dataDir + File.separator + "duke.txt";

    /**
     * A level 3 chat bot Duke.
     */
    public static void main(String[] args) throws IOException, DukeException {
        greetings();
        ArrayList<Task> todo = null;
        try {
            todo = dbLoad();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Scanner ai = new Scanner(System.in);
        String input = ai.nextLine();
        while (!input.equals("bye")) {
            String arr1[] = input.split("/");
            String arr2[] = arr1[0].split(" ");
            try {
                switch (arr2[0]) {
                    case "":
                        break;

                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        if (todo.size() == 0) {
                            System.out.println("OOPS!!! Your list is empty.");
                        }
                        for (int i = 0; i < todo.size(); i++) {
                            System.out.print(i + 1 + ".");
                            System.out.println(todo.get(i).toString());
                        }
                        break;

                    case "mark":
                        int index = Integer.parseInt(arr2[1]);
                        if (index < 1 || index > todo.size()) {
                            throw new DukeException("Sorry, this task number is invalid.");
                        }
                        todo.get(index - 1).setStatus(true);
                        dbSave(todo);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(todo.get(index - 1).toString());
                        break;

                    case "unmark":
                        int No = Integer.parseInt(arr2[1]);
                        if (No < 1 || No > todo.size()) {
                            throw new DukeException("Sorry, this task number is invalid.");
                        }
                        todo.get(No - 1).setStatus(false);
                        dbSave(todo);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(todo.get(No - 1).toString());
                        break;

                    case "delete":
                        int dindex = Integer.parseInt(arr2[1]);
                        if (dindex < 1 || dindex > todo.size()) {
                            throw new DukeException("Sorry, this task number is invalid.");
                        }
                        Task removed = todo.get(dindex - 1);
                        todo.remove(dindex - 1);
                        dbSave(todo);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removed.toString());
                        System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                        break;

                    case "todo":
                        if (arr2.length < 2) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        String name = arr1[0].substring(arr1[0].indexOf(" ") + 1);
                        todo.add(new Todos(name));
                        dbSave(todo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.get(todo.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                        break;

                    case "deadline":
                        if (arr2.length < 2) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String dname = arr1[0].substring(arr1[0].indexOf(" ") + 1, arr1[0].length() - 1);
                        String dtime = arr1[1].substring(arr1[1].indexOf(" ") + 1);
                        todo.add(new Deadlines(dname, dtime));
                        dbSave(todo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.get(todo.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                        break;

                    case "event":
                        if (arr2.length < 2) {
                            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                        }
                        String ename = arr1[0].substring(arr1[0].indexOf(" ") + 1, arr1[0].length() - 1);
                        String strtime = arr1[1].substring(arr1[1].indexOf(" ") + 1, arr1[1].length() - 1);
                        String endtime = arr1[2].substring(arr1[2].indexOf(" ") + 1);
                        todo.add(new Events(ename, strtime, endtime));
                        dbSave(todo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.get(todo.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                        break;

                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            input = ai.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static ArrayList<Task> dbLoad() throws IOException {
        File dir = new File(dataDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File duke = new File(dataPath);
        if (!duke.exists()) {
            return new ArrayList<Task>();
        }
        ArrayList<Task> todo = null;
        FileInputStream f = null;
        ObjectInputStream f1 = null;
        try {
            f = new FileInputStream(dataPath);
            f1 = new ObjectInputStream(f);
            todo = (ArrayList<Task>)f1.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                f1.close();
                f.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        return todo;
    }

    public static void dbSave(ArrayList<Task> todo) throws IOException {
        FileOutputStream f1 = null;
        ObjectOutputStream f2 = null;
        try {
            f1 = new FileOutputStream(dataPath);
            f2 = new ObjectOutputStream(f1);
            f2.writeObject(todo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                f2.close();
                f1.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greetings = "Hello! I'm Duke" + "\nWhat can I do for you?";
        System.out.println(greetings);
    }
}
