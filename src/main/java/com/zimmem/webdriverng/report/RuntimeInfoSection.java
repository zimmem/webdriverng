package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author zhaowen.zhuangzw 2012-5-30 下午1:36:03
 */
public class RuntimeInfoSection implements Section {

    private String currentUrl;

    private String pageSource;

    @Override
    public void toHtml(Writer writer) throws IOException {
        writer.write("<h2>Explorer Infomation:</h2>");
        writer.write("<h3>Current URL:</h3>");
        writer.write("<a href=\"" + currentUrl + "\">" + currentUrl + "</a>");
        if (StringUtils.isNotBlank(pageSource)) {
            writer.write("<h3>Page Sources:</h3>");
            writer.write("<pre>" + StringEscapeUtils.escapeHtml(pageSource) + "</pre>");
        }

    }

    /**
     * @return the currentUrl
     */
    public String getCurrentUrl() {
        return currentUrl;
    }

    /**
     * @param currentUrl the currentUrl to set
     */
    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    /**
     * @return the pageSource
     */
    public String getPageSource() {
        return pageSource;
    }

    /**
     * @param pageSource the pageSource to set
     */
    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

}
