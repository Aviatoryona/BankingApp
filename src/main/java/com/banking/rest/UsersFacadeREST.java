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
package com.banking.rest;

import com.banking.entities.Users;
import com.banking.interfaces.UsersLogicI;
import com.banking.models.MessageModel;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Aviator
 */
@Stateless
@Path("/users")
public class UsersFacadeREST {

    @EJB
    private UsersLogicI usersLogicI;

    @GET
    @Path(value = "/findById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users getUser(@PathParam(value = "id") int id) {
        return usersLogicI.getUser(id);
    }

    @GET
    @Path(value = "/findByEmail/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users getUser(@PathParam(value = "email") String email) {
        return usersLogicI.getUser(email);
    }

    @POST
    @Path(value = "/getUser/{email}/{pwd}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users getUser(@PathParam(value = "email") String username, @PathParam(value = "pwd") String pwd) {
        return usersLogicI.getUser(username, pwd);
    }

    @POST
    @Path(value = "/addUser")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel addUser(Users users) {
        return usersLogicI.addUser(users);
    }

    public MessageModel removeUser(Users users) {
        return usersLogicI.removeUser(users);
    }

    @GET
    @Path(value = "/getUsers/{limit}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Users> getUsers(@PathParam(value = "limit") int limit) {
        return usersLogicI.getUsers(limit);
    }

    public MessageModel updateUser(Users users) {
        return usersLogicI.updateUser(users);
    }

    @POST
    @Path(value = "/changeUsername/{email}/{userName}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel changeUsername(@PathParam(value = "email") String email, @PathParam(value = "userName") String userName) {
        return usersLogicI.changeUsername(email, userName);
    }

    @POST
    @Path(value = "/changePassword/{email}/{pwd}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel changePwd(@PathParam(value = "email") String email, @PathParam(value = "pwd") String pwd) {
        return usersLogicI.changePwd(email, pwd);
    }

}
