import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {

    private File dataFile;
    private Ui ui;
    public Storage(File dataFile, Ui ui) {
        this.dataFile = dataFile;
        this.ui = ui;
    }

    public Task interpretLine(String str) throws CorruptedDataException {
        String[] directives = str.split("\\|");
        return Parser.parseLine(directives);
    }

    public LinkedList<Task> readFile(Scanner sc) {
        LinkedList<Task> tasks = new LinkedList<>();
        while (sc.hasNextLine()) {
            try {
                tasks.add(interpretLine(sc.nextLine()));
            } catch (CorruptedDataException e) {
                this.ui.printMessage(e.getMessage());
            }
        }
        return tasks;
    }

    public LinkedList<Task> initialize() throws IOException {
        this.dataFile.createNewFile();
        Scanner sc = new Scanner(this.dataFile);
        return readFile(sc);
    }

    public void updateFile(LinkedList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(String.valueOf(this.dataFile));
            writer.write("");
            for (int i = 0; i < tasks.size(); i++) {
                writer.append(tasks.get(i).dataFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            this.ui.printMessage("ALERT! Unable to overwrite data, input is not saved!");
        }
    }
}
