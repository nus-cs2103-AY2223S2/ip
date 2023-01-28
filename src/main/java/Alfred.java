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

    public static void main(String[] args) {
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        System.out.println("| Your favourite personal assistant:  |");
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        Alfred.printLogo();
        Alfred.printIntro();

        String path = "data/alfred.txt";
        File dataFile = new File(path);

        itemsList = new ArrayList<>();
        try {
            Alfred.loadFileContents(dataFile);
        } catch (IOException e) {
            Alfred.echoCommand("Error, invalid file path");
            return;
        } catch (AlfredException e) {
            Alfred.echoCommand(e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        String commandLine = sc.nextLine();

        while (true) {
            String[] lineArr = commandLine.split(" ");
            String command = lineArr[0];
            try {
                if (command.equals("bye") && lineArr.length == 1) { // So we can still add taskNames that start with bye
                    Alfred.saysBye();
                    Alfred.writeToFile(dataFile);   // Handles the adding of the new task into the data file
                    System.exit(0); // means if the program crashes halfway we won't the files at all
                } else if (command.equals("list") && lineArr.length == 1) {
                    Alfred.listItems();
                } else if (command.equals("mark") && lineArr.length == 2) {
                    Alfred.markItem(lineArr[1]); // must be int catch error
                } else if (command.equals("unmark") && lineArr.length == 2) {
                    Alfred.unmarkItem(lineArr[1]);
                } else if (command.equals("delete") && lineArr.length == 2) {
                    Alfred.deleteItem(lineArr[1]);
                } else if (command.equals("list") && lineArr.length == 2){
                    String second = lineArr[1];
                    try {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
                        LocalDate date = LocalDate.parse(second, format);
                        Alfred.listItems(date);
                    } catch (DateTimeParseException e) {
                        throw new AlfredException("The date format should be given as dd/mm/yyyy\n");
                    }
                } else {
                    Alfred.addItem(commandLine);
                }
            } catch (AlfredException e) {
                Alfred.echoCommand(e.toString()); // only valid data can be saved into the file
            } finally {
                commandLine = sc.nextLine();
            }
        }
    }

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

    private static void echoCommand(String command) {
        Alfred.printLines();
        command = "    " + command;
        System.out.println(command);
        Alfred.printLines();
    }

    private static void saysBye() {
        String command = "Bye. Hope to see you again soon!";
        Alfred.echoCommand(command);
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

    private static void printLogo() {
        System.out.println(" _____ __     ______ _____ ____ ___ ");
        System.out.println("|  -  |  |   |  ____|  _  |  __| _ \\     ");
        System.out.println("| | | |  |   | |___ | |_|_| |__|| | |  ");
        System.out.println("|  -  |  |___|  ___||  _ \\  |__||_| |");
        System.out.println("|_| |_| ____ |__|   |_| \\_|____|__ /   ");

    }

    private static void printIntro() {
        String intro = "Hello! I'm Alfred :>\n"
                + "How can I help you today?";
        Alfred.printLines();
        System.out.println(intro);
        Alfred.printLines();
    }

    private static void printLines() {
        System.out.println("    ____________________________________________________________");
    }
}
