package com.github.xsp.account;

import com.alibaba.fastjson.JSON;
import com.github.ontio.account.Account;
import com.github.ontio.common.Helper;
import com.github.ontio.crypto.ECC;
import com.github.ontio.crypto.SignatureScheme;
import org.junit.Test;

/**
 * @author Sean.Xiao
 * @date 2018/6/23 下午7:00
 */
public class AccountTest {

    @Test
    public void accountTest() throws Exception {
        Account account = new Account(Helper.hexToBytes("7eeb1f37ce6802f0d459905306efcc0b0f520537110d7a08918659c9da12cbce"), SignatureScheme.SHA256WITHECDSA);

        System.out.println(account.getPrivateKey().getAlgorithm());
        System.out.println(account.getPrivateKey().getFormat());
        System.out.println(account.getPrivateKey().toString());
        System.out.println(account.getAddressU160().toBase58());
    }
}
