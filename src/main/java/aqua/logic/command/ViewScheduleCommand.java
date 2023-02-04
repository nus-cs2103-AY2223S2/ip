package aqua.logic.command;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import aqua.aquatask.AquaTask;
import aqua.graphic.ScheduleComponent;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.util.Timeable;


public class ViewScheduleCommand extends CommandController {
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
        LocalDateTime start = getMonday(LocalDateTime.now());
        LocalDateTime end = start.plusDays(7);
        List<AquaTask> tasks = manager.getTaskManager().filterWithin(start, end);
        return new DisplayData(tasks, start);
    }


    private LocalDateTime getMonday(LocalDateTime time) {
        DayOfWeek day = time.getDayOfWeek();
        int minusDays = day.getValue() - DayOfWeek.MONDAY.getValue();
        LocalDateTime monday = time.minusDays(minusDays);
        return LocalDateTime.of(
                monday.getYear(),
                monday.getMonthValue(),
                monday.getDayOfMonth(),
                0, 0);
    }


    private ScheduleComponent formScheduleDisplay(LocalDateTime startTime, List<AquaTask> tasks) {
        List<Timeable> timeables = tasks.stream()
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





    private class TimeableAquaTask implements Timeable {
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
        public String toString() {
            return task.toString();
        }
    }
}
