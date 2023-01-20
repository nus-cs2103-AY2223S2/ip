package domain.features;

import domain.eventloop.ExecutableRegisterable;
import domain.eventloop.ExitStatus;
import domain.eventloop.IdentifiableExecutable;
import domain.eventloop.NestableExecutableObject;

public class Bye implements IdentifiableExecutable, ExecutableRegisterable {
    @Override
    public ExitStatus execute(String[] tokens) {
        System.out.println("Bye! See you next time:-)");
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
