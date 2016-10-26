package rpc.thriftdemo2;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;

/**
 * Created by Administrator on 2016/5/9.
 */
public class ServerMain {
    //非阻塞 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式。
    public void startServer2() {
        try {
            System.out.println("server start .2...");

            TProcessor tprocessor = new ThriftServer.Processor<ThriftServer.Iface>(
                    new ThriftServerImpl());

            TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(7912);
            TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
            tnbArgs.processor(tprocessor);
            tnbArgs.transportFactory(new TFramedTransport.Factory());
            tnbArgs.protocolFactory(new TCompactProtocol.Factory());

            // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
            TServer server = new TNonblockingServer(tnbArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }
    //阻塞 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
    public void startServer() {
        try {
            System.out.println("...... server start .1....");

            TProcessor tprocessor = new ThriftServer.Processor<ThriftServer.Iface>(
                    new ThriftServerImpl());

            TServerSocket serverTransport = new TServerSocket(7911);
            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
                    serverTransport);
            ttpsArgs.processor(tprocessor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());

            // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TServer server = new TThreadPoolServer(ttpsArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }
    public static void xx(){
        ServerMain server=new ServerMain();
        server.startServer2();
    }
    public static void yy(){
        ServerMain server=new ServerMain();
        server.startServer();
    }
    public static void main(String[] arg){
        yy();
        xx();
    }
}
