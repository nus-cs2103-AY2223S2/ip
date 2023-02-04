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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class ViewScheduleCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new ExecutionTask<List<AquaTask>>(args, manager) {
            @Override
            protected List<AquaTask> process(ArgumentMap args, LogicManager manager) {
                return filterTasks(args, manager);
            }
        });
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new ExecutionDisplayerTask<List<AquaTask>>(args, logicManager, ioManager) {
            @Override
            protected List<AquaTask> process(ArgumentMap args, LogicManager manager) {
                return filterTasks(args, manager);
            }

            @Override
            protected void display(List<AquaTask> tasks, IoManager manager) {
                manager.popup(new SchedulePopup(getMonday(LocalDateTime.now()), tasks));
            }
        });
    }


    private List<AquaTask> filterTasks(ArgumentMap args, LogicManager manager) {
        LocalDateTime start = getMonday(LocalDateTime.now());
        LocalDateTime end = start.plusDays(7);
        return manager.getTaskManager().filterWithin(start, end);
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





    private class SchedulePopup extends Popup {
        private boolean isDragged = false;
        private double x = 0;
        private double y = 0;


        SchedulePopup(LocalDateTime startTime, List<AquaTask> tasks) {
            VBox box = new VBox();
            box.setStyle("-fx-background-color: white");
            getContent().add(box);
            box.setOnMousePressed(this::handleMousePress);
            box.setOnMouseDragged(this::handleMouseDragged);
            box.setOnMouseReleased(this::handleMouseRelease);
            List<Timeable> timeables = tasks.stream()
                    .map(task -> new Timeable() {
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
                    }).collect(Collectors.toList());
            ScheduleComponent schedule = new ScheduleComponent(startTime, timeables);
            box.getChildren().add(schedule);
        }


        private void handleMousePress(MouseEvent event) {
            if (!isDragged) {
                x = event.getScreenX();
                y = event.getScreenY();
            }
            isDragged = true;
        }

        private void handleMouseRelease(MouseEvent event) {
            isDragged = false;
        }

        private void handleMouseDragged(MouseEvent event) {
            double xChange = event.getScreenX() - x;
            double yChange = event.getScreenY() - y;
            setX(getX() + xChange);
            setY(getY() + yChange);
            x = event.getScreenX();
            y = event.getScreenY();
        }
    }
}
