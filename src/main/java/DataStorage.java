import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {
    private final String FILEPATH;
    public DataStorage(String filepath){
        this.FILEPATH = filepath;
    }

    public void save(ArrayList<Task> dataArray) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task data : dataArray) {
                String text = data.saveFormat();
                fw.write(text + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printCurrentTask () {
        if (!Files.exists(Path.of(FILEPATH))) {
            new File(FILEPATH).getParentFile().mkdirs();
        } else {
            File file = new File(FILEPATH);
            try {
                Scanner sc = new Scanner(file);
                if (file.length() == 0) {
                    System.out.println("You do not have any tasks in your list currently :D");
                } else {
                    System.out.println("\tThese is your current TaskList");
                    while (sc.hasNextLine()) {
                        String text = sc.nextLine();
                        System.out.println("\t" + text.trim());
                    }
                }
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(FILEPATH);
        Scanner loader = new Scanner(file);
        while(loader.hasNextLine()) {
            data.add(loader.nextLine());
        }
        return data;
    }
}


