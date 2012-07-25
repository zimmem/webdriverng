package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.Writer;

/**
 * @author zhaowen.zhuangzw 2012-2-19 下午5:02:16
 */
public class ParameterSection implements Section {

    private Object[] prameters;

    public ParameterSection(Object[] parameters) {
        this.prameters = parameters;
    }

    @Override
    public void toHtml(Writer writer) throws IOException {
        writer.write("<h2>Parameters</h2><table border=\"1\">");
        for (Object parameter : this.prameters) {
            writer.write("<tr><td>");
            writer.write(parameter == null ? "null" : parameter.toString());
            writer.write("</td></tr>");
        }
        writer.write("</table>");

    }
}
