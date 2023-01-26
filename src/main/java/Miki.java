import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;

public class Miki {
    private static final String DATA_PATH = "./data/";

    private static class MikiArgsException extends Exception {
        protected MikiArgsException(String message) {
            super(message);
        }
    }

    private static class MikiLoadException extends Exception {
        protected MikiLoadException(String message) {
            super(message);
        }
    }

    private static void printDiv() {
        System.out.println("    ____________________________________________________________");
    }

    private static void print(String s) {
        System.out.println("     " + s);
    }

    private static void printAdded(Task t, int taskCount) {
        print("Added this thing! That makes " + taskCount + (taskCount == 1 ? " task" : " tasks") + ":");
        print("  " + t.toString());
    }

    private static void printSaves() {
        createSaveDir();
        File dir = new File(DATA_PATH);
        if (dir == null) {
            print("(default save location is missing?!)");
            return;
        }
        File[] saves = dir.listFiles();
        for (int i = 0; i < saves.length; i++) {
            print("> " + saves[i].getName());
        }
    }

    private static int parseIndex(String[] args, int taskCount) throws MikiArgsException {
        int idx;
        if (args.length == 0) throw new MikiArgsException("you didn't specify which one?!");
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException ex) {
            throw new MikiArgsException("\"" + args[0] + "\" isn't a real integer! There's no task #" + args[0] + "!");
        }
        if (idx < 0 || idx >= taskCount) {
            String message = "There's no task #" + args[0] + "! ";
            if (taskCount == 1) message += "There is currently 1 task...";
            else message += "There are currently " + taskCount + " tasks...";
            throw new MikiArgsException(message);
        }
        return idx;
    }

    private static void createSaveDir() {
        File dir = new File(DATA_PATH);
        if (!dir.mkdirs()) {

        }
    }

    private static void save(String pathString, ArrayList<Task> tasks) throws IOException {
        createSaveDir();
        Path path = FileSystems.getDefault().getPath(DATA_PATH).resolve(pathString);
        BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        try {
            bw.write(Integer.toString(tasks.size()));
            for (int i = 0; i < tasks.size(); i++) {
                String[] repres = tasks.get(i).save();
                bw.newLine();
                bw.write(Integer.toString(repres.length));
                for (int j = 0; j < repres.length; j++) {
                    bw.newLine();
                    bw.write(repres[j]);
                }
            }
        } finally {
            bw.close();
        }
    }

    private static ArrayList<Task> load(String pathString) throws IOException, MikiLoadException {
        createSaveDir();
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = FileSystems.getDefault().getPath(DATA_PATH).resolve(pathString);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        try {
            int numTasks = Integer.parseInt(br.readLine());
            for (int i = 0; i < numTasks; i++) {
                int taskLines = Integer.parseInt(br.readLine());
                String[] repres = new String[taskLines];
                for (int j = 0; j < taskLines; j++) {
                    repres[j] = br.readLine();
                }
                switch (repres[0].charAt(0)) {
                    case 'T':
                        tasks.add(Todo.parseLoad(repres));
                        break;
                    case 'D':
                        tasks.add(Deadline.parseLoad(repres));
                        break;
                    case 'E':
                        tasks.add(Event.parseLoad(repres));
                        break;
                    default:
                        //invalid ver? try to handle
                }
            }
        } catch (NumberFormatException | TaskParseException ex) {
            throw new MikiLoadException("this file is corrupt...");
        } finally {
            br.close();
        }
        return tasks;
    }

    public static void main(String[] args) {
        boolean ascii_only = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ascii-only")) ascii_only = true;
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        if (!ascii_only) {
            print("\uD83C\uDF80✨");
            print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        } else {
            print("01 f3 80 / 27 28");
            print("Hello " + username + " !! Konmiki! \\(^v^)/");
        }
        printDiv();
        try {
            tasks = load("autosave.txt");
        } catch (IOException | MikiLoadException ex) {

        }

        boolean exit_cmd = false;
        while (!exit_cmd) {
            System.out.print(">");
            String cmd_line = sc.nextLine();
            String cmd = cmd_line.split(" ")[0].toLowerCase();
            String[] cmd_args = {};
            if (cmd_line.contains(" ")) {
                cmd_args = cmd_line.substring(cmd.length() + 1).split(" ");
            }
            try {
                printDiv();
                switch (cmd) {
                    case "bye":
                        exit_cmd = true;
                        print("Otsumiki!~ I'll see you later!");
                        break;
                    case "list":
                        print("caught in 4k:");
                        for (int i = 0; i < tasks.size(); i++) {
                            print(Integer.toString(i + 1) + ". " + tasks.get(i));
                        }
                        break;
                    case "mark": {
                        int idx = parseIndex(cmd_args, tasks.size());
                        tasks.get(idx).mark();
                        print("Yay!! Task marked as done:");
                        print("  " + tasks.get(idx));
                        break;
                    }
                    case "unmark": {
                        int idx = parseIndex(cmd_args, tasks.size());
                        tasks.get(idx).unmark();
                        print("okay...! task unmarked as undone:");
                        print("  " + tasks.get(idx));
                        break;
                    }
                    case "todo": {
                        Todo newTodo = Todo.parseArgs(cmd_args);
                        tasks.add(newTodo);
                        printAdded(newTodo, tasks.size());
                        break;
                    }
                    case "deadline": {
                        Deadline newDeadline = Deadline.parseArgs(cmd_args);
                        tasks.add(newDeadline);
                        printAdded(newDeadline, tasks.size());
                        break;
                    }
                    case "event": {
                        Event newEvent = Event.parseArgs(cmd_args);
                        tasks.add(newEvent);
                        printAdded(newEvent, tasks.size());
                        break;
                    }
                    case "delete": {
                        int idx = parseIndex(cmd_args, tasks.size());
                        Task delTask = tasks.remove(idx);
                        print("hm hmm... task #" + (idx + 1) + " deleted! " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " left.");
                        print("- " + delTask);
                        break;
                    }
                    case "save": {
                        String pathString = "";
                        for (int i = 0; i < cmd_args.length; i++) {
                            pathString += (i > 0 ? " " : "") + cmd_args[i];
                        }
                        if (!pathString.equals("")) {
                            try {
                                save(pathString, tasks);
                                print("done! i've written your list to: " + pathString);
                            } catch (IOException ex) {
                                print("umm... i can't write on that!");
                                print("> " + ex.getMessage());
                            }
                        } else {
                            print("tell me where to write!!!");
                        }
                        break;
                    }
                    case "load": {
                        String pathString = "";
                        for (int i = 0; i < cmd_args.length; i++) {
                            pathString += (i > 0 ? " " : "") + cmd_args[i];
                        }
                        if (!pathString.equals("")) {
                            try {
                                tasks = load(pathString);
                                print("done! i've taken your list from: " + pathString);
                            } catch (IOException ex) {
                                print("umm... i can't read from that!");
                                print("> " + ex.getMessage());
                            } catch (MikiLoadException ex) {
                                print(ex.getMessage());
                            }
                        } else {
                            print("tell me what to read!!!");
                            printSaves();
                        }
                        break;
                    }
                    default:
                        throw new MikiArgsException("\"" + cmd + "\" isn't a real word!");
                }
            } catch (TaskParseException | MikiArgsException ex) {
                print("?!?!? " + ex.getMessage());
            }
            printDiv();
            try {
                save("autosave.txt", tasks);
            } catch (IOException ex) {

            }
        }
    }
}
