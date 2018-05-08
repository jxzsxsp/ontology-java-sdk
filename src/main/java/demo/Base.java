package demo;

import com.github.ontio.OntSdk;

/**
 * 本体SDK基础配置类
 *
 * @author Sean.Xiao
 * @date 2018/5/8 下午7:26
 */
public class Base {

    public static Object lock = new Object();

    public static OntSdk getOntSdk() throws Exception {
        String ip = "http://es.xiaoyuechen.com";
        String restUrl = ip + ":" + "20384";
        String rpcUrl = ip + ":" + "20386";
        String wsUrl = ip + ":" + "20385";

        OntSdk wm = OntSdk.getInstance();
        wm.setRpc(rpcUrl);
        wm.setRestful(restUrl);
        wm.setWesocket(wsUrl, lock);
        wm.setDefaultConnect(wm.getRestful());

        wm.openWalletFile("MyWalletDemo.json");
        wm.setCodeAddress("80e7d2fc22c24c466f44c7688569cc6e6d6c6f92");

        return wm;
    }
}
