package mongodb;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MongoSimpleTest {
    Mongo mongo;
    DB db;
    DBCollection collection;
    DBCollection testuser, books;
    BasicDBObject user1, user2, book;
    MapReduceCommand cmd;
    WriteResult writeResult;

    public void mongoinit() throws UnknownHostException {
        try {
            mongo = new Mongo("192.168.2.130", 27017);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        //获取temp DB；如果默认没有创建，mongodb会自动创建
        db = mongo.getDB("testdb");
        //获取users DBCollection；如果默认没有创建，mongodb会自动创建
        testuser = db.getCollection("testuser");
    }
    @RequestMapping("/mongofind.do")
    @ResponseBody
    public String find() throws UnknownHostException {
        mongoinit();
        StringBuffer result = new StringBuffer();
        DBCursor cur = testuser.find();
        try {
            while (cur.hasNext()) {
                result.append("<br>" + cur.next());
            }
        } finally {
            cur.close();
        }
        mongo.close();
        return result.toString();
    }

    @RequestMapping("/mongosave.do")
    @ResponseBody
    public String save() throws UnknownHostException {
        mongoinit();
        user1 = new BasicDBObject();
        user1.put("name", "testuser_save");
        user1.put("age", 25);
        writeResult = testuser.save(user1);
        //	mongo.close();
        //	return "mongo-save  :"+writeResult.getN();
        StringBuffer result = new StringBuffer();
        DBCursor cur = testuser.find(new BasicDBObject("name", "testuser_save"));
        try {
            while (cur.hasNext()) {
                result.append("<br>" + cur.next());
            }
        } finally {
            cur.close();
        }
        mongo.close();
        return result.toString();
    }

    @RequestMapping("/mongoinsert.do")
    @ResponseBody
    public String insert() throws UnknownHostException {
        mongoinit();
        user2 = new BasicDBObject();
        user2.put("name", "testuser_insert");
        user2.put("age", 55);
        List<DBObject> list = new ArrayList<DBObject>();
        list.add(user2);
        writeResult = testuser.insert(list);
        // return "mongo-insert  :"+writeResult.getN();
        StringBuffer result = new StringBuffer();
        DBCursor cur = testuser.find(new BasicDBObject("name", "testuser_insert"));
        try {
            while (cur.hasNext()) {
                result.append("<br>" + cur.next());
            }
        } finally {
            cur.close();
        }
        mongo.close();
        return result.toString();
    }

    @RequestMapping("/mongoupdate.do")
    @ResponseBody
    public String update() throws UnknownHostException {
        mongoinit();
        user1 = new BasicDBObject();
        user1.put("name", "testuser_update");
        user1.put("age", 25);
        writeResult = testuser.update(new BasicDBObject("_id", new ObjectId("56403f52f3a7e2b6c11f9290")), new BasicDBObject("name", "testuser_update"));
        //mongo.close();
        //return "mongo-update  :"+writeResult.getN();
        StringBuffer result = new StringBuffer();
        DBCursor cur = testuser.find(new BasicDBObject("name", "testuser_update"));
        try {
            while (cur.hasNext()) {
                result.append("<br>" + cur.next());
            }
        } finally {
            cur.close();
        }
        mongo.close();
        return result.toString();
    }

    @RequestMapping("/mongoremove.do")
    @ResponseBody
    public String remove() throws UnknownHostException {
        mongoinit();
        writeResult = testuser.remove(new BasicDBObject("name", "testuser1"));
        //MongoDB（>=）大于等于操作符 - $gte
        mongo.close();
        return "mongo-remove  ";
    }

    @RequestMapping("/mongofindAndmodify.do")
    @ResponseBody
    public String findAndMondify() throws UnknownHostException {
        mongoinit();
        testuser.findAndModify(new BasicDBObject("age", 25), new BasicDBObject("name", "findAndMondify"));
        StringBuffer result = new StringBuffer();
        DBCursor cur = testuser.find(new BasicDBObject("name", "findAndMondify"));
        try {
            while (cur.hasNext()) {
                result.append("<br>" + cur.next());
            }
        } finally {
            cur.close();
        }
        mongo.close();
        return result.toString();
    }

    @RequestMapping("/mongomapreduce.do")
    @ResponseBody
    public String script() throws UnknownHostException {
        mongoinit();
        try {
            books = db.getCollection("books");

            book = new BasicDBObject();
            book.put("name", "Understanding JAVA");
            book.put("pages", 100);
            books.insert(book);

            book = new BasicDBObject();
            book.put("name", "Understanding JSON");
            book.put("pages", 200);
            books.insert(book);

            book = new BasicDBObject();
            book.put("name", "Understanding XML");
            book.put("pages", 300);
            books.insert(book);

            book = new BasicDBObject();
            book.put("name", "Understanding Web Services");
            book.put("pages", 400);
            books.insert(book);

            book = new BasicDBObject();
            book.put("name", "Understanding Axis2");
            book.put("pages", 150);
            books.insert(book);
            String map = "function() { " +
                    "var category; " +
                    "if ( this.pages >= 250 ) " +
                    "category = 'Big Books'; " +
                    "else " +
                    "category = 'Small Books'; " +
                    "emit(category, {name: this.name});}";

            String reduce = "function(key, values) { " +
                    "var sum = 0; " +
                    "values.forEach(function(doc) { " +
                    "sum += 1; " +
                    "}); " +
                    "return {books: sum};} ";

            cmd = new MapReduceCommand(books, map, reduce,
                    null, MapReduceCommand.OutputType.INLINE, null);


        } catch (Exception e) {
            e.printStackTrace();
        }

        MapReduceOutput out = books.mapReduce(cmd);
        StringBuffer result = new StringBuffer();

        for (DBObject o : out.results()) {
            result.append(o.toString());
        }
        return result.toString();
    }

}
