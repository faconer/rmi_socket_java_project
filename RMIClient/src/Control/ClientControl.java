/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Model.Match;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Model.User; 
import Server.ServerInterface;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LENPOVO
 */
public class ClientControl {
    private String serverHost = "localhost";
    private int serverPort = 3232;
    private ServerInterface rmiServer;
    private Registry registry;
    private String rmiService = "rmiServer";
    
    public ClientControl()
    {
        try{
        // lay the dang ki
        registry = LocateRegistry.getRegistry(serverHost, serverPort);
        // tim kiem RMI server
        rmiServer = (ServerInterface)(registry.lookup(rmiService));
        }
        catch(RemoteException e){
        e.printStackTrace();
        }
        catch(NotBoundException e){
        e.printStackTrace();
        }
    }
    
    public String remoteCheckLogin(User user)
    {
        String result = null;
        try {
        result = rmiServer.checkLogin(user);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
        return result;
    }
    
    public int remoteRegistration(User user)
    {
        try {
           if ( rmiServer.Registration(user) == 1)
               return 1;
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void remoteImOnline(User user) 
    {
        try {
            rmiServer.ImOnline(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList remoteListOnline(User me)
    {
        ArrayList a = null;
        try {
            a=rmiServer.ListOnline(me);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public void remoteImOffline(User user) 
    {
        try {
            rmiServer.ImOffline(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int remoteCheckStatus(User user)
    {
        int status =0;
        try {
            status = rmiServer.CheckStatus(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public void remoteChalenge(User thachdau, User bithachdau)
    {
        try {
            rmiServer.Chalenge(thachdau, bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User remoteGetChalenge(User bithachdau)
    {
        User us = null;
        try {
            us = rmiServer.GetChalenge(bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
    
    public void remoteResponChalenge(User thachdau, User bithachdau)
    {
        try {
            rmiServer.ResponChalenge(thachdau, bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User remoteGetResponChalenge(User thachdau)
    {
        User us = null;
        try {
            us = rmiServer.GetResponChalenge(thachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
    
    public User remoteFindUser(User user)
    {
        User us = null;
        try {
            us = rmiServer.findUser(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
    
    public void remoteImInGate(User user) 
    {
        try {
            rmiServer.ImInGate(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList remoteGetTest(User me)
    {
        ArrayList a = null;
        try {
            a = rmiServer.GetTest(me);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public void remoteMakeTest(User thachdau, User bithachdau)
    {
        try {
            rmiServer.MakeTest(thachdau, bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remoteSendResult(Match match)
    {
        try {
            rmiServer.SendResult(match);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList remoteGetSeverResult(Match me)
    {
        ArrayList result = null;
        try {
            result = rmiServer.GetSeverResult(me);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void remoteContinueChalenge(User thachdau, User bithachdau)
    {
        try {
            rmiServer.ContinueChalenge(thachdau, bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User remoteContinueGetChalenge(User bithachdau)
    {
        User us = null;
        try {
            us = rmiServer.ContinueGetChalenge(bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
    
    public void remoteContinueResponChalenge(User thachdau, User bithachdau)
    {
        try {
            rmiServer.ContinueResponChalenge(thachdau, bithachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User remoteContinueGetResponChalenge(User thachdau)
    {
        User us = null;
        try {
            us = rmiServer.ContinueGetResponChalenge(thachdau);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
}
