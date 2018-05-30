package exercise.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lichen@daojia.com on 2018-5-30.
 */
public class ExampleClient extends LeaderSelectorListenerAdapter implements Closeable{

    private final String name;

    private final LeaderSelector leaderSelector;

    private final AtomicInteger leaderCount = new AtomicInteger( );


    public ExampleClient(CuratorFramework client,String path, String name) {
        this.name = name;

        //create a leader selector using the given path for management
        // all participants in a given leader selection must use the same path
        //ExampleClient here is alse a LeaderSelectorListener but this isn't required
        this.leaderSelector = new LeaderSelector(client,path ,this);

        //for most cases you will want your instance to requeue when it relinquishes leadership
//        leaderSelector.autoRequeue();
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {

    }
}
