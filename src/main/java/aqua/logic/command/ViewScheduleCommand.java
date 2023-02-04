package aqua.logic.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import aqua.aquatask.AquaTask;
import aqua.graphic.schedule.ScheduleComponent;
import aqua.graphic.schedule.ScheduleTimeable;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.util.DateUtils;
import javafx.css.PseudoClass;


public class ViewScheduleCommand extends CommandController {
    private static final PseudoClass PSEUDO_CLASS_COMPLETE = PseudoClass.getPseudoClass("complete");
    private static final PseudoClass PSEUDO_CLASS_INCOMPLETE = PseudoClass.getPseudoClass("incomplete");

    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new ExecutionTask<DisplayData>(args, manager) {
            @Override
            protected DisplayData process(ArgumentMap args, LogicManager manager) {
                return filterTasks(args, manager);
            }
        });
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new ExecutionDisplayerTask<DisplayData>(args, logicManager, ioManager) {
            @Override
            protected DisplayData process(ArgumentMap args, LogicManager manager) {
                return filterTasks(args, manager);
            }

            @Override
            protected void display(DisplayData data, IoManager manager) {
                manager.popup(formScheduleDisplay(data.startTime, data.tasks));
            }
        });
    }


    private DisplayData filterTasks(ArgumentMap args, LogicManager manager) {
        LocalDateTime start = DateUtils.getStartOfWeek(LocalDateTime.now());
        LocalDateTime end = start.plusDays(7);
        List<AquaTask> tasks = manager.getTaskManager().filterWithin(start, end);
        return new DisplayData(tasks, start);
    }


    private ScheduleComponent formScheduleDisplay(LocalDateTime startTime, List<AquaTask> tasks) {
        List<ScheduleTimeable> timeables = tasks.stream()
                .map(task -> new TimeableAquaTask(task))
                .collect(Collectors.toList());
        return new ScheduleComponent(startTime, timeables);
    }





    private class DisplayData {
        final List<AquaTask> tasks;
        final LocalDateTime startTime;


        public DisplayData(List<AquaTask> tasks, LocalDateTime startTime) {
            this.tasks = tasks;
            this.startTime = startTime;
        }
    }





    private class TimeableAquaTask extends ScheduleTimeable {
        private final AquaTask task;


        TimeableAquaTask(AquaTask task) {
            this.task = task;
        }


        @Override
        public LocalDateTime getStart() {
            return task.getStart().orElseThrow();
        }


        @Override
        public LocalDateTime getEnd() {
            return task.getEnd().orElseThrow();
        }


        @Override
        public List<String> getStyleClass() {
            return List.of("schedule-box");
        }


        @Override
        public List<PseudoClass> getPseudoClass() {
            if (task.isComplete()) {
                return List.of(PSEUDO_CLASS_COMPLETE);
            }
            return List.of(PSEUDO_CLASS_INCOMPLETE);
        }


        @Override
        public String toString() {
            return task.toString();
        }
    }
}
