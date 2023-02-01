package task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class TaskList extends ArrayList<Task> {
    // Stores everything in the lists and takes care of adding/removing from the list
    // author: notnoop
    // Reused from https://stackoverflow.com/questions/1096621/
    public TaskList(List<String> fileData) throws IOException {
        //Somehow gotta read the fileData in
        for (String line : fileData) {
            // process the line
            String[] lineSplit = line.split("#");
            switch (lineSplit[0]) {
            case "T":
                // To-do entry
                Task todo = new Todo(lineSplit[2]);
                if (parseInt(lineSplit[1]) == 1) {
                    todo.setChecked(true);
                }
                this.add(todo);
                break;
            case "D":
                // task.Deadline
                Task dead = new Deadline(lineSplit[2], lineSplit[3]);
                if (parseInt(lineSplit[1]) == 1) {
                    dead.setChecked(true);
                }
                this.add(dead);
                break;
            case "E":
                // task.Event
                Task event = new Event(lineSplit[2], lineSplit[3], lineSplit[4]);
                if (parseInt(lineSplit[1]) == 1) {
                    event.setChecked(true);
                }
                this.add(event);
                //Fallthrough
            }
        }
    }

    public TaskList() {

    }

    public void mark(int index) throws IndexOutOfBoundsException {
        super.get(index).setChecked(true);
    }

    public void unmark(int index) throws IndexOutOfBoundsException {
        super.get(index).setChecked(false);
    }

}
