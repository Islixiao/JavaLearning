package HttpStudy.RunOobHttp;

/***Created by moyongzhuo
 *On 2017/9/21  ***15:38.
 ******/
import java.net.*;
import java.io.*;
    public class URLConnectionDemo
    {
        public static void main(String [] args)
        {
            try
            {
                URL url = new URL("http://www.runoob.com");
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = null;
                if(urlConnection instanceof HttpURLConnection)//urlConnection是HttpURLConnection的一个实例，返回true
                {
                    connection = (HttpURLConnection) urlConnection;
                }
                else
                {
                    System.out.println("请输入 URL 地址");
                    return;
                }
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String urlString = "";
                String current;
                while((current = in.readLine()) != null)
                {
                    urlString += current;
                }
                System.out.println(urlString);
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
