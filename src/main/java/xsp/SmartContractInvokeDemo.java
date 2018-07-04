package xsp;

import com.github.ontio.OntSdk;
import com.github.ontio.account.Account;
import com.github.ontio.common.Helper;
import com.github.ontio.core.transaction.Transaction;
import com.github.ontio.crypto.SignatureScheme;
import demo.Base;

/**
 * @author Sean.Xiao
 * @date 2018/7/4 下午7:07
 */
public class SmartContractInvokeDemo {

    public static void main(String[] args) {

        try {
            OntSdk ontSdk = Base.getOntSdk();
            String code = "58c56b6a00527ac46a51527ac46a00c30548656c6c6f9c6416006a51c300c36a52527ac46a52c3650b006c756661006c756655c56b6a00527ac46a00c3680f4e656f2e52756e74696d652e4c6f6761516c7566";

            Account account = new Account(Helper.hexToBytes("7eeb1f37ce6802f0d459905306efcc0b0f520537110d7a08918659c9da12cbce"), SignatureScheme.SHA256WITHECDSA);

            byte[] bs = Helper.hexToBytes("52c762f5ce3aedfacc5b3471aae87143826e87dfaeaaed7df6a13597d593a59e");

            Transaction tx = ontSdk.vm().makeInvokeCodeTransaction(
                    "52c762f5ce3aedfacc5b3471aae87143826e87dfaeaaed7df6a13597d593a59e",
                    "Hello",
                    bs,
                    account.getAddressU160().toBase58(),
                    20020000L,
                    500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
