import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The chatbot that users will be interacting with.
 *
 * @author wz2k
 */
public class Duke {
    /**
     * Storage of user's tasks.
     */
    private ArrayList<Task> taskList;

    private final Storage storage;

    public Duke(Storage storage) {
        this.storage = storage;
    }

    /**
     * This is the main method which starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke;
        try {
            Storage storage = new Storage("data/duke.txt");
            storage.initializeStorage();
            duke = new Duke(storage);
            duke.loadTask();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Hello. This is Duke");
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        while (!end) {
            String input = scanner.nextLine();
            end = duke.processInput(input);
        }
    }

    /**
     * This method processes the user's input and outputs
     * the relevant reply by the chatbot.
     *
     * @param text the user's input.
     * @return a boolean based on if the conversation has ended.
     */
    public boolean processInput(String text) {
        try {
            if (text.startsWith("mark ")) {
                this.checkTextLength(text, 5);
                this.markTask(Integer.parseInt(text.substring(5)));
                return false;
            } else if (text.startsWith("unmark ")) {
                this.checkTextLength(text, 7);
                this.unmarkTask(Integer.parseInt(text.substring(7)));
                return false;
            } else if (text.startsWith("todo ")) {
                this.checkTextLength(text, 5);
                Task task = new Todo(text.substring(5), false);
                this.storeTask(task);
                return false;
            } else if (text.startsWith("deadline ")) {
                String[] textArr = text.split("/", 2);
                this.checkTextLength(textArr[0], 9);
                Task task = new Deadline(textArr[0].substring(9), false,
                        textArr[1].substring(3));
                this.storeTask(task);
                return false;
            } else if (text.startsWith("event ")) {
                String[] textArr = text.split("/", 3);
                this.checkTextLength(textArr[0], 6);
                Task task = new Event(textArr[0].substring(6), false,
                        textArr[1].substring(5).trim(), textArr[2].substring(3));
                this.storeTask(task);
                return false;
            } else if (text.startsWith("delete ")) {
                this.checkTextLength(text, 7);
                this.deleteTask(Integer.parseInt(text.substring(7)));
                return false;
            }

            switch (text) {
                case "bye":
                    this.endChat();
                    return true;
                case "list":
                    this.displayTaskStorage();
                    break;
                default:
                    throw new DukeException("Input is not recognized.");
            }

        } catch (DukeException de) {
            reply(de.getMessage());
        }

        return false;
    }

    /**
     * This method is used to output a message from the chatbot
     * to the user.
     *
     * @param message the string the chatbot will reply.
     */
    private void reply(String message) {
        System.out.println(message);
    }

    /**
     * This method is used to send a message to signal the end
     * of the conversation.
     */
    private void endChat() {
        String endMessage = "Chat with Duke has ended";
        this.reply(endMessage);
    }

    /**
     * This method stores the task into the chatbot's
     * task storage.
     *
     * @param task the task to be added.
     */
    private void storeTask(Task task) {
        try {
            this.taskList.add(task);
            this.storage.storeTask(task);
        } catch (IOException e) {
            System.out.println("Failed to add task");
            System.out.println(e.getMessage());
        }

        this.reply("The following task has been added:");
        this.reply("  " + task.toString());
        this.reply("Total tasks: " + this.taskList.size());
    }

    /**
     * This method outputs the entire list of tasks stored
     * by the chatbot in order.
     */
    private void displayTaskStorage() {
        int size = this.taskList.size();

        if (size == 0) {
            this.reply("No task stored.");
            return;
        }

        this.reply("The following tasks are stored:");

        for (int i = 0; i < size; i++) {
            Task task = this.taskList.get(i);
            this.reply((i + 1) + "." + task.toString());
        }
    }

    /**
     * This method marks the task at the specified task
     * number as done.
     *
     * @param taskNumber the task order in the storage.
     */
    private void markTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);

        try {
            task.mark();
            this.storage.restructure(taskList);
        } catch (IOException e) {
            System.out.println("Failed to mark task");
            System.out.println(e.getMessage());
        }

        this.reply("The following task is marked as done:");
        this.reply("  " + task.toString());
    }

    /**
     * This method marks the task at the specified task
     * number as not done.
     *
     * @param taskNumber the task order in the storage.
     */
    private void unmarkTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);

        try {
            task.unmark();
            this.storage.restructure(taskList);
        } catch (IOException e) {
            System.out.println("Failed to unmark task");
            System.out.println(e.getMessage());
        }

        this.reply("The following task is marked as not done:");
        this.reply("  " + task.toString());
    }

    /**
     * This method checks if the length of the text meets
     * the required length.
     *
     * @param text the text which length is to be verified.
     * @param length minimum required length of text.
     * @throws DukeException if text length is insufficient.
     */
    private void checkTextLength(String text, int length) throws DukeException {
        if (text.trim().length() < length) {
            throw new DukeException("Description is invalid.");
        }
    }

    /**
     * This method deletes a specified task based on its order.
     *
     * @param taskNumber specifies the task to be deleted.
     * @throws DukeException if task does not exist.
     */
    private void deleteTask(int taskNumber) throws DukeException {
        int size = this.taskList.size();
        if (size == 0 || taskNumber > size) {
            throw  new DukeException("Task number does not exist.");
        }

        Task task = this.taskList.get(taskNumber - 1);

        try {
            this.taskList.remove(taskNumber - 1);
            this.storage.restructure(taskList);
        } catch (IOException e) {
            System.out.println("Failed to delete task");
            System.out.println(e.getMessage());
        }

        this.reply("The following task has been deleted:");
        this.reply("  " + task.toString());
        this.reply("Total tasks: " + this.taskList.size());
    }

    private void loadTask() throws IOException {
        this.taskList = storage.getTasks();
    }
}
