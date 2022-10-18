/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import negocio.Pessoa;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 *
 * @author iapereira
 */
public class PessoaDAO {

    private String uri;
    private ConnectionString connectionString;
    private CodecRegistry pojoCodecRegistry;
    private CodecRegistry codecRegistry;
    private MongoClientSettings clientSettings;
    private String database;
    private String collection;

    public PessoaDAO() {

        this.uri = "mongodb://localhost:27017";
        this.connectionString = new ConnectionString(uri);

        this.pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        this.codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        this.clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        this.database = "minhaBase";
        this.collection = "users";

    }

    public void inserir(Pessoa pessoa) {

        try ( MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Pessoa> users = db.getCollection(this.collection, Pessoa.class);
            users.insertOne(pessoa);

        }

    }

    public void deletarPorNome(String nome) {
        try ( MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Pessoa> users = db.getCollection(this.collection, Pessoa.class);

//                delatar por nome
            users.deleteOne(eq("nome", nome));
        }

    }

    public void update(Pessoa p) {

        try ( MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Pessoa> users = db.getCollection(this.collection, Pessoa.class);

            users.replaceOne(eq("_id", p.getId()), p);

        }

    }

//          update por id
// listar todos
    public MongoCursor<Pessoa> listar() {
        MongoCursor<Pessoa> cursor;
        try ( MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Pessoa> users = db.getCollection(this.collection, Pessoa.class);

            cursor = users.find().iterator();
            return cursor;
        }

    }

}
