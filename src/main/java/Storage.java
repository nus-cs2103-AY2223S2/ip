import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Storage {
    private File file;

    public Storage() {
    }

    public void save(TaskList list) {
        this.checkAndCreateFile();
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i=1; i<list.size() + 1; i++) {
                bufferedWriter.write(list.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("File cannot be written to.");
        }
    }
    
    private void checkAndCreateFile() {
        this.file = new File("./data/duke.txt");
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }



}
