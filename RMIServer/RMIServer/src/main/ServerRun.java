/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import View.LoginServerView;
import java.rmi.RemoteException;

/**
 *
 * @author LENPOVO
 */
public class ServerRun {
    public static void main(String[] args) throws RemoteException {
    LoginServerView view = new LoginServerView();
}
}
