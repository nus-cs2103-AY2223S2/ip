package domain.usecases;

import domain.models.core.ExecutableRegisterable;
import domain.models.core.ExitStatus;
import domain.models.core.IdentifiableExecutable;
import domain.models.core.NestableExecutableObject;

public class ByeUsecase implements IdentifiableExecutable, ExecutableRegisterable {
    @Override
    public ExitStatus execute(String[] tokens) {
        System.out.println("ByeUsecase! See you next time:-)");
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
