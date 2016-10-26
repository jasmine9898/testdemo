package rpc.thriftdemo2;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/5/9.
 */
@Controller
public class ThriftClient {
    //非阻塞
    public String siginClient2(UserInfo user) {
        TTransport transport = null;
        Result result=null;
        try {
            transport = new TFramedTransport(new TSocket("192.168.2.206", 7912, 30000));
            // 协议要和服务端一致
            TProtocol protocol = new TCompactProtocol(transport);
            ThriftServer.Client client = new ThriftServer.Client(protocol);
            transport.open();
            result = client.sigin(user);
            System.out.println("sigin result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
        return "Thrift siginClient =: " + result;
    }
    public void loginClient2(String name,String pwd) {
        TTransport transport = null;
        try {
            transport = new TFramedTransport(new TSocket("192.168.2.206",7912, 30000));
            // 协议要和服务端一致
            TProtocol protocol = new TCompactProtocol(transport);
            ThriftServer.Client client = new ThriftServer.Client(protocol);
            transport.open();
            Result result = client.login(name,pwd);
            System.out.println("login result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
    public String loginClient(String name,String pwd) {
        TTransport transport = null;
        Result result=null;
    try {
        transport = new TSocket("192.168.2.206", 7911, 30000);
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol(transport);
        // TProtocol protocol = new TCompactProtocol(transport);
        // TProtocol protocol = new TJSONProtocol(transport);
        ThriftServer.Client client = new ThriftServer.Client(
                protocol);
        transport.open();
        result= client.login(name,pwd);
        System.out.println("Thrift loginClient =: " + result);
    } catch (TTransportException e) {
        e.printStackTrace();
    } catch (TException e) {
        e.printStackTrace();
    } finally {
        if (null != transport) {
            transport.close();
        }
    }
        return "Thrift loginClient =: " + result;
    }
    public String siginClient(UserInfo user) {
        TTransport transport = null;
        Result result=null;
        try {
            transport = new TSocket("192.168.2.206", 7911, 30000);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            // TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            ThriftServer.Client client = new ThriftServer.Client(
                    protocol);
            transport.open();
            result = client.sigin(user);
            System.out.println("Thrift siginClient =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
        return "Thrift siginClient =: " + result;
    }
    @RequestMapping("thrift.do")
    @ResponseBody
    public String xx() {
        ThriftClient client=new ThriftClient();
        client.loginClient("11","22");
        return client.siginClient(new UserInfo(1,"name","pwd","2016"));
    }
    @RequestMapping("thrift2.do")
    @ResponseBody
    public String client2() {
        ThriftClient client=new ThriftClient();
        client.loginClient2("11","22");
        return client.siginClient2(new UserInfo(1,"name","pwd","2016"));
    }
}
