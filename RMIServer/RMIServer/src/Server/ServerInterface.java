/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import Model.Match;
import java.rmi.Remote;
import java.rmi.RemoteException;
import Model.User;
import java.util.ArrayList;
/**
 *
 * @author LENPOVO
 */
public interface ServerInterface extends Remote{
    public String checkLogin(User user) throws RemoteException; //Kiem tra dang nhap
    public User findUser(User user) throws RemoteException;     //Tim nguoi dung, tra ra nguoi dung
    public int Registration(User user) throws RemoteException;  //Dang ki
    public void ImOnline(User user) throws RemoteException;     //Tao trang thai online khi nguoi dung online
    public void ImOffline(User user) throws RemoteException;    //Tao trang thai offline khi nguoi dung tat
    public ArrayList ListOnline(User me) throws RemoteException; //Danh sach nhung nguoi online
    public void Chalenge(User thachdau, User bithachdau) throws RemoteException; //Gui thach dau
    public int CheckStatus(User user) throws RemoteException; //Kiem tra trang thai nguoi dung
    public User GetChalenge(User bithachdau) throws RemoteException; //Lay loi thach dau
    public void ResponChalenge(User thachdau, User bithachdau) throws RemoteException; //Tra loi loi thach dau cua doi thu
    public User GetResponChalenge(User thachdau) throws RemoteException; //Nhan cau tra loi loi thach dau
    public void ImInGate(User user) throws RemoteException; //Tao trang thai trong tran
    public ArrayList GetTest(User user) throws RemoteException; //Lay de thi
    public void MakeTest(User thachdau, User bithachdau) throws RemoteException; //Tao de thi
    public void SendResult(Match match) throws RemoteException; //Gui ket qua vua thi
    public ArrayList GetSeverResult(Match match) throws RemoteException; //Nhan ket qua tu sever
    public void ContinueChalenge(User thachdau, User bithachdau) throws RemoteException; //Thach dau tiep
    public User ContinueGetChalenge(User bithachdau) throws RemoteException; //Tiep tuc nhan loi thach dau tiep
    public void ContinueResponChalenge(User thachdau, User bithachdau) throws RemoteException; //Tra loi loi thach dau tiep
    public User ContinueGetResponChalenge(User thachdau) throws RemoteException; //Nhan tra loi loi thach dau tiep
}
