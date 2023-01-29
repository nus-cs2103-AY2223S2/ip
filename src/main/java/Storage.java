import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String fileName;
    private final String fileDir;

    private final String fileSaveLoc;

    public Storage(String fileDir, String fileName) {
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.fileSaveLoc = fileDir + "/" + fileName;

    }

    public List<String> load() throws DukeException {
        List<String> fileContent = new ArrayList<>();
        try {
            Path path = Paths.get(fileDir);
            if (!Files.exists(path)) {
                new File(fileDir).mkdir();
            }
            if (!Files.exists(Paths.get(fileSaveLoc))) {
                new File(fileSaveLoc).createNewFile();
            }
            Scanner fileReader = new Scanner(new File(fileSaveLoc));
            while (fileReader.hasNextLine()) {

                fileContent.add(fileReader.nextLine());
            }

            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException();
        }
        return fileContent;
    }

    public void saveTaskList(TaskList data) {

        try {
            FileWriter writerObj = new FileWriter(fileSaveLoc, false);
            writerObj.write(data.toSaveData());
            writerObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
