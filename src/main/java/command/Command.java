package command;

import storage.TaskList;

public abstract class Command {
    /**
     * Processes the command and return a reply.
     * @param taskList  the storage
     * @return  Return the reply.
     */
    public abstract String run(TaskList taskList);
}
