/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;

public class CRUD_23090101_D_2025 {
    public static void main(String[] args) {
        String URL = "mongodb://localhost:27017/";
        MongoClient client = MongoClients.create(URL);
        MongoDatabase db = client.getDatabase("uts_23090101_D_2025");
        MongoCollection<Document> tabel = db.getCollection("coll_23090101_D_2025");
        
        //insert data (create)
        Document data1 = new Document("name","Ranifa Fitriyana") 
                .append("email", "ranifa@gmail.com")
                .append("mataKuliah", Arrays.asList("Pemrograman Komputer", "Pemrograman Web", "Basis Data"))
                .append("status", "Mahasiswa aktif");
        tabel.insertOne(data1); //proses input data
        
        Document data2 = new Document("name","Salwa Eka") 
                .append("email", "salwa@gmail.com")
                .append("mataKuliah", Arrays.asList("Pemrograman Komputer", "Pemrograman Web", "Basis Data"))
                .append("status", "Mahasiswa tidak aktif");
        tabel.insertOne(data2); //proses input data
        
        Document data3 = new Document("name","Tri Afni") 
                .append("email", "afni@gmail.com")
                .append("mataKuliah", Arrays.asList("Pemrograman Komputer", "Pemrograman Web", "Basis Data"))
                .append("status", "Mahasiswa aktif");
        tabel.insertOne(data3); //proses input data
        
        //view data (read)
        System.out.println("==========Before Update==========");
        FindIterable<Document> result = tabel.find(); //mencari data untuk di tampilkan
        for (Document d : result) {
              System.out.println(d.toJson());
              System.out.println("Nama mahasiswa: "+d.get("name")); //untuk menampilkan nama mahasiswa
              System.out.println("Status: "+d.get("status")); //untuk menampilkan status mahasiswa
        }
        
        //update data
        Bson filter = Filters.eq("name", "Ranifa Fitriyana"); // nama mahasiswa yang ingin di update
        Bson update = Updates.set("nim", 23090101); // data yang ingin di tambahkan / di update
        tabel.updateOne(filter, update); // mengupdate data
        
        //view setelah update
        System.out.println("==========After Update==========");
        result = tabel.find(); //mengembalikan data
        for (Document d : result) {
              System.out.println(d.toJson()); // menampilkan data
              System.out.println("Nama Mahasiswa: "+d.get("name"));
              System.out.println("NIM: "+d.get("nim"));
              System.out.println("Status: "+d.get("status"));
        }
        
        // delete data (hapus)
        System.out.println("==========Delete Data==========");
        Bson dataTarget = Filters.eq("nim", 23090101); //diisi dengan data yang ingin di hapus
        tabel.deleteOne(dataTarget); // menghapus data

        // searching data
        System.out.println("==========Search Data==========");
        Bson find = Filters.eq("nim", 23090101); // data yang ingin dicari 
        Document d = tabel.find(find).first(); // mencari data pada tabel document
        if (d != null) {
            System.out.println(d.toJson()); //menampilkan data yang ditemukan
            System.out.println("Nama Mahasiswa: "+d.get("name"));
            System.out.println("NIM: "+d.get("nim"));
            System.out.println("Status: "+d.get("status"));
        } else {
            System.out.println("Data not found"); //jika data tidak ditemukan
        }    
    }
}
