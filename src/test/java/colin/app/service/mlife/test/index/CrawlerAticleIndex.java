package colin.app.service.mlife.test.index;

import colin.app.service.mlife.core.pojo.CrawlerAticle;
import colin.app.service.mlife.test.CommonTestConfig;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by joker on 16/9/8.
 */

public class CrawlerAticleIndex extends CommonTestConfig{
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String index_address="db/index";
    @Test
    public void buildCrawlerAticleIndex() throws IOException {
        String id="57c00f62a64c226610f391ba";
        Query aticleQuery=Query.query(Criteria.where("_id").is(id));
        List<CrawlerAticle> crawlerAticleList=mongoTemplate.find(aticleQuery, CrawlerAticle.class,"crawler_aticle");
        //建立索引
        Analyzer aticleAnalyzer=new SmartChineseAnalyzer(true);
        //索引本地文件存储
        Path path=Paths.get(index_address);
        path.toFile().mkdirs();
        Directory aticleIndexDir= FSDirectory.open(path);
        //创建IndexWriter，进行索引文件的写入
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(aticleAnalyzer);
        //写入索引
        IndexWriter indexWriter=new IndexWriter(aticleIndexDir,indexWriterConfig);
        //内容提取，进行索引的存储。
        Document doc=new Document();
        if(null!=crawlerAticleList&&!crawlerAticleList.isEmpty()){
            String content=crawlerAticleList.get(0).getAticleContent();
            StringField crawlerAticleField=new StringField("crawlerAticle",content, Field.Store.YES);
            doc.add(crawlerAticleField);
            indexWriter.addDocument(doc);
        }
        indexWriter.close();
    }
}
