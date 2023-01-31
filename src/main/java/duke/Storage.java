package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/** Storage is a class that help store data in the local file and also read data from local file,
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Storage {
    private final String data_address;
    public Storage(String file_name) {
        Path currentRelativePath = Paths.get("");
        String currentRelativePath_name = currentRelativePath.toAbsolutePath().toString();
        this.data_address = currentRelativePath_name + "\\data\\" + file_name;
        try {
            File file_parent = new File(currentRelativePath_name + "\\data");
            if (!file_parent.exists()) {
                file_parent.mkdir();
            }
            File data_file = new File(data_address);
            if (!data_file.exists()){
                data_file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("sth went wrong");
        }
    }

    /**
     * Returns all tasks stored in duke.txt.
     *
     * @return An arrayList of all tasks stored in duke.txt.
     * @throws IOException if the file duke.txt is not found.
     */
    public ArrayList<Task> load(){
        ArrayList<Task> arrayList = new ArrayList<>();
        try{
            File file = new File(data_address);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                String[] data_parts = data.split("/");
                String type = data_parts[0];
                switch (type){
                    case "T":
                        arrayList.add(new ToDos(data_parts[2], Integer.valueOf(data_parts[1])));
                        break;
                    case "D":
                        arrayList.add(new Deadline(data_parts[2], LocalDate.parse(data_parts[3]), Integer.valueOf(data_parts[1])));
                        break;
                    case "E":
                        arrayList.add(new Event(data_parts[2], LocalDate.parse(data_parts[3]),LocalDate.parse(data_parts[4]), Integer.valueOf(data_parts[1])));
                        break;
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println(e);
        }
        return arrayList;
    }

    /**
     * Takes in an array of tasks and store in duke.txt
     *
     * @param arrayList an arraylist of tasks that need to be stored in duke.txt
     * @throws IOException if the file duke.txt is not found.
     */
    public void update_data(ArrayList<Task> arrayList){
        String data = "";
        try {
            FileWriter fw = new FileWriter(this.data_address);
            for (Task t: arrayList){
                data += t.dataFormat() + "\n";
            }
            fw.write(data);
            fw.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
