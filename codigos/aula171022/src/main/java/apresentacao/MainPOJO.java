/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apresentacao;

import negocio.Pessoa;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import persistencia.PessoaDAO;

/**
 *
 * @author iapereira
 */
public class MainPOJO {

    public static void main(String[] args) {

        PessoaDAO pessoaDAO = new PessoaDAO();
        
       

//        Pessoa p = new Pessoa();
//        p.setNome("lorrana");
//        pessoaDAO.inserir(p);
//
////        pessoaDAO.deletarPorNome("lorrana");
        MongoCursor<Pessoa> cursor = pessoaDAO.listar();
        while (cursor.hasNext()) {
            Pessoa p1 = cursor.next();
            if (p1.getNome().equals("igor")){
                p1.setNome("igor pereira");
                pessoaDAO.update(p1);
            }
            System.out.println(p1);
        }

//        String uri = "mongodb://localhost:27017";
//        ConnectionString connectionString = new ConnectionString(uri);
//        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
//        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
//        MongoClientSettings clientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .codecRegistry(codecRegistry)
//                .build();
//        try ( MongoClient mongoClient = MongoClients.create(clientSettings)) {
//            
//            MongoDatabase db = mongoClient.getDatabase("minhaBase");
//            MongoCollection<Pessoa> users = db.getCollection("users", Pessoa.class);
//
//            // create a new grade.
////            Pessoa pessoa = new Pessoa();
////            pessoa.setNome("raquel");
////              inserir um objeto
////            users.insertOne(pessoa);
////    delatar por nome
////                users.deleteOne(eq("nome", "raquel"));
////          update por id
////            users.updateOne(eq("_id", new ObjectId("634def257ccb98575ca195dd")), set("nome", "rafael betito"));
//            // listar todos
//            MongoCursor<Pessoa> cursor = users.find().iterator();
//
//            while (cursor.hasNext()) {
//                Pessoa p = cursor.next();
//                System.out.println(p);
//            }
//        }
    }
}
