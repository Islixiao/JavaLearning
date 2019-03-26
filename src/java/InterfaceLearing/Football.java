package InterfaceLearing;

/***Created by moyongzhuo
 *On 2017/9/19  ***18:05.
 ******/
public interface Football  extends Sports{
    public void homeTeamScored(int points);
    public void visitingTeamScored(int points);
    public void endOfQuarter(int quarter);
}
