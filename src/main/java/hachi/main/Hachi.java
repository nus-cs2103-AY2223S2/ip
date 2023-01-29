package hachi.main;

import hachi.commands.Command;

/**
 * Hachi, a virtual assistant, that process user instruction
 * and helps to store tasks and events.
 */
public class Hachi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Hachi constructuor.
     *
     * @param filePath The relative path to the file containing saved tasks.
     */
    public Hachi(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTaskList();
        } catch (Exception e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the program by reading user instruction and executing it.
     */
    public void run() {
        this.ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            this.ui.showLine();
            try {
                String command = this.ui.readCommand();

                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                System.out.println("    Try typing: " +
                        "\n     list                             | View your to-do list" +
                        "\n     todo \"task\"                      | Add a task to your to-do list" +
                        "\n     deadline \"task\" /by \"yyyy-mm-dd\" | Add a task to complete by the specified deadline" +
                        "\n     event \"event\" /from \"yyyy-mm-dd\" | Add an event on the specified date" +
                        "\n     /to \"yyyy-mm-dd\"" +
                        "\n     mark \"num\"                       | Mark the (num)th item in your list as completed" +
                        "\n     unmark \"num\"                     | Mark the (num)th item in your list as uncompleted" +
                        "\n     bye                              | Quit hachi.Hachi\n");

            }
        }
    }

    public static void main(String[] args) {
        new Hachi("hachi.txt").run();
    }
}

