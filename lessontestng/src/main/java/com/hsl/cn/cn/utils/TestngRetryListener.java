package com.hsl.cn.cn.utils;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestngRetryListener implements IAnnotationTransformer {

    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retry = iTestAnnotation.getRetryAnalyzer();

        if (retry == null) {
            iTestAnnotation.setRetryAnalyzer(TestngRetry.class);
        }
    }
}
