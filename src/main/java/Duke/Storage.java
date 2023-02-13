package Duke;

import Duke.Tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Contains Storage object that deals with loading listOfTasks from the file and saving listOfTasks in the file
 */
public class Storage {
    private final static DateTimeFormatter  timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter  HrFormat = DateTimeFormatter.ofPattern("HHmm");
    private  String file;

    /**
     * The constructor for Storage
     * @param file the file/path where data is saved/store in
     */
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

    /**
     * splits string in format regrex " / '
     * @param string the string to split
     * @return String[] the string array after split
     */
    public String[] splitStorage(String string) {
        return string.split(" / ");
    }

    /**
     * The method readnWriteData for reading data from input file path and writing it/ storing it into ArraylistOfTasks
     * @return ArrayList<Task>
     */

    public  ArrayList<Task> readnWriteData(){
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try{
            File data = new File(file);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String txt = sc.nextLine();
                String[] segments = splitStorage(txt);
                Task addedtask;
                if (segments[0].equals("T")) {
                    addedtask = new ToDo(segments[2]);
                    listOfTasks.add(addedtask);
                    if (segments[1].equals("1")) {
                        addedtask.markDone();
                    }
                }
                else if (segments[0].equals("D")){
                    LocalDateTime doneBy = LocalDateTime.parse(segments[3],timeFormat);
                    addedtask = new Deadline(segments[2], doneBy);
                    listOfTasks.add(addedtask);
                    if (segments[1].equals("1")) {
                        addedtask.markDone();
                    }
                }
                else if (segments[0].equals("E")){
                    LocalDateTime from = LocalDateTime.parse(segments[3],timeFormat);
                    LocalTime to = LocalTime.parse(segments[4],HrFormat);
                    addedtask = new Events(segments[2], from, to);
                    listOfTasks.add(addedtask);
                    if (segments[1].equals("1")) {
                        addedtask.markDone();
                    }
                }
            }
        }
        catch (IOException | ArrayIndexOutOfBoundsException e){
            createDataFile();
        }
        return listOfTasks;
    }

    /**
     * The method of saveData to save the data from a TaskList into file
     * @param listOfTasks
     */

    public void saveData(TaskList listOfTasks) throws  IOException{
        FileWriter fwrite = new FileWriter("database/data.txt", false);
        for (int i = 0; i < listOfTasks.size(); i++) {
            fwrite.write(listOfTasks.get(i+1).changeFormat() + "\n");
        }
        fwrite.close();
    }
}