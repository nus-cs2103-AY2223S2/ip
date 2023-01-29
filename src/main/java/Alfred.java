import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Alfred {

    private static ArrayList<Task> itemsList;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Alfred(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (AlfredException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayOpening();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlfredException e) {
                ui.displayError(e);
            }
        }
    }

    public static void main(String[] args) {
        Alfred alfred = new Alfred("data/alfred.txt");
        alfred.run();
    }


    /*
    private static void loadFileContents(File dataFile) throws IOException, AlfredException {

        class CheckMark {
            public void isMark(int value, Task task) {
                if (value == 1) {
                    task.markAsDone();
                }
            }
        }
        // anonymous function?
        CheckMark checkMark = new CheckMark();
        // how to create the new directory?
        dataFile.getParentFile().mkdir();
        if (!dataFile.createNewFile()) {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNext()) {
                String[] lineArr = sc.nextLine().split(" \\| ");
                String taskType = lineArr[0];
                Task task;
                // What happens if the data in the file is not as the format given?
                try {
                    switch (taskType) {
                        case "T":
                            task = new ToDo(lineArr[2]);
                            checkMark.isMark(Integer.parseInt(lineArr[1]), task);
                            break;
                        case "D":
                            task = new Deadline(lineArr[2], lineArr[3]);
                            checkMark.isMark(Integer.parseInt(lineArr[1]), task);
                            break;
                        case "E":
                            String[] duration = lineArr[3].split("-");
                            task = new Event(lineArr[2], duration[0], duration[1]);
                            checkMark.isMark(Integer.parseInt(lineArr[1]), task);
                            break;
                        default:
                            throw new AlfredException("I'm sorry but there is an" +
                                    " invalid task in the data file");
                            // No task type
                    }
                    itemsList.add(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new AlfredException("There is probably a missing separator in your file");
                }

            }
        }
    }

    private static void writeToFile(File dataFile) {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : itemsList) {
                fw.write(task.addToFile());
            }
            fw.close();
        } catch (IOException e) {
            Alfred.echoCommand(String.format("Something went wrong while" +
                    " saving the tasks, %s\n", e.getMessage()));
        }
    }


    private static void addItem(String commandLine) throws AlfredException {
        String[] commandArr = commandLine.split(" ", 2);
        String typeTask = commandArr[0];
        String[] descriptionArr;
        String[] lineArr;
        Task task;
        // Handling the type of task
        switch (typeTask) {
            case "todo":
                if (commandArr.length == 1) {
                    throw new AlfredException("The description of a todo cannot be empty\n");
                }
                task = new ToDo(commandArr[1]);
                itemsList.add(task);
                break;
            case "deadline": // Need to consider what if no '/' is given
                lineArr = commandLine.split("/by ");
                if (lineArr.length == 1) {
                    throw new AlfredException("Deadlines should have a due date ." +
                            "Eg: \"<TaskName> /by <DueDate>\"\n");
                }
                descriptionArr = lineArr[0].split(" ", 2);
                if (descriptionArr.length == 1) {
                    throw new AlfredException("Deadlines should have a due date ." +
                            "Eg: \"<TaskName> /by <DueDate>\"\n");
                }
                try {
                    task = new Deadline(descriptionArr[1], lineArr[1]);
                    itemsList.add(task);
                    break;
                } catch (DateTimeParseException e) {
                    throw new AlfredException("The date format should be given as dd/mm/yyyy 24hr-time\n");
                }
            case "event": // need to consider what if no '/from' and '/or is not given?
                lineArr = commandLine.split("/from | /to ");
                if (lineArr.length < 2) { // not sure how to check if theres /from and /to
                    throw new AlfredException("Events should have start and end time. " +
                            "Eg: \"<EventName> /from <StartTime> /to <EndTime>\"\n");
                }
                descriptionArr = lineArr[0].split(" ", 2);
                if (descriptionArr.length == 1) {
                    throw new AlfredException("Events should have start and end time. " +
                            "Eg: \"<EventName> /from <StartTime> /to <EndTime>\"\n");
                }
                try {
                    task = new Event(descriptionArr[1], lineArr[1], lineArr[2]);
                    itemsList.add(task);
                    break;
                } catch (DateTimeParseException e) {
                    throw new AlfredException("The date format should be given as dd/mm/yyyy 24hr-time\n");
                }
            default:
                throw new AlfredException("I'm sorry, but I don't know what that means :<\n");
        }
        // Alfred's response to remaining tasks
        String numTasks = itemsList.size() == 1 ? "task" : "tasks";
        String command = String.format("Noted, task added: \n      %s\n" +
                "    Number of %s in the list: %d\n", task, numTasks, itemsList.size());
        Alfred.echoCommand(command);
    }

    private static void markItem(String indexArg) throws AlfredException {
        try {
            int index = Integer.parseInt(indexArg) - 1;
            Task task = itemsList.get(index);
            task.markAsDone();
            String command = "Well done! Good job " +
                    "for completing your task!\n";
            command += String.format("      %s\n", task);
            Alfred.echoCommand(command);
        } catch (NumberFormatException e) {
            throw new AlfredException("To mark, item you need to pass a valid integer!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new AlfredException(String.format("There are only %d pending tasks\n", itemsList.size()));
        }
    }

    private static void unmarkItem(String indexArg) throws AlfredException {
        try {
            int index = Integer.parseInt(indexArg) - 1;
            Task task = itemsList.get(index);
            task.unmarkTask();
            String command = "I have un-mark this task. Remember to complete " +
                    "your task on time!\n";
            command += String.format("      %s\n", task);
            Alfred.echoCommand(command);
        } catch (NumberFormatException e) {
            throw new AlfredException("To un-mark item, you need to pass a valid integer!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new AlfredException(String.format("There are only %d pending tasks\n", itemsList.size()));
        }
    }
    private static void deleteItem(String indexArg) throws AlfredException {
        try {
            int index = Integer.parseInt(indexArg) - 1;
            Task task = itemsList.remove(index);
            String command = "Noted. I've removed this task. Remember to clear your " +
                    "remaining tasks!\n";
            command += String.format("      %s\n", task);
            Alfred.echoCommand(command);
        } catch (NumberFormatException e) {
            throw new AlfredException("To delete, item you need to pass a valid integer!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new AlfredException(String.format("There are only %d pending tasks\n", itemsList.size()));
        }
    }


    private static void listItems() {
        int itemIndex = 1;
        StringBuilder command = new StringBuilder("Here are your pending tasks: \n");
        if (itemsList.isEmpty()) {
            Alfred.echoCommand("Woohoo! You have no pending tasks\n");
            return;
        }
        for (Task item : itemsList) {
            command.append(String.format("    %d. %s\n", itemIndex, item));
            itemIndex++;
        }
        String numTasks = itemsList.size() == 1 ? "task" : "tasks";
        command.append(String.format("    You have %d %s in the list\n", itemsList.size(), numTasks));
        Alfred.echoCommand(command.toString());
    }

    private static void listItems(LocalDate date) {
        int itemIndex = 1;
        String initial = String.format("Here are your pending tasks on %s: \n", date);
        StringBuilder command = new StringBuilder(initial);
        if (itemsList.isEmpty()) {
            Alfred.echoCommand("Woohoo! You have no pending tasks\n");
            return;
        }
        for (Task item : itemsList) {
            if (item.containsDate(date)) {
                command.append(String.format("    %d. %s\n", itemIndex, item));
                itemIndex++;
            }
        }
        String numTasks = itemIndex == 1 ? "task" : "tasks";
        command.append(String.format("    You have %d %s on %s in the list\n", itemIndex - 1, numTasks, date));
        Alfred.echoCommand(command.toString());
    }
    */
}
