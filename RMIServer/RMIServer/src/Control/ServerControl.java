/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Model.Match;
import Model.Question;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Model.User;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Server.ServerInterface;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/**
 *
 * @author LENPOVO
 */
public class ServerControl extends UnicastRemoteObject implements ServerInterface{
    private int serverPort = 3232;
    private Registry registry;
    private Connection con;
    private String rmiService = "rmiServer";
    private ArrayList DSThachDau = null;
    private ArrayList DSBiThachDau = null;
    private ArrayList DSTLThachDau = null;
    private ArrayList DSTLBiThachDau = null;
    private ArrayList DSDeThi = null;
    private ArrayList CDSThachDau = null;
    private ArrayList CDSBiThachDau = null;
    private ArrayList CDSTLThachDau = null;
    private ArrayList CDSTLBiThachDau = null;
    public ServerControl() throws RemoteException
    {
        GetDBConnection getcon = new GetDBConnection();
        con = getcon.getConnection();
        DSThachDau = new ArrayList<Object>(); //Danh sach nhung nguoi dang thach dau nguoi khac
        DSBiThachDau = new ArrayList<Object>(); //Danh sach nhung nguoi dang bi nguoi khac thach dau
        DSTLThachDau = new ArrayList<Object>(); //Danh sach tra ve nguoi thach dau (tin hieu tu nguoi bi thach dau gui len sever de tra loi cho thang thach dau)
        DSTLBiThachDau = new ArrayList<Object>(); //Sever lay tin hieu vua roi gui ve cho thang thach dau la nguoi bi thach dau da dong y vao tran
        DSDeThi = new ArrayList<Object>(); //Danh sach de thi 
        CDSThachDau = new ArrayList<Object>(); //Chuc nang tuong tu nhung danh cho phan "dau tiep"
        CDSBiThachDau = new ArrayList<Object>();//Chuc nang tuong tu nhung danh cho phan "dau tiep"
        CDSTLThachDau = new ArrayList<Object>();//Chuc nang tuong tu nhung danh cho phan "dau tiep"
        CDSTLBiThachDau = new ArrayList<Object>();//Chuc nang tuong tu nhung danh cho phan "dau tiep"
        // dang ki RMI server
        try{
        registry = LocateRegistry.createRegistry(serverPort);
        registry.rebind(rmiService, this);
        }catch(RemoteException e){
        throw e;
        }
    }
    
    public String checkLogin(User user) throws RemoteException{
        String result = "";
        if(checkUser(user))
        result = "ok";
        return result;
    }
    
    private boolean checkUser(User user)  //Ham kiem tra uer co ton tai ko
    {
        String query = "Select * FROM Users WHERE account ='" + user.getUserName() + "' AND password ='" + user.getPassword() + "'";
        try {
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         if (rs.next()) {
          return true;
         }
        }catch(Exception e) {
         e.printStackTrace();
        } 
        return false;
    }

