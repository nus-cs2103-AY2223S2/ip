import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String PATH = "./data/duke.txt";
    private static FileWriter fw;
    public Storage() {}
    /* code reused from:
https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
author Aaron D
 */
    public TaskList loadData() throws IOException {
        File directory = new File("./data");
        if (!directory.exists()){
            directory.mkdir();
        }
        File file = new File(PATH);
        if(file.createNewFile()) {
            System.out.println("Seems like you're new here. Welcome onboard and let's get started! ^-^");
            return new TaskList();
        } else {
            return readTextFileToList(file);
        }
    }
    public TaskList readTextFileToList(File f) throws IOException {
        Scanner fs = new Scanner(f);
        TaskList taskList = new TaskList();
        System.out.println("read text file to list"); //
        while (fs.hasNext()) {
            String strTask = fs.nextLine();
            char taskLetter = strTask.charAt(1);
            System.out.println(strTask); //
            switch (taskLetter) {
            case 'T':
                //strTask.replace("[T]", // format: [X][X] xxx | <from> - <by>
                taskList.addToDoFromFile(strTask);
                break;
            case 'E':
                //strTask.replace("[E]", "event");
                taskList.addEventFromFile(strTask);

                break;
            case 'D':
                //strTask.replace("[D]", "deadline");
                taskList.addDeadlineFromFile(strTask);
                break;
            }
        }
        saveListToFile(taskList.getTasks());
        return taskList;
    }
    /*
    public void saveTaskToFile(Task t) throws IOException {
        fw.write(t.toString());
        fw.write("\n");
    } */
    public void closeFileWriter() throws IOException {
        fw.close();
    }
    public void saveListToFile(ArrayList<Task> list) throws IOException {
            fw = new FileWriter(PATH);
            for(Task t : list) {
                String formatted = t.toFileFormat(); // format: [X][X] xxx | <from> - <by>
                fw.write(formatted);
                fw.write("\n");
            }
    }
}
