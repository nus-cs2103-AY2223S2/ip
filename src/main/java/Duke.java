import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;


public class Duke {
    protected final String RECORD_DIR = "./data";
    protected final String RECORD_NAME = "/duke.txt";
    protected final ArrayList<Task> tasks = new ArrayList<>();
    protected final String myName;
    protected final ArrayList<String> commandList = new ArrayList<>();

    public Duke() {
        this.myName = "Duke";
        loadUpRecordIfExists(getRecordPath());
    }

    /**
     * Returns whether the input string is of the specified command type
     * @param string: the input string from the user
     * @param command: a candidate command to check against
     * @return: whether the input string is of the specified command type
     */
    public boolean checkCommand(String string, Command command) {
        boolean isCommand = false;
        switch (command) {
        case LIST:
            isCommand = string.equalsIgnoreCase("list");
            break;
        case MARK:
        case UNMARK:
        case TODO:
        case DEADLINE:
        case EVENT:
        case DELETE:
            isCommand = string.toUpperCase().startsWith(command.name());
            break;
        }
        return isCommand;
    }

    /**
     * Gets the content of the command
     * @param string: the command string
     * @param command: a command type
     * @return the content of the command
     * @throws DukeException when the string is not complete
     */
    public String getCommandContent(String string, Command command) throws DukeException {
        String commandString = command.name().toLowerCase();
        if ((!commandString.equals("list")) && string.length() <= commandString.length() + 1) {
            throw new DukeException("The command argument is not complete.");
        }
        return string.substring(string.indexOf(commandString) + commandString.length() + " ".length());
    }

    /**
     * Handles the input string from the user
     * @param inMsg: the input message from the user
     * @param suppressPrint: suppress print out message or not
     * @throws DukeException when the command is unknown
     */
    public void handleCommand(String inMsg, boolean suppressPrint) throws DukeException {
        String stringToPrint = "";
        if (checkCommand(inMsg,Command.LIST)) {
            stringToPrint = listTasksMsg();
        } else if (checkCommand(inMsg,Command.MARK)) {
            int idx = Integer.parseInt(inMsg.substring(5)) - 1;
            stringToPrint = markTaskDone(idx);
        } else if (checkCommand(inMsg,Command.UNMARK)) {
            int idx = Integer.parseInt(inMsg.substring(7)) - 1;
            stringToPrint = unmarkTaskDone(idx);
        } else if (checkCommand(inMsg, Command.TODO)){
            String todoName = getCommandContent(inMsg, Command.TODO);
            ToDo todo = new ToDo(todoName);
            stringToPrint = addTask(todo);
        } else if (checkCommand(inMsg, Command.DEADLINE)) {
            String deadlineContent = getCommandContent(inMsg, Command.DEADLINE);
            Deadline ddl = new Deadline(deadlineContent);
            stringToPrint = addTask(ddl);
        } else if (checkCommand(inMsg, Command.EVENT)) {
            String eventContent = getCommandContent(inMsg, Command.EVENT);
            Event event = new Event(eventContent);
            stringToPrint = addTask(event);
        } else if (checkCommand(inMsg, Command.DELETE)) {
            String indexToDelete = getCommandContent(inMsg, Command.DELETE);
            stringToPrint = deleteTask(Integer.parseInt(indexToDelete));
        } else {
            throw new DukeException("  OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (!suppressPrint) {
            printStructuredString(stringToPrint);
        }
    }

    /**
     * Print the string in a pre-specified format
     * @param string: the string content to print out
     */
    public void printStructuredString(String string) {
        String longLine = "____________________________________________________________";
        System.out.println(longLine + "\n" + string + "\n" + longLine);
    }

    /**
     * Return the string representation of the task list
     * @param isIndexed: whether to add an index number at the beginning of each task
     *                 or not
     * @return the string representation of the task list
     */
    public String getTaskListString(boolean isIndexed) {
        String s = "";
        int count = 1;
        for (Task t: this.tasks) {
            s += (isIndexed ? (count + ". ") : "")  + t;
            if (count < tasks.size()) {
                s += "\n";
            }
            count += 1;
        }
        return s;
    }

    /**
     * Returns the string representation to be printed out when the command "list" is invoked
     * @return the string representation of the message
     */
    public String listTasksMsg() {
        String taskListString = "Here are the tasks in your list:\n" +
                this.getTaskListString(true);
        return taskListString;
    }

    /**
     * The hello message at start-up
     * @return the hello message
     */
    public String greeting() {
        return String.format("Hello! I'm %s \nWhat can I do for you?", this.myName);
    }

    /**
     * Returns the end message and do final clean-up
     * @return the bye-bye message to be printed out
     */
    public String endMsg() throws DukeException {
        this.saveToFile(this.RECORD_DIR);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Checks if the command marks the end
     * @param string: User-input string
     * @return if the string marks the end of the conversation
     */
    public boolean isEnd(String string) {
        return Objects.equals(string.toLowerCase(), "bye");
    }

    /**
     * Add a task to the list
     * @param task: a task to add
     * @return the string response after adding a task
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }

    public void markTaskDone(String s) {
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            Task t = iterator.next();
            if (t.getName().equals(s)) {
                t.markDone();
            }
        }
    }

    public String markTaskDone(int idx) {
        Task t = this.tasks.get(idx);
        t.markDone();
        return String.format("Nice! I've marked this task as done:\n  %s", t);
    }

    public void unmarkTaskDone(String s) {
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            Task t = iterator.next();
            if (t.getName().equals(s)) {
                t.unmarkDone();
            }
        }
    }

    public String unmarkTaskDone(int idx) {
        Task t = this.tasks.get(idx);
        t.unmarkDone();
        return String.format("OK, I've marked this task as not done yet:\n  %s", t);
    }

    public void removeTask(String s) {
        this.tasks.removeIf(task -> task.getName() == s);
    }

    public String deleteTask(int idx) {
        Task t = tasks.get(idx);
        tasks.remove(idx);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", t, tasks.size());
    }

    private String getRecordPath() {
        return this.RECORD_DIR + "/" + this.RECORD_NAME;
    }

    /**
     * Add user command to a list
     * @param string: the user-input command
     */
    public void addCommandList(String string) {
        commandList.add(string);
    }

    public String getCommandListString() {
        String string = "";
        for (String s: commandList) {
            string = string + s + "\n";
        }
        return string;
    }

    /**
     * Saves the list of tasks to a file
     * @param path: the path of the file to save to
     */
    public void saveToFile(String path) throws DukeException {
        // String taskListString = this.getTaskListString(false); // no index for file
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            // create the directory and the record file
            // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
            if (!(new File(this.RECORD_DIR).exists())) {
                Files.createDirectories(Paths.get(path));
            }
            String recordPath = getRecordPath();
            File file = new File(recordPath);
            file.createNewFile();

            // write to the file
            FileWriter myWriter = new FileWriter(recordPath);
            String commandListString = getCommandListString();
            myWriter.write(commandListString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Load up the record file at start-up
     * @param path: path to the record file
     */
    public void loadUpRecordIfExists(String path) {
        String recordPath = getRecordPath();
        File file = new File(recordPath);
        if (!file.exists()) {
            return;
        }

        // read the file
        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                handleCommand(data, true);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            ;
        }
    }
}
