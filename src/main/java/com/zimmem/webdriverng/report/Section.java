package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.Writer;

/**
 * @author zhaowen.zhuangzw 2012-2-19 下午4:31:49
 */
interface Section {

    public void toHtml(Writer writer) throws IOException;

}
