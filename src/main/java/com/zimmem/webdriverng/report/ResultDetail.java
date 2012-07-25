package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.ITestResult;

/**
 * 类ResutlDetail.java的实现描述：TODO 类实现描述
 * 
 * @author zhaowen.zhuangzw 2012-2-19 下午4:30:34
 */
public class ResultDetail {

    private String        title;

    private List<Section> sections;

    private ITestResult   result;

    public ResultDetail(ITestResult result) {
        sections = new ArrayList<Section>();
        this.result = result;
        title = result.getName();

        String desc = result.getMethod().getDescription();
        if (StringUtils.isNotBlank(desc)) {
            addSection(new DescriptionSection(desc));
        }

        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            addSection(new ParameterSection(parameters));
        }

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            addSection(new ExceptionSection(throwable));
        }

    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void toHtml(Writer writer) throws IOException {
        writer.write("<!DOCTYPE html>\r\n");
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>" + title + "</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>" + title + "</h1>");
        for (Section section : sections) {
            section.toHtml(writer);
        }
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
    }

    /**
     * @param sections
     */
    public void addSections(List<Section> sections) {
        if (sections != null && !sections.isEmpty()) {
            this.sections.addAll(sections);
        }
    }
}
