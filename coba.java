/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner 1
 */
public class coba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseManager n = new DatabaseManager();
        try {
            n.login("145314019", "145314019");
            System.out.println("sukses");
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
    
}
