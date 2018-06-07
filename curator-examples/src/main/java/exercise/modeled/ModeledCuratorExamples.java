package exercise.modeled;

import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.curator.x.async.modeled.JacksonModelSerializer;
import org.apache.curator.x.async.modeled.ModelSpec;
import org.apache.curator.x.async.modeled.ModeledFramework;
import org.apache.curator.x.async.modeled.ZPath;

import java.util.function.Consumer;

/**
 * Created by lichen@daojia.com on 2018-6-7.
 */
public class ModeledCuratorExamples
{
    public static ModeledFramework<PersonModel> wrap(AsyncCuratorFramework client)
    {
        JacksonModelSerializer<PersonModel> serializer = JacksonModelSerializer.build( PersonModel.class );

        //build a model specification - you can pre-build all the model specifications for your app at startup
        ModelSpec<PersonModel> modelSpec = ModelSpec.builder( ZPath.parse("/example/path"), serializer ).build();

        //warp a CuratorFramework instance so that it can be used "modeled".
        //do this once and re-use the returned ModelFramework instance.
        //ModelFramework instances are tied to a given path.
        return ModeledFramework.wrap( client,modelSpec );
    }

    public static void createOrUpdate(ModeledFramework<PersonModel> modeled,PersonModel model){
        //change the affected path to be modeled's base path plus id: i.e. "/example/path/{id}"
        ModeledFramework<PersonModel> atId = modeled.child( model.getId().getId() );

        //by default ModeledFramework instance update the node if it already exists
        //so this will either create or update the node
        atId.set( model );// note - this is async
    }

    public static void readPerson(ModeledFramework<PersonModel> modeled, String id, Consumer<PersonModel> receiver)
    {
        //read the person with the given ID and asynchronously call the receiver after it is read
        modeled.child( id ).read().whenComplete( (person,exception)->{
            if (exception != null ) {
                exception.printStackTrace();
            }
            else
            {
                receiver.accept( person );
            }
        } );
    }
}
