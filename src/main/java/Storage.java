import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

public class Storage {



    public Storage() {

    }




    public void updateTasksInFile (TaskList list) throws IOException {
        PrintWriter logger = new PrintWriter("Task Data.txt");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            logger.write(list.getTaskAtIndex(i) + "\n");
        }
        logger.close();
    }

    public void loadTaskData (File taskDataFile, TaskList list) {
        try {
            Scanner scanner = new Scanner(taskDataFile);

            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String taskDescription = task.substring(7);
                boolean isMarked = (task.charAt(4) == 'X');
                char taskIdentifier = task.charAt(1);
                if (taskIdentifier == 'T') {
                    list.addTask(new ToDo(taskDescription));
                    if (isMarked) {
                       list.markDone(list.getNumberOfTasks() - 1);
                    }

                } else if (taskIdentifier == 'E') {
                    String[] splitTimes = taskDescription.split(":");
                    String description = taskDescription.split(".")[0].substring(8);
                    String startDayTime = splitTimes[1];
                    String endDayTime = splitTimes[2];
                    list.addTask(new Event(description, startDayTime, endDayTime));
                    if (isMarked) {
                        list.markDone(list.getNumberOfTasks() - 1);
                    }

                } else {
                    String[] splitDeadline = taskDescription.split(".");
                    String description = splitDeadline[0].substring(8);
                    String deadline = splitDeadline[1].split(":")[1];
                    list.addTask(new Deadline(description, deadline));
                    if (isMarked) {
                        list.markDone(list.getNumberOfTasks() - 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
    }
}
