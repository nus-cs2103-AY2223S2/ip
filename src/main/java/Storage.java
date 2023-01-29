import java.io.*;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadFile() {
        TaskList taskList = null;
        try {
            taskList = new TaskList();
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task prevTask;
                String data = sc.nextLine();
                String[] commandInFile = data.split("\\|");
                boolean isDoneInFile = commandInFile[1].charAt(0) == 'X';
                if (commandInFile[0].equals("T")) {
                    prevTask = new Todo(data.substring(6));
                    prevTask.isDone = isDoneInFile;
                    taskList.addTask(prevTask);
                } else if (commandInFile[0].equals("D")) {
                    prevTask = new Deadline(commandInFile[2].substring(1), commandInFile[3].substring(1));
                    prevTask.isDone = isDoneInFile;
                    taskList.addTask(prevTask);
                } else if (commandInFile[0].equals("E")) {
                    String[] splitString = commandInFile[3].substring(1).split("-");
                    prevTask = new Event(commandInFile[2].substring(1), splitString[0], splitString[1].substring(1));
                    prevTask.isDone = isDoneInFile;
                    taskList.addTask(prevTask);
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println("File not found, created new file for you");
            File newFile = new File("data/duke.txt");
            File folder = new File("data/duke.txt".split("/")[0]);
            try {
                folder.mkdir();
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Handle exception here");
            }
        }
        return taskList;
    }

    public void appendToFile(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            String statusOfTask = task.getStatusIcon();
            String descriptionOfTask = task.description;
            String taskType = task.toString().substring(1,2);
            if (task instanceof Todo) {
                fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "\n");
            } else if (task instanceof Deadline) {
                fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " + ((Deadline) task).by + "\n");
            } else if (task instanceof Event) {
                fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " + ((Event) task).from + "- " + ((Event) task).to + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void updateFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int j = 0; j < taskList.getNumberOfTasks(); j++) {
                Task currentTask = taskList.getTask(j);
                String statusOfTask = currentTask.getStatusIcon();
                String descriptionOfTask = currentTask.description;
                String taskType = currentTask.toString().substring(1,2);
                if (currentTask instanceof Todo) {
                    fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "\n");
                } else if (currentTask instanceof Deadline) {
                    fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " + ((Deadline) currentTask).by + "\n");
                } else if (currentTask instanceof Event) {
                    fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " + ((Event) currentTask).from + "- " + ((Event) currentTask).to + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
