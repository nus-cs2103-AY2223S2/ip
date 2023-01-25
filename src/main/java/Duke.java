import java.io.InputStreamReader;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;

public class Duke {

    static List<Task> storedText = new ArrayList<Task>();

    public static void main(String[] args) throws IOException, DukeExceptions, StringIndexOutOfBoundsException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        Path fileData = getData();
        storedText = loadTask(fileData);
        processUserInput(bReader);
        storeTask(fileData);
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static void greeting() {
        System.out.println("Hello, I am Duke!\nWhat can I do for you?");
    }

    public static Path getData() throws IOException{
        //Check if directory AND file exists.
        Path pathToCheck = Paths.get("..", "..", "..", "data");
        if (!Files.exists(pathToCheck)) {
            Files.createDirectory(pathToCheck);
        }

        Path fileToCheck = Paths.get("..", "..", "..", "data", "dukedata.txt");
        if (!Files.exists(fileToCheck)) {
            Files.createFile(fileToCheck);
        }

        return fileToCheck;
    }

    public static ArrayList<Task> loadTask(Path dataFile) throws IOException, DukeExceptions{
        ArrayList<Task> useThis = new ArrayList<>();
        Scanner scannerForFileData = new Scanner(dataFile);
        if (!scannerForFileData.hasNext()) {
            scannerForFileData.close();
            return useThis;
        }
        while (scannerForFileData.hasNextLine()) {
            String taskToLoad = scannerForFileData.nextLine();  
            // String dataWithoutIndexes = taskToLoad.substring(taskToLoad.indexOf(".") + 1);
            String dataWithoutIndexes = taskToLoad.substring(2);
            Character isDone = dataWithoutIndexes.charAt(4);
            Character taskType = dataWithoutIndexes.charAt(1);
            Task toAdd = new Task("");

            if (taskType.equals('T')) {
                String nameOfTask = dataWithoutIndexes.substring(6);
                toAdd = new ToDo(nameOfTask);
                if (isDone.equals('X')) {
                    toAdd.setDone();
                }
                useThis.add(toAdd);
            }

            if (taskType.equals('D')) {
                String deadlineWithBy = dataWithoutIndexes.substring(dataWithoutIndexes.lastIndexOf("("));
                String rawDate = deadlineWithBy.split("\\(by: ")[1].split("\\)")[0];
                String[] nameSplitInArr = dataWithoutIndexes.substring(6).split(" ");
                String nameOfTask = nameSplitInArr[0];
                for (int i = 1; i < nameSplitInArr.length; i++) {
                    if (nameSplitInArr[i].equals("(by:")) {
                        break;
                    }
                    nameOfTask = nameOfTask + " " + nameSplitInArr[i];
                }
                String toInitialize = nameOfTask + " /by" + " " + rawDate;
                toAdd = new Deadlines(toInitialize);
                if (isDone.equals('X')) {
                    toAdd.setDone();
                }
                useThis.add(toAdd);
            }

            if (taskType.equals('E')) {
                String timeframe = dataWithoutIndexes.substring(dataWithoutIndexes.lastIndexOf("("));
                String from = timeframe.substring(1).split(" to:")[0].substring(5);
                String to = timeframe.substring(1).split("to: ")[1].split("\\)")[0];
                String[] nameSplitInArr = dataWithoutIndexes.substring(6).split(" ");
                String nameOfTask = nameSplitInArr[0];
                for (int i = 1; i < nameSplitInArr.length; i++) {
                    if (nameSplitInArr[i].equals("(from:")) {
                        break;
                    }
                    nameOfTask = nameOfTask + " " + nameSplitInArr[i];
                }
                String toInitialize = nameOfTask + " /from" + from + " /to " + to;
                toAdd = new Events(toInitialize);
                if (isDone.equals('X')) {
                    toAdd.setDone();
                }
                useThis.add(toAdd); 
            }
        }
        scannerForFileData.close();
        return useThis;
    }

