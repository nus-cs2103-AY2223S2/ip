package Meggy.Exception;

import Meggy.Resource;

/**
 * Deals with the absence of argument after commands that needs argument(s).
 */
public class MeggyNoArgException extends MeggyException {
    public MeggyNoArgException() {
        super(Resource.errNoArgs);
    }
}
