package com.github.ontio.common;

import com.github.ontio.account.Account;
import com.github.ontio.crypto.SignatureScheme;
import com.github.ontio.sdk.exception.SDKException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {
    Account account;
    @Before
    public void setUp() throws Exception {
        account = new Account(SignatureScheme.SHA256WITHECDSA);
    }

    @Test
    public void compareTo() throws Exception {
        Account account2 = new Account(SignatureScheme.SHA256WITHECDSA);
        int res = account2.getAddressU160().compareTo(account.getAddressU160());
        assertNotNull(res);
    }

    @Test
    public void parse() {
        Address address = Address.parse(account.getAddressU160().toHexString());
        assertEquals(address,account.getAddressU160());
    }

    @Test
    public void addressFromPubKey() {
        Address address = Address.addressFromPubKey(account.serializePublicKey());
        assertEquals(address,account.getAddressU160());
    }

    @Test
    public void addressFromPubKey1() {
        Address address = Address.addressFromPubKey(Helper.toHexString(account.serializePublicKey()));
        assertEquals(address,account.getAddressU160());
    }

    @Test
    public void addressFromMultiPubKeys() throws Exception {
        Account account2 = new Account(SignatureScheme.SHA256WITHECDSA);
        Address res = Address.addressFromMultiPubKeys(2,account.serializePublicKey(),account2.serializePublicKey());
        assertNotNull(res);
    }

    @Test
    public void toBase58() throws SDKException {
        String res = account.getAddressU160().toBase58();
        Address addr = Address.decodeBase58(res);
        assertEquals(addr,account.getAddressU160());
    }

    @Test
    public void toScriptHash() {
        Address addr = Address.toScriptHash(Helper.hexToBytes("12a67b"));
        assertNotNull(addr);
    }

    @Test
    public void pubKeyToBase58(){
        Address addr1 = Address.addressFromPubKey("1202024714d59834799310a4aea84f1dceb55058481624497633b97cc7ba9e96e85eac");
        Address addr2 = Address.parse("012cf101547425b5f581d61bcd1c81615f045fb5");
        Address addr3 = Address.decodeBase58("TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu");
        System.out.println("Base58 addr1 = " + addr1.toBase58());
        System.out.println("Base58 addr2 = " + addr2.toBase58());
        System.out.println("Base58 addr3 = " + addr3.toBase58());

        System.out.println("HexString addr1 = " + addr1.toHexString());
        System.out.println("HexString addr2 = " + addr2.toHexString());
        System.out.println("HexString addr3 = " + addr3.toHexString());
    }

}