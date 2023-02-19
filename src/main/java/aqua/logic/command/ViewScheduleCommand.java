package aqua.logic.command;

import java.time.LocalDateTime;

import aqua.exception.SyntaxException;
import aqua.graphic.TaskView;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.manager.TaskFilterReport;
import aqua.util.DateUtils;
import javafx.scene.Parent;


/** A {@code CommandController} to view schedule. */
public class ViewScheduleCommand extends CommandController {
    private static final String POPUP_TITLE = "Task Schedule";
    private static final int DAYS_IN_WEEK = 7;


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new ExecutionTask<DisplayData>(args, manager) {
            @Override
            protected DisplayData process(ArgumentMap args, LogicManager manager) throws SyntaxException {
                return filterTasks(args, manager);
            }
        });
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new ExecutionDisplayerTask<DisplayData>(args, logicManager, ioManager) {
            @Override
            protected DisplayData process(ArgumentMap args, LogicManager manager) throws SyntaxException {
                return filterTasks(args, manager);
            }

            @Override
            protected void display(DisplayData data, IoManager manager) {
                Parent root = new TaskView(data.startTime, data.report).getRoot();
                manager.popup(root, POPUP_TITLE);
            }
        });
    }


    private DisplayData filterTasks(ArgumentMap args, LogicManager manager) throws SyntaxException {
        LocalDateTime start = DateUtils.toStartOfWeek(LocalDateTime.now());
        if (args.getMainInput().isPresent()) {
            LocalDateTime time = DateUtils.parse(args.getMainInput().get());
            start = DateUtils.toStartOfWeek(time);
        }
        LocalDateTime end = start.plusDays(DAYS_IN_WEEK);
        TaskFilterReport report = manager.getTaskManager().filterWithin(start, end);
        return new DisplayData(report, start);
    }





    private class DisplayData {
        final TaskFilterReport report;
        final LocalDateTime startTime;


        DisplayData(TaskFilterReport report, LocalDateTime startTime) {
            this.report = report;
            this.startTime = startTime;
        }
    }
}
