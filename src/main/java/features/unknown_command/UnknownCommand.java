package features.unknown_command;
import event_loop.Executable;
import event_loop.ExecutableRegisterable;
import event_loop.ExitStatus;
import event_loop.NestableExecutableObject;

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
