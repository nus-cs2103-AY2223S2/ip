import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DESTINATION = "data/duke.txt";
    private final File file;
    public Storage() {
        file = new File(FILE_DESTINATION);
    }
    public TaskList readSavedFile() {
        File file = new File(FILE_DESTINATION);
        TaskList taskList = new TaskList();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                taskList.addTask(parseStringToTask(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }

    public Task parseStringToTask(String string) {
        String[] arr = string.split(",");
        if (arr[0].equals("T")) {
            return new ToDo(arr[2]
                    , arr[1].equals("1"));
        } else if (arr[0].equals("D")) {
            return new Deadline(arr[2]
                    , arr[1].equals("1")
                    , LocalDate.parse(arr[3]));
        } else {
            return new Event(arr[2]
                    , arr[1].equals("1")
                    , LocalDate.parse(arr[3])
                    , LocalDate.parse(arr[4]));
        }
    }
    public void saveTaskListToStorage(TaskList taskList) {
        try {
            FileWriter myWriter = new FileWriter(file); // this truncates the duke.txt to size 0
            for (int i = 0; i < taskList.getArraySize(); i++) {
                myWriter.write(taskList.getTask(i).toStorableString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
