package aqua.graphic.schedule;

import java.util.List;

import aqua.util.Timeable;


public abstract class ScheduleTimeable extends Timeable {
    public abstract List<String> getStyleClass();
}
