package aqua.graphic.schedule;

import java.util.List;

import aqua.util.Timeable;
import javafx.css.PseudoClass;


/** A {@code Timeable} that can be displayed. */
public abstract class ScheduleTimeable extends Timeable {
    /**
     * Returns the list of CSS styleclasses that should be added to this
     * {@code Timeable}.
     *
     * @return a list of CSS styleclasses.
     */
    public abstract List<String> getStyleClass();


    /**
     * Return a list of CSS pseudo classes to add to this {@code Timeable}.
     *
     * @return a list of CSS pseudoclasses.
     */
    public abstract List<PseudoClass> getPseudoClass();
}
