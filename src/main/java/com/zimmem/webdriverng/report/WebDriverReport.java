package com.zimmem.webdriverng.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.testng.IClass;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

/**
 * 生成比较好看的TestNG报告
 * 
 * @author zhaowen.zhuangzw 2012-2-18 下午2:10:55
 */
public class WebDriverReport implements IReporter {

    private static final String[] jsFile          = { "jquery.js", "jquery.layout.js", "jquery.ui.js", "report.js" };
    private static final String[] cssFile         = { "jquery-ui.css", "jquery.layout.css", "report.css" };
    private static final String[] iconFile        = { "suite.gif", "suitefail.gif", "suiteok.gif", "suiteskip.gif",
            "test.gif", "testfail.gif", "testok.gif", "testskip.gif" };

    private PrintWriter           index           = null;

    private File                  outputDir       = null;

    private File                  detailOutputDir = null;

    private int                   resultId        = 0;

    /*
     * (non-Javadoc)
     * @see org.testng.IReporter#generateReport(java.util.List, java.util.List, java.lang.String)
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init(outputDirectory);
        try {
            index = new PrintWriter(new File(outputDir, "index.html"));
            index.println("<!DOCTYPE html>");
            index.println("<html>");
            index.println("<head>");
            copyStaticFile();
            index.println("</head>");
            index.println("<body>");
            List<TOCNode> tocTree = generateTocTree(xmlSuites, suites);
            printToc(tocTree);
            index.println("<div class=\"ui-layout-center\"><iframe id=\"detail_frame\"/>");
            index.println("</div>");
            index.println("</body>");
            index.println("</html>");

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            index.close();
        }

    }

    private void init(String outputDirectory) {
        outputDir = new File(outputDirectory, "webdirver-report");
        try {
            if (outputDir.exists()) {
                FileUtils.cleanDirectory(outputDir);
            } else {
                outputDir.mkdirs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        detailOutputDir = new File(outputDir, "detail");
        detailOutputDir.mkdirs();

    }

    /**
     * @param tocTree
     */
    private void printToc(List<TOCNode> tocTree) {
        index.print("<div class=\"ui-layout-west\"><div><button id=\"show-all\">Show all</button><button id=\"only-failed\">Only failed</button></div>");
        index.print("<ul id=\"suit-toc\">");
        for (TOCNode tocNode : tocTree) {
            index.print(tocNode.toHtml());
        }
        index.print("</ul>");
        index.print("</div>");

    }

    private List<TOCNode> generateTocTree(List<XmlSuite> xmlSuites, List<ISuite> suites) {
        List<TOCNode> list = new ArrayList<TOCNode>();
        for (ISuite iSuite : suites) {
            TOCNode node = new TOCNode();
            node.setText(iSuite.getName());
            list.add(node);
            Map<String, ISuiteResult> results = iSuite.getResults();
            if (results != null && !results.isEmpty()) {
                for (ISuiteResult result : results.values()) {
                    ITestContext testContext = result.getTestContext();
                    TOCNode contextNode = new TOCNode();
                    contextNode.setText(testContext.getName());
                    node.addChild(contextNode);
                    Map<IClass, List<ITestResult>> classMap = new HashMap<IClass, List<ITestResult>>();
                    processTestResult(testContext.getPassedTests(), classMap);
                    processTestResult(testContext.getPassedConfigurations(), classMap);
                    processTestResult(testContext.getSkippedTests(), classMap);
                    processTestResult(testContext.getFailedTests(), classMap);
                    processTestResult(testContext.getFailedConfigurations(), classMap);
                    for (IClass clazz : classMap.keySet()) {
                        TOCNode clazzNode = new TOCNode();
                        clazzNode.setText(clazz.getName());
                        contextNode.addChild(clazzNode);
                        for (ITestResult testResult : classMap.get(clazz)) {
                            String path = generateResultHtmlDetail(testResult);
                            TOCResultNode resultNode = new TOCResultNode();
                            clazzNode.addChild(resultNode);
                            resultNode.setText(testResult.getName());
                            resultNode.setHref(path);
                            resultNode.updateStatus(TestStatus.getStatus(testResult.getStatus()));

                        }
                    }
                }
            }

        }
        return list;
    }

    /**
     * @param testResult
     */
    private String generateResultHtmlDetail(ITestResult testResult) {
        resultId++;
        String path = "detail/" + resultId + ".html";
        File detailFile = new File(outputDir, path);
        try {
            FileWriter writer = new FileWriter(detailFile);
            ResultDetail detail = new ResultDetail(testResult);
            detail.addSections(SectionCollector.getSections(testResult));
            detail.toHtml(writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }

    private void processTestResult(IResultMap resultMap, Map<IClass, List<ITestResult>> classMap) {

        Set<ITestResult> allResults = resultMap.getAllResults();
        for (ITestResult result : allResults) {
            IClass clazz = result.getTestClass();
            List<ITestResult> list = classMap.get(clazz);
            if (list == null) {
                list = new ArrayList<ITestResult>();
                classMap.put(clazz, list);
            }
            list.add(result);
        }
    }

    /**
     * @throws IOException
     */
    private void copyStaticFile() {
        copyJsFile(new File(outputDir, "js"));
        File cssDir = new File(outputDir, "css");
        copyCssFile(cssDir);
        copyIconFile(new File(cssDir, "icon"));
    }

    /**
     * @param file
     * @throws IOException
     */
    private void copyCssFile(File file) {
        file.mkdirs();
        for (String css : cssFile) {
            InputStream inputStream = getJarResource("/css/" + css);
            copyStreamToFile(inputStream, new File(file, css));
            index.println("<link href=\"css/" + css + "\" type=\"text/css\" rel=\"stylesheet\">");
        }

    }

    private void copyIconFile(File file) {
        file.mkdirs();
        for (String icon : iconFile) {
            InputStream inputStream = getJarResource("/css/icon/" + icon);
            copyStreamToFile(inputStream, new File(file, icon));
        }

    }

    /**
     * @param string
     * @return
     */
    private InputStream getJarResource(String string) {
        return getClass().getResourceAsStream(string);

    }

    /**
     * @param file
     * @throws IOException
     */
    private void copyJsFile(File file) {
        file.mkdirs();
        for (String js : jsFile) {
            InputStream inputStream = getJarResource("/js/" + js);
            copyStreamToFile(inputStream, new File(file, js));
            index.println("<script src=\"js/" + js + "\" type=\"text/javascript\"></script>");
        }

    }

    /**
     * @param file
     * @throws IOException
     */
    private void copyStreamToFile(InputStream is, File file) {
        try {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                try {
                    int nread;
                    byte[] buffer = new byte[4096];
                    while (0 < (nread = is.read(buffer))) {
                        outputStream.write(buffer, 0, nread);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
