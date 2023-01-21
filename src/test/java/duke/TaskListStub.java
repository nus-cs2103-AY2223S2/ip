package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskListStub extends TaskList{
    List<TaskStub> list;
    public TaskListStub(){
        List<TaskStub> ls = new ArrayList<>();
        TaskStub ts = new TaskStub(false, "test des");
        ls.add(ts);
        this.list = ls;
    }
}
