package xsp;

import com.github.ontio.OntSdk;
import com.github.ontio.sdk.wallet.Account;
import com.github.ontio.sdk.wallet.Identity;
import demo.Base;

/**
 * @author Sean.Xiao
 * @date 2018/7/2 下午5:09
 */
public class ApiDemo {

    public static void main(String[] args) {

        try {
            OntSdk ontSdk = Base.getOntSdk();

            Object balance = ontSdk.getRestful().getBalance("ASqVhSZW8CsyTHKb5jw2TbT1tKzFMQA4FX");
            System.out.println("ASqVhSZW8CsyTHKb5jw2TbT1tKzFMQA4FX balance:" + balance.toString());
            Account account = ontSdk.getWalletMgr().getDefaultAccount();
            Identity identity = ontSdk.getWalletMgr().getDefaultIdentity();


            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
