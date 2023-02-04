package aqua.graphic.schedule;

import java.util.List;

import aqua.util.Timeable;
import javafx.css.PseudoClass;


public abstract class ScheduleTimeable extends Timeable {
    public abstract List<String> getStyleClass();
    public abstract List<PseudoClass> getPseudoClass();
}
