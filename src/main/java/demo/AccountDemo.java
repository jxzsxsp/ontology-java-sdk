package demo;

import com.github.ontio.OntSdk;
import com.github.ontio.sdk.info.AccountInfo;
import com.github.ontio.sdk.wallet.Identity;

/**
 * @Description 账户Demo
 * @Author Sean.Xiao
 * @Date 2018/5/8 下午7:37
 */
public class AccountDemo {
    public static void main(String[] args) {

        try {
            OntSdk ontSdk = Base.getOntSdk();
            AccountInfo info = ontSdk.getWalletMgr().createAccountInfoFromPriKey("passwordtest","e467a2a9c9f56b012c71cf2270df42843a9d7ff181934068b4a62bcdd570e8be");
            System.out.println(info.addressBase58);
            Identity accountInfo = ontSdk.getWalletMgr().importIdentity("6PYSGbmZWnP9HZ9UvF7ScZaPRxXWbPeomMN6umP1ur2QnqhVzrsrCmK4Sf", "passwordtest",info.addressBase58);
            System.out.println(accountInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
