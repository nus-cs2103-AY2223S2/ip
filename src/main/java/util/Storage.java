package util;

import java.io.*;

public class Storage {
    private String filePath;
    private String fileDir;
    public Storage(String fileDir, String filePath) {
        // check if fileDir and filePath are not empty
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    public BufferedReader load() throws DukeException {
        BufferedReader br;
        try {
            File directory = new File(this.fileDir);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new DukeException("Unable to read from file, creating a new file");
        }
        return br;
    }

    public void saveTasks(TaskList tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String tl = tasks.saveTaskList();
            fw.write(tl);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
