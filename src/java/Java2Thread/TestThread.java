package Java2Thread;

/***Created by moyongzhuo
 *On 2018/2/28  ***9:40.
 ******/
public class TestThread {

/*1111111111111111111111          rannable; start           */
//    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
//            new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    while (true) {
//
//                    }
//                }
//            }).start();
//        }
//        while(true){
//
//        }
//    }
/*22222222222222222         join                           */
//    public static void main(String[] args) {
//        Thread thread = new MyRunner3();
//        thread.start();
//        try {
//            //主线程等待thread的业务处理完了之后再向下运行
//            //thread和主线程合并了
//            thread.join();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        for(int i = 0; i < 100; i++){
//            System.out.println("main : " + i);
//        }
//    }
    /*   3333333333333333333333333      t.start           */
//public static void main(String args[]) throws IllegalThreadStateException {
//    MyThread m = new MyThread();  //实例化Runnable子类对象
//    Thread t = new Thread(m, "自定义线程");
//    System.out.println("线程执行前：" + t.isAlive());       //false
//    t.start();
//    //isAlive(),判断线程是否处于活动状态
//    System.out.println("线程启动之后：" + t.isAlive());  //true
//}
//}
/* 444444444444444               后台进程                 */
//    public static void main(String args[]) {
//        Thread t1 = new Thread(new MyThread_Priority(), "线程A");
//        Thread t2 = new Thread(new MyThread_Priority(), "线程B");
//        Thread t3 = new Thread(new MyThread_Priority(), "线程C");
////        t1.setPriority(Thread.MIN_PRIORITY);
////        t2.setPriority(Thread.MAX_PRIORITY);
////        t3.setPriority(Thread.NORM_PRIORITY);
//        t1.setPriority(2);
//        t2.setPriority(1);
//        t3.setPriority(3);
//        t1.start();
//        t2.start();
//        t3.start();
//    }
//}
    /*5555555555555555555    后台线程 setDaemon*/
//public static void main(String args[]){
//    MyThread_setDaemon mt = new MyThread_setDaemon() ;  // 实例化Runnable子类对象
//    Thread t = new Thread(mt,"线程");     // 实例化Thread对象
//    t.setDaemon(true) ; // 此线程在后台运行,去掉后会一直打印
//    t.start() ; // 启动线程
//}
//}
   /*666666666666666666     中断线程          */
public static void main(String args[]) {
            MyThread_Interrupt m = new MyThread_Interrupt();  //实例化Runnable子类对象
            Thread t = new Thread(m, "自定义线程");
            t.start();
            try {
                //sleep方法会出现异常
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            t.interrupt();
        }

}



/*2222222222222222222     sleep  */
class MyRunner3 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println("i am " + getName());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
/*3333333333333333333    Rannable接口 */
class MyThread implements Runnable {
    //实现Runnable接口
    public void run() {  //覆写run 方法
//currentThread().getName()取得当前正在运行的线程的名称        System.out.println(Thread.currentThread().getName() + "运行");
    }


}
/*4444444444444444444    优先级问题  */
class MyThread_Priority implements Runnable{
    //实现Runnable接口
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
//    线程的优先级用数字表示，范围从1到10，默认的是为5
//        每个线程默认的优先级与创建它的父线程的优先级相同
//    优先级越高的线程，被执行的顺序就比较靠前，在Thread中存在三个常量：MAX_PRIORITY、
//
//    MIN_PRIORITY、NORM_PRIORITY
}
/*5555555555555555555    后台线程 setDaemon*/
class MyThread_setDaemon implements Runnable{ // 实现Runnable接口
    public void run(){  // 覆写run()方法
        while(true){
            System.out.println(Thread.currentThread().getName() + "在运行。") ;
        }
    }
};
/*666666666666666666     中断线程 interrupt*/
class MyThread_Interrupt implements Runnable{
    //实现Runnable接口
    public void run() {  //覆写run 方法

        try {
            //sleep方法会出现异常
            System.out.println("1、进入run方法");
            Thread.sleep(6000);     //程序会暂停1000毫秒再执行
            System.out.println("2、已经完成了休眠");
        } catch (InterruptedException e) {
            System.out.println("3、休眠被终止！");
            return;     //返回方法调用处


        }
        System.out.println("4、run方法正常结束");
    }
}