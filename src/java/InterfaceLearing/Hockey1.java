package InterfaceLearing;

/***Created by moyongzhuo
 *On 2017/9/19  ***18:09.
 ******/
// 文件名: Hockey.java
public interface Hockey1 extends Sports
{
    public void homeGoalScored();
    public void visitingGoalScored();
    public void endOfPeriod(int period);
    public void overtimePeriod(int ot);
}
