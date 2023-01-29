import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.nio.file.Path;
import java.util.regex.Pattern;


public class ToDoList {
    private ArrayList<Task> arr = new ArrayList<>(); //1-based index
    private int toDoCount;

    public ToDoList() {
        arr.add(new ToDoTask("0index")); //1-based index
        this.toDoCount = 0;
    }

    public int getToDoCount() {
        return this.toDoCount;
    }

    public void add(Task task) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        ++this.toDoCount;
        arr.add(task);
        System.out.println(divider + "The Duke has added the following task: \n"
                + " - " + task + "\n"
                + "You now have " + this.toDoCount + " task!\n"+ divider);
    }

    public void delete(int ind) throws DukeException {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        Task rm = arr.get(ind);
        arr.remove(ind);
        --this.toDoCount;
        System.out.println(divider + "The Duke has removed the following task: \n"
                + " - " + rm + "\n"
                + "You now have " + this.toDoCount + " task!\n"+ divider);
    }


    public void unmarkTask(int ind) throws DukeException {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markNotDone();
        System.out.println(divider + "The Duke has unmarked the following task: \n"
                + " - " + arr.get(ind) + "\n" + divider);
    }

    public void  markTask(int ind) throws DukeException {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markDone();
        System.out.println(divider + "The Duke has marked the following task: \n"
                + " - " + arr.get(ind) + "\n" + divider);
    }

    public void save() throws IOException {
        String home = System.getProperty("user.dir");
        Path path = java.nio.file.Paths.get(home, "iP-data", "Ip-data.txt");
        FileWriter fw = new FileWriter(path.toString()); //file structure
        for (int i=1; i<=this.toDoCount; i++) {
            fw.write(arr.get(i).save());
        }
        fw.close();
    }

    public static ToDoList load() throws IOException {
        String home = System.getProperty("user.dir");
        Path path = java.nio.file.Paths.get(home, "iP-data", "Ip-data.txt");
        if (!Files.exists(path)) {
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            Files.createFile(path);
        }
        File f = new File(path.toString()); //file structure
        ToDoList ls = new ToDoList();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(Pattern.quote("/+/"));
            String command = input[0];
            Task task;
            switch (command) { //to settle out index error
                case "TODO":
                    task = new ToDoTask(input[2]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                case "DEADLINE":
                    task = new DeadlineTask(input[2], input[3]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
                case "EVENT":
                    task = new EventTask(input[2], input[3], input[4]);
                    if (input[1].equals("DONE")) {
                        task.markDone();
                    }
                    ls.add(task);
                    break;
            }
        }
        return ls;
    }

    @Override
    public String toString() {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        String output = divider + "TO DO LIST: \n";
        for (int i=1; i<=this.toDoCount; i++) {
            output = output + i + "." + arr.get(i) + "\n";
        }
        output = output + divider;
        return output;
    }
}
