package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author zhaowen.zhuangzw 2012-2-19 下午4:56:43
 */
public class ExceptionSection implements Section {

    private Throwable throwable;

    public ExceptionSection(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public void toHtml(Writer writer) throws IOException {
        writer.write("<h2>Exception:</h2><pre>");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String stack = sw.toString();
        StringEscapeUtils.escapeHtml(writer, stack);
        writer.write("</pre>");
    }
}
