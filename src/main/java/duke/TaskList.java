package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void printList(){
        for (int i = 0; i < this.size() ; i++){
            int num = i + 1;
            String output = num + ". " + this.get(i);
            Ui.indent(output);
        }
    }

    public void findList(String input) {
        int size = this.size();
        int index = 1;
        for (int i = 0; i < size; i++) {
            Task t = this.get(i);
            if (t.getTask().contains(input)) {
                String output = index + ". " + t;
                Ui.indent(output);
                index++;
            }
        }
    }

    public Task markTask(int index){

        Task  t = this.get(index - 1);
        t.mark();
        return t;
    }

    public Task unmarkTask(int index){

        Task t = this.get(index-1);
        t.unmark();
        return t;
    }

    public void delete(int index){
        this.remove(index - 1);
    }
}
