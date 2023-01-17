package response;

import storage.ToDoList;
import storage.Task;

public class MarkResponse extends Response {
    private Integer idxToMark;

    public MarkResponse(String inputContent) {
        this.idxToMark = Integer.parseInt(inputContent);
    }

    @Override
    public String exec(ToDoList toDoList) {
        Task currTask = toDoList.mark(idxToMark);
        return String.format("Nice! I've marked this task as done:\n\t\t%s", currTask.toString());
    }
}
