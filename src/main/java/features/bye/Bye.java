package features.bye;

import event_loop.ExitStatus;
import event_loop.IdentifiableExecutable;

public class Bye implements IdentifiableExecutable {
    @Override
    public ExitStatus execute(String[] tokens) {
        System.out.println("Bye! See you next time:-)");
        return ExitStatus.terminate;
    }

    @Override
    public String getId() {
        return "bye";
    }
}
