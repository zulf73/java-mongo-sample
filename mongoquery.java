import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import org.bson.Document;

public class mongoquery {
	private static final String DATABASE = "test";
	private static final String COLLECTION = "emailsvc";
	private static String mongoClientUri = "";

	public static void main(String[] args) {

		mongoClientUri = "mongodb://unique:unique@cluster0-shard-00-02.3cmqe.mongodb.net:27017/";
		char[] pass = { 'u', 'n', 'i', 'q', 'u', 'e' };
		MongoCredential credential = MongoCredential.createCredential("unique", "test", pass);

		MongoClientOptions options = MongoClientOptions.builder().serverSelectionTimeout(40500).build();
		MongoClient mongo = new MongoClient(new ServerAddress("cluster0-shard-00-02.3cmqe.mongodb.net", 27017),
				Arrays.asList(credential), options);
		// MongoClient mongo = new MongoClient(new MongoClientURI(mongoClientUri));
		MongoCollection collection = mongo.getDatabase(DATABASE).getCollection(COLLECTION);

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
		mongo.close();
	}
}
