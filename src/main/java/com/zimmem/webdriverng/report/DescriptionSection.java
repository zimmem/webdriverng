package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author zhaowen.zhuangzw 2012-2-19 下午10:01:06
 */
public class DescriptionSection implements Section {

    private String desc;

    public DescriptionSection(String desc) {
        this.desc = desc;
    }

    @Override
    public void toHtml(Writer writer) throws IOException {
        writer.write("<h2>Description</h2>");
        StringEscapeUtils.escapeHtml(writer, desc);

    }

}
