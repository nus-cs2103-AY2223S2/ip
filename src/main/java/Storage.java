import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Storage {
    private static String DUKETXT = "./data/duke.txt";
    private static ArrayList<Task> list = new ArrayList<Task>(100);

    public void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void markListItem(int index) {
        list.get(index).toBeMarked();
    }

    public void unmarkListItem(int index) {
        list.get(index).toBeUnmarked();
    }

    public void addTodoItem(String input) {
        list.add(new Todo(input.substring(5, input.length())));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addDeadlineItem(String first_word, String second_word) {
        list.add(new Deadline(first_word, second_word));
    }

    public void addEventItem(String name, String startTime, String endTime) {
        list.add(new Event(name, startTime, endTime));
    }

    public void deleteListItem(int index) {
        Task temp = list.get(index);
        list.remove(index);
        System.out.println("The Duke has removed this task: " + temp);
        System.out.println("Now you have " + list.size() + " in the list.");
    }


     /**
     * Part of the code extracted from https:/
     * /www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
     */

     public void loadFileData() {
        try {
            File file = new File("./data");
            if (file.exists()) {
                File txtFile = new File(DUKETXT);
                FileReader fileReader = new FileReader(txtFile);
                readToFile(fileReader);
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readToFile(FileReader file) {
        try {
            BufferedReader reader = new BufferedReader(file);
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(DUKETXT);
            for (Task t : list) {
                writer.write(t.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
