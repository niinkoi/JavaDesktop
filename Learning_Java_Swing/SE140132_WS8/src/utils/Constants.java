/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Khoi Nguyen
 */
public class Constants {
    
    public static class Message {
        public static final String SUCCESSFULLY = "successfully";
        public static final String FAILED = "failed";
    }
    
    public static class Query {
        
        public static String getByCode(String obj, String codeName) {
            return "SELECT * FROM " + obj + " where " + codeName + " = ?";
        }
        
        public static String saveQuery(String obj, int amount) {
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO ")
                 .append(obj)
                 .append(" VALUES (");
            
            for (int i = 1; i < amount; i++) {
                query.append("?,");
                if (i == amount - 1) {
                    query.append("?)");
                }
            }
            return query.toString();
        }
        
        private static final String[] ITEMS = {"itemName", "supCode", "unit", "price", "supplying"};
        private static final String[] SUPPLIERS = {"supName", "address", "colloborating"};
        
        public static String updateQuery(String obj, String codeName, int amount) {
            StringBuilder query = new StringBuilder();
            String[] fields;
            
            if (obj.equals("Items")) {
                fields = ITEMS;
            } else {
                fields = SUPPLIERS;
            }
            
            query.append("UPDATE ").append(obj).append(" SET ");
            for (int i = 0; i < amount; i++) {
                if (i == amount - 1) {
                        query.append(fields[i]).append(" = ?");
                } else {
                        query.append(fields[i]).append(" = ?, ");
                }    
             }   

            query.append(" WHERE ").append(codeName).append(" = ?");
            return query.toString();
        }
        
        public static String deleteQuery(String obj, String codeName) {
            return "DELETE FROM " + obj + " WHERE " + codeName + " = ?";
        }
        
        public static String getListQuery(String obj) {
            return "SELECT * FROM " + obj;
        }
    }
    
}
