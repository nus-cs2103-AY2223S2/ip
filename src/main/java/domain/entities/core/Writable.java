package domain.entities.core;

/**
 * The interface that represents a target object that can be written to.
 * <p>
 * If throwing exception can happen during the writing process, please use the
 * {@link ThrowingWritable} interface instead.
 */
public interface Writable extends ThrowingWritable {
    /**
     * Writes a line of content to the target object.
     *
     * @param content the content to be written.
     */
    @Override
    void writeln(Object content);

    /**
     * Writes a line of content to the target object.
     *
     * @param content the content to be written to the interface.
     */
    @Override
    void write(Object content);

    /**
     * Clears the target object, i.e. removes all content from it.
     */
    @Override
    void clear();
}
