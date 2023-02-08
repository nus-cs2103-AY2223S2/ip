package elems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File dataFile;

    public Storage(String filePathName, Ui ui) {
        this.dataFile = new File(filePathName);
        try {
            boolean createdFile = dataFile.createNewFile();
            if (createdFile){
                ui.display("Data file successfully created!");
            } else {
                ui.display("Data file already exists!");
            }
        } catch (IOException e) {
            System.err.println("Failed to create file :(");
            e.printStackTrace();
        }
    }

    public void refreshStorage(TaskList taskList) throws IOException{
            FileWriter clearFile = new FileWriter(this.dataFile, false);
            clearFile.close();
            FileWriter writer = new FileWriter(this.dataFile);
            writer.write(taskList.getListStorageText());
            writer.close();

    }

    public ArrayList<String> load(){
        Scanner scanner = null;
        ArrayList<String> taskText = new ArrayList<>();
        try {
            scanner = new Scanner(dataFile);

            while (scanner.hasNextLine()){
                taskText.add(scanner.nextLine());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("There is no data file found!");
            e.printStackTrace();
        }
        return taskText;
    }

}
