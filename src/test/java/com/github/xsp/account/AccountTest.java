package com.github.xsp.account;

import com.alibaba.fastjson.JSON;
import com.github.ontio.account.Account;
import com.github.ontio.crypto.SignatureScheme;
import org.junit.Test;

/**
 * @author Sean.Xiao
 * @date 2018/6/23 下午7:00
 */
public class AccountTest {

    @Test
    public void accountTest() throws Exception {
        Account account = new Account(SignatureScheme.SHA256WITHECDSA);
        System.out.println(JSON.toJSONString(account));
    }
}
