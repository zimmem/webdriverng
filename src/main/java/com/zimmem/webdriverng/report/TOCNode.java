package com.zimmem.webdriverng.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaowen.zhuangzw 2012-2-18 下午10:58:04
 */
public class TOCNode {

    private List<String>  style;
    private TOCNode       parent;
    private List<TOCNode> children;
    private TestStatus    status;
    private String        text;

    public TOCNode() {
        children = new ArrayList<TOCNode>();
        status = TestStatus.skip;
    }

    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"testng-node ");
        if (TestStatus.failure.equals(this.status)) {
            sb.append("testng-suit-fail");
        } else if (TestStatus.success.equals(this.status)) {
            sb.append("testng-suit-ok");
        } else {
            sb.append("testng-suit-skip");
        }

        sb.append("\"><span>");
        sb.append(text);
        sb.append("</span>");
        if (children != null && !children.isEmpty()) {
            sb.append("<ul>");
            for (TOCNode node : children) {
                sb.append(node.toHtml());
            }
            sb.append("</ul>");
        }
        sb.append("</li>");
        return sb.toString();
    }

    /**
     * @return the style
     */
    public List<String> getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(List<String> style) {
        this.style = style;
    }

    /**
     * @return the parent
     */
    public TOCNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(TOCNode parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    public List<TOCNode> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void addChild(TOCNode node) {
        if (node == null) return;
        children.add(node);
        node.setParent(this);
    }

    /**
     * @return the status
     */
    public TestStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void updateStatus(TestStatus status) {
        if (TestStatus.failure.equals(this.status) || TestStatus.failure.equals(status)) {
            this.status = TestStatus.failure;
        } else if (TestStatus.success.equals(this.status) || TestStatus.success.equals(status)) {
            this.status = TestStatus.success;
        } else {
            this.status = TestStatus.skip;
        }
        if (parent != null) {
            parent.updateStatus(this.status);
        }

    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
