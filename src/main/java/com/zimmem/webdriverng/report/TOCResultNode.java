package com.zimmem.webdriverng.report;

/**
 * @author zhaowen.zhuangzw 2012-2-18 下午11:51:18
 */
public class TOCResultNode extends TOCNode {

    private String href = null;

    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href the href to set
     */
    public void setHref(String href) {
        this.href = href;
    }

    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"testng-node ");
        if (TestStatus.failure.equals(getStatus())) {
            sb.append("testng-test-fail");
        } else if (TestStatus.success.equals(getStatus())) {
            sb.append("testng-test-ok");
        } else {
            sb.append("testng-test-skip");
        }
        sb.append("\"><a href=\"#" + href + "\">");
        sb.append(getText());
        sb.append("</a>");
        sb.append("</li>");
        return sb.toString();
    }
}
