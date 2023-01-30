import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String greeting = "____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________";
    private static final String goodbye = "____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws DukeException {

        File dataFile = new File("data/duke.txt");
        try {
            if(!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
        } catch (IOException error) {
            throw new DukeException("Unable to create file.");
        }
        System.out.println(greeting);
        boolean active = true;
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        try {
            readFile(dataFile);
        } catch (DukeException de) {
            System.out.println(de.getMessage() + "\n");
        }
        while(active) {
            try{
                switch (command) {
                    case "bye":
                        active = false;
                        System.out.println(goodbye);
                        break;
                    case "list":
                        printLine();
                        for (Task task : tasks) {
                            if (task != null) {
                                System.out.println(tasks.indexOf(task)+ "." + task);
                            }
                        }
                        printLine();
                        break;
                    case "deadline":
                        String deadlineInst = sc.nextLine();
                        String[] deadlineArr = deadlineInst.split("/by");
                        addTask(new Deadline(deadlineArr[0].trim(), deadlineArr[1].trim()));
                        break;
                    case "todo":
                        addTask(new Todo(sc.nextLine().trim()));
                        break;
                    case "event":
                        String eventInst = sc.nextLine();
                        String[] eventArr = eventInst.split("/");
                        addTask(new Event(eventArr[0].trim(), eventArr[1].substring(5).trim(), eventArr[2].substring(3).trim()));
                        break;
                    case "mark": {
                        int num = sc.nextInt();
                        markTask(num);
                        break;
                    }
                    case "unmark": {
                        int num = sc.nextInt();
                        unmarkTask(num);
                        break;
                    }
                    case "delete": {
                        int num = sc.nextInt();
                        deleteTask(num);
                        break;
                    }
                    default:
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
            writeFile();
            command = sc.next();
        }
        sc.close();
    }
    public static void addTask(Task input) {
        tasks.add(input);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + input.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }
    public static void deleteTask(int input) {
        tasks.remove(input - 1);
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + tasks.get(input));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    public static void markTask(int input) {
        Task task = tasks.get(input - 1);
        task.markDone();
        printLine();
        System.out.println("Nice! I've marked this task as done");
        System.out.println(task);
        printLine();
    }
    public static void unmarkTask(int input) {
        Task task = tasks.get(input - 1);
        task.markUndone();
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    private static void writeFile() throws DukeException {
        try {
            FileWriter file = new FileWriter("data/duke.txt");
            StringBuilder strings = new StringBuilder();
            for (Task task : tasks) {
                strings.append(task.toFileString());
            }
            file.write(String.valueOf(strings));
            file.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred.");
        }
    }
    public static void readFile(File f) throws DukeException {
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String[] task = scan.nextLine().split(" \\|");
                switch (task[0]) {
                    case "T":
                        Task todo = new Todo(task[2]);
                        if (task[1].equals("1")) {
                            todo.markDone();
                        }
                        addTask(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(task[2], task[3]);
                        if (task[1].equals("1")) {
                            deadline.markDone();
                        }
                        addTask(deadline);
                        break;
                    case "E":
                        Task event = new Event(task[2], task[3], task[4]);
                        if (task[1].equals("1")) {
                            event.markDone();
                        }
                        addTask(event);
                        break;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File does not exist");
        }
    }
}
