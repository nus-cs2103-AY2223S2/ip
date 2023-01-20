package domain.usecases;

import domain.models.core.Executable;
import domain.models.core.ExecutableRegisterable;
import domain.models.core.ExitStatus;
import domain.models.core.NestableExecutableObject;

public class EchoUsecase implements Executable, ExecutableRegisterable {
    @Override
    public ExitStatus execute(String[] tokens) {
        System.out.println(String.join(" ", tokens));
        // after this, we would want it to skip the current execution loop.
        return ExitStatus.finishCurrentIteration;
    }

    @Override
    public void register(NestableExecutableObject nestable) {
        nestable.registerPostExecutable(this);
    }
}
