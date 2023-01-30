package domain.entities.core;

/**
 * The interface for objects that have both ids and can be executed. This is
 * here just so that when we need it, we can require the object that's passed
 * in to a method to be both identifiable and executable.
 */
public interface IdentifiedCommandable extends Commandable, Identifiable {
}
