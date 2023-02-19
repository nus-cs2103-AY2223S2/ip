package cluck.storage;

import cluck.taskList.TaskList;
import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    public static Task buildTaskFromSave(String savedTask) {
        String[] savedTaskFields = savedTask.split("\\|");
        boolean isMarked;

        if (savedTaskFields[1].equals("1")) {
            isMarked = true;
        } else if (savedTaskFields[1].equals("0")) {
            isMarked = false;
        } else {
            System.out.println("Corrupted data found, skipping corrupted data.");
            return null;
        }

        switch (savedTaskFields[0]) {
        case "E":
            return new Event(isMarked, savedTaskFields[2], savedTaskFields[3], savedTaskFields[4]);

        case "D":
            return new Deadline(isMarked, savedTaskFields[2], savedTaskFields[3]);

        case "T":
            return new ToDo(isMarked, savedTaskFields[2]);

        default:
            System.out.println("Corrupted data found, skipping corrupted data.");
            return null;
        }
    }

    public TaskList readSave(File savedFile) {
        try {
            TaskList taskList = new TaskList();
            Task currTask;
            Scanner savedFileScanner = new Scanner(savedFile);
            while (savedFileScanner.hasNextLine()) {
                currTask = buildTaskFromSave(savedFileScanner.nextLine());
                taskList.addTask(currTask);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
