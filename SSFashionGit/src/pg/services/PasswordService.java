/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg.services;

import java.util.List;

import pg.exception.UserBlockedException;
import pg.model.login;
import pg.model.menu;
import pg.model.module;
import pg.model.password;
import pg.model.wareinfo;

public interface PasswordService {

	public List<login> login(String loginName,String password) throws UserBlockedException;
	public List<wareinfo> getAllStoreName();
	public List<module> getAllModuleName();
	public List<menu> getAllMenuName();

	public List<module> getUserModule(int i);
	public List<menu> getUserMenu(int i,int moduleId);
	public List<menu> getAdminUserMenu(int i,int moduleId);
}
