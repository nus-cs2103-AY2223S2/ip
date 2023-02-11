package domain.usecases;

import domain.entities.core.*;

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
        assert (tokens.length > 0) : "The tokens should not be empty.";
        writable.writeln(String.join(" ", tokens));
        // after this, we would want it to skip the current execution loop.
        return ExitStatus.finishCurrentIteration;
    }

    @Override
    public void register(NestedCommandableObject nestable) {
        nestable.registerPostExecutable(this);
    }
}
