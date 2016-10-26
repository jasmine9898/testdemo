package rpc.thriftdemo2;

import org.apache.thrift.TException;

/**
 * Created by Administrator on 2016/5/9.
 */
public class ThriftServerImpl implements ThriftServer.Iface {
    public Result sigin(UserInfo user) throws TException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result(1,"sleep 1000 java-thrift-server sigin ok");
    }

    public Result login(String name, String pwd_md5) throws TException {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result(2,"sleep 2000 java-thrift-server login ok");
    }

    public Result session(String name) throws TException {
        return new Result(3,"java-thrift-server session ok");
    }
}
