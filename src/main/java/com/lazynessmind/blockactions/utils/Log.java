package com.lazynessmind.blockactions.utils;

import com.lazynessmind.blockactions.BlockActions;

public class Log {

    public static void l(int i, Object msg) {
        switch (i) {
            case 0:
                BlockActions.LOGGER.error(msg);
                break;
            case 1:
                BlockActions.LOGGER.warn(msg);
                break;
            default:
                BlockActions.LOGGER.debug(msg);
        }
    }
}
