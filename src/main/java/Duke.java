import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        File file = new File("/data/dukeList.txt");

        boolean fileExists = file.exists();

        if (!fileExists) {
            File dir = new File("/data");
            boolean dirCreated = dir.mkdir();
            try {
                File dukeList = new File("/data/dukeList.txt");
                if (dukeList.createNewFile()) {
                    System.out.println("File created: " + dukeList.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error occurred.");
            }
        }


        ArrayList<Task> list = readFile();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            }
            if (str.equals("list")) {
                recite(list);
                continue;
            }

            switch (getSwitch(str)) {
                case 0:
                    int n;
                    Task t;
                    String num;
                    num = str.substring(5);
                    n = Integer.parseInt(num) - 1;
                    t = list.get(n);
                    t.markAsDone();
                    writeToFile(list);
                    System.out.println("Nice! I've marked this task as done:\n"
                            + "  [" + t.getStatusIcon() + "] " + t.description);
                    break;
                case 1:
                    int n_1;
                    Task t_1;
                    String num_1;
                    num_1 = str.substring(7, 9);
                    n_1 = Integer.parseInt(num_1) - 1;
                    t_1 = list.get(n_1);
                    t_1.unMark();
                    writeToFile(list);
                    System.out.println("OK, I've marked this task as not done yet:\n"
                            + "  [" + t_1.getStatusIcon() + "] " + t_1.description);
                    break;
                case 2:
                    Task a;
                    try {
                        a = new Todo(str.substring(5));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    list.add(a);
                    writeToFile(list);
                    System.out.println("Got it. I've added this task:\n  "
                                        + a.toString() + "\nNow you have " + list.size()
                                        + " tasks in the list.");
                    break;
                case 3:
                    int ind = str.indexOf("/by");
                    Task b = null;
                    try {
                        b = new Deadline(str.substring(9, ind), str.substring(ind+4));
                        b.isDate();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    list.add(b);
                    writeToFile(list);
                    System.out.println("Got it. I've added this task:\n  "
                            + b.toString() + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;
                case 4:
                    int index_1 = str.indexOf("/from");
                    int index_2 = str.indexOf("/to");
                    Task c = null;
                    try {
                        c = new Event(str.substring(6, index_1), str.substring(index_1+6, index_2),
                                                str.substring(index_2 + 4));
                        c.isDate();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    list.add(c);
                    writeToFile(list);
                    System.out.println("Got it. I've added this task:\n  "
                            + c.toString() + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;
                case 5:
                    int nD;
                    Task tD;
                    String numD;
                    numD = str.substring(7);
                    nD = Integer.parseInt(numD) - 1;
                    tD = list.get(nD);
                    list.remove(nD);
                    writeToFile(list);
                    System.out.println("Noted. I've removed this task:\n  "
                            + tD.toString()
                            + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;

                case 6:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    static void recite(ArrayList<Task> l) {
        for (int i = 1; i <= l.size(); i++) {
            Task t = l.get(i-1);
            System.out.println("" + i + ". " + t.toString());
        }

    }
    static int getSwitch(String str) {
        if (str.startsWith("mark ")) return 0;
        if (str.startsWith("unmark ")) return 1;
        if (str.startsWith("todo ")) return 2;
        if (str.startsWith("deadline ")) return 3;
        if (str.startsWith("event ")) return 4;
        if (str.startsWith("delete ")) return 5;
        return 6;
    }

    static ArrayList<Task> readFile() {
        ArrayList<Task> dukeList = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("/data/dukeList.txt"));
//            new FileWriter("/data/dukeList.txt", false).close();
            while (s.hasNextLine()) {
                String str = s.nextLine();
                System.out.println(str);
                if (str.startsWith("E")) {
                    Event e = new Event(str.substring(7, str.indexOf(" |f")),
                            str.substring(str.indexOf(" |f") +3, str.indexOf(" |t")),
                            str.substring(str.indexOf(" |t") + 3));
                    if (str.startsWith("E | X")) {
                        e.markAsDone();
                    }
                    dukeList.add(e);
                }
                if (str.startsWith("D")) {
                    Deadline d = new Deadline(str.substring(7, str.indexOf(" |b")),
                            str.substring(str.indexOf(" |b") + 4));
                    if (str.startsWith("D | X")) {
                        d.markAsDone();
                    }
                    dukeList.add(d);
                }
                if (str.startsWith("T")) {
                    Todo t = new Todo(str.substring(7));
                    if (str.startsWith("T | X")) {
                        t.markAsDone();
                    }
                    dukeList.add(t);
                }

            }
            s.close();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
        }
        return dukeList;
    }

    static void writeToFile(ArrayList<Task> dukeList) {
        try {
            FileWriter writer = new FileWriter("/data/dukeList.txt", false);
            for(Task t: dukeList) {
                if (t instanceof Deadline) {
                    writer.write("D | " + t.getStatusIcon() + " | " + t.description + " |by " + ((Deadline) t).by
                            + System.lineSeparator());
                }
                if (t instanceof Event) {
                    writer.write("E | " + t.getStatusIcon() + " | " + t.description + " |f " + ((Event) t).from
                                + " |t " +  ((Event) t).to + System.lineSeparator());
                }
                if (t instanceof Todo) {
                    writer.write("T | " + t.getStatusIcon() + " | " + t.description + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
