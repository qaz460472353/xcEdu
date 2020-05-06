package com.xuecheng.manage_cms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    //存文件
    @Test
    public void testStore() throws FileNotFoundException {
        //定义file
        File file =new File("d:/index_banner.ftl");
        //定义fileInputStream
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId = gridFsTemplate.store(fileInputStream, "index_banner.ftl");
        System.out.println(objectId);
    }

    //取文件
    @Test
    public void queryFile() throws IOException {
        //根据文件id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5b9cb02435794805b43b2b04")));

        //打开一个下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建GridFsResource对象，获取流
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        //从流中取数据
        String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
        System.out.println(content);

    }
    //删除文件
    @Test
    public void testDelFile()  throws  IOException  {
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("5b32480ed3a022164c4d2f92")));
    }

    /*
        @Test
        public void testGridFs() throws FileNotFoundException {
            //要存储的文件
            File file = new File("d:/index_banner.html");
            //定义输入流
            FileInputStream inputStram = new FileInputStream(file);
            //向GridFS存储文件
            ObjectId gridFSFile = gridFsTemplate.store(inputStram, "轮播图测试文件01", "");
            //得到文件ID
            String fileId = gridFSFile.toString();
            System.out.println(file);
        }
        @Test
        public void queryFile() throws IOException {
            String fileId = "5b32480ed3a022164c4d2f92";
            //根据文件id查询GridFS
            GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
            //得到输入流
            InputStream inputStream = gridFSDBFile.getInputStream();
            //定义输出流
            FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/index_banner01.html"));
            //写文件
            IOUtils.copy(inputStream,fileOutputStream);
        }*/

}
