package duke.instruction;

import duke.customization.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * An abstract instruction class encapsulating a user input instruction in Duke, which can be extended
 * by more specific input instructions like addToDoInstruction, ExitInstructions, etc.
 */

public abstract class Command {
    static final Ui format = new Ui(70, 4);

    /**
     * Execute the respective instructions.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     * @throws DukeException Throws Exceptions when user input invalid instruction
     */
    public abstract void run(TaskList list) throws DukeException;
}