    @Override
    public int Registration(User user) throws RemoteException { //Ham dang ki tai khoan
        if(checkUser(user))
            return 0;
        else
        {
           String query = "INSERT INTO Users(account, password, name, status) VALUES(?, ?, ?, ?)"; //Lenh sql
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                ps.setInt(4, 0);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 1;
    }
        @Override
    public void ImOnline(User user) throws RemoteException { //Update trang thai la online sau khi dang nhap
        String query = "UPDATE Users SET status = 1 WHERE account ='" + user.getUserName() + "'" ;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList ListOnline(User me) throws RemoteException { //Ham tra ve danh sach nhung nguoi dang online
        Statement stmt;
        ArrayList<Object> a = new ArrayList<Object>();
        try {
            stmt = con.createStatement();
            ResultSet rs =null;
            rs = stmt.executeQuery("SELECT * FROM Users WHERE status !=0 AND account != '" + me.getUserName() + "'");
            while (rs.next())
                {
                    User user = new User(rs.getNString(1), rs.getNString(2));
                    user.setName(rs.getNString(3));
                    Object o = (Object) user;
                    a.add(o);
                }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public void ImOffline(User user) throws RemoteException { //Update trang thai thanh offline
        String query = "UPDATE Users SET status = 0 WHERE account ='" + user.getUserName() + "'" ;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Chalenge(User thachdau, User bithachdau) throws RemoteException {  
        DSThachDau.add((Object) thachdau);
        DSBiThachDau.add((Object) bithachdau);
    }

    @Override
    public int CheckStatus(User user) throws RemoteException {  //Kiem tra trang thai
        String query = "Select * FROM Users WHERE account ='" + user.getUserName() + "'";
        int status = 0;
        try {
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         if (rs.next())
         {
            status = rs.getInt(4);
         }
        }catch(Exception e) {
         e.printStackTrace();
        } 
        return status;
    }

    @Override
    public User GetChalenge(User bithachdau) throws RemoteException {  
        User dsbithachdau;
        User thachdau = null;
        int i=0;
        for (i = 0; i < DSBiThachDau.size(); i++) 
        {
            dsbithachdau = (User) DSBiThachDau.get(i);
            if (dsbithachdau.getUserName().equals(bithachdau.getUserName())==true) 
            {
                DSBiThachDau.remove(i);
                thachdau = (User) DSThachDau.get(i);
                DSThachDau.remove(i);
                break;
            }
        }
        return thachdau;
    }

    @Override
    public void ResponChalenge(User thachdau, User bithachdau) throws RemoteException {
        DSTLThachDau.add((Object) thachdau);
        DSTLBiThachDau.add((Object) bithachdau);
    }

    @Override
    public User GetResponChalenge(User thachdau) throws RemoteException {
        User user = null; //Nguoi thach dau
        User cpt = null; //Nguoi bi thach dau
        int i=0;
        for (i = 0; i < DSTLThachDau.size(); i++) 
        {
            user = (User) DSTLThachDau.get(i);
            if (user.getUserName().equals(thachdau.getUserName())==true)
            {
                cpt = (User) DSTLBiThachDau.get(i);
                break;
            }
        }
        for (i = 0; i < DSTLThachDau.size(); i++) 
        {
            user = (User) DSTLThachDau.get(i);
            if (user.getUserName().equals(thachdau.getUserName())==true)
            {
                DSTLThachDau.remove(i);
                DSTLBiThachDau.remove(i);
            }
        }
        return cpt;
    }

    @Override
    public User findUser(User user) throws RemoteException { 
        String query = "Select * FROM Users WHERE account ='" + user.getUserName() + "'";
        User  us = new User();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                us.setUserName(rs.getNString(1));
                us.setPassword(rs.getNString(2));
                us.setName(rs.getNString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }

    @Override
    public void ImInGate(User user) throws RemoteException {  //Update trang thai dang trong tran
        String query = "UPDATE Users SET status = 2 WHERE account ='" + user.getUserName() + "'" ;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList GetTest(User user) throws RemoteException {   //Lay de thi
        ArrayList a = new ArrayList();
        User us1 = null;
        User us2 = null;
        for (int i =0; i<DSDeThi.size(); i++)
        {
            a = (ArrayList) DSDeThi.get(i);
            us1 = (User) a.get(0);
            us2 = (User) a.get(1);
            if (user.getUserName().equals(us1.getUserName())==true || user.getUserName().equals(us2.getUserName())==true)
            {
                a.remove(0);
                a.remove(0);
                DSDeThi.remove(i);
                break;
            }
        }
        return a;
    }

    @Override
    public void MakeTest(User thachdau, User bithachdau) throws RemoteException {  //Tao de thi ngau nhien
        ArrayList test1 = new ArrayList<Object>(); //2 De thi giong nhau cho 2 client
        ArrayList test2 = new ArrayList<Object>();
        test1.add((Object) thachdau); //Moi de thi la 1 mang arraylist, phan tu dau la danh tinh client nhan de thi, 8 phan tu tiep theo la cau hoi, phan tu cuoi cung la id de thi
        test1.add((Object) bithachdau);
        test2.add((Object) thachdau);
        test2.add((Object) bithachdau);
        String query = "SELECT TOP 8 *FROM Question ORDER BY NEWID()"; //Lay 8 cau hoi ngau nhien
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                Question q = new Question();    //Nhap du lieu cho doi tuong Question la q
                q.setIdQuest(rs.getInt(1));
                q.setQuestion(rs.getNString(2));
                q.setAnswer1(rs.getNString(3));
                q.setAnswer2(rs.getNString(4));
                q.setTrueAnswer(rs.getNString(5));
                test1.add((Object) q);   //Tao q xong add vao 2 arraylist de thi
                test2.add((Object) q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id = CreateNewIdMatch(); //tao id cho de thi
        test1.add(id); //add them phan tu id vao de thi
        test2.add(id);
        DSDeThi.add((Object) test1);
        DSDeThi.add((Object) test2);
        CreateNewMatch(id, thachdau, bithachdau); //Tao 1 tran dau moi trong sql
    }

    public int CreateNewIdMatch()  //Tao id moi cho de thi
    {
        String query = "SELECT Max(idMatch) as idMax FROM Match";
        int lastId = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                lastId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId + 1;
    }
    
    public void CreateNewMatch(int id, User thachdau, User bithachdau)  //Tao tran dau moi
    {
       String query = "INSERT INTO Match(idMatch, user1, user2, datetime, time1, time2, point1, point2, wrong1, wrong2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, thachdau.getUserName());
                ps.setString(3, bithachdau.getUserName());
                ps.setString(4, java.time.LocalDateTime.now().toString());
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.setInt(8, 0);
                ps.setInt(9, 0);
                ps.setInt(10, 0);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    
    @Override
    public void SendResult(Match match) throws RemoteException {  //Gui ket qua tu 1 client
        String query = "SELECT user1, user2, point1 FROM Match WHERE idMatch = " + match.getIdMatch();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                if (rs.getNString(1).equals(match.getUser())==true)
                {
                    String query1 = "UPDATE Match SET time1 =  " + match.getTime() + ", wrong1 = "+ match.getWrong() + "WHERE idMatch = " + match.getIdMatch();
                    PreparedStatement ps = con.prepareStatement(query1);
                    ps.executeUpdate();
                }
                else
                {
                    if (rs.getNString(2).equals(match.getUser())==true)
                    {
                        String query2 = "UPDATE Match SET time2 =  " + match.getTime() + ", wrong2 = "+ match.getWrong() + "WHERE idMatch = " + match.getIdMatch();
                        PreparedStatement ps = con.prepareStatement(query2);
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList GetSeverResult(Match me) throws RemoteException {  //Server tra ve ket qua tog hop
        String query = "SELECT * FROM Match WHERE idMatch = " + me.getIdMatch();
        ArrayList result = new ArrayList<Object>();
        Match enemy = new Match();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                if (rs.getNString(2).equals(me.getUser())==true)
                {
                    if (rs.getInt(6)!=0)
                    {
                        enemy.setIdMatch(me.getIdMatch());
                        enemy.setUser(rs.getNString(3));
                        enemy.setTime(rs.getInt(6));
                        enemy.setWrong(rs.getInt(10));
                        if ((me.getWrong()==0&&enemy.getWrong()!=0)||(me.getWrong()==0&&enemy.getWrong()==0&&(me.getTime()<enemy.getTime()))) //neu thang
                        {
                            me.setPoint(10);
                            query = "UPDATE Match SET point1 = 10 WHERE idMatch ='" + me.getIdMatch() + "'" ;
                            PreparedStatement ps = con.prepareStatement(query);
                            ps.executeUpdate();
                        }
                        else
                        {
                            if ((me.getWrong()!=0&&enemy.getWrong()!=0)||(me.getWrong()==0&&enemy.getWrong()==0&&(me.getTime()==enemy.getTime()))) //neu hoa
                            {
                                me.setPoint(5);
                                enemy.setPoint(5);
                                query = "UPDATE Match SET point1 = 5, point2 = 5 WHERE idMatch ='" + me.getIdMatch() + "'" ;
                                PreparedStatement ps = con.prepareStatement(query);
                                ps.executeUpdate();
                            }
                            else
                            {
                                if ((me.getWrong()!=0&&enemy.getWrong()==0)||(me.getWrong()==0&&enemy.getWrong()==0&&(me.getTime()>enemy.getTime()))) //neu thua
                                {
                                    enemy.setPoint(10);
                                    query = "UPDATE Match SET point2 = 10 WHERE idMatch ='" + me.getIdMatch() + "'" ;
                                    PreparedStatement ps = con.prepareStatement(query);
                                    ps.executeUpdate();
                                }
                            }
                        }
                        result.add((Object) me);
                        result.add((Object) enemy);
                        return result;
                    }
                }
                else
                {
                    if (rs.getNString(3).equals(me.getUser())==true)
                    {
                        if (rs.getInt(5)!=0)
                        {
                            enemy.setIdMatch(me.getIdMatch());
                            enemy.setUser(rs.getNString(2));
                            enemy.setTime(rs.getInt(5));
                            enemy.setWrong(rs.getInt(9));
                            if ((me.getWrong()==0&&enemy.getWrong()!=0)||(me.getWrong()==0&&enemy.getWrong()==0&&(me.getTime()<enemy.getTime())))
                            {
                                me.setPoint(10);
                                query = "UPDATE Match SET point2 = 10 WHERE idMatch ='" + me.getIdMatch() + "'" ;
                                PreparedStatement ps = con.prepareStatement(query);
                                ps.executeUpdate();
                            }
                            else
                            {
                                if ((me.getWrong()!=0&&enemy.getWrong()!=0)||(me.getWrong()==0&&enemy.getWrong()==0&&(me.getTime()==enemy.getTime())))
                                {
                                    me.setPoint(5);
                                    enemy.setPoint(5);
                                    query = "UPDATE Match SET point1 = 5, point2 = 5 WHERE idMatch ='" + me.getIdMatch() + "'" ;
                                    PreparedStatement ps = con.prepareStatement(query);
                                    ps.executeUpdate();
                                }
                                else
                                {
                                    if ((me.getWrong()!=0&&enemy.getWrong()==0)||(me.getWrong()==0&&enemy.getWrong()==0&&(me.getTime()>enemy.getTime())))
                                    {
                                        enemy.setPoint(10);
                                        query = "UPDATE Match SET point1 = 10 WHERE idMatch ='" + me.getIdMatch() + "'" ;
                                        PreparedStatement ps = con.prepareStatement(query);
                                        ps.executeUpdate();
                                    }
                                }
                            }
                            result.add((Object) me);
                            result.add((Object) enemy);
                            return result;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void ContinueChalenge(User thachdau, User bithachdau) throws RemoteException { //dau tiep
        CDSThachDau.add((Object) thachdau);
        CDSBiThachDau.add((Object) bithachdau);
    }

    @Override
    public User ContinueGetChalenge(User bithachdau) throws RemoteException {
        User dsbithachdau;
        User thachdau = null;
        int i=0;
        for (i = 0; i < CDSBiThachDau.size(); i++) 
        {
            dsbithachdau = (User) CDSBiThachDau.get(i);
            if (dsbithachdau.getUserName().equals(bithachdau.getUserName())==true) 
            {
                CDSBiThachDau.remove(i);
                thachdau = (User) CDSThachDau.get(i);
                CDSThachDau.remove(i);
                break;
            }
        }
        return thachdau;
    }

    @Override
    public void ContinueResponChalenge(User thachdau, User bithachdau) throws RemoteException {
        CDSTLThachDau.add((Object) thachdau);
        CDSTLBiThachDau.add((Object) bithachdau);
    }

    @Override
    public User ContinueGetResponChalenge(User thachdau) throws RemoteException {
        User user = null; //Nguoi thach dau
        User cpt = null; //Nguoi bi thach dau
        int i=0;
        for (i = 0; i < CDSTLThachDau.size(); i++) 
        {
            user = (User) CDSTLThachDau.get(i);
            if (user.getUserName().equals(thachdau.getUserName())==true)
            {
                cpt = (User) CDSTLBiThachDau.get(i);
                break;
            }
        }
        for (i = 0; i < CDSTLThachDau.size(); i++) 
        {
            user = (User) CDSTLThachDau.get(i);
            if (user.getUserName().equals(thachdau.getUserName())==true)
            {
                CDSTLThachDau.remove(i);
                CDSTLBiThachDau.remove(i);
            }
        }
        return cpt;
    }
    

}
