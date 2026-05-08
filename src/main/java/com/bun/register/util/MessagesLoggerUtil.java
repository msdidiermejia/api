package com.bun.register.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessagesLoggerUtil {

    private static final Logger logger = LoggerFactory.getLogger(MessagesLoggerUtil.class);

    public void info(String idTx, String method, String level, String message) {
        Date now = new Date();
        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        switch (level) {
            case Constants.LEVEL_INFO:
                logger.info(Constants.STRUCTUR_LOG, formatDate, method, Constants.LEVEL_INFO, idTx, message);
                break;
            case Constants.LEVEL_WARN:
                logger.warn(Constants.STRUCTUR_LOG, formatDate, method, Constants.LEVEL_WARN, idTx, message);
                break;
            case Constants.LEVEL_ERROR:
                logger.error(Constants.STRUCTUR_LOG, formatDate, method, Constants.LEVEL_ERROR, idTx, message);
                break;
            default:
                logger.debug(Constants.STRUCTUR_LOG, formatDate, method, Constants.LEVEL_DEBUG, idTx, message);
                break;
        }
    }
}

