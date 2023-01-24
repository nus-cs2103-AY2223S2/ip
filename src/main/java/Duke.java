import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> task = new ArrayList<>();

    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    private static void add(Task t) {
        task.add(t);
        updateData(t);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:\n\t  " + t + "\n\tNow you have "
                + task.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    private static void list() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < task.size(); i++) {
            int tmp = i + 1;
            System.out.println("\t" + tmp + "." + task.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    private static void mark(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= task.size()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task.get(i).setDone(true);
            updateData(i, 1);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + task.get(i));
            System.out.println("\t____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("enter");
            throw new DukeInvalidArgumentException("mark");
        }
    }

    private static void unmark(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= task.size()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task.get(i).setDone(false);
            updateData(i, 0);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + task.get(i));
            System.out.println("\t____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("mark");
        }
    }

    private static void delete(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= task.size()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task.remove(i);
            removeData(i);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tNoted. I've removed this task:\n\t  " + task.get(i)
                    + "\n\tNow you have " + task.size() + " tasks in the list.");
            System.out.println("\t____________________________________________________________");
        } catch ( NumberFormatException e) {
            System.out.println("enter");
            throw new DukeInvalidArgumentException("delete");
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                String[] split = input.split(" ", 2);
                String cmd = split[0];

                switch (cmd) {
                case "list":
                    list();
                    break;
                case "mark":
                case "unmark":
                case "delete":
                    if (split.length == 1 || split[1].isEmpty()) {
                        throw new DukeEmptyArgumentException(cmd);
                    }
                    if (cmd.equals("mark")) {
                        mark(split[1]);
                    } else if (cmd.equals("unmark")){
                        unmark(split[1]);
                    } else {
                        delete(split[1]);
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    if (split.length == 1 || split[1].isEmpty()) {
                        throw new DukeEmptyArgumentException(cmd);
                    }
                    Task t = null;
                    switch (cmd) {
                        case "todo":
                            t = new ToDos(split[1]);
                            break;
                        case "deadline":
                            String[] s1 = split[1].split("/by ", 2);
                            t = new Deadlines(s1[0], s1[1]);
                            break;
                        case "event":
                            String[] s2 = split[1].split("/from ", 2);
                            String[] s3 = s2[1].split("/to ", 2);
                            t = new Events(s2[0], s3[0], s3[1]);
                            break;
                    }
                    add(t);
                    break;
                default:
                    throw new DukeUnknownCommandException(input);
                }
            } catch (DukeUnknownCommandException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t____________________________________________________________");
            } catch (DukeEmptyArgumentException e) {
                System.out.println("\t____________________________________________________________");
                System.out.printf("\t☹ OOPS!!! The description of a %s cannot be empty.\n", e.getMessage());
                System.out.println("\t____________________________________________________________");
            } catch (DukeInvalidArgumentException e) {
                System.out.println("\t____________________________________________________________");
                System.out.printf("\t☹ OOPS!!! The description of a %s is invalid.\n", e.getMessage());
                System.out.println("\t____________________________________________________________");
            } finally {
                input = scanner.nextLine();
            }
        }
    }

    private static void updateData(Task t) {
        try {
            Path path = Path.of("data/duke.txt");
            List<String> allLine =  Files.readAllLines(path);
            String s = t.toString().charAt(1) + " | " + t.getStatusIcon() + " | " + t.description;
            if (t instanceof Deadlines) {
                Deadlines d =  (Deadlines) t;
                s += " | " + d.getBy();
            } else if (t instanceof Events) {
                Events e = (Events) t;
                s += " | " + e.getStart() + " | " + e.getEnd();
            }
            allLine.add(s);
            Files.write(path, allLine);
        } catch (IOException e) {
            System.out.println("Cannot read from Duke.txt data disk");
        }
    }

    private static void updateLocalTaskList() {
        try {
            Path path = Path.of("data/duke.txt");
            List<String> allLine = Files.readAllLines(path);
            if (allLine.isEmpty()) {
                return;
            }

            for (int i = allLine.size() - 1; i >= 0; i--) {
                String taskDescription = allLine.get(i);
                String[] s = taskDescription.split(" \\| ");

                Task t = null;
                boolean isDone = s[1] == "1" ?true:false;
                if (s[0].equals("T")) {
                    t = new ToDos(s[2]);
                } else if (s[0].equals("D")) {
                    t = new Deadlines(s[2], s[3]);
                } else if (s[0].equals("E")) {
                    t = new Events(s[2], s[3], s[4]);
                }
                t.setDone(isDone);
                task.add(0, t);
            }
        } catch (IOException e) {
            System.out.println("Cannot read from Duke.txt data file");
        }
    }

    private static void updateData(int lineNumber, int status) {
        try {
            Path path = Path.of("data/duke.txt");
            List<String> allLine = Files.readAllLines(path);

            // overwrite the duke.txt file
            String line = allLine.get(lineNumber);
            String s = line.substring(0, 4) + status + line.substring(5);
            allLine.set(lineNumber, s);

            Files.write(path, allLine);
        } catch (IOException e) {
            System.out.println("Cannot read from duke.txt data file");
        }
    }

    private static void removeData(int lineNumber) {
        try {
            Path path = Path.of("data/duke.txt");
            List<String> allLine = Files.readAllLines(path);

            // remove the line from duke.txt
            allLine.remove(lineNumber);

            Files.write(path, allLine);
        } catch (IOException e) {
            System.out.println("Cannot read from duke.txt data file");
        }
    }

    private static void retrieveData() {
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                System.out.println("\t" + s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read from duke.txt data file");
        }
    }

    public static void main(String[] args) throws IOException {
        File folder = new File("data/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        greet();
        retrieveData();
        updateLocalTaskList();
        run();
        exit();
    }
}
