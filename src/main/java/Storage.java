import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
public class Storage {
    private final static DateTimeFormatter  timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter  HrFormat = DateTimeFormatter.ofPattern("HHmm");
    private  String file;
    public Storage(String file){
        this.file = file;
    }
    private void createDataFile() {
        File f = new File(file);
        new File("database").mkdir();
        try {
            f.createNewFile();
        }
        catch (IOException e){
            System.out.println("File does not exist creating now");
        }
    }
    public  ArrayList<Task> readnWriteData(){
        ArrayList<Task> list = new ArrayList<>();
        try{
            File data = new File(file);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String txt = sc.nextLine();
                String[] segments = txt.split(" / ");
                String task = segments[0];
                String indicator = segments[1];
                String description = segments[2];
                Task addedtask;
                if (task.equals("T")) {
                    addedtask = new ToDo(description);
                    list.add(addedtask);
                    if (indicator.equals("1")) {
                        addedtask.markDone();
                    }
                }
                else if (task.equals("D")){
                    LocalDateTime Doneby = LocalDateTime.parse(segments[3],timeFormat);
                    addedtask = new Deadline(description, Doneby);
                    list.add(addedtask);
                    if (indicator.equals("1")) {
                        addedtask.markDone();
                    }
                }
                else if (task.equals("E")){
                    LocalDateTime from = LocalDateTime.parse(segments[3],timeFormat);
                    LocalTime to = LocalTime.parse(segments[4],HrFormat);
                    addedtask = new Events(description, from, to);
                    list.add(addedtask);
                    if (indicator.equals("1")) {
                        addedtask.markDone();
                    }
                }
            }
        }
        catch (IOException | ArrayIndexOutOfBoundsException e){
            createDataFile();
        }
        return list;
    }
    public  void saveData(TaskList list) throws  IOException{
        FileWriter fwrite = new FileWriter("database/data.txt", false);
        for (int i = 0; i < list.size(); i++) {
            fwrite.write(list.get(i+1).changeFormat() + "\n");
        }
        fwrite.close();
    }
}
