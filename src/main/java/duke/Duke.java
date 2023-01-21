package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    static protected ArrayList<Task> tasks = new ArrayList<>();
    static protected final String DATA_DIR = "data/";
    static protected final String DATA_FILENAME = "duke.txt";

    /**
     * Helper function to add a new task to the ArrayList, while outputting a message
     * @param newTask the new duke.Task object to be added
     */
    public static void addTask(Task newTask) {
        tasks.add(newTask);
        prettyPrint(String.format(
                "Got it. I've added this task:\n\t\t%s\n\tNow you have %d task%s in the list.",
                newTask,
                tasks.size(),
                tasks.size() > 1 ? "s" : ""
        ));
    }

    /**
     * Helper function to add a new task to the ArrayList, while outputting a message
     * @param idx The index of the task to be removed
     * @throws BadCommandException
     */
    public static void removeTask(int idx) throws BadCommandException {
        if (idx >= tasks.size() || idx < 0) {
            throw new BadCommandException("Index given is out of bounds!");
        }
        Task taskToDelete = tasks.get(idx);
        tasks.remove(idx);
        prettyPrint(String.format(
                "Got it. I've removed this task:\n\t\t%s\n\tNow you have %d task%s in the list.",
                taskToDelete,
                tasks.size(),
                tasks.size() > 1 ? "s" : ""
        ));
    }

    /**
     * Helper function the output a list of tasks
     */
    public static void listTasks() {
        StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append(String.format(
                    "\t%d. %s",
                    i + 1,
                    tasks.get(i)
            ));
            if (i < tasks.size() - 1) {
                listOutput.append("\n");
            }
        }
        prettyPrint(listOutput.toString());
    }

    /**
     * Helper function to parse the commands and params (if available)
     * @param inputStr the inputted string by the user
     * @throws BadCommandException
     */
    public static void parseInput(String inputStr) throws BadCommandException {
        inputStr = inputStr.trim();
        try {
            if (inputStr.equals("list")) {
                listTasks();
                return;
            }
            String[] inputSplit = inputStr.split(" ", 2);
            if (inputSplit.length < 2) {
                throw new BadCommandException("There are insufficient parameters!");
            }
            String command = inputSplit[0].trim();
            String params = inputSplit[1].trim();
            if (command.equals("mark")) {
                int idx = Integer.parseInt(params) - 1;
                if (idx >= tasks.size() || idx < 0) {
                    throw new BadCommandException("Index given is out of bounds!");
                }
                tasks.get(idx).markAsDone();
                prettyPrint(String.format(
                        "Nice! I've marked this task as done:\n\t\t%s",
                        tasks.get(idx)
                ));
            } else if (command.equals("unmark")) {
                int idx = Integer.parseInt(params) - 1;
                if (idx >= tasks.size() || idx < 0) {
                    throw new BadCommandException("Index given is out of bounds!");
                }
                tasks.get(idx).unmarkAsDone();
                prettyPrint(String.format(
                        "OK, I've marked this task as not done yet:\n\t\t%s",
                        tasks.get(idx)
                ));
            } else if (command.equals("delete")) {
                int idx = Integer.parseInt(params) - 1;
                removeTask(idx);
            } else if (command.equals("todo")) {
                addTask(new Todo(params.trim()));
            } else if (command.equals("deadline")) {
                String[] paramsSplit = params.split("/by", 2);
                if (paramsSplit.length < 2) {
                    throw new BadCommandException("There are insufficient parameters!");
                }
                addTask(new Deadline(paramsSplit[0].trim(), paramsSplit[1].trim()));
            } else if (command.equals("event")) {
                String[] paramsSplit = params.split("/from|/to", 3);
                if (paramsSplit.length < 3) {
                    throw new BadCommandException("There are insufficient parameters!");
                }
                addTask(new Event(paramsSplit[0].trim(), paramsSplit[1].trim(), paramsSplit[2].trim()));
            } else {
                throw new BadCommandException("I'm sorry, but I don't know what that means :-(");
            }
            saveTasks();
        } catch (DukeException e) {
            prettyPrint(e.getMessage());
        }
    }
    public static void saveTasks() {
        File dataDirectory = new File(DATA_DIR);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File dataFile = new File(DATA_DIR + DATA_FILENAME);
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
            for (Task task: tasks) {
                ArrayList<String> params = new ArrayList<>();
                params.add(task.getTaskType().getSymbol());
                params.add(task.isDone() ? "1" : "0");
                params.add(task.getDescription());
                if (task instanceof Event) {
                    Event castedTask = (Event) task;
                    params.add(castedTask.getStartTime());
                    params.add(castedTask.getEndTime());
                } else if (task instanceof Deadline) {
                    Deadline castedTask = (Deadline) task;
                    params.add(castedTask.getByDate());
                }
                String outputStr = String.join(" | ", params);
                fw.write(outputStr + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            prettyPrint("Error saving to hard drive: " + e.getMessage());
        }
    }
    public static void retrieveData() {
        File dataFile = new File(DATA_DIR + DATA_FILENAME);
        if (!dataFile.exists()) {
            return;
        }
        try {
            Scanner fileScanner = new Scanner(dataFile);
            int rowCnt = 0;
            while (fileScanner.hasNext()) {
                rowCnt++;
                String[] inputStr = fileScanner.nextLine().trim().split(" \\| ");
                if (inputStr.length < 3) {
                    continue;
                }
                try {
                    Task newTask = null;
                    if (inputStr[0].equals(Task.TaskSymbol.TODO.getSymbol())) {
                        newTask = new Todo(inputStr[2]);
                        tasks.add(newTask);
                    } else if (inputStr[0].equals(Task.TaskSymbol.DEADLINE.getSymbol())) {
                        if (inputStr.length < 4) {
                            continue;
                        }
                        newTask = new Deadline(inputStr[2], inputStr[3]);
                        tasks.add(newTask);
                    } else if (inputStr[0].equals(Task.TaskSymbol.EVENT.getSymbol())) {
                        if (inputStr.length < 5) {
                            continue;
                        }
                        newTask = new Event(inputStr[2], inputStr[3], inputStr[4]);
                        tasks.add(newTask);
                    }
                    if (newTask != null) {
                        if (Integer.parseInt(inputStr[1]) == 1) {
                            newTask.markAsDone();
                        }
                    }
                } catch (DukeException e) {
                    prettyPrint(String.format(
                            "Error processing row %d of %s: %s",
                            rowCnt,
                            DATA_DIR + DATA_FILENAME,
                            e.getMessage()
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            prettyPrint("Error retrieving local data: " + e.getMessage());
        }
    }
    /**
     * Helper function to output to the user "prettily".
     * @param out the string to output
     */
    public static void prettyPrint(String out) {
        final String divider = "____________________________________________________________";
        System.out.println(String.format("\t%s\n\t%s\n\t%s", divider, out, divider));
    }
    public static void main(String[] args) {
        // Retrieve list from data/duke.txt
        retrieveData();
        // Start receiving inputs from terminal
        Scanner inputScanner = new Scanner(System.in);
        prettyPrint("Hello! I'm Duke\n\tWhat can I do for you?");
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            try {
                parseInput(inputStr);
            } catch (BadCommandException e) {
                prettyPrint(e.getMessage());
            }
            inputStr = inputScanner.nextLine().trim();
        }
        prettyPrint("Bye. Hope to see you again soon!");;
    }
}
