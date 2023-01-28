import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *Database is a class that help store data in the local file and also read data from local file,
 *it creates a new file under 'data' directory if the given file name does not appear under 'data'
 */
public class Database {
    private String data_address;
    public Database (String file_name) {
        Path currentRelativePath = Paths.get("");
        String currentRelativePath_name = currentRelativePath.toAbsolutePath().toString();
        this.data_address = currentRelativePath_name + "\\data\\" + file_name;
        try {
            File file_parent = new File(currentRelativePath_name + "\\data");
            System.out.println(1);
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

    public ArrayList<Task> get_data(){
        ArrayList<Task> arrayList = new ArrayList<>();
        try{
            File file = new File(data_address);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println("data here: " + data);
                String[] data_parts = data.split("/");
                System.out.println(Arrays.toString(data_parts));
                String type = data_parts[0];
                switch (type){
                    case "T":
                        System.out.println(Arrays.toString(data_parts));
                        arrayList.add(new ToDos(data_parts[2]));
                        break;
                    case "D":
                        System.out.println(Arrays.toString(data_parts));
                        arrayList.add(new Deadline(data_parts[2], data_parts[3]));
                        break;
                    case "E":
                        System.out.println(Arrays.toString(data_parts));
                        arrayList.add(new Event(data_parts[2], data_parts[3]));
                        break;
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println(e);
        }
        System.out.println("getdata function: " + Arrays.deepToString(arrayList.toArray()));
        return arrayList;
    }

    public void update_data(ArrayList<Task> arrayList){
        String data = "";
        try {
            FileWriter fw = new FileWriter(this.data_address);
            for (Task t: arrayList){
                System.out.println(t.toString());
                System.out.println(t.dataFormat());
                data += t.dataFormat() + "\n";
            }
            fw.write(data);
            fw.close();
        } catch (IOException E){
            System.out.println(E);
        }
    }
}
