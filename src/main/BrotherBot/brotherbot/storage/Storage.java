package brotherbot.storage;

import brotherbot.commands.*;
import brotherbot.ui.Ui;

import java.io.*;
import java.util.Scanner;

// load tasks from file, save changes to file
public class Storage {
    private File hardDisk;
    private TaskList taskStorage;


    public Storage(String filepath) {
        this.hardDisk = new File(filepath);
        this.taskStorage = new TaskList();
    }


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
            ui.toUser("An error occurred while creating the new file: data.txt");
            e.printStackTrace();
        }
    }

    public TaskList getTasks() {
        return this.taskStorage;
    }


    public void save(Command c) {
        if (!(c instanceof ExitCommand || c instanceof DisplayCommand)) {
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

