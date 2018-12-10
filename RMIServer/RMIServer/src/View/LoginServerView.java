/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Control.ServerControl;
import java.rmi.RemoteException;

/**
 *
 * @author LENPOVO
 */
public class LoginServerView 
{
    public LoginServerView() throws RemoteException
    {
        new ServerControl();  
        showMessage("RMI server is running...");
    }
    public void showMessage(String msg)
    {
        System.out.println(msg);
    }
}
