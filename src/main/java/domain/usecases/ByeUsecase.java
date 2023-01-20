package domain.usecases;

import domain.entities.core.*;

public class ByeUsecase implements IdentifiableExecutable, ExecutableRegisterable {
    /**
     * Creates a new Bye Usecase.
     * @param writable the destination that this ByeUsecase would write its
     *                 content to.
     */
    public ByeUsecase(Writable writable) {
        this.writable = writable;
    }
    private final Writable writable;
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
    public void register(NestableExecutableObject nestable) {
        nestable.registerIdentifiableExecutable(this);
    }
}
