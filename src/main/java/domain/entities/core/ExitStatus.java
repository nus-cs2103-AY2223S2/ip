package domain.entities.core;

/**
 * This is the return type of executing any command. It should signify what the
 * event loop in the input manager shall do after executing it.
 * <li>
 * If a command returns skipCurrentLoop, then it means that all the commands
 * that could be executed after this would be skipped.
 * </li>
 * <li>
 * If a command returns terminate, then it means that the event loop shall
 * terminate.
 * </li>
 * <li>
 * If a command returns continueCurrentLoop, then it means that all the event
 * loop will continue executing the commands after this in the same iteration.
 * </li>
 */
public enum ExitStatus {
    /**
     * This means that the event loop shall skip the rest of the commands in the
     * current iteration and continue to the next iteration.
     */
    finishCurrentIteration,
    /**
     * This means that the event loop shall terminate.
     */
    terminate,
    /**
     * This means that the event loop shall continue executing the commands in
     * the current iteration.
     */
    continueExecute
}
