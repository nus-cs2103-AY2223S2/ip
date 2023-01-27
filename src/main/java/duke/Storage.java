package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;

public class Storage {

    protected String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public void load(TaskList taskList) throws DukeException {
        try {
            FileReader fileReader = new FileReader(this.filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] entry = line.split(" ");
                String c = entry[0];
                boolean isDone;
                if (entry[1].equals("true")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                StringBuilder desc = new StringBuilder();
                desc.append(" ");
                for (int i = 2; i < entry.length - 1; i++) {
                    desc.append(entry[i]);
                    desc.append(" ");
                }

                desc.append(entry[entry.length - 1]);
                if (c.equals("todo")) {
                    taskList.addTask(Todo.create(desc.toString(), isDone));
                } else if (c.equals("deadline")) {
                    taskList.addTask(Deadline.create(desc.toString(), isDone));
                } else if (c.equals("event")) {
                    taskList.addTask(Event.create(desc.toString(), isDone));
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new DukeException();
        }
    }

    public void save(TaskList taskList) throws DukeException{
        try {
            File file = new File("./data/duke.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i  = 0; i < taskList.getSize(); i++) {
                Task t = taskList.getTask(i);
                String entry = t.getType() + " " + t.getIsDone() + " " + t.getDescription();
                if (t instanceof Deadline) {
                    entry += " /by " + ((Deadline) t).getDueDate();
                } else if (t instanceof Event) {
                    entry += " /from " + ((Event) t).getStartTime() + " /to " + ((Event) t).getEndTime();
                }
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
