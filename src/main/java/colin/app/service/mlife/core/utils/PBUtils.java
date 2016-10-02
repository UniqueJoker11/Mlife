package colin.app.service.mlife.core.utils;

import colin.app.service.mlife.core.pb.PersonPB;

import java.io.*;

/**
 * Created by joker on 16/10/2.
 */
public class PBUtils {
    public static void main(String[] args) {
        PersonPB.Person.Builder personBuilder= PersonPB.Person.newBuilder();
        personBuilder.setEmail("9191@qq.com").setName("joker").setId(2);
        PersonPB.Person person=personBuilder.build();
        File pbFile=new File("pb/record.txt");
        if(!pbFile.exists()){
            try {
                pbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream pbFileOut=new FileOutputStream(pbFile);
            pbFileOut.write(person.toByteArray());
            System.out.println("写入文件成功");
            FileInputStream pbFileIn=new FileInputStream(pbFile);
            byte[] content=new byte[pbFileIn.available()];
            int index=0;
            StringBuilder conStr=new StringBuilder();
            while((index=pbFileIn.read(content))!=-1){
                conStr.append(new String(content,"utf-8"));
            }
            System.out.println("读取文件完毕,文件内容是"+conStr.toString()+",文件大小是"+content.length);
            PersonPB.Person person2= PersonPB.Person.parseFrom(content);
            System.out.println("反解析完数据的姓名是"+person2.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
