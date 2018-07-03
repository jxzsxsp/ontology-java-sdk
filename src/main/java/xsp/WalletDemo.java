package xsp;

import com.alibaba.fastjson.JSON;
import com.github.ontio.OntSdk;
import com.github.ontio.sdk.wallet.Account;
import demo.Base;

import java.util.List;

/**
 * @author Sean.Xiao
 * @date 2018/7/2 下午5:39
 */
public class WalletDemo {

    public static void main(String[] args) {

        try {
            OntSdk ontSdk = Base.getOntSdk();

            ontSdk.getWalletMgr().createAccount("passwordtest");
            ontSdk.getWalletMgr().writeWallet();

            List<Account> accountList = ontSdk.getWalletMgr().getWallet().getAccounts();

            System.out.println(JSON.toJSONString(accountList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
