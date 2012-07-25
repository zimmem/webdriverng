/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.zimmem.webdriverng.report;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaowen.zhuangzw 2012-2-19 下午10:29:26
 */
public class ScreenshotSection implements Section {

    private List<String> screenshots;

    public ScreenshotSection() {
        screenshots = new ArrayList<String>();
    }

    public void addScreenshot(String screenshot) {
        screenshots.add(screenshot);
    }

    @Override
    public void toHtml(Writer writer) throws IOException {
        writer.write("<h2>Screenshot:</h2>");
        for (String screenshot : screenshots) {
            writer.write("<img src=\"data:image/gif;base64," + screenshot + "\"/>");
        }
    }

}
