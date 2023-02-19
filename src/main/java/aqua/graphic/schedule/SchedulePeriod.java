package aqua.graphic.schedule;

import java.util.ArrayList;
import java.util.List;

import aqua.util.Period;
import javafx.css.PseudoClass;
import javafx.scene.Node;


/** A {@code Period} that can be displayed. */
public abstract class SchedulePeriod extends Period {
    private final ArrayList<Node> linkedNodes = new ArrayList<>();


    /**
     * Links a {@code Node} to this period.
     *
     * @param node - the node to link.
     */
    public void addLink(Node node) {
        linkedNodes.add(node);
    }


    /**
     * Returns an immutable list view of {@code Node} linked to this period.
     *
     * @return an immutable list view of {@code Node} linked to this period.
     */
    public List<Node> getLinks() {
        return List.copyOf(linkedNodes);
    }


    /**
     * Returns the list of CSS styleclasses that should be added to this
     * {@code Period}.
     *
     * @return a list of CSS styleclasses.
     */
    public abstract List<String> getStyleClass();


    /**
     * Return a list of CSS pseudo classes to add to this {@code Period}.
     *
     * @return a list of CSS pseudoclasses.
     */
    public abstract List<PseudoClass> getPseudoClass();
}
