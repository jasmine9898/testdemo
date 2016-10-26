package rpc.thriftdemo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2016/5/26.
 */
public class HelloClient {
    public static void main(String[] args) {
        TTransport transport = null;
        try {
            // transport = new TSocket("192.168.2.206", 7911, 30000);  //阻塞
            transport = new TFramedTransport(new TSocket("localhost",7911, 30000));//非阻塞
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            // TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            transport.open();
            String response = client.hi();
            System.out.print("response :"+response);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }
}
