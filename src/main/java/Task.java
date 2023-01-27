import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.time.LocalDateTime;

public abstract class Task {

    private final String NAME;
    private static File dataFile;
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final StringBuilder strBuild = new StringBuilder();
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private static final Scanner sc = new Scanner(System.in);
    private boolean done;
    private static boolean isItNew;
    private static int count = 0;

    public Task(String name, boolean done) {
        this.NAME = name;
        this.done = done;
        count++;
    }

    protected static void addToList(Task t) {
        taskList.add(t);
    }

    public static void delete(String i) {
        int cint = Integer.parseInt(i) - 1;
        try {
            Task curr = taskList.get(cint);
            curr.minus();
            taskList.remove(cint);
            System.out.println(Duke.DIVIDER + "Alright, deleted task:\n" + curr
                    + "\n" + curr.numberTask() + " tasks left!\n" + Duke.DIVIDER);
        } catch (Exception m){
            System.out.println("Number entered out of range, type the number again");
            String s = sc.nextLine();
            delete(s);
        }
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public int numberTask() {
        return count;
    }

    public void minus() {
        count--;
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
    public static void taskFile(File file, boolean isNew) throws FileNotFoundException {
        dataFile = file;
        isItNew = isNew;
        if (!isNew) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String in = sc.nextLine();
                String[] split = in.split(" \\| ");
                boolean isDone = split[1].equals("1");
                switch (split[0].toUpperCase()) {
                    case "T" :
                        Todo t = new Todo(split[2], isDone);
                        taskList.add(t);
                        break;
                    case "E" :
                        Event e = new Event(split[2], split[3], split[4], isDone);
                        taskList.add(e);
                        break;
                    case "D" :
                        Deadline d = new Deadline(split[2], split[3], isDone);
                        taskList.add(d);
                        break;
                    default:
                        break;

                }
            }
        }
    }
    public static void printList() {
        ListIterator<Task> li = taskList.listIterator();
        if (!li.hasNext()) {
            System.out.println(Duke.DIVIDER + "List is empty.......\n" + Duke.DIVIDER);
        } else {
            int count = 1;
            System.out.println(Duke.DIVIDER + "Hurry up and finish these tasks:");
            while (li.hasNext()) {
                Task curr = li.next();
                System.out.print(curr);
                count++;
            }
            System.out.println(Duke.DIVIDER);
        }
    }
    public static void writeToFile() {
        if (!taskList.isEmpty()) {
            try (BufferedWriter writer = Files.newBufferedWriter(dataFile.toPath())) {
                for (Task curr : taskList) {
                    writer.write(curr.write(dataFile));
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (!isItNew) {
                try (BufferedWriter writer = Files.newBufferedWriter(dataFile.toPath())) {
                    writer.write("");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract String write(File file);


    public static void printDefault(Task t) {
        System.out.println(Duke.DIVIDER + "Aite letsgetit you added:\n" + t.toString()  +
                "currently you have " + t.numberTask() + " tasks\n" + Duke.DIVIDER);
    }
    @Override
    public String toString() {
        return done ? "[X] " + NAME
                    : "[ ] " + NAME;
    }

    public String toWrite() {
        return done ? "1 | " + NAME
                : "0 | " + NAME;
    }
}
