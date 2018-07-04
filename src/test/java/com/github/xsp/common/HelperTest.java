package com.github.xsp.common;

import com.github.ontio.common.Helper;
import org.junit.Test;

/**
 * @author Sean.Xiao
 * @date 2018/7/4 下午7:28
 */
public class HelperTest {

    @Test
    public void strToBytes() {
        byte[] bs = Helper.hexToBytes("52c762f5ce3aedfacc5b3471aae87143826e87dfaeaaed7df6a13597d593a59e");
        String hi = Helper.toHexString(bs);

        for (byte b : bs) {
            System.out.println(b);
        }
        System.out.println(hi);
    }
}
