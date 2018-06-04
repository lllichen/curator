package exercise.framework;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by lichen@daojia.com on 2018-6-1.
 */
public class CreateClientExamples {

    public static CuratorFramework createSimple(String connectionString) {

        //these are reasonable arguments for the ExponentialBackoffRetry.The first
        //retry will wait 1 second - the second will wait up to 2 seconds - the third will wait up to
        // 4 seconds
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry( 1000,3 );

        //the simplest way to get a CuratorFramework instance, This will use default values.
        //The only required arguments are the connection string  and retry policy
        return CuratorFrameworkFactory.newClient( connectionString,retryPolicy );
    }


    public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs){

        //using the CuratorFrameworkFactory.build() gives the fine grained control
        //over creation options.See the CuratorFramework.Builder doc
        //Details
        return CuratorFrameworkFactory.builder()
                .connectString( connectionString )
                .retryPolicy( retryPolicy )
                .connectionTimeoutMs( connectionTimeoutMs )
                .sessionTimeoutMs( sessionTimeoutMs )
                //etc. etc.
                .build();
    }
}
