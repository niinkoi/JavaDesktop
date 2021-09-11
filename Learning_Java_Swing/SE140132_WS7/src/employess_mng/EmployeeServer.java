/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employess_mng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Khoi Nguyen
 */
public class EmployeeServer extends UnicastRemoteObject
                            implements EmployeeMngInteface {
    
    String filename;

    public EmployeeServer(String filename) throws RemoteException {
        super();
        this.filename = filename;
    }
    
    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public Vector getInitialData() throws RemoteException {
        Vector data = new Vector(0);
        try (
                FileReader fileReader = new FileReader(filename);
                BufferedReader reader = new BufferedReader(fileReader);) {
            
            String line;
            StringTokenizer stringTokenizer;
//            String code, name;
//            int salary;
            
            while ((line = reader.readLine()) != null) {
                stringTokenizer = new StringTokenizer(line, ",");
                Vector vector = new Vector();
                
                vector.add(stringTokenizer.nextToken());
                vector.add(stringTokenizer.nextToken());
                vector.add(Integer.parseInt(stringTokenizer.nextToken()));
                
                data.add(vector);
            }
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

    @Override
    public boolean saveList(Vector data) throws RemoteException {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter writer = new PrintWriter(fileWriter);
            for (int i = 0; i < data.size(); i++) {
                Vector vector = ((Vector) (data.get(i)));
                
                String line = "";
                line += vector.get(0) + "," + vector.get(1) + "," + vector.get(2);
                System.out.println(vector.get(2) + line);
                writer.println(line);
            }
            writer.close();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
}
