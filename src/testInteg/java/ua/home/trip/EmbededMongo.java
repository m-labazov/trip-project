package ua.home.trip;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "spring-test-context.xml" })
public class EmbededMongo {
	private static final String DATABASE_NAME = "embedded";

	private MongodExecutable mongodExe;
	private MongodProcess mongod;
	private Mongo mongo;

	@Before
	public void beforeEach() throws Exception {
        // MongoDBRuntime runtime = MongoDBRuntime.getDefaultInstance();
        // mongodExe = runtime.prepare(new MongodConfig(Version.V2_3_0, 12345,
        // Network.localhostIsIPv6()));
        // mongod = mongodExe.start();
        // mongo = new Mongo("localhost", 12345);
	}

	@After
	public void afterEach() throws Exception {
		if (this.mongod != null) {
			this.mongod.stop();
			this.mongodExe.stop();
		}
	}


}
