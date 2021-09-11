/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employess_mng;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Khoi Nguyen
 */
public class ManagerServerProgram {
    
    public static void main(String[] args) {
        String serviceName = "rmi://127.0.0.1:1098/EmployeeService";
        String filename = "C:\\Users\\Khoi Nguyen\\OneDrive\\Documents\\NetBeansProjects\\BookStore\\SE140132_WS7\\employees.txt";
        
        EmployeeServer server = null;
        
        try {
            server = new EmployeeServer(filename);
            
            Registry registry = LocateRegistry.createRegistry(1098);
            Naming.rebind(serviceName, server);
            System.out.println("Service " + serviceName + " is running.");
            
        } catch (RemoteException | MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
