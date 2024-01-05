package com.nikea.service.impl;

import com.nikea.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendSms(String message) {
        // sending sms logic
        try {
            //emulating sms logic execution
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        logger.info("Sms notification has been successfully sent. Message: " + message);
    }
}
