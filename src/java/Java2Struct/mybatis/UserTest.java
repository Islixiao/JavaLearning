//package Java2Struct.mybatis;
//
//import Java2Struct.mybatis.model.Person;
//import Java2Struct.mybatis.utils.MybatisUtil;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
///***Created by moyongzhuo
// *On 2018/3/20  ***17:58.
// ******/
//public class UserTest {
//
//    SqlSession sqlSession ;
//    @Test
//    public void insertPerson(){
//        sqlSession = MybatisUtil.getSqlSession();
//        int id = 10000;
//        String userName = "test";
//        int age = 18;
//        String mobilePhone = "18000000000";
//        Person person = new Person();
//        person.setId(id);
//        person.setAge(age);
//        person.setUserName(userName);
//        person.setMobilePhone(mobilePhone);
//        try{
//            sqlSession.insert("insertPerson",person);
//            sqlSession.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            MybatisUtil.closeSession(sqlSession);
//        }
//
//    }
//
//    @Test
//    public void queryById(){
//        sqlSession = MybatisUtil.getSqlSession();
//        int id = 1;
//        try{
//            Person person = sqlSession.selectOne("queryById",id);
//
//            sqlSession.commit();
//            System.out.println(person.getUserName());
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            MybatisUtil.closeSession(sqlSession);
//        }
//
//    }
//}
