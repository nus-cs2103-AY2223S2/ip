package domain.usecases;
import domain.entities.core.*;

public class UnknownCommandUsecase implements Executable, ExecutableRegisterable {
    public UnknownCommandUsecase(Writable writable) {
        this.writable = writable;
    }
    private final Writable writable;
    @Override
    public ExitStatus execute(String[] tokens) {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that " +
                "means :-(");
        return ExitStatus.finishCurrentIteration;
    }

    @Override
    public void register(NestableExecutableObject nestable) {
        nestable.registerPostExecutable(this);
    }
}
