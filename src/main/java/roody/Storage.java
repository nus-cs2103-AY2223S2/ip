package roody;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String defaultFolderPath = "./data";
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    // Loads information from txt file or creates new text file
    public ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File data = new File(filePath);
            File folder = new File(defaultFolderPath);
            // check if file exists
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (data.createNewFile()) {
                //System.out.println("File created: " + data.getName());
            } else {
                //System.out.println("File already exists.");
                Scanner s = new Scanner(data);
                String task = "";
                while (s.hasNextLine()) {
                    task = s.nextLine();
                    String[] inputs = task.split("\\|", 5);
                    //System.out.println(Arrays.toString(inputs));
                    // filter by task
                    Task temp;
                    if (inputs[2].equals("T")) {
                        temp = new Todo(inputs[0]);
                    } else if (inputs[2].equals("D")) {
                        temp = new Deadline(inputs[0], LocalDate.parse(inputs[3]));
                    } else if (inputs[2].equals("E")) {
                        temp = new Event(inputs[0], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]));
                    } else {
                        new RoodyException("Error loading text");
                        s.close();
                        return null;
                    }
                    if (inputs[1].equals("true")) {
                        temp.setDone();
                    }
                    list.add(temp);
                }
                s.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    // saves information if any into roody.txt
    public void saveFile(ArrayList<Task> list) {
        ArrayList<String> buffer = new ArrayList<>();
        Path output = Paths.get("./data/Roody.txt");
        for (Task t : list) {
            buffer.add(t.saveTask());
        }
        try {
            Files.write(output, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
