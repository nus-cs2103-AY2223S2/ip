package response;

import response.Response;
import storage.ToDoList;
import storage.Task;

public class UnMarkResponse extends Response {
    private Integer idxToMark;

    public UnMarkResponse(String inputContent) {
        this.idxToMark = Integer.parseInt(inputContent);
    }

    @Override
    public String exec(ToDoList toDoList) {
        Task currTask = toDoList.unmark(idxToMark);
        return String.format("OK, I've marked this task as not done yet:\n\t\t%s", currTask.toString());
    }
}