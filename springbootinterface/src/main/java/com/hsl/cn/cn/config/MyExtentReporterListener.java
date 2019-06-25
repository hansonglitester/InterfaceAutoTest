package com.hsl.cn.cn.config;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.vimalselvam.testng.EmailReporter;
import com.vimalselvam.testng.NodeName;
import com.vimalselvam.testng.SystemInfo;
import com.vimalselvam.testng.listener.ExtentTestNgFormatter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import com.aventstack.extentreports.ResourceCDN; //3.15

public class MyExtentReporterListener extends ExtentTestNgFormatter {
    private static final String REPORTER_ATTR = "extentTestNgReporter";
    private static final String SUITE_ATTR = "extentTestNgSuite";
    private ExtentReports reporter;
    private List<String> testRunnerOutput;
    private Map<String, String> systemInfo;
    private ExtentHtmlReporter htmlReporter;
    private static ExtentTestNgFormatter instance;

    public MyExtentReporterListener() {
        setInstance(this);
        this.testRunnerOutput = new ArrayList();
        String reportPathStr = System.getProperty("reportPath");

        File reportPath;
        try {
            reportPath = new File(reportPathStr);
        } catch (NullPointerException var6) {
            reportPath = new File("test-output");
        }

        if (!reportPath.exists() && !reportPath.mkdirs()) {
            throw new RuntimeException("Failed to create output run directory");
        } else {
            File reportFile = new File(reportPath, "report.html");
            File emailReportFile = new File(reportPath, "emailable-report.html");
            this.htmlReporter = new ExtentHtmlReporter(reportFile);
            this.htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);

            EmailReporter emailReporter = new EmailReporter(emailReportFile);
            this.reporter = new ExtentReports();
            this.reporter.attachReporter(new ExtentReporter[]{this.htmlReporter, emailReporter});
        }
    }

    public static ExtentTestNgFormatter getInstance() {
        return instance;
    }

    private static void setInstance(ExtentTestNgFormatter formatter) {
        instance = formatter;
    }

    public Map<String, String> getSystemInfo() {
        return this.systemInfo;
    }

    public void setSystemInfo(Map<String, String> systemInfo) {
        this.systemInfo = systemInfo;
    }

    public void onStart(ISuite iSuite) {
        ExtentTest suite = this.reporter.createTest(iSuite.getName());
        String configFile = iSuite.getParameter("report.config");
        if (!Strings.isNullOrEmpty(configFile)) {
            this.htmlReporter.loadXMLConfig(configFile);
        }

        String systemInfoCustomImplName = iSuite.getParameter("system.info");
        if (!Strings.isNullOrEmpty(systemInfoCustomImplName)) {
            this.generateSystemInfo(systemInfoCustomImplName);
        }

        iSuite.setAttribute("extentTestNgReporter", this.reporter);
        iSuite.setAttribute("extentTestNgSuite", suite);
    }

    private void generateSystemInfo(String systemInfoCustomImplName) {
        try {
            Class<?> systemInfoCustomImplClazz = Class.forName(systemInfoCustomImplName);
            if (!SystemInfo.class.isAssignableFrom(systemInfoCustomImplClazz)) {
                throw new IllegalArgumentException("The given system.info class name <" + systemInfoCustomImplName + "> should implement the interface <" + SystemInfo.class.getName() + ">");
            } else {
                SystemInfo t = (SystemInfo)systemInfoCustomImplClazz.newInstance();
                this.setSystemInfo(t.getSystemInfo());
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException var4) {
            throw new IllegalStateException(var4);
        }
    }

    public void onFinish(ISuite iSuite) {
    }

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    public void onTestFailure(ITestResult iTestResult) {
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
        ISuite iSuite = iTestContext.getSuite();
        ExtentTest suite = (ExtentTest)iSuite.getAttribute("extentTestNgSuite");
        ExtentTest testContext = suite.createNode(iTestContext.getName());
        iTestContext.setAttribute("testContext", testContext);
    }

    public void onFinish(ITestContext iTestContext) {
        ExtentTest testContext = (ExtentTest)iTestContext.getAttribute("testContext");
        if (iTestContext.getFailedTests().size() > 0) {
            testContext.fail("Failed");
        } else if (iTestContext.getSkippedTests().size() > 0) {
            testContext.skip("Skipped");
        } else {
            testContext.pass("Passed");
        }

    }

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (iInvokedMethod.isTestMethod()) {
            ITestContext iTestContext = iTestResult.getTestContext();
            ExtentTest testContext = (ExtentTest)iTestContext.getAttribute("testContext");
            ExtentTest test = testContext.createNode(iTestResult.getName());
            iTestResult.setAttribute("test", test);
        }

    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (iInvokedMethod.isTestMethod()) {
            ExtentTest test = (ExtentTest)iTestResult.getAttribute("test");
            List<String> logs = Reporter.getOutput(iTestResult);
            Iterator var5 = logs.iterator();

            while(var5.hasNext()) {
                String log = (String)var5.next();
                test.info(log);
            }

            int status = iTestResult.getStatus();
            if (1 == status) {
                test.pass("Passed");
            } else if (2 == status) {
                test.fail(iTestResult.getThrowable());
            } else {
                test.skip("Skipped");
            }

            String[] var11 = iInvokedMethod.getTestMethod().getGroups();
            int var7 = var11.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String group = var11[var8];
                test.assignCategory(new String[]{group});
            }
        }

    }

    public void addScreenCaptureFromPath(ITestResult iTestResult, String filePath) throws IOException {
        ExtentTest test = (ExtentTest)iTestResult.getAttribute("test");
        test.addScreenCaptureFromPath(filePath);
    }

    public void addScreenCaptureFromPath(String filePath) throws IOException {
        ITestResult iTestResult = Reporter.getCurrentTestResult();
        Preconditions.checkState(iTestResult != null);
        ExtentTest test = (ExtentTest)iTestResult.getAttribute("test");
        test.addScreenCaptureFromPath(filePath);
    }

    public void setTestRunnerOutput(String message) {
        this.testRunnerOutput.add(message);
    }

    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        if (this.getSystemInfo() != null) {
            Iterator var4 = this.getSystemInfo().entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var4.next();
                this.reporter.setSystemInfo((String)entry.getKey(), (String)entry.getValue());
            }
        }

        this.reporter.setTestRunnerOutput(this.testRunnerOutput);
        this.reporter.flush();
    }

    public void addNewNodeToTest() {
        this.addNewNodeToTest(NodeName.getNodeName());
    }

    public void addNewNodeToTest(String nodeName) {
        this.addNewNode("test", nodeName);
    }

    public void addNewNodeToSuite() {
        this.addNewNodeToSuite(NodeName.getNodeName());
    }

    public void addNewNodeToSuite(String nodeName) {
        this.addNewNode("extentTestNgSuite", nodeName);
    }

    private void addNewNode(String parent, String nodeName) {
        ITestResult result = Reporter.getCurrentTestResult();
        Preconditions.checkState(result != null);
        ExtentTest parentNode = (ExtentTest)result.getAttribute(parent);
        ExtentTest childNode = parentNode.createNode(nodeName);
        result.setAttribute(nodeName, childNode);
    }

    public void addInfoLogToNode(String logMessage) {
        this.addInfoLogToNode(logMessage, NodeName.getNodeName());
    }

    public void addInfoLogToNode(String logMessage, String nodeName) {
        ITestResult result = Reporter.getCurrentTestResult();
        Preconditions.checkState(result != null);
        ExtentTest test = (ExtentTest)result.getAttribute(nodeName);
        test.info(logMessage);
    }

    public void failTheNode(Throwable t) {
        this.failTheNode(NodeName.getNodeName(), t);
    }

    public void failTheNode(String nodeName, Throwable t) {
        ITestResult result = Reporter.getCurrentTestResult();
        Preconditions.checkState(result != null);
        ExtentTest test = (ExtentTest)result.getAttribute(nodeName);
        test.fail(t);
    }

    public void failTheNode(String logMessage) {
        this.failTheNode(NodeName.getNodeName(), logMessage);
    }

    public void failTheNode(String nodeName, String logMessage) {
        ITestResult result = Reporter.getCurrentTestResult();
        Preconditions.checkState(result != null);
        ExtentTest test = (ExtentTest)result.getAttribute(nodeName);
        test.fail(logMessage);
    }
}