    public static void storeTask(Path dataFile) throws IOException{
        if (storedText.size() == 0) {
            Files.write(dataFile, "".getBytes());
            return;
        }
        Files.write(dataFile, "".getBytes());
        Task firstTask = storedText.get(0);
        String toSave = "1." + firstTask.toString() + " \n";
        Files.write(dataFile, toSave.getBytes(), StandardOpenOption.APPEND);
        for (int i = 1; i < storedText.size(); i++) {
            Integer currIndex = i + 1;
            Task currTask = storedText.get(i);
            String toUse = currIndex.toString() + "." + currTask.toString() + " \n";
            Files.write(dataFile, toUse.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void processUserInput(BufferedReader brToUse) throws IOException, DukeExceptions, StringIndexOutOfBoundsException{
        String userInput = brToUse.readLine();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storedText.size(); i++) {
                        Integer currIndex = i + 1;
                        Task currTask = storedText.get(i);
                        String toUse = currIndex.toString() + "." + currTask.toString();
                        System.out.println(toUse);
                    }
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("delete")) {
                    String[] commandSplit = userInput.split(" ");
                    if (commandSplit.length <= 1) {
                        throw new DukeExceptions("");
                    }
                    int indexToUse = Integer.parseInt(commandSplit[1]) - 1;
                    if (indexToUse >= storedText.size() || indexToUse < 0) {
                        throw new DukeExceptions("Wrong size for mark/unmark");
                    }
                    Task gettingTask = storedText.remove(indexToUse);
                    String toOutput = "Noted. I've removed this task:\n  " + gettingTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list";
                    System.out.println(toOutput);
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("mark")) {
                    String[] commandSplit = userInput.split(" ");
                    if (commandSplit.length <= 1) {
                        throw new DukeExceptions("");
                    }
                    int indexToUse = Integer.parseInt(commandSplit[1]) - 1;
                    if (indexToUse >= storedText.size() || indexToUse < 0) {
                        throw new DukeExceptions("Wrong size for mark/unmark");
                    }
                    Task currTask = storedText.get(indexToUse);
                    currTask.setDone();
                    // String toOutput = "Nice! I've marked this task as done:\n" + "[X] " + currTask.getName();
                    String toOutput = "Nice! I've marked this task as done:\n  " + currTask.toString();
                    System.out.println(toOutput);
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("unmark")) {
                    String[] commandSplit = userInput.split(" ");
                    if (commandSplit.length <= 1) {
                        throw new DukeExceptions("");
                    }
                    int indexToUse = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (indexToUse >= storedText.size() || indexToUse < 0) {
                        throw new DukeExceptions("Wrong size for mark/unmark");
                    }
                    Task currTask = storedText.get(indexToUse);
                    currTask.setUndone();
                    String toOutput = "Ok, I've marked this task as not done yet:\n  " + currTask.toString();
                    System.out.println(toOutput);
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    String useForInit = "";
                    Task addTask = new Task("");
                    if (userInput.startsWith("todo")) {
                        useForInit = userInput.substring(4);
                        addTask = new ToDo(useForInit);
                    } else if (userInput.startsWith("deadline")) {
                        useForInit = userInput.substring(8);
                        addTask = new Deadlines(useForInit);
                    } else {
                        useForInit = userInput.substring(5);
                        addTask = new Events(useForInit);
                    }
                    storedText.add(addTask);
                    String toPrint = "";
                    if (storedText.size() == 1) {
                        toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " task in the list.";
                    } else {
                        toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list.";                   
                    }
                    System.out.println(toPrint);
                    userInput = brToUse.readLine();
                    continue;
                }
                throw new DukeExceptions("");
        } catch (DukeExceptions e) {
            System.out.println(e.toString());
            userInput = brToUse.readLine();
        }

            //Clarify if still need to have level-2 functionality.
            // Task toAdd = new Task(userInput);
            // storedText.add(toAdd);
            // String toOutput = "added: " + toAdd.name;
            // System.out.println(toOutput);
            // userInput = brToUse.readLine();

        }
    }

}
