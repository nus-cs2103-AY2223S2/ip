package duke.storage;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import duke.tasklist.TaskList;
import java.util.Scanner;
import duke.tasks.Task;
import duke.dukeexceptions.DukeExceptions;

public class Storage {
    private File file;
    private final String path;

    public Storage(String path){
        this.path = path;
        fileSetup();
    }

    public void fileSetup() {
        File directory = new File(path);
        try {
            if(!directory.exists()){
                directory.mkdir();
            }

            this.file = new File(path, "duke.txt");

            if(!this.file.exists()){
                this.file.createNewFile();
            }


        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    public TaskList loadFile(){
        TaskList result = new TaskList();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                result.addTask(Task.decode(nextLine));
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeExceptions("Error when loading from save");
        }
        return result;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);

            fileWriter.write(taskList.saveTaskList());

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
