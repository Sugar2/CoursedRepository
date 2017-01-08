package com.coursed.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Trach on 1/7/2017.
 */
@Service
public class LoginAttemptService {

    public static final int MAX_ATTEMPT_TILL_BLOCK = 5; // TODO
    public static final int MAX_ATTEMPT_TILL_CAPTCHA = 1;

    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(final String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(final String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(final String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT_TILL_BLOCK;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    public boolean isCaptchaNeeded(final String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT_TILL_CAPTCHA;
        } catch (final ExecutionException e) {
            return false;
        }
    }
}
