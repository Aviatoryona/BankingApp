/*
 * Copyright (C) 2020 Aviator
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.banking.soap;

import com.banking.entities.Users;
import com.banking.models.MessageModel;
import com.banking.rest.UsersFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author Aviator
 */
@WebService(serviceName = "UsersFacadeSOAP")
@Stateless()
public class UsersFacadeSOAP {

    @EJB
    private UsersFacadeREST usersFacadeREST;

    @WebMethod(operationName = "getUser")
    public Users getUser(@WebParam(name = "id") int id) {
        return usersFacadeREST.getUser(id);
    }

    @WebMethod(operationName = "getUser_1")
    @RequestWrapper(className = "getUser_1")
    @ResponseWrapper(className = "getUser_1Response")
    public Users getUser(@WebParam(name = "email") String email) {
        return usersFacadeREST.getUser(email);
    }

    @WebMethod(operationName = "getUser_2")
    @RequestWrapper(className = "getUser_2")
    @ResponseWrapper(className = "getUser_2Response")
    public Users getUser(@WebParam(name = "username") String username, @WebParam(name = "pwd") String pwd) {
        return usersFacadeREST.getUser(username, pwd);
    }

    @WebMethod(operationName = "addUser")
    public MessageModel addUser(@WebParam(name = "users") Users users) {
        return usersFacadeREST.addUser(users);
    }

    @WebMethod(operationName = "removeUser")
    public MessageModel removeUser(@WebParam(name = "users") Users users) {
        return usersFacadeREST.removeUser(users);
    }

    @WebMethod(operationName = "getUsers")
    public List<Users> getUsers(@WebParam(name = "limit") int limit) {
        return usersFacadeREST.getUsers(limit);
    }

    @WebMethod(operationName = "updateUser")
    public MessageModel updateUser(@WebParam(name = "users") Users users) {
        return usersFacadeREST.updateUser(users);
    }

    @WebMethod(operationName = "changeUsername")
    public MessageModel changeUsername(@WebParam(name = "email") String email, @WebParam(name = "userName") String userName) {
        return usersFacadeREST.changeUsername(email, userName);
    }

    @WebMethod(operationName = "changePwd")
    public MessageModel changePwd(@WebParam(name = "email") String email, @WebParam(name = "pwd") String pwd) {
        return usersFacadeREST.changePwd(email, pwd);
    }

}
