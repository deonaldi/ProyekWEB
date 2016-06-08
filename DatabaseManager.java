/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS YO
 */
public class DatabaseManager {
    
    public Connection getConnection(){
       String host = "localhost";
       String port = "1521";
       String db = "orcl";
       String usr = "hr";
       String pwd = "hr";
              try{
           Class.forName("oracle.jdbc.driver.OracleDriver");}
       catch (ClassNotFoundException ex){
           System.out.println("Maaf driver class tidak ditemukan");
           System.out.println(ex.getMessage());}
              Connection conn = null;
       try{
           conn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+port+":"+db, usr, pwd);
       }
       catch (SQLException ex){
           System.out.println("Maaf koneksi tidak berhasil");
           System.out.println(ex.getMessage());
       }
       
       if (conn!=null) {
           System.out.println("Koneksi ke database terbentuk");
       }
       else{
           System.out.println("Koneksi gagal terbentuk");
       }
       return conn;
   }
    
    public Mahasiswa login(String username, String password) throws Exception{
       Mahasiswa m = new Mahasiswa();
       Connection conn = null;
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();   
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery("select * from mahasiswa where nim='" + username + "' and password='" + password + "'");
           
           while(rest.next()){
               
               m.setNim(rest.getString(1));
               m.setNama_mahasiswa(rest.getString(2));
               m.setTotalSKS(rest.getInt(4));
               m.setMakul(this.showMataKuliah(username));
           }}
              catch (SQLException ex){
           System.out.println(ex.getMessage());}
       finally{
           try{
               rest.close();
               stat.close();
               conn.close();}
                      catch (SQLException ex){
               System.out.println(ex.getMessage());
           }}
       
        if (m.getNim()==null) {
            throw new Exception("NIM atau Password salah");
        }
       return m;
    
    }
    
    public Admin loginAdmin(String username, String password) throws Exception{
       Admin a = new Admin();
       Connection conn = null;
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();   
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery("select user from admin where user='" + username + "' and password='" + password + "'");
           
           while(rest.next()){
                a.setNama(rest.getString(1));
           }}
              catch (SQLException ex){
           System.out.println(ex.getMessage());}
       finally{
           try{
               rest.close();
               stat.close();
               conn.close();}
                      catch (SQLException ex){
               System.out.println(ex.getMessage());
           }}
       
        if (a.getNama()==null) {
            throw new Exception("username atau Password salah");
        }
       return a;
    
    }

    public Mahasiswa[] showMahasiswa(){
       Connection conn = null;
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();
       Mahasiswa mhs[] = null;
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery("SELECT count(nim) FROM mahasiswa");
           rest.next();
           mhs = new Mahasiswa[rest.getInt(1)];
           rest = stat.executeQuery("SELECT NIM, NAMA, TOTAL_SKS FROM mahasiswa");
           int index =0;
           while(rest.next()){
               mhs[index] = new Mahasiswa();
               mhs[index].setNim(rest.getString(1));
               mhs[index].setNama_mahasiswa(rest.getString(2));
               mhs[index].setTotalSKS(rest.getInt(3));
               index++;               
           }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
           
       }
       finally{
           try{
               rest.close();
               stat.close();
               conn.close();
           }
           catch (SQLException ex){
               System.out.println(ex.getMessage());
           }
       }
   return mhs;
   }
    
    public MataKuliah[] showMataKuliah(String nim){
       Connection conn = null;
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();
       MataKuliah makul[] = null;
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery("SELECT COUNT (*) TOTAL FROM KULIAH JOIN MATA_KULIAH USING (kode) WHERE NIM=" + nim);
           rest.next();
           makul = new MataKuliah[rest.getInt(1)];
           rest = stat.executeQuery("SELECT kode, nama_makul, dosen, jumlah_sks FROM KULIAH JOIN MATA_KULIAH USING (kode) WHERE NIM=" + nim);
           int index =0;
           while(rest.next()){
               makul[index] = new MataKuliah();
               makul[index].setKode_matkul(rest.getString(1));
               makul[index].setNama_matkul(rest.getString(2));
               makul[index].setNama_dosen(rest.getString(3));
               makul[index].setJum_SKS(rest.getInt(4));
               index++;               
           }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
           
       }
       finally{
           try{
               rest.close();
               stat.close();
               conn.close();
           }
           catch (SQLException ex){
               System.out.println(ex.getMessage());
           }
       }
   return makul;
   }
    
    public MataKuliah[] showMataKuliah(){
       Connection conn = null;
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();
       MataKuliah makul[] = null;
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery("SELECT COUNT (*) TOTAL FROM MATA_KULIAH");
           rest.next();
           makul = new MataKuliah[rest.getInt(1)];
           rest = stat.executeQuery("SELECT kode, nama_makul, dosen, jumlah_sks FROM MATA_KULIAH");
           int index =0;
           while(rest.next()){
               makul[index] = new MataKuliah();
               makul[index].setKode_matkul(rest.getString(1));
               makul[index].setNama_matkul(rest.getString(2));
               makul[index].setNama_dosen(rest.getString(3));
               makul[index].setJum_SKS(rest.getInt(4));
               index++;               
           }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
           
       }
       finally{
           try{
               rest.close();
               stat.close();
               conn.close();
           }
           catch (SQLException ex){
               System.out.println(ex.getMessage());
           }
       }
   return makul;
   }
    
    public void tambahMakul(String nim, String kode){
       String text = null;
       Connection conn = null;
       PreparedStatement pstat = null;
       
       conn = this.getConnection();
   
       try{
           pstat = conn.prepareCall("INSERT INTO KULIAH VALUES(?,?)");
           pstat.setString(1, nim);
           pstat.setString(2, kode);
           pstat.executeUpdate();
           conn.commit();
           text = "Data sudah ditambahkan";
       }
       catch(SQLException ex){
//           throw new Exception(ex.getMessage());
       }
       finally{
           try{
               pstat.close();
               conn.close();}
           catch (SQLException ex){
//               throw new Exception(ex.getMessage());
           }
       }
       //return text;
   }
    
    public String hapusMakul(String nim, String kode) throws Exception{
       String text = null;
       Connection conn = null;
       PreparedStatement pstat = null;
       
       conn = this.getConnection();
   
       try{
           pstat = conn.prepareStatement("DELETE FROM KULIAH WHERE NIM=? AND KODE=?");
           pstat.setString(1, nim);
           pstat.setString(2, kode);
           pstat.executeUpdate();
           conn.commit();
       }
       catch(SQLException ex){
           throw new Exception(ex.getMessage());
       }
       finally{
           try{
               pstat.close();
               conn.close();}
           catch (SQLException ex){
               throw new Exception(ex.getMessage());
           }
       }
       return text;
   }
    
    public String updatePassword(Mahasiswa mhs, String passLama, String passBaru) throws Exception{
       String text = null;
       Connection conn = null;
       PreparedStatement pstat = null;
       
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();
       
       String nim = mhs.getNim();
       String nama = mhs.getNama_mahasiswa();
       String password = null;
       
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery("SELECT PASSWORD FROM MAHASISWA WHERE NIM=" + nim);
           rest.next();
           password = rest.getString(1);
       } 
       catch (SQLException ex){
           System.out.println(ex.getMessage());
           
       }


       if (password.compareTo(passLama)!=0) {
            throw new Exception("Password lama salah");
        }
       try{
           pstat = conn.prepareCall("UPDATE MAHASISWA SET PASSWORD=? WHERE NIM=?");
           pstat.setString(1, passBaru);
           pstat.setString(2, nim);
           pstat.executeUpdate();
           conn.commit();
           text = "Password sudah diganti";
       }
       catch(SQLException ex){
           throw new Exception(ex.getMessage());
           
       }
       finally{
           try{
               pstat.close();
               conn.close();
               
           }
           catch (SQLException ex){
               throw new Exception(ex.getMessage());
           }
       }
       return text;    
   }
    
    public Mahasiswa[] cariMahasiswa(String keyword, String collumn) throws Exception{
       Connection conn = null;
       Statement stat = null;
       ResultSet rest = null;
       conn = this.getConnection();
       Mahasiswa mhs[] = null;
       String statement = "SELECT count(nim) FROM mahasiswa WHERE LOWER("+collumn+") LIKE '%"+keyword+"%'";
       try{
           stat = conn.createStatement();
           rest = stat.executeQuery(statement);
           rest.next();
           int count = rest.getInt(1);
           if (count==0) {
               throw new Exception("Data tidak ditemukan");
           }
           mhs = new Mahasiswa[count];
           statement = "SELECT NIM, NAMA, TOTAL_SKS FROM mahasiswa WHERE LOWER("+collumn+") LIKE '%"+keyword+"%'";
           rest = stat.executeQuery(statement);
           int index =0;
           while(rest.next()){
               mhs[index] = new Mahasiswa();
               mhs[index].setNim(rest.getString(1));
               mhs[index].setNama_mahasiswa(rest.getString(2));
               mhs[index].setTotalSKS(rest.getInt(3));
               index++;               
           }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
           
       }
       finally{
           try{
               rest.close();
               stat.close();
               conn.close();
           }
           catch (SQLException ex){
               System.out.println(ex.getMessage());
           }
       }
   return mhs;
   }
}
