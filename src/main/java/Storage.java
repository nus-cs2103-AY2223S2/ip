import java.io.FileNotFoundException;
import java.util.Arrays;
import java.nio.file.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String path;

    public Storage(String path) {
        this.path = path;
    }

    public void saveTask(TaskList tasklist) {
        try {
            FileWriter fileWriter = new FileWriter(this.path);

            for (int i = 0; i < tasklist.currentSize; i++) {

                if (tasklist.tasks.get(i) instanceof Deadline) {
                    Deadline curr = (Deadline) tasklist.tasks.get(i);
                    fileWriter.write(curr.type +"/" + String.valueOf(curr.completionStatus) + "/" + curr.name);
                    fileWriter.write("/" + curr.endTime);
                } else if (tasklist.tasks.get(i) instanceof Event) {
                    Event curr = (Event) tasklist.tasks.get(i);
                    fileWriter.write(curr.type +"/" + String.valueOf(curr.completionStatus) + "/" + curr.name);
                    fileWriter.write("/" + curr.startTime);
                    fileWriter.write("/" + curr.endTime);
                } else if (tasklist.tasks.get(i) instanceof Todo) {
                    Todo curr = (Todo) tasklist.tasks.get(i);
                    fileWriter.write(curr.type +"/" + String.valueOf(curr.completionStatus) + "/" + curr.name);
                }
                if (i < tasklist.currentSize - 1) {
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("jialat got problem bro\n" + exception.getMessage());
        }

    }

    public TaskList loadTask() {

        TaskList tasklist = new TaskList(100);

        try {
            File file = new File(this.path);
            if (!file.exists()) {
                Files.createFile(Path.of("./duke.txt"));
                //System.out.println("we here bois2");
            }
            Scanner sc = new Scanner(file);
            //System.out.println("we here bois3");
            while(sc.hasNextLine()) {
                //System.out.println("we here bois1");
                String temp1 = sc.nextLine();
                String[] input = temp1.split("/");
                //System.out.println(temp1);
                //System.out.println(Arrays.toString(input));
                if (input[0].equals("T")) {
                    Todo temp = new Todo(input[2]);
                    if (input[1].equals("true")) {
                        temp.markAsDone();
                    }
                    tasklist.tasks.add(temp);
                    tasklist.currentSize++;
                } else if (input[0].equals("D")) {
                    Deadline temp = new Deadline(input[2], input[3]);
                    if (input[1].equals("true")) {
                        temp.markAsDone();
                    }
                    tasklist.tasks.add(temp);
                    tasklist.currentSize++;
                } else if (input[0].equals("E")) {
                    Event temp = new Event(input[2], input[3], input[4]);
                    if (input[1].equals("true")) {
                        temp.markAsDone();
                    }
                    tasklist.tasks.add(temp);
                    tasklist.currentSize++;
                } else {
                    System.out.println("Invalid type idek how u got here man");
                }
            }

        } catch (IOException exception) {
            System.out.println("bro where tf is ur file");
            System.out.println(exception.getMessage());
        }
        //System.out.println("we here bois4");
        return tasklist;
    }
}
