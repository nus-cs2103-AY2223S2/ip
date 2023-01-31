import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String SAVE_FILE_PATH = "./data/";
    private static final String SAVE_FILE_NAME = "state.data";
    private static final File saveFile = new File(SAVE_FILE_PATH + SAVE_FILE_NAME);
    private Ui ui;

    // todo: raise exceptions on errors and handle in Duke to print error messages instead
    public Storage(Ui ui) {
        this.ui = ui;
    }

    public List<Task> loadState() {
        this.ui.systemPrint("Loading saved data...");

        File dir = new File(SAVE_FILE_PATH);
        if (!dir.exists()) {
            ui.systemPrint("data directory not found! Creating it now...");
            if (dir.mkdirs()) {
                ui.systemPrint("Successfully created data directory!");
            }
        }

        if (!saveFile.exists()) {
            ui.systemPrint("Save file not found! Creating it now...");
            try {
                saveFile.createNewFile();
                ui.systemPrint("Successfully created save file!");
            } catch (IOException e) {
                ui.systemPrint("I/O failed: " + e.toString() + ". Data will not be saved!");
            } catch (SecurityException e) {
                ui.systemPrint("Write access denied by security manager. Data will not be saved!");
            }
        } else {
            try {
                Scanner saveFileScanner = new Scanner(saveFile);
                List<Task> result = new ArrayList<>();
                while (saveFileScanner.hasNext()) {
                    result.add(Task.parseCsvString(saveFileScanner.nextLine()));
                }
                ui.systemPrint("Save file loaded successfully!");
                return result;
            } catch (FileNotFoundException e) {
                // should not happen since we checked for file existence beforehand
                System.out.println("Unexpected error occurred - save file not found. Data will not be saved!");
            }
        }

        return null;
    }
    public void saveState(List<Task> tasks) {
        // will save entire `tasks` list for now, will make it more specific
        // in later iterations
        try {
            FileWriter saveFileWriter = new FileWriter(saveFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                saveFileWriter.write(tasks.get(i).getCsvString());
                // add line separator if not last Task in `tasks`
                if (i < tasks.size() - 1) {
                    saveFileWriter.write(System.lineSeparator());
                }
            }
            saveFileWriter.close();
        } catch (IOException e) {
            ui.prettyPrint("I/O failed: " + e.toString() + ". Data will not be saved!");
            return;
        }
    }
}
