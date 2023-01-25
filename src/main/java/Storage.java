import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    public Storage() {
    }
    public void readSavedFile(File file, TaskList taskList) {
        try {
            Scanner myReader = new Scanner(file);
            String data;
            while (myReader.hasNextLine()) {
                taskList.addTask(parseStringToTask(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Task parseStringToTask(String string) {
        String[] arr = string.split(",");
        if (arr[0].equals("T")) {
            Task t = new ToDo(arr[2], arr[1].equals("1"));
            return t;
        } else if (arr[0].equals("D")) {
            Task t = new Deadline(arr[2], arr[1].equals("1"), LocalDate.parse(arr[3]));
            return t;
        } else {
            Task t = new Event(arr[2], arr[1].equals("1"), LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
            return t;
        }
    }
    public void saveTaskListToStorage(File file, TaskList taskList) {
        try {
            FileWriter myWriter = new FileWriter(file);
            // this truncates the duke.txt to size 0
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
