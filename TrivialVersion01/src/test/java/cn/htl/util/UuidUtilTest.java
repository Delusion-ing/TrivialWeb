package cn.htl.util;

import org.junit.Test;

public class UuidUtilTest {
    @Test
    public void getUuid(){
        String code = UuidUtil.getUuid();
        System.out.println(code);
    }

}
