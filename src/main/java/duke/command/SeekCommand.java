package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Executable command to mark an existing task as completed.
 *
 * @author Guo-KeCheng
 */
public class SeekCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * MarkCommand constructor.
     *
     * @param input    String representation of task number
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public SeekCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Find the availability of the specified duration
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        String[] inputs = input.split(" ");

        // If index falls out of bounds
        if (inputs.length != 2) {
            throw new DukeException("OOPS!!! Invalid syntax");
        }

        int quantifier = Integer.parseInt(inputs[0]);
        if (quantifier < 0) {
            throw new DukeException("Impossible to find negative free time!");
        }

        String unitOfTime = inputs[1];

        System.out.println(unitOfTime);
        if (!unitOfTime.equals("days") && !unitOfTime.equals("hours") && !unitOfTime.equals("minutes")) {
            throw new DukeException("Wrong unit of time");
        }

        LocalDateTime availableDate = taskList.seekAvailability(quantifier, unitOfTime);

        return ui.printAvailability(availableDate, input);
    }
}
