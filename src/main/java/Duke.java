import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String INDENT_LINE = "____________________________________________________________";
    private ArrayList<Task> taskList;
    private final String FILE_PATH = "./data/duke.txt";

    public Duke() {
        taskList = new ArrayList<>();
    }
    private void bye() {
        System.out.println(INDENT_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(INDENT_LINE);
    }

    private void showList() {
        System.out.println(INDENT_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i+1) + "." + taskList.get(i));
        }
        System.out.println(INDENT_LINE);
    }

    private void mark(int index) {
        taskList.get(index).markAsDone();
        System.out.println(INDENT_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index+1) + "." + taskList.get(index));
        System.out.println(INDENT_LINE);
    }

    private void unmark(int index) {
        taskList.get(index).markAsUndone();
        System.out.println(INDENT_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println((index+1) + "." + taskList.get(index));
        System.out.println(INDENT_LINE);
    }

    private void delete(int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println(INDENT_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(INDENT_LINE);
    }

    private void writeAll() {
        try {
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(String s) {
        String[] arr = s.split("/");
        char eventType = arr[0].charAt(0);
        boolean isDone = (arr[1].charAt(0) == '1');
        String description = arr[2];
        if (eventType == 'T') {
            Todo t = new Todo(description, isDone);
            taskList.add(t);
        } else if (eventType == 'D') {
            String by = arr[3].substring(4);
            Deadline d = new Deadline(description, isDone, by);
            taskList.add(d);
        } else if (eventType == 'E') {
            String from = arr[3].substring(6);
            String to = arr[4].substring(4);
            Event e = new Event(description, isDone, from, to);
            taskList.add(e);
        }
    }

    private void readAll() {
        try {
            File f = new File(FILE_PATH);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                read(s);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            try {
                File f = new File(FILE_PATH);
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                String task = sc.nextLine();
                if (task.equals("bye")) {
                    bye();
                    break;
                } else if (task.equals("list")) {
                    showList();
                } else if (task.startsWith("mark")) {
                    int index = Integer.parseInt(task.substring(5)) - 1;
                    mark(index);
                } else if (task.startsWith("unmark")) {
                    int index = Integer.parseInt(task.substring(7)) - 1;
                    unmark(index);
                } else if (task.startsWith("delete")) {
                    int index = Integer.parseInt(task.substring(7)) - 1;
                    delete(index);
                }
                else {
                    if (task.startsWith("todo")) {
                        String description = task.substring(5);
                        if (description.trim().equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        taskList.add(new Todo(description));
                    } else if (task.startsWith("deadline")) {
                        String[] arr = task.split("/");
                        String description = arr[0].substring(9);
                        String by = arr[1].substring(3);
                        taskList.add(new Deadline(description, by));
                    } else if (task.startsWith("event")) {
                        String[] arr = task.split("/");
                        String description = arr[0].substring(6);
                        String from = arr[1].substring(5);
                        String to = arr[2].substring(3);
                        taskList.add(new Event(description, from, to));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    int len = taskList.size();
                    System.out.println(INDENT_LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(len - 1));
                    System.out.println("Now you have " + len + " tasks in the list.");
                    System.out.println(INDENT_LINE);
                }

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        System.out.println(INDENT_LINE);
        System.out.println("Hello! I'm Vincent");
        System.out.println("What can I do for you?");
        System.out.println(INDENT_LINE);

        Duke d = new Duke();
        d.readAll();
        d.start();
        d.writeAll();
    }
}
