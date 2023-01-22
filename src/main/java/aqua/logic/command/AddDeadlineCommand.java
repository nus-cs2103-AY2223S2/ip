package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.aquatask.AquaDeadline;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.util.DateUtils;


public class AddDeadlineCommand extends AddTaskCommand {
    @Override
    public AquaDeadline createTask(ArgumentMap args) throws IllegalSyntaxException {
        String name = args.getMainInput()
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalSyntaxException("Name disappeared!"));
        String byString = args.get("by")
                .orElseThrow(() -> new IllegalSyntaxException("[by] disappeared!"));
        LocalDateTime byTime = DateUtils.parse(byString);
        return new AquaDeadline(name, byTime);
    }
}
