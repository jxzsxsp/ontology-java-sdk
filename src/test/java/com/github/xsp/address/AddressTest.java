package com.github.xsp.address;

import com.github.ontio.common.Address;
import org.junit.Test;

/**
 * @author Sean.Xiao
 * @date 2018/6/23 下午6:47
 */
public class AddressTest {

    @Test
    public void addressHash() throws Exception {
        Address address1 = Address.addressFromPubKey("02d83c6c9c296031983482cf4e30e3afd85f09ed426bda5bbfc32ef7b1a4ffb752");
        Address address2 = Address.decodeBase58("ASqVhSZW8CsyTHKb5jw2TbT1tKzFMQA4FX");

        System.out.println(address1.toBase58());
        System.out.println(address2.toBase58());
        System.out.println(address1.toString());
        System.out.println(address2.toHexString());
    }
}
