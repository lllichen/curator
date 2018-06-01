package exercise.discovery;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Created by lichen@daojia.com on 2018-5-31.
 */
public class DiscoveryExample {


    private static final String PATH = "/discovery/example";

    public static void main(String[] args) throws Exception {

        // this method is scaffolding to get the example up and running

        TestingServer server = new TestingServer();
        CuratorFramework client = null;
        ServiceDiscovery<InstanceDetails> serviceDiscovery = null;
        Map<String, ServiceProvider<InstanceDetails>> providers = Maps.newHashMap();

        client = CuratorFrameworkFactory.newClient( server.getConnectString(),new ExponentialBackoffRetry( 1000,3 ) );
        client.start();

        JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<InstanceDetails>( InstanceDetails.class );
        serviceDiscovery = ServiceDiscoveryBuilder.builder( InstanceDetails.class ).client( client ).basePath( PATH ).serializer( serializer ).build();
        serviceDiscovery.start();


    }


    private static void processCommands(ServiceDiscovery<InstanceDetails> serviceDiscovery, Map<String,ServiceProvider<InstanceDetails>> providers,CuratorFramework client){

        printHelp();

        List<ExampleServer> servers = Lists.newArrayList();

        BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
    }

    private static void printHelp(){
        System.out.println("Am example of using the ServiceDiscovery APIs. This example is driven by entering commands at the prompt:\n");
        System.out.println("add <name> <description>: Adds a mock service with the given name and description");
        System.out.println("delete <name>: Deletes one of the mock service with the given name");
        System.out.println("list: Lists all the currently registered services");
        System.out.println("random <name>: Lists a random instance of the service with the given name");
        System.out.println("quit: Quit the example");
        System.out.println();
    }

}
