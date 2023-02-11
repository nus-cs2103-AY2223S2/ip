package domain.usecases;

import domain.entities.core.*;

/**
 * A {@link UnknownCommandUsecase} is a usecase that would be triggered when
 * the user types in a command that is not recognized by the program.
 */
public class UnknownCommandUsecase implements Commandable, CommandRegisterable {
    /**
     * The destination that this UnknownCommandUsecase would write its
     * content. To show the flexibility of this design, you can choose to
     * pass in a {@link presentation.ui.SystemOut} or a
     * {@link presentation.ui.SystemOut} here, and it would write to either
     * system out or system err.
     */
    private final Writable writable;

    /**
     * Creates a new {@link UnknownCommandUsecase}.
     *
     * @param writable the destination that this {@link UnknownCommandUsecase}
     *                 would write its content to.
     */
    public UnknownCommandUsecase(Writable writable) {
        this.writable = writable;
    }

    @Override
    public ExitStatus execute(String[] tokens) {
        writable.writeln("☹ OOPS!!! I'm sorry, but I don't know what that " +
                "means :-(");
        return ExitStatus.finishCurrentIteration;
    }

    @Override
    public void register(NestedCommandableObject nestable) {
        nestable.registerPostExecutable(this);
    }
}
