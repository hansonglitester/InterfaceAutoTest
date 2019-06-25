package com.hsl.cn.cn.reporter;

import com.google.gson.Gson;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class TestNGIReporterListener implements IReporter {
    //生成的路径以及文件名
    private static final String OUTPUT_FOLDER = "test-output/" + geTime()+"/";
    private static final String   filename="report.html";
    //测试报告模板路径
    private static final String template = "src/main/resources/ReportTemplate.html";
    @Override
    public void generateReport(List <XmlSuite> xmlSuites, List <ISuite> suites, String outputDirectory) {

        int totalFailedSize = 0;
        int totalPassedSize = 0;
        int totalSkippedSize = 0;
        String beginTime;
        long totalTime;

        for (ISuite suite : suites) {

            System.out.println("1-------------" + suite.getResults());
            //获取多个<test>标签
            Map <String, ISuiteResult> suiteResults = suite.getResults();


            for (Map.Entry <String, ISuiteResult> entry : suiteResults.entrySet()) {
                //遍历每一个test标签下的内容
                System.out.println("2-----------" + entry.getKey() + ":" + entry.getValue());

                ISuiteResult suiteResult = entry.getValue();
                ITestContext testContext = suiteResult.getTestContext();

                //统计没有个test标签失败、成功、跳过
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                int failedSize = failedTests.size();
                int passedSize = passedTests.size();
                int skippedSize = skippedTests.size();

                System.out.println("3------failedSize:" + failedSize + ";passedSize:" + passedSize + ";skippedSize:" + skippedSize);
                totalFailedSize += failedSize;
                totalPassedSize += passedSize;
                totalSkippedSize += skippedSize;
            }

        }

        //将测试的结果放在json串中
        Map <String, Object> result = new HashMap <>();
        result.put("testName", "接口自动化测试");
        result.put("testPass", totalPassedSize);
        result.put("testFail", totalFailedSize);
        result.put("testSkip", totalSkippedSize);
        result.put("testAll", totalPassedSize + totalFailedSize + totalSkippedSize);
        result.put("beginTime", "20190508");
        result.put("totalTime", 456+"ms");
        //result.put("testResult", listInfo);
        Gson gson = new Gson();
        String resultData = gson.toJson(result);

        //读取模板，用json串的数据替换掉测试中模板数据
        String s =read(template);
        s=s.replaceFirst("\\$\\{resultData\\}", resultData);

        //将替换后的数据写到文件里，即我们的测试报告内容
        write(s);

    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    private static String geTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(d);
    }


    public String read(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            byte[] b = new byte[8192];
            int len = 0;
            while ((len = bis.read(b)) != -1) {

                sb.append(new String(b, 0, len));
            }
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  sb.toString();
    }

    public void  write(String content) {
        try{
            File file =new File(OUTPUT_FOLDER);
            if (!file.exists()){
                file.mkdirs();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FOLDER+filename));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        File file=new File("output/11/1.html");
        System.out.println(file.exists());file.exists();

        if (!file.exists()){
            file.mkdirs();
        }
    }

}