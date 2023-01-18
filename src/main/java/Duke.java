import java.util.Scanner;

/**
 * The chatbot that users will be interacting with.
 *
 * @author wz2k
 */
public class Duke {
    /**
     * The name of the chatbot
     */
    public String name = "Duke";

    /**
     * Storage of user's tasks
     */
    private final Task[] taskStorage = new Task[100];

    /**
     * Number of tasks stored by the chatbot
     */
    private int taskStorageSize = 0;

    /**
     * This is the main method which starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello. This is " + duke.name);
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        while (!end) {
            String input = scanner.nextLine();
            end = duke.processInput(input);
        }
    }

    /**
     * This method processes the user's input and outputs
     * the relevant reply by the chatbot
     *
     * @param text the user's input.
     * @return a boolean based on if the conversation has ended.
     */
    public boolean processInput(String text) {
        if (text.startsWith("mark")) {
            this.markTask(Integer.parseInt(text.substring(5)));
            return false;
        } else if (text.startsWith("unmark")){
            this.unmarkTask(Integer.parseInt(text.substring(7)));
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
                this.storeTask(text);
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
     * task storage
     *
     * @param text the description of the task.
     */
    private void storeTask(String text) {
        Task task = new Task(text);
        this.taskStorage[this.taskStorageSize] = task;
        this.taskStorageSize++;
        this.reply("added: " + text);
    }

    /**
     * This method outputs the entire list of tasks stored
     * by the chatbot in order
     */
    private void displayTaskStorage() {
        int size = this.taskStorageSize;

        if (size == 0) {
            this.reply("No task stored.");
            return;
        }

        for (int i = 0; i < size; i++) {
            Task task = this.taskStorage[i];
            this.reply((i + 1) + "." + task.getTaskDetails());
        }
    }

    /**
     * This method marks the task at the specified task
     * number as done
     *
     * @param taskNumber the task order in the storage.
     */
    private void markTask(int taskNumber) {
        Task task = this.taskStorage[taskNumber - 1];
        task.mark();
        this.reply("The following task is marked as done:");
        this.reply("  " + task.getTaskDetails());
    }

    /**
     * This method marks the task at the specified task
     * number as not done
     *
     * @param taskNumber the task order in the storage.
     */
    private void unmarkTask(int taskNumber) {
        Task task = this.taskStorage[taskNumber - 1];
        task.unmark();
        this.reply("The following task is marked as not done:");
        this.reply("  " + task.getTaskDetails());
    }
}
