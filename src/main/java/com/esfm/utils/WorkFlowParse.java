package com.esfm.utils;

import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;

import java.util.Map;

public class WorkFlowParse {
    String parStr;
    String conditionStr;
    String conditionCategory;
    String conditionExp;
    String conditionResTrue;
    String conditionResFalse;

    public WorkFlowParse(String parseStr) {
        this.parStr = parseStr;
        conditionStr = parStr.substring(parStr.indexOf('\'') + 1, parStr.lastIndexOf('\''));
        conditionCategory = parStr.substring(0, parStr.lastIndexOf('-'));
        conditionExp = parStr.substring(parStr.indexOf('-') + 1, parStr.indexOf('='));
        conditionResTrue = parStr.substring(parStr.indexOf('?') + 1, parStr.lastIndexOf(':'));
        conditionResFalse = parStr.substring(parStr.indexOf(':') + 1);
    }

    public static void main(String[] arg) {

    }

    public String getConditionExp() {
        return conditionExp;
    }

    public String getConditionCategory() {
        return conditionCategory;
    }

    /**
     * 根据给定的值来判断当前的下序流程号
     */
    public String getNextProcessNo(Map<String, String> conditionValue) {
        JexlEngine jexlEngine = new Engine();
        JexlExpression expression = jexlEngine.createExpression(conditionStr);
        JexlContext jexlContext = new MapContext();
        conditionValue.forEach(jexlContext::set);
        Boolean res = (Boolean) expression.evaluate(jexlContext);
        return res ? conditionResTrue : conditionResFalse;
    }

    public boolean isSuccess(Map<String, String> conditionValue) {
        JexlEngine jexlEngine = new Engine();
        JexlExpression expression = jexlEngine.createExpression(conditionStr);
        JexlContext jexlContext = new MapContext();
        conditionValue.forEach(jexlContext::set);
        return (Boolean) expression.evaluate(jexlContext);
    }
}
