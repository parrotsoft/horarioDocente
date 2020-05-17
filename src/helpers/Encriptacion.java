/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author miguel
 */
public class Encriptacion {

    public static String encriptaClave(String clave) {
        return DigestUtils.md5Hex(clave);
    }

    public static String desencriptaClave(String clave) {
        return "";
    }
    
}
