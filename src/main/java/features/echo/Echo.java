package features.echo;

import event_loop.Executable;
import event_loop.ExecutableRegisterable;
import event_loop.ExitStatus;
import event_loop.NestableExecutableObject;

public class Echo implements Executable, ExecutableRegisterable {
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
