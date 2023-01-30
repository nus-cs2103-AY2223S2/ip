package domain.entities;

import domain.entities.core.Disposable;
import domain.entities.core.StringReadable;

/**
 * The interface that represents a data loader, i.e. something that can load
 * new lines of Strings.
 * <p>
 * This could be used as  abstraction over the {@link java.util.Scanner}
 * class.
 */
public abstract class DataLoader implements Disposable, StringReadable {
}
