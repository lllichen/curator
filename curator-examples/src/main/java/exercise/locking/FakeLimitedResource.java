package exercise.locking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lichen@daojia.com on 2018-6-5.
 * simulate some external resource that can only be access by one process at a time.
 *
 */
public class FakeLimitedResource {

    private final AtomicBoolean isUse = new AtomicBoolean( false );

    public void use() throws InterruptedException {

        // in a real application this would be accessing/manipulating a shared resource
        if (!isUse.compareAndSet( false,true ) ){
            throw new IllegalStateException( "Needs to be used by one client at a time" );
        }

        try
        {
            Thread.sleep( (long) (3 * Math.random()));
        }finally
        {
            isUse.set( false );
        }
    }
}
