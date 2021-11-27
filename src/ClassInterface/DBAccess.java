package ClassInterface;

import java.sql.*;
import Authentication.Authentication;

public class DBAccess {
    private Authentication auth;

    public DBAccess(Authentication auth1){
        this.auth = auth1;
    }

    private PreparedStatement usePreparedstmt(String[] vals){
        PreparedStatement sql = auth.getPS();
        try{
            for(int i = 0; i<vals.length;i++){
                sql.setString(i+1, vals[i]);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sql;
        }

    private String setter(int n, String[] c, String[] v){
        String resultString= "";
        for(int i=0;i<n-1;i++){
            resultString+=c[i]+"="+v[i]+",";
        }
        resultString+=c[n]+"="+v[n];
        return resultString;
    }
    
    public void update(String table, String columns, String values, int id) {
        int sized = columns.split(",").length;
        String resultString = setter(sized,columns.split(","),values.split(","));
        String[] localList = {table,resultString};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            sql.setInt(3, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void create(String table, String columns, String values) {
        int sized = columns.split(",").length;
        String resultString = setter(sized,columns.split(","),values.split(","));
        String[] localList = {table,resultString};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public ResultSet viewAll(String table, String columns) {
        ResultSet result = null;
        String[] localList = {columns,table};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            result = sql.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
        
    public ResultSet viewSpecific(String table, String columns, String criteria) {
        ResultSet result = null;
        String[] localList = {columns,table,criteria};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            result = sql.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}