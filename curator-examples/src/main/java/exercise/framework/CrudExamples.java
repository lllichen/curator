package exercise.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * Created by lichen@daojia.com on 2018-6-4.
 */
public class CrudExamples {

    public static void create(CuratorFramework client, String path,  byte[] payload) throws Exception {
        //this will create the given ZNode with the given data
        client.create().forPath( path,payload );
    }

    public static void createEphemeral(CuratorFramework client, String path, byte[] payload) throws Exception {
        //this will create the given EPHEMERAL ZNode with the given data
        client.create().withMode( CreateMode.EPHEMERAL ).forPath( path, payload );
    }

    public static String createEphemeralSequential(CuratorFramework client, String path, byte[] payload) throws Exception {
        // this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given data using Curator protection.

        /*
            Protection Mode:

            it turns out there is an edge case that exists when creating  sequential-ephemeral nodes. The creation
            can succeed on the server. but the server can crash before the created node name is returned to the client.
            However, the ZK session is still valid so the ephemeral node is not deleted. thus, there is no way for the
            client to determine what node was created for them.

            Even without sequential-ephemeral, however, the creation can succeed on the server but the client ( for various
            reason) will not know it, Putting the create builder into protection mode works around this.the name of the node
            that is created is prefixed with a GUID. if node creation fails the normal retry mechanism will occur.On the retry,
            the parent path is first searched for a node that has the GUID in it.If that node is found, it is assumed to be the
            lost node that was successfully created on the first try and is returned to the caller.
         */
        return client.create().withProtection().withMode( CreateMode.EPHEMERAL_SEQUENTIAL ).forPath( path,payload );
    }

    public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {

        //set data for the given node
        client.setData().forPath( path,payload );
    }

    public static void setDataAsync(CuratorFramework client, String path, byte[] payload) throws Exception {

        //this is one method of getting event/async notifications
        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

                //examine event for details
            }
        };
        client.getCuratorListenable().addListener( listener );

        //set data for the given node asynchronously.The completion notification
        // is done via the CuratorListener.
        client.setData().inBackground().forPath( path,payload );
    }

    public static void setDataAsyncWithCallback(CuratorFramework client, BackgroundCallback callback, String path, byte[] payload ) throws Exception {
        //this is another method of getting notification of an async completion
        client.setData().inBackground(callback).forPath( path, payload );
    }

    public static void delete(CuratorFramework client, String path) throws Exception {
        //delete the given node
        client.delete().forPath( path );
    }

    public static void guaranteedDelete(CuratorFramework client, String path) throws Exception {
        //delete the given node and guarantee that it completes

        /*
         Guaranteed Delete

         Solves this edge case: deleting a node can fail due to connection issues.Further, if the node was ephemeral, the node will not get auto-deleted as the session is still valid.
         This can wreak havoc with lock implements.

         When guaranteed is set, Curator will record failed node deletions and attempt to delete them in the background until successful.NOTE: you will still get an exception when the
         deletion fails.But, you can be assured that as long as the CuratorFramework instance is open attempts will be made to delete the node.
         */

        client.delete().guaranteed().forPath( path );
    }


    public static List<String> watchedGetChildren(CuratorFramework client, String path) throws Exception {
        // get children and set a watcher on the node. The Watcher notification will come through the CuratorListener (see setDataAsync() above)
        return client.getChildren().watched().forPath( path );
    }


    public static List<String> watchedGetChildren(CuratorFramework client, String path, Watcher watcher) throws Exception {

        // get children and set the given watcher on the node.
        return client.getChildren().usingWatcher( watcher ).forPath( path );
    }
}
