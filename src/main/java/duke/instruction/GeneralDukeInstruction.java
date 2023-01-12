package duke.instruction;

import duke.customization.DisplayFormat;
import duke.exception.GeneralDukeException;
import duke.task.TaskList;

/**
 * An abstract instruction class encapsulating a user input instruction in Duke, which can be extended
 * by more specific input instructions like addToDoInstruction, ExitInstructions, etc.
 */

public abstract class GeneralDukeInstruction {
    static final DisplayFormat format = new DisplayFormat(70, 4);
    public abstract void run(TaskList list) throws GeneralDukeException;
}