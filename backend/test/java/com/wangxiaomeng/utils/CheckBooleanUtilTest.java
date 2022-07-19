package com.wangxiaomeng.utils;

import org.junit.Assert;
import org.junit.Test;

public class CheckBooleanUtilTest {
    @Test
    public void testAnd_or_And(){
        boolean var1 = CheckBooleanUtil.or(
                ()->CheckBooleanUtil.and(
                        ()->"a".equals("a"),
                        ()->"b".equals("b")),
                ()->CheckBooleanUtil.and(
                        ()->"a".equals("b"),
                        ()->"b".equals("b")));
        Assert.assertTrue(var1);
    }

    @Test
    public void testAnd_and_And(){
        boolean var1 = CheckBooleanUtil.and(
                ()->CheckBooleanUtil.and(
                        ()->"a".equals("a"),
                        ()->"b".equals("b")),
                ()->CheckBooleanUtil.and(
                        ()->"a".equals("b"),
                        ()->"b".equals("b")));
        Assert.assertFalse(var1);
    }

    @Test
    public void testOr_and_Or(){
        boolean var1 = CheckBooleanUtil.and(
                ()->CheckBooleanUtil.or(
                        ()->"a".equals("a"),
                        ()->"b".equals("b")),
                ()->CheckBooleanUtil.or(
                        ()->"a".equals("b"),
                        ()->"b".equals("b")));
        Assert.assertTrue(var1);
    }
}
