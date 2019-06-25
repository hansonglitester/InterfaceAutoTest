package com.hsl.cn.cn.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestngRetry implements IRetryAnalyzer {
	
	 private int retryCount = 0;
	 private static final int maxRetryCount = 3;
	 
	  public boolean retry(ITestResult result) {
	    if (retryCount < maxRetryCount) {
	      retryCount++;
	      return true;
	    }
	    return false;
	  }


}
