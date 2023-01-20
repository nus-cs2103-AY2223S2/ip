package domain.entities.core;

/**
 * An interface wrapping around things that can be written to.
 */
public interface Writable {
    /**
     * Write the content to the interface. This will not add a new line to the
     * method. The content's toString method will be called.
     * @param content the content to be written to the interface.
     */
    public void write(Object content);

    /**
     * Write the content to the interface. This will add a new line to the
     * method. The content's toString method will be called.
     * @param content the content to be written to the interface.
     */
    public void writeln(Object content);
}
