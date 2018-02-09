package ontology.sdk.transaction;

import ontology.account.KeyType;
import ontology.common.Helper;
import ontology.core.Transaction;
import ontology.io.BinaryReader;
import ontology.OntSdk;
import ontology.sdk.exception.Error;
import ontology.sdk.info.RecordInfo;
import ontology.sdk.wallet.Identity;
import ontology.sdk.claim.Claim;
import ontology.core.DataSignature;
import ontology.sdk.exception.SDKException;
import ontology.sdk.info.account.AccountInfo;
import ontology.sdk.manager.ConnectMgr;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.math.ec.ECPoint;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by zx on 2018/1/9.
 */
public class OntIdTx {
    public ConnectMgr connManager;
    public OntSdk sdk;
    private OntIdTx instance = null;
    private String codeHash = null;
    private String wsSessionId = "";
    public OntIdTx(OntSdk sdk) {
        this.sdk =sdk;
    }
    public void setCodeHash(String codeHash){
        this.codeHash = codeHash.replace("0x","");
    }
    public String getCodeHash(){
        return codeHash;
    }
    public void setWsSessionId(String sessionId){
        if(!this.wsSessionId.equals(sessionId)) {
            this.wsSessionId = sessionId;
        }
    }
    public String getWsSessionId(){
        return wsSessionId;
    }
    //注册ontid
    public Identity register(String password) throws Exception {
        if(codeHash == null){
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        AccountInfo info = sdk.getWalletMgr().createIdentityInfo(password);
        String ontid = "did:ont:" + info.address;
        byte[] pk = Helper.hexToBytes(info.pubkey);
        List list = new ArrayList<Object>();
        list.add("RegIdByPublicKey".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(ontid.getBytes());
        tmp.add(pk);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,info.address,info.pubkey);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        Identity identity = sdk.getWalletMgr().addOntIdController(ontid,info.encryptedprikey,info.address);
        sdk.getWalletMgr().writeWallet();
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(!b){
            throw new SDKException(Error.getDescArgError("sendRawTransaction error"));
        }
        System.out.println("hash:"+tx.hash().toString());
        return identity;
    }
    //注册ontid
    public Identity register(String password,Map<String, Object> attrsMap) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        AccountInfo info = sdk.getWalletMgr().createIdentityInfo(password);
        String ontid = "did:ont:" + info.address;
        byte[] pk = Helper.hexToBytes(info.pubkey);
        List list = new ArrayList<Object>();
        list.add("RegId".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(ontid.getBytes());
        tmp.add(pk);
        byte attriNum = (byte) attrsMap.size();
        byte[] allAttrsBys = new byte[]{};

        for (Map.Entry<String, Object> e : attrsMap.entrySet()) {
            Object val = e.getValue();
            String tmpVal = "";
            byte[] bs = null;
            String type = "Object";
            byte[] attrsBys = new byte[]{};
            if (val instanceof BigInteger) {
                bs = Helper.addBytes(new byte[]{(byte) e.getKey().getBytes().length}, e.getKey().getBytes());
                type = "Integer";
                tmpVal = String.valueOf((int)val);
            } else if (val instanceof byte[]) {
                bs = Helper.addBytes(new byte[]{(byte) e.getKey().getBytes().length}, e.getKey().getBytes());
                type = "ByteArray";
                tmpVal = new String((byte[])val);
            } else if (val instanceof Boolean) {
                bs = Helper.addBytes(new byte[]{(byte) e.getKey().getBytes().length}, e.getKey().getBytes());
                type = "Boolean";
                tmpVal = String.valueOf((boolean)val);
            } else if (val instanceof Integer) {
                bs = Helper.addBytes(new byte[]{(byte) e.getKey().getBytes().length}, e.getKey().getBytes());
                type = "Integer";
                tmpVal = String.valueOf((int)val);
            } else if (val instanceof String) {
                bs = Helper.addBytes(new byte[]{(byte) e.getKey().getBytes().length}, e.getKey().getBytes());
                type = "String";
                tmpVal = (String)val;
            } else {
                bs = Helper.addBytes(new byte[]{(byte) e.getKey().getBytes().length}, e.getKey().getBytes());
                type = "Object";
                tmpVal = JSON.toJSONString(val);
            }

            bs = Helper.addBytes(bs, Helper.addBytes(new byte[]{(byte)type.length()},type.getBytes()));
            byte[] valBys = JSON.toJSONString(tmpVal).getBytes();
            bs = Helper.addBytes(bs, Helper.addBytes(new byte[]{(byte) (valBys.length / 256), (byte) (valBys.length % 256)}, valBys));
            attrsBys = Helper.addBytes(attrsBys, bs);
            if (attrsBys.length / (256 * 256) > 0) {
                return null;
            }
            attrsBys = Helper.addBytes(new byte[]{(byte) (attrsBys.length / 256), (byte) (attrsBys.length % 256)}, attrsBys);
            allAttrsBys = Helper.addBytes(allAttrsBys,attrsBys);
        }

        tmp.add(Helper.addBytes(new byte[]{attriNum}, allAttrsBys));
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(sdk.getSmartcodeTx().createCodeParamsScript(list), codeHash, info.address,info.pubkey);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        Identity identity = sdk.getWalletMgr().addOntIdController(ontid,info.encryptedprikey,info.address);
        sdk.getWalletMgr().writeWallet();
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if (!b) {
            return null;
        }
        return identity;
    }
    public RecordInfo parseAttribute(byte[] code) throws Exception{
        try (ByteArrayInputStream ms = new ByteArrayInputStream(code, 0, code.length - 0)) {
            try (BinaryReader reader = new BinaryReader(ms)) {
                 reader.readVarBytes(); //pk
                RecordInfo info = new RecordInfo();
                info.value = new String(reader.readVarBytes2());
                String type = new String(reader.readVarBytes());
                info.key = new String(reader.readVarBytes());
                info.ontid = new String(reader.readVarBytes());
                reader.readBytes(2);//55c1
                info.opreation = new String(reader.readVarBytes());
                return info;
            }
        }
    }
    //通过guardian注册ontid
    public String registerByGuardian(String password,String guardianAddr) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        AccountInfo info = sdk.getWalletMgr().createIdentityInfo(password);
        byte[] did = ("did:ont:"+info.address).getBytes();
        byte[] guardianDid = ("did:ont:"+guardianAddr).getBytes();
        List li = new ArrayList<Object>();
        li.add("CreateIdentityByGuardian".getBytes());
        li.add(did);
        li.add(guardianDid);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(sdk.getSmartcodeTx().createCodeParamsScript(li),codeHash,guardianAddr,info.pubkey);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //添加公钥
    public String  addPubKey(String password,String addr,String newpubkey) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("AddKey".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(Helper.hexToBytes(newpubkey));
        tmp.add(pk);
        tmp.add(new byte[]{0});
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //添加公钥
    public String  addPubKey(String password,String addr,String newpubkey,String  recoveryScriptHash) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        List list = new ArrayList<Object>();
        list.add("AddKey".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(Helper.hexToBytes(newpubkey));
        tmp.add(Helper.hexToBytes(recoveryScriptHash));
        tmp.add(new byte[]{1});
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //通过guardian添加公钥
    public String  addPubKeyByGuardian(String password,String addr,String newpubkey,String guardianAddr) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        byte[] guardianDid = ("did:ont:"+guardianAddr).getBytes();
        List list = new ArrayList<Object>();
        list.add("AddKeyByGuardian".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(Helper.hexToBytes(newpubkey));
        tmp.add(guardianDid);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,guardianAddr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //添加公钥
    public String  removePubKey(String password,String addr,String removepk) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("RemoveKey".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(Helper.hexToBytes(removepk));
        tmp.add(pk);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //删除公钥
    public String  rmovePubKey(String password,String addr,byte[] key,String  recoveryScriptHash) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        List list = new ArrayList<Object>();
        list.add("RemoveKey".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(key);
        tmp.add(Helper.hexToBytes(recoveryScriptHash));
        tmp.add(1);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //添加恢复人
    public String  addRecovery(String password,String addr,String  recoveryScriptHash) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("AddRecovery".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(Helper.hexToBytes(recoveryScriptHash));
        tmp.add(pk);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //改变恢复人
    public String  changeRecovery(String password,String addr,String  newRecoveryScriptHash,String  oldRecoveryScriptHash) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("ChangeRecovery".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(Helper.hexToBytes(newRecoveryScriptHash));
        tmp.add(pk);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //更新属性
    public String  updateAttribute(String password,String ontid,byte[]  path,byte[] type,byte[] value) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        if(type.length >= 255 || path.length >= 255 ){
            throw new SDKException(Error.getDescArgError("param error"));
        }
        byte[] did = (ontid).getBytes();
        String addr = ontid.replace("did:ont:","");
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("AddAttribute".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(path);
        tmp.add(type);
        tmp.add(value);
        tmp.add(pk);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    //添加多个属性
    public String  updateAttributeArray(String password,String addr, List<Object> attriList) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        byte[] did = ("did:ont:"+addr).getBytes();
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("AddAttributeArray".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(pk);
        tmp.add(attriList);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    public String  getDDO(String ontid) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        return sdk.getConnectMgr().getDDO(codeHash,ontid.replace("did:ont:",""));
    }
    private Object parseData(String obj){
        byte[] bys = obj.getBytes();
        int num = bys[0];
        int offset = 1;
        List list = new ArrayList();
        for (int i = 0; i < num; i++) {
            int len = bys[offset] * 256 + bys[offset + 1];
            byte[] data = new byte[len];
            offset = offset + 2;
            System.arraycopy(bys, offset, data, 0, len);
            list.add(data);
            offset = offset + len;
        }
        return num;
    }
    public Object  getDDO(String password,String addr,String ontid) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        List list = new ArrayList<Object>();
        list.add("GetDDO".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(ontid.getBytes());
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        List listResult = (List)sdk.getConnectMgr().sendRawTransactionPreExec(txHex);
        for (int i = 0; i < listResult.size(); i++) {
            parseData((String)listResult.get(i));
        }
        return listResult;
    }
    public String[] getPubKeys(String did) throws Exception {
        return new String[]{};
    }
    public LinkedHashMap<String, Object> constructRecord(String text){
        LinkedHashMap<String, Object> recordData = new LinkedHashMap<String, Object> ();
        LinkedHashMap<String, Object> data = new  LinkedHashMap<String, Object>();
        data.put("Algrithem", KeyType.SM2.name());
        data.put("Hash","");
        data.put("Text",text);
        data.put("Signature","");

        recordData.put("Data",data);
        recordData.put("CAkey","");
        recordData.put("SeqNo","");
        recordData.put("Timestamp",0);
        return  recordData;
    }
    public String  addRecord(String password,String ontid,byte[]  key,byte[] value) throws Exception {
        if (codeHash == null) {
            throw new SDKException(Error.getDescArgError("null codeHash"));
        }
        if(key.length >= 255 ){
            throw new SDKException(Error.getDescArgError("param error"));
        }
        byte[] did = (ontid).getBytes();
        String addr = ontid.replace("did:ont:","");
        byte[] pk = Helper.hexToBytes(sdk.getWalletMgr().getAccountInfo(password,addr).pubkey);
        List list = new ArrayList<Object>();
        list.add("AddRecord".getBytes());
        List tmp = new ArrayList<Object>();
        tmp.add(did);
        tmp.add(key);
        tmp.add(value);
        list.add(tmp);
        Transaction tx = sdk.getSmartcodeTx().makeInvokeCodeTransaction(password,sdk.getSmartcodeTx().createCodeParamsScript(list),codeHash,addr);
        String txHex = sdk.getWalletMgr().signatureData(password,tx);
        boolean b = sdk.getConnectMgr().sendRawTransaction(wsSessionId,txHex);
        if(b){
            return tx.hash().toString();
        }
        return null;
    }
    public RecordInfo parseRecord(byte[] code) throws Exception{
        try (ByteArrayInputStream ms = new ByteArrayInputStream(code, 0, code.length - 0)) {
            try (BinaryReader reader = new BinaryReader(ms)) {
                RecordInfo info = new RecordInfo();
                info.value = new String(reader.readVarBytes2());
                info.key = new String(reader.readVarBytes());//path
                info.ontid = new String(reader.readVarBytes());//did
                reader.readBytes(2);//55c1
                info.opreation = new String(reader.readVarBytes());//AddAttribute
                return info;
            }
        }
    }
   //创建claim
    public String createOntIdClaim(String password,String context, Map<String, Object> claimMap, Map metaData) throws SDKException {
        Claim claim = null;
        Map map = new HashMap<String, Object>();
        for (Map.Entry<String, Object> e : claimMap.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        try {
            String sendDid = (String) metaData.get("Issuer");
            String receiverDid = (String) metaData.get("Subject");
            if (sendDid == null || receiverDid == null) {
                return null;
            }
            String[] sendDidStr = sendDid.split(":");
            String[] receiverDidStr = receiverDid.split(":");
            if (sendDidStr.length != 3 || receiverDidStr.length != 3) {
                throw new SDKException(Error.getDescArgError("Did error"));
            }
            claim = new Claim(sdk.getWalletMgr().getAlgrithem(), sdk.getWalletMgr().getAccount(password,sendDidStr[2]), context, map, sendDid, receiverDid, metaData);
            return claim.getClaim();
        } catch (SDKException e) {
            throw new SDKException(e);
        }
    }
    //验证claim
    public boolean verifyOntIdClaim(String claim) throws Exception {
        DataSignature sign = null;
        try {
            JSONObject obj = JSON.parseObject(claim);
            String issuerDid = obj.getJSONObject("Metadata").getString("Issuer");
            String[] str = issuerDid.split(":");
            if (str.length != 3) {
                throw new SDKException(Error.getDescArgError("Did error"));
            }
            String method = str[1];
            String addr = str[2];
            byte[] pubkeyBys = null;

            String issuerDdo = getDDO(issuerDid);//.getIdentityUpdate(method, addr);
            String pubkeyStr = JSON.parseObject(issuerDdo).getJSONArray("Owners").getJSONObject(0).getString("Value");
//            String publicKeyBase64 = JSON.parseObject(issuerDdo).getJSONArray("Owners").getJSONObject(0).getString("publicKeyBase64");
//            pubkeyBys = Base64.getDecoder().decode(publicKeyBase64);

            String signature = obj.getJSONObject("Signature").getString("Value");
            obj.remove("Signature");
            ECPoint pubkey = sdk.getWalletMgr().getPubkey(pubkeyStr);
            sign = new DataSignature();
            byte[] data = JSON.toJSONString(obj).getBytes();
            return sign.verifySignature(sdk.getWalletMgr().getAlgrithem(), pubkey, data, Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            throw new SDKException(e);
        }
    }


}
