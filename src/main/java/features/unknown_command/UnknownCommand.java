package features.unknown_command;
import eventloop.Executable;
import eventloop.ExecutableRegisterable;
import eventloop.ExitStatus;
import eventloop.NestableExecutableObject;

public class UnknownCommand implements Executable, ExecutableRegisterable {
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
