package domain.usecases;
import domain.models.core.Executable;
import domain.models.core.ExecutableRegisterable;
import domain.models.core.ExitStatus;
import domain.models.core.NestableExecutableObject;

public class UnknownCommandUsecase implements Executable, ExecutableRegisterable {
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
