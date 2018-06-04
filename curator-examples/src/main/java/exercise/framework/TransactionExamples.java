package exercise.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;

import java.util.Collection;

/**
 * Created by lichen@daojia.com on 2018-6-4.
 */
public class TransactionExamples {

    public static Collection<CuratorTransactionResult> transaction (CuratorFramework client) throws Exception {
        // this example shows how to use Zookeeper's transaction.

        CuratorOp createOp = client.transactionOp().create().forPath( "/a/path/","some data".getBytes() );
        CuratorOp setDataOp = client.transactionOp().setData().forPath( "/another/path/","another data".getBytes() );
        CuratorOp deleteOp = client.transactionOp().delete().forPath( "/yet/another/path" );

        Collection<CuratorTransactionResult> results = client.transaction().forOperations( createOp,setDataOp,deleteOp );

        for (CuratorTransactionResult result : results) {
            System.out.println(result.getForPath()+ "-" + result.getType());
        }
        return results;
    }

}
