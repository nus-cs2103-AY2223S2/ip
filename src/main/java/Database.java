import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private File file;

    public Database(String relFilePath) throws IOException {
        String home = System.getProperty("user.home");
        Path dirPath = Paths.get(home, "cs2103t/ip", relFilePath.split("/")[0]);
        Files.createDirectories(dirPath);

        Path absFilePath = Paths.get(dirPath.toString(), relFilePath.split("/")[1]);
        this.file = new File(absFilePath.toString());
        file.createNewFile();
    }

    public void updateInputs(ArrayList<Task> inputs) throws IOException, InvalidDateTimeException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String task = scanner.nextLine();

            char taskType = task.charAt(2);
            boolean isMark = task.charAt(5) == 'X';

            switch (taskType) {
            case 'T':
                inputs.add(new Todo(task.substring(8)));
                break;
            case 'D':
                inputs.add(Deadline.createDeadline(task.substring(8)));
                break;
            case 'E':
                inputs.add(Event.createEvent(task.substring(8)));
                break;
            }

            if(isMark) {
                inputs.get(inputs.size() - 1).setIsDone(true);
            }
        }
    }

    public void updateDatabase(ArrayList<Task> task) throws IOException {
        file.delete();
        file.createNewFile();
        for(Task t : task) {
            appendToFile(t.toString());
        }
    }

    public void appendToFile(String textToAppend) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
        fw.write(textToAppend);
        fw.newLine();
        fw.close();
    }
}
