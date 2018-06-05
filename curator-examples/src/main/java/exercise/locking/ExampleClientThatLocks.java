package exercise.locking;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * Created by lichen@daojia.com on 2018-6-5.
 */
public class ExampleClientThatLocks {

    private final InterProcessMutex lock;

    private final FakeLimitedResource resource;

    private final String clientName;

    public ExampleClientThatLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {

        this.lock = new InterProcessMutex( client, lockPath );
        this.resource = resource;
        this.clientName = clientName;
    }


    public void doWork(long time, TimeUnit unit) throws Exception {

        System.out.println(clientName + " is ready to acquire the lock");
        if (!lock.acquire( time,unit )){
            System.out.println(clientName + "acquire error");
            throw new IllegalStateException( clientName +" could not acquire the lock");
        }
        try
        {
            System.out.println(clientName + " has the lock");
            resource.use();
        }finally
        {
            System.out.println(clientName + " releasing the lock");
            lock.release(); // always release the lock in

        }

    }
}
