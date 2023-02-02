import java.io.*;

public class Storage {
    TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    private Task parseTaskString(String taskString) {
        String taskType;
        String isDone;
        String taskName;
        String by;
        String from;
        String to;
        Task task;
        String[] parsedTask;

        parsedTask = taskString.split("\\|");
        taskType = parsedTask[0];
        isDone = parsedTask[1];
        taskName = parsedTask[2];
        if (taskType.equals("T")) {
            task = new Todo(taskName);
        } else if (taskType.equals("D")) {
            by = parsedTask[3];
            task = new Deadline(taskName, by);
        } else {
            from = parsedTask[3];
            to = parsedTask[4];
            task = new Event(taskName, from, to);
        }

        if (Boolean.valueOf(isDone)) {
            task.mark();
        }

        return task;
    }

    public void saveTasks() throws IOException {
        FileWriter taskFileWriter = new FileWriter("C:\\Users\\jedng\\Documents\\CS2103T\\ip\\ip\\data\\tasks.txt");
        Task task;
        try (BufferedWriter bw = new BufferedWriter(taskFileWriter)) {
            for (int i = 0; i < taskList.getListSize(); i++) {
                task = taskList.getTask(i);
                bw.write(task.getParsedTaskDataString());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    public TaskList loadTasks() {
        Task parsedTask;
        String taskString;
        File dataDirectory = new File("..\\..\\..\\data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File taskFilePath = new File("C:\\Users\\jedng\\Documents\\CS2103T\\ip\\ip\\data\\tasks.txt");
        try {
            if (!taskFilePath.exists()) {
                taskFilePath.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(taskFilePath));
            while ((taskString = br.readLine()) != null) {
                parsedTask = parseTaskString(taskString);
                taskList.addTask(parsedTask);
            }
        } catch (IOException e) {
            //idea: if no file, create directory AND file
            System.out.println("An error occurred while creating the file");
            e.printStackTrace();
        }
        return taskList;
    }
}
