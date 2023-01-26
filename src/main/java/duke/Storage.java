package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    TaskList<Task> readFile() throws NeroException {
        TaskList<Task> taskList = new TaskList<Task>();
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] inputs = line.split(" | ");
                ArrayList<String> cleanedInputs = new ArrayList<String>();
                String s = "";
                for (int i = 0; i < inputs.length; i++) {
                    if (inputs[i] != "|" || i == inputs.length) {
                        cleanedInputs.add(s);
                    } else {
                        s += inputs[i];
                    }
                }
                boolean isDone = (cleanedInputs.get(1).equals("0")) ? false : true;
                if (cleanedInputs.get(0).equals("T")) {
                    taskList.addTask(new ToDo(cleanedInputs.get(2), isDone));
                } else if (cleanedInputs.get(0).equals("D")) {
                    taskList.addTask(new Deadline(cleanedInputs.get(2), isDone, cleanedInputs.get(3)));
                } else {
                    taskList.addTask(new Event(cleanedInputs.get(2), isDone, cleanedInputs.get(3)));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new NeroException("File was not found!");
        }
        return taskList;
    }

    void saveFile(TaskList<Task> taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(taskList.get(i).toSave());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("The file was not found!");
        }
    }
}
