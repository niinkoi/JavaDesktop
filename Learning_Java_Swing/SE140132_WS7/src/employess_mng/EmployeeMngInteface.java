/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employess_mng;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Khoi Nguyen
 */
public interface EmployeeMngInteface extends Remote {
    
    Vector getInitialData() throws RemoteException;
    
    boolean saveList(Vector data) throws RemoteException;
    
}
