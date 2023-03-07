package com.nasr.chatapplication.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class VerificationCacheCode extends CacheStore<String>{

    public static final int EXPIRATION_DATE=3;
    public static final TimeUnit TIME_UNIT= TimeUnit.MINUTES;
    public VerificationCacheCode() {
        super(EXPIRATION_DATE, TIME_UNIT);
    }
}
