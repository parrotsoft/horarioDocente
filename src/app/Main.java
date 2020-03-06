/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import vista.Splash;

/**
 *
 * @author estudiante
 */
public class Main {
    public static void main(String[] argv) {
        new Thread (new Splash()).start();
    }
}
