package domain.usecases;

import domain.entities.core.CommandRegisterable;
import domain.entities.core.Commandable;
import domain.entities.core.ExitStatus;
import domain.entities.core.NestedCommandableObject;
import domain.entities.core.Writable;

/**
 * A {@link EchoUsecase} is a usecase that would echo the tokens that it
 * receives.
 */
public class EchoUsecase implements Commandable, CommandRegisterable {
    /**
     * The writable that this usecase writes to.
     */
    private final Writable writable;

    /**
     * Creates a new EchoUsecase.
     *
     * @param writable the writable that this EchoUsecase writes to.
     */
    public EchoUsecase(Writable writable) {
        this.writable = writable;
    }

    @Override
    public ExitStatus execute(String[] tokens) {
        writable.writeln(String.join(" ", tokens));
        // after this, we would want it to skip the current execution loop.
        return ExitStatus.finishCurrentIteration;
    }

    @Override
    public void register(NestedCommandableObject nestable) {
        nestable.registerPostExecutable(this);
    }
}
