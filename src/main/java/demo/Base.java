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

        return wm;
    }

    public static OntSdk getTestNet() throws Exception {
        String ip = "http://polaris1.ont.io";
        String restUrl = ip + ":" + "20384";
        String rpcUrl = ip + ":" + "20386";
        String wsUrl = ip + ":" + "20385";

        OntSdk wm = OntSdk.getInstance();
        wm.setRpc(rpcUrl);
        wm.setRestful(restUrl);
        wm.setWesocket(wsUrl, lock);
        wm.setDefaultConnect(wm.getRestful());

        wm.openWalletFile("MyWalletDemo.json");

        return wm;
    }
}
