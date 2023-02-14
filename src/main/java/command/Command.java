package command;

import storage.TaskList;

/**
 * Command abstract class to handle interactions with database.
 */
public abstract class Command {
    /**
     * Processes the command and return a reply.
     *
     * @param taskList the storage
     * @return Return the reply.
     */
    public abstract String run(TaskList taskList);
}
