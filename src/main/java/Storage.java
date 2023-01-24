import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String path;
    private final File saveFile;

    public Storage(String path) {
        this.path = path;
        saveFile = new File(path);
    }

    public void save(ArrayList<Task> tasks) throws DukeWriteException, DukeFileCreationException {
        saveFile.delete();
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new DukeFileCreationException();
        }
        StringBuilder save = new StringBuilder();
        for (Task task : tasks) {
            save.append(task.save());
        }
        this.writeToFile(save.toString());
    }

    public void writeToFile(String s) throws DukeWriteException {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(s);
            fw.close();
        } catch (IOException ioException) {
            throw new DukeWriteException();
        }
    }

    public ArrayList<Task> loadSaveFile() throws DukeFileCreationException, DukeReadException {
        Scanner scanner;

        ArrayList list = new ArrayList<Task>();

        try {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeFileCreationException();
        }

        try {
            scanner = new Scanner(saveFile);
        } catch (FileNotFoundException ignored) {
            throw new DukeReadException();
        }

        while (scanner.hasNext()) {
            String fn = scanner.next();
            String[] details = scanner.nextLine()
                    .strip()
                    .split("-");
            switch (fn) {
            case "todo":
                list.add(new ToDos(details[0],
                        Boolean.parseBoolean(details[1])
                ));
                break;
            case "deadline":
                list.add(new Deadlines(details[0],
                        Boolean.parseBoolean(details[1]),
                        details[2]
                ));
                break;
            case "event":
                list.add(new Events(details[0],
                        Boolean.parseBoolean(details[1]),
                        details[2],
                        details[3]
                ));
                break;
            }
        }
        return list;
    }
}
