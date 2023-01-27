package duke;

import java.util.Scanner;

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
    }

    /** Runs Duke bot. */
    public void run() {
        this.storage.loadData(this.tasks);
        Ui.welcomeMsg();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] descriptions = input.split(" ", 2);
            String taskType = descriptions[0];

            if (taskType.equals("bye")) {
                this.storage.saveData(this.filePath, this.tasks);
                Ui.exitMsg();
                return;
            }
            
            Parser.parseInput(this.tasks, taskType, descriptions);
        }
    }

    public static void main(String[] args) {
        new Duke("../data/taskData.txt").run();
    }
}