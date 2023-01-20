package domain.usecases;

import domain.entities.core.*;

public class EchoUsecase implements Executable, ExecutableRegisterable {
    /**
     * Creates a new EchoUsecase.
     * @param writable the writable that this EchoUsecase writes to.
     */
    public EchoUsecase(Writable writable) {
        this.writable = writable;
    }

    /**
     * The writable that this usecase writes to.
     */
    private final Writable writable;
    @Override
    public ExitStatus execute(String[] tokens) {
        writable.writeln(String.join(" ", tokens));
        // after this, we would want it to skip the current execution loop.
        return ExitStatus.finishCurrentIteration;
    }

    @Override
    public void register(NestableExecutableObject nestable) {
        nestable.registerPostExecutable(this);
    }
}
