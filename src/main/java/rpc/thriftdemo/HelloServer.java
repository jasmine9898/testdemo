package rpc.thriftdemo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2016/5/26.
 */
public class HelloServer {
    public static void main(String[] args){
        try {
            System.out.print("server starting ...");
            //阻塞 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TProcessor tprocessor = new Hello.Processor<Hello.Iface>(new HelloImpl());
           // TServerSocket serverTransport = serverTransport = new TServerSocket(7911);
            //TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(7911);
            TNonblockingServer.Args Args = new TNonblockingServer.Args(tnbSocketTransport);
            Args.processor(tprocessor);
            Args.protocolFactory(new TBinaryProtocol.Factory());
           // TServer server = new TThreadPoolServer(Args);
            TServer server = new TNonblockingServer(Args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
