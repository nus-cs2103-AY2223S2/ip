package domain.usecases;

import domain.entities.core.CommandRegisterable;
import domain.entities.core.ExitStatus;
import domain.entities.core.IdentifiedCommandable;
import domain.entities.core.NestedCommandableObject;
import domain.entities.core.Writable;

/**
 * A {@link ByeUsecase} is a usecase that would terminate the program. It can
 * be triggered when the user types in "bye".
 */
public class ByeUsecase implements IdentifiedCommandable, CommandRegisterable {
    /**
     * The destination that this ByeUsecase would write its content to.
     */
    private final Writable writable;

    /**
     * Creates a new Bye Usecase.
     *
     * @param writable the destination that this ByeUsecase would write its
     *                 content to.
     */
    public ByeUsecase(Writable writable) {
        this.writable = writable;
    }

    @Override
    public ExitStatus execute(String[] tokens) {
        writable.writeln("Bye! See you next time:-)");
        return ExitStatus.terminate;
    }

    @Override
    public String getId() {
        return "bye";
    }

    @Override
    public void register(NestedCommandableObject nestable) {
        nestable.registerIdentifiableExecutable(this);
    }
}
