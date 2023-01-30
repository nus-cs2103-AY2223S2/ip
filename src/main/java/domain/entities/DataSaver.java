package domain.entities;

import domain.entities.core.Disposable;
import domain.entities.core.ThrowingWritable;

/**
 * The interface that represents a data saver, i.e. something that can save
 * new lines of Strings.
 * <p>
 * This could be used as abstraction over the {@link java.io.FileWriter} and
 * many other similar classes.
 */
public abstract class DataSaver implements Disposable, ThrowingWritable {
}
