package com.zimmem.webdriverng.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;

/**
 * @author zhaowen.zhuangzw 2012-2-19 下午10:18:11
 */
public class SectionCollector {

    private static Map<ITestResult, List<Section>> sectionMap = new HashMap<ITestResult, List<Section>>();

    public static void collectSection(ITestResult result, Section section) {
        List<Section> list = sectionMap.get(result);
        if (list == null) {
            list = new ArrayList<Section>();
            sectionMap.put(result, list);
        }
        list.add(section);
    }

    public static List<Section> getSections(ITestResult result) {
        return sectionMap.get(result);
    }

}
