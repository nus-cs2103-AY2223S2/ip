package DukeHelpfulCode.Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

import DukeHelpfulCode.Tasks.*;
import DukeHelpfulCode.Exceptions.*;

public class Storage {

    public String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    // should have a read, write and search function in this class

    private Task taskFromText(String[] s){
        /**
         *  Reads the task from the Text.
         *
         * @param   s       The text to read the task from
         * @return  task    Task that the user indicated
         */
        String type = s[0]; // [T] = todo | [D] = deadline | [E] = event
        boolean isDone = s[1].equals("[X]"); // note that if not done, s[1] will be "["
        String name = "";
        Task t = null;
        int i = isDone ? 2 : 3;
        if (type.equals("[T]")){
            for (; i < s.length; i++){
                name += s[i] + " ";
            }
            t = new Todo(name, isDone);
        } else if (type.equals("[D]")){
            while (!s[i].equals("(by:")){
                name += s[i] + " ";
                i++;
            }
            i++; // to skip the "(by:"
            String dd = s[i] + " " + s[i+1] + " " + s[i+2] + " " + s[i+3] + " " + s[i+4];
            // dd MMM yy hh:mm a)
            t = new Deadline(name, dd.substring(0,dd.length()-1), isDone);
        } else if (type.equals("[E]")){
            while (!s[i].equals("(from:")){
                name += s[i] + " ";
                i++;
            }
            i++; // to skip the "(from:"
            String sd = s[i] + " " + s[i+1] + " " + s[i+2] + " " + s[i+3] + " " + s[i+4];
            // dd MMM yy hh:mm a)
            i+=6; // to skip "to:"
            String ed = s[i] + " " + s[i+1] + " " + s[i+2] + " " + s[i+3] + " " + s[i+4];
            // dd MMM yy hh:mm a)
            t = new Event(name, sd, ed.substring(0,ed.length()-1),isDone);
        }

        return t;

    }

    private LocalDateTime formatDateTime(String s) {
        /**
         * Formats the datetime from string
         *
         * @param s     The text version of the datetime
         * @return dateTime the formatted dateTime
         */
        LocalDateTime dt = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
             dt = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            // Do nothing, just continue to the next format
        }
        return dt;
    }

    public List<Task> load() throws EmptyTaskListException, IOException {
        /**
         *  Loads the currently existing tasks and stuff?
         *  this function goes into the new Tasklist()
         *  if empty throws EmptyTaskListException
         *  if not empty return the tasklist current existing
         *
         * @param   none
         * @return  taskList    The currently existing stuff from the save file.
         */
        File taskListText = new File("tasks.txt");
        List<Task> taskList = new ArrayList<>();
        if (!taskListText.exists()) {
            taskListText.createNewFile();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                taskList.add(taskFromText(line.split(" ")));
            }
            if (taskList.size() == 0) {
                throw new EmptyTaskListException();
            }
        }

        return taskList;

    }

    public void write(TaskList tl) throws IOException {
        /**
         * Writes to the save file
         *
         * @param   tl Tasklist
         * @return  none
         */
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        for (int i = 0; i < tl.len(); i++){
            System.out.println(tl.getTaskList().get(i).toString());
            writer.write(tl.getTaskList().get(i).toString()+"\n");
            writer.flush();
        }
    }

}
