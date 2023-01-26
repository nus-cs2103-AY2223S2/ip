import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    static int exit = 0;
    static List<Task> task_list = new ArrayList<>();

    public static void main(String[] args) {
        loadList();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        while (exit != 1) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();

            if (info.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                exit = 1;
            } else if (info.equals("list")) {
                showList();
            } else {
                try {
                    String[] segments = info.split(" ", 2);
                    String first = segments[0];
                    switch (first) {
                    case "mark":
                        try {
                            int n = Integer.parseInt(segments[1]) - 1;
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[X] "+ task_list.get(n).msg);
                            task_list.get(n).done = 1;
                            saveList();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index Out");
                        }
                        break;
                    case "unmark":
                        try {
                            int num = Integer.parseInt(segments[1]) - 1;
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[ ] "+ task_list.get(num).msg);
                            task_list.get(num).done = 0;
                            saveList();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index Out");
                        }
                        break;
                    case "todo":
                        String action = info.split(" ", 2)[1];
                        Task t = new Todo(action);
                        task_list.add(t);
                        saveList();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        try {
                            String msg = segments[1].split("/by ",2)[0];
                            String by = segments[1].split("/by ",2)[1];
                            Task d = new Deadline(msg, by);
                            task_list.add(d);
                            saveList();
                            System.out.println("Got it. I've added this task:");
                            System.out.println(d);
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            System.out.println("Date Unacceptable (YYYY-MM-DD PLZ)");
                        }
                        break;
                    case "event":
                        String event = segments[1].split("/from",2)[0];
                        String from = segments[1].split("/from",2)[1].split("/to")[0];
                        String to = segments[1].split("/from",2)[1].split("/to")[1];
                        Task e = new Event(event, from, to);
                        task_list.add(e);
                        saveList();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(e);
                        System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        break;
                    case "delete":
                        try {
                            int index = Integer.parseInt(segments[1]) - 1;
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(task_list.get(index));
                            task_list.remove(index);
                            saveList();
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        } catch (IndexOutOfBoundsException a) {
                            System.out.println("OOPS!!! You can not delete air~");
                        }
                        break;
                    default:
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Arguments not enough.");
                }
            }
        }
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (Task tk : task_list) {
            System.out.println( (task_list.indexOf(tk)+1) + "." + tk.toString());
        }
    }

    public static void loadList() {
        try {
            File f = new File("src/main/data/task_list.txt");
            System.out.println("Loading List...");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                char type = line.charAt(0);
                switch (type) {
                case 'T':
                    Task td = new Todo(line.substring(2));
                    if (line.charAt(1) == '0') {
                        td.done = 0;
                    } else {
                        td.done = 1;
                    }
                    task_list.add(td);
                    break;
                case 'D':
                    String[] deadLine = line.substring(2).split(" /by ",2);
                    Task dd = new Deadline(deadLine[0], deadLine[1]);
                    if (line.charAt(1) == '0') {
                        dd.done = 0;
                    } else {
                        dd.done = 1;
                    }
                    task_list.add(dd);
                    break;
                case 'E':
                    String[] info = line.substring(2).split(" /from ",2);
                    String[] time = info[1].split(" /to ",2);
                    Task et = new Event(info[0], time[0], time[1]);
                    if (line.charAt(1) == '0') {
                        et.done = 0;
                    } else {
                        et.done = 1;
                    }
                    task_list.add(et);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("task_list.txt Not Found");
            try {
                System.out.println("Creating the File...");
                new File("src/main/data/task_list.txt").createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void saveList() {
        try {
            FileWriter fw = new FileWriter("src/main/data/task_list.txt");
            for (Task tk : task_list) {
                fw.write(tk.getInfo().toString()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
