import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class hdfsTest {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        //write
        Path path = new Path("/import/tmp/Wtest.txt");
        FSDataOutputStream fout = fs.create(path);
        byte[] bWrite = "hello hadoop distribute file system \n".getBytes();
        fout.write(bWrite); //写入字节数组
        fout.flush(); //flush提供了一种将缓冲区的数据强制刷新到文件系统的方法
        fout.close(); //关闭写出流

        fout = fs.append(path);
        fout.write("append: the append method of java API \n".getBytes());
        fout.close(); //关闭写出流

        //read
        FSDataInputStream fin = fs.open(path);
        byte[] buff = new byte[128];
        int len = 0 ;

        while( (len = fin.read(buff,0,128))  != -1 )
        {
            System.out.print(new String(buff,0,len));
        }

        //创建目录      
        if(fs.mkdirs(new Path("/import/test")))
        {
            System.out.println("mkdir /import/test success ");
        }

        //列出目录
        FileStatus[]  paths = fs.listStatus(new Path("/import"));
        for(int i = 0 ; i < paths.length ;++i)
        {
            System.out.println(paths[i].toString());
            System.out.println(paths[i].getLen());
            System.out.println(paths[i].isDirectory());
            System.out.println(paths[i].getPath().getParent());
            System.out.println(paths[i].getPath());
            System.out.println(paths[i].getPath().getName());
        }

        //删除
//        if(fs.delete(new Path("/import"), true))
//        {
//            System.out.println("delete directory /import ");
//        }

        fin.close();
        fs.close();
    }

}