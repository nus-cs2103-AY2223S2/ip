import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Duke {
    public static int taskCounter = 0;

    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String newLine = System.getProperty("line.separator");
        String path = "data/duke.txt";

        ArrayList<Task> toStore = new ArrayList<>(100);
        try {
            initData(path, toStore);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        taskCounter = toStore.size();
        System.out.println("-------------------------------------------------------" +
                newLine + "Hello! Jak Sie Masz! I am Borat.\n What I do for you Premier Azamat?");

        String inData;
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        inData = scan.nextLine();
        String[] arrofStr = inData.split(" ", 2);

        while (!exit) {
            switch (arrofStr[0]) {
            case "bye":
                exit = true;
                System.out.println("Chenquieh. Hope to see you again Premier Azamat!");
                break;
            case "list":
                System.out.println("Here are the tasks in your list my premier:");
                int counter = 0;
                for (Task i : toStore) {
                    counter++;
                    System.out.println(counter + ". " + i.toString());
                }
                inData = scan.nextLine();
                arrofStr = inData.split(" ", 2);
                break;
            default:
                try {
                    validateCmd(arrofStr);
                } catch (MissingDescriptionException e) {
                    System.out.println(e.toString());
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                }
                switch (arrofStr[0]) {
                case "delete": {
                    int indx = Integer.parseInt(arrofStr[1]) - 1;
                    Task toRemove = toStore.get(indx);
                    System.out.println("Noted. I've removed this task:" + toRemove);
                    toStore.remove(indx);
                    taskCounter--;
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                }
                case "unmark": {
                    int indx = Integer.parseInt(arrofStr[1]) - 1;
                    Task toMark = toStore.get(indx);
                    toMark.changeCompletion();
                    toStore.set(indx, toMark);
                    System.out.println("OK, I've marked this task as not done yet:\n" + toMark);
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                }
                case "mark": {
                    Task toMark;
                    int indx;
                    indx = Integer.parseInt(arrofStr[1]) - 1;
                    toMark = toStore.get(indx);
                    toMark.changeCompletion();
                    toStore.set(indx, toMark);
                    System.out.println("Nice! I've marked this task as done:\n" + toMark);
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                }
                case "todo":
                    Todo todo = new Todo(arrofStr[1]);
                    toStore.add(todo);
                    taskCounter++;
                    System.out.println("Very nice. I've added this task:\n" + todo);
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                case "deadline":
                    String[] dl = arrofStr[1].split("/by");
                    try {
                        validateDate(dl);
                    } catch (InvalidCmdException e) {
                        System.out.println(e.toString());
                        inData = scan.nextLine();
                        arrofStr = inData.split(" ", 2);
                    }
                    Deadline deadline = new Deadline(dl[0], dl[1]);
                    toStore.add(deadline);
                    taskCounter++;
                    System.out.println("Very nice. I've added this task: \n" + deadline);
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                case "event":
                    String[] ev = arrofStr[1].split("/from");
                    String[] time = ev[1].split("/to");
                    Event event = new Event(ev[0], time[0], time[1]);
                    toStore.add(event);
                    taskCounter++;
                    System.out.println("Very nice. I've added this task: \n" + event);
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                default:
                    System.out.println("☹ OOPS!!! Invalid command, prepare for execution.");
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                }
                break;
            }
        }
        saveToFile(toStore);
    }


    public static void validateCmd(String[] cmd) throws MissingDescriptionException {
        if (cmd.length == 1) {
            throw new MissingDescriptionException("You need to " +
                    "be more specific");
        }

    }

    public static void validateDate(String[] cmd) throws InvalidCmdException {
        if (cmd.length == 1) {
            throw new InvalidCmdException("Please specify date.");
        }
    }

    public static void initData(String path , ArrayList<Task> arrList) throws IOException {
        boolean isFileExist = new java.io.File(path).exists();
        if (!isFileExist) {
            Path dir = Paths.get(path);
            try {
                Files.createDirectories(dir.getParent());
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }

            try {
                Files.createFile(dir);
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        } else {
            try {
                File toLoad = new File(path);
                Scanner fileScanner = new Scanner(toLoad);
                while (fileScanner.hasNextLine()) {
                    String currTask = fileScanner.nextLine();
                    parseTask(arrList, currTask);
                }
            } catch (FileNotFoundException f) {
                System.out.println(f.getMessage());
            }
        }
    }

    public static void parseTask(ArrayList<Task> arrList, String currTask) {
        Task task = null;
        String[] toCheck = null;
        switch (currTask.charAt(1)) {
        case 'T':
            task = new Todo(currTask.substring(7));
            break;
        case 'D':
            toCheck = currTask.split("by:", 2);
            String desc = toCheck[0].substring(7, toCheck[0].length() - 1);
            String date = toCheck[1].substring(0, toCheck[1].length() - 1);
            task = new Deadline(desc, date);
            break;
        case 'E':
            toCheck = currTask.split("from:", 2);
            String descEvent = toCheck[0].substring(7, toCheck[0].length() - 1);
            String[] fromTo = toCheck[1].split("to:", 2);
            String from = fromTo[0].substring(0, fromTo[0].length()- 1);
            String to = fromTo[1].substring(0, fromTo[1].length()- 1);
            task = new Event(descEvent, from, to);
            break;
        }
        if (currTask.charAt(4) == 'X') {
            try{
                task.changeCompletion();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        arrList.add(task);
    }

    public static void saveToFile(ArrayList<Task> itemList) {
        String path = "data/duke.txt";
        File myFoo = new File(path);
        FileWriter fooWriter = null;
        try {
            fooWriter = new FileWriter(myFoo, false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Task current : itemList) {
            try{
                fooWriter.write(current + "\n");
            }  catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            fooWriter.flush();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

