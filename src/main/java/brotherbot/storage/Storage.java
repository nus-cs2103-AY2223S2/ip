package brotherbot.storage;

import brotherbot.commands.Command;
import brotherbot.commands.DisplayCommand;
import brotherbot.commands.ExitCommand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     */
    public String load() {
        try {
            String output;
            boolean created = hardDisk.createNewFile();
            // load existing data.txt file
            if (!created) {
                Scanner scanner = new Scanner(hardDisk);
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    if (input == "") {
                        continue;
                    }
                    boolean isDone = Character.isLetter(input.charAt(8));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    Task x;
                    if (input.contains("From:")) {
                        int endDescription = input.indexOf("From:") - 1;
                        int endStart = input.indexOf("To:") - 1;
                        String start = LocalDateTime.parse(input.substring(endDescription + 7, endStart), formatter).format(formatter2);
                        String end = LocalDateTime.parse(input.substring(endStart + 5), formatter).format(formatter2);
                        x = new Event(input.substring(11, endDescription), start, end);
                    } else if (input.contains("By:")) {
                        int endDescription = input.indexOf("By:") - 1;
                        String deadline = LocalDateTime.parse(input.substring(endDescription + 5), formatter).format(formatter2);
                        x = new Deadline(input.substring(11, endDescription), deadline);
                    } else {
                        x = new Todo(input.substring(11));
                    }
                    if (isDone) {
                        x.markAsDone();
                    }
                    this.taskStorage.add(x);

                    // Printout existing brotherbot.storage database

                }
                output = this.taskStorage.display();
                scanner.close();
            } else {
                output = "New file created: data.txt";
            }
            return output;
        } catch (IOException e) {
            String output = "An error occurred while creating the new file: data.txt";
            e.printStackTrace();
            return output;
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

