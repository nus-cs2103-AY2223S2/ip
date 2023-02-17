package duke;

/**
 * Creates a new Duke bot that tracks tasks.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private String filePath;

    /**
     * A public constructor to initialize Duke instance.
     *
     * @param filePath Path of file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.filePath = filePath;
        this.storage.loadData(this.tasks);
    }

    /**
     * Gets the response in regards to user input.
     *
     * @param input User input.
     * @return Response to user input.
     */
    public String getResponse(String input) {
        String[] descriptions = input.split(" ", 2);
        String taskType = descriptions[0];

        if (taskType.equals("bye")) {
            this.storage.saveData(this.filePath, this.tasks);
            System.exit(0);
        }
        return Parser.parseInput(this.tasks, taskType, descriptions);
    }
}
