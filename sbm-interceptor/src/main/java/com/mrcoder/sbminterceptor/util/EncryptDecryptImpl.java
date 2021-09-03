package com.mrcoder.sbminterceptor.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class EncryptDecryptImpl implements IEncryptDecrypt {
    @Override
    public <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException {
        return EncryptDecryptUtils.encrypt(declaredFields, parameterObject);
    }

    @Override
    public <T> T decrypt(T result) throws IllegalAccessException {
        return EncryptDecryptUtils.decrypt(result);
    }
}
