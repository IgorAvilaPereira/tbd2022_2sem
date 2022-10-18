/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package apresentacao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author iapereira
 */
public class Aula171022 {

    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try ( MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("minhaBase");
            MongoCollection<org.bson.Document> collection = database.getCollection("users");
            try {
//                InsertOneResult result = collection.insertOne(new Document()
//                                            .append("nome", "marcio"));
//                System.out.println("Success! Inserted document id: " + result.getInsertedId());

                Bson query = eq("nome", "marcio");

                DeleteResult result = collection.deleteOne(query);

            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }
}
