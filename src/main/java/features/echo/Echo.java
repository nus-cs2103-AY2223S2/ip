package features.echo;

import event_loop.Executable;
import event_loop.ExitStatus;

public class Echo implements Executable {
    @Override
    public ExitStatus execute(String[] tokens) {
        System.out.println(String.join(" ", tokens));
        // after this, we would want it to skip the current execution loop.
        return ExitStatus.skipCurrentLoop;
    }
}
