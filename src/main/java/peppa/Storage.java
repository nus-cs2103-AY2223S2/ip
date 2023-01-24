package peppa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private File f;

    private Storage() {}

    public Storage(String filepath) {
        try {
            this.filepath = filepath;
            File f = new File(filepath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            this.f = f;
        } catch (IOException e) {
            Ui.displayMessage("Boink! There seems to be an I/O error. Please try again.");
        }
    }

    public void loadData(TaskList taskList, Ui screen) {
        Ui.displayMessage("Initialising data (if any)...... ");
        try {
            Scanner sc = new Scanner(this.f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Parser.parseFileEntry(line, taskList);
            }
            Ui.displayMessage("Done!");
        } catch (FileNotFoundException e) {
            Ui.displayMessage("Boink! Peppa could not locate the file. Please try again.");
        } catch (PeppaException e) {
            Ui.displayMessage(e.getMessage());
        }
    }

    public void saveChanges(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.getLength(); i++) {
                fw.write(tasks.retrieveTask(i).toFormatString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Ui.displayMessage("Boink! Peppa could not save changes to the task list. Please try again.");
        }
    }
}
