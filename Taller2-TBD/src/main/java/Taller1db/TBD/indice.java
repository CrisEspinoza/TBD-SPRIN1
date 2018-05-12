
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.mongodb.client.MongoDatabase;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;


public class indice{

    MongoClient mongoClient = new MongoClient();
    DB db = mongoClient.getDB("twitter6");
    DBCollection collection = db.getCollection("statusJSONImpl");
    DBCursor cursor = collection.find();


    public  void indexar(){
        try {
            Directory dir = FSDirectory.open(Paths.get("index/"));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            // Create a new index in the directory, removing any
            // previously indexed documents:
            iwc.setOpenMode(OpenMode.CREATE);
            //// Add new documents to an existing index: OpenMode.CREATE_OR_APPEND

            IndexWriter writer = new IndexWriter(dir, iwc);
            String ruta = "libros/";
            while(cursor.hasNext()) {

                Document doc = null;
                DBObject elemento = cursor.next();

                doc = new Document();
                // aca se deciden que elementos se quieren guardar en el indice
                // los  StringField son mas orientados a informacion
                // los textfield es lo que se  tokenizara
                doc.add(new StringField("id",elemento.get("_id").toString(),Field.Store.YES));
                doc.add(new TextField("text",elemento.get("text").toString(),Field.Store.YES));
                if(writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                    System.out.println("Indexando el archivo: "+elemento.get("_id")+"con texto"+elemento.get("text"));
                    writer.addDocument(doc);
                }
                else {
                    writer.updateDocument(new Term("text"+elemento.get("text")), doc);
                }



            }
            writer.close();
        }
        catch(IOException ioe) {
            //Logger.getLogger(BuscaSimple.class.getName()).log(Level.SEVERE, null, ioe);
        }


      public void buscar(String equipo){
            try {

                IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index/")));
                IndexSearcher searcher = new IndexSearcher(reader);
                Analyzer analyzer = new StandardAnalyzer();

                QueryParser parser = new QueryParser("text", analyzer);
                Query query = parser.parse(equipo);

                TopDocs results = searcher.search(query, 9999);
                ScoreDoc[] hits = results.scoreDocs;
                System.out.println(hits.length);
                for(int i = 0; i < hits.length; i++) {
                    Document doc = searcher.doc(hits[i].doc);
                    String id_salida = doc.get("id");
                    System.out.println(id_salida);
                }
                reader.close();
            }
            catch(IOException ioe) {
                Logger.getLogger(ProbandoB.class.getName()).log(Level.SEVERE, null, ioe);
            }






        }
    }







}