package xsp;

import com.github.ontio.OntSdk;
import com.github.ontio.account.Account;
import com.github.ontio.common.Helper;
import com.github.ontio.core.payload.DeployCode;
import com.github.ontio.core.transaction.Transaction;
import com.github.ontio.crypto.SignatureScheme;
import demo.Base;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Sean.Xiao
 * @date 2018/7/2 下午5:51
 */
public class SmartContractDeployDemo {

    public static void main(String[] args) {

        try {
            OntSdk ontSdk = Base.getOntSdk();
            String code = "58c56b6a00527ac46a51527ac46a00c30548656c6c6f9c6416006a51c300c36a52527ac46a52c3650b006c756661006c756655c56b6a00527ac46a00c3680f4e656f2e52756e74696d652e4c6f6761516c7566";

            Account account = new Account(Helper.hexToBytes("7eeb1f37ce6802f0d459905306efcc0b0f520537110d7a08918659c9da12cbce"), SignatureScheme.SHA256WITHECDSA);

            Transaction tx = ontSdk.vm().makeDeployCodeTransaction(
                    code,
                    true,
                    "HelloWorld",
                    "v0.0.1",
                    "Sean.Xiao",
                    "jxzsxsp@qq.com",
                    "My first smart contract",
                    account.getAddressU160().toBase58(),
                    20020000L,
                    500L);

            ontSdk.signTx(tx, new Account[][]{{account}});
            String txHex = Helper.toHexString(tx.toArray());
            System.out.println(txHex);

            Object gasLimitResult = ontSdk.getConnect().sendRawTransactionPreExec(txHex);
            System.out.println(gasLimitResult);

            Object result = ontSdk.getConnect().sendRawTransaction(txHex);
            System.out.println(result);

            System.out.println("txhash:" + tx.hash().toString());
            String txhash = tx.hash().toHexString();
            System.out.println("txhash:" + txhash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
