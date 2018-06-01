package exercise.leader;

import org.apache.curator.test.TestingServer;

/**
 * Created by lichen@daojia.com on 2018-5-30.
 */
public class TestServerSample {

    private  TestingServer testingServer;


    private static TestServerSample ourInstance = new TestServerSample();

    public static TestServerSample getInstance() {
        return ourInstance;
    }

    private TestServerSample() {
        try {
            testingServer = new TestingServer(  );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TestingServer getTestingServer() {
        return testingServer;
    }
}
