/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import common.remote.AuthServer;
import common.remote.FormServer;
import server.remoteimpl.AuthServerImpl;
import server.remoteimpl.FormServerImpl;

/**
 *
 * @author ambrish
 */
public class ModuleServer {

    private static AuthServer authServer = null;
    private static FormServer formServer = null;

    public static AuthServer authServer() {
        if (authServer == null) {
            authServer = new AuthServerImpl();
        }
        return authServer;
    }

    public static FormServer formServer() {
        if (formServer == null) {
            formServer = new FormServerImpl();
        }
        return formServer;
    }

}
