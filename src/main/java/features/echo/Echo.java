package features.echo;

import eventloop.Executable;
import eventloop.ExecutableRegisterable;
import eventloop.ExitStatus;
import eventloop.NestableExecutableObject;

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
