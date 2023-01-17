package response;

import storage.ToDoList;

/**
 * Represents a response to list everything in the to do list
 */
public class ListResponse extends Response{
    /**
     * List everything in the to do list
     * @param toDoList The to do list to be printed
     * @return Customised string representation of the to do list
     */
    @Override
    public String exec(ToDoList toDoList) {
        return toDoList.toString();
    }
}
