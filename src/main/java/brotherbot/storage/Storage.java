package brotherbot.storage;

import brotherbot.commands.Command;
import brotherbot.commands.DisplayCommand;
import brotherbot.commands.ExitCommand;
import brotherbot.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private File hardDisk;
    private TaskList taskStorage;

    /**
     * Constructor for creating a Storage object.
     *
     * @param filePath The path where the Tasks are stored.
     */
    public Storage(String filePath) {
        this.hardDisk = new File(filePath);
        this.taskStorage = new TaskList();
    }

    /**
     * Load existing data into taskList if existing data file exists.
     * Creates new data file if no existing data file exist.
     *
     * @param ui User interface to display the relevant loaded data.
     */
    public void load(Ui ui) {
        try {
            boolean created = hardDisk.createNewFile();
            // load existing data.txt file
            if (!created) {
                Scanner scanner = new Scanner(hardDisk);
                if (!scanner.hasNextLine()) {
                    ui.toUser("No Existing Tasks my brother!");
                } else {
                    ui.toUser("Existing Tasks my brother!");
                }
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    if (input == "") {
                        continue;
                    }
                    boolean isDone = Character.isLetter(input.charAt(8));
                    Task x;
                    if (input.contains("From:")) {
                        int endDescription = input.indexOf("From:") - 1;
                        int endStart = input.indexOf("To:") - 1;
                        x = new Event(input.substring(11, endDescription), input.substring(endDescription + 7, endStart), input.substring(endStart + 5));
                    } else if (input.contains("By:")) {
                        int endDescription = input.indexOf("By:") - 1;
                        x = new Deadline(input.substring(11, endDescription), input.substring(endDescription + 5));
                    } else {
                        x = new Todo(input.substring(11));
                    }
                    if (isDone) {
                        x.markAsDone();
                    }
                    this.taskStorage.add(x);

                    // Printout existing brotherbot.storage database
                    this.taskStorage.display(ui);
                }
                scanner.close();
            } else {
                ui.toUser("New file created: data.txt");
            }
        } catch (IOException e) {
            ui.showLoadingError();
            e.printStackTrace();
        }
    }

    public TaskList getTasks() {
        return this.taskStorage;
    }

    /**
     * Saves existing tasks in taskList into a data file.
     *
     * @param command Executed command to determine if save action to update data file is required.
     */
    public void save(Command command) {
        if (!(command instanceof ExitCommand || command instanceof DisplayCommand)) {
            try {
                FileWriter writer = new FileWriter(hardDisk, false);
                PrintWriter printWriter = new PrintWriter(writer);
                this.taskStorage.write(printWriter);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

