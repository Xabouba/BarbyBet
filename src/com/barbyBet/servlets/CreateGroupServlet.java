package com.barbyBet.servlets;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.ServletUtil;
import com.sun.media.jfxmedia.logging.Logger;

/**
 * Servlet implementation class CreateGroupServlet
 */
@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String VUE_CREATE_GROUP = "/WEB-INF/jsp/createGroup.jsp";
	private static final String VUE_INDEX    	 = "/WEB-INF/jsp/index.jsp";
    private static final String GROUP_SERVLET    = "/Barby_Bet/group";
    
    private User currentUser;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroupServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser.getId() == null) {
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
			
			this.getServletContext().getRequestDispatcher(VUE_CREATE_GROUP).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);

		if(currentUser.getId() == null){
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
	    }
		
		String actionType = request.getParameter("actionType");
		
		if(actionType != null) {
			if("look-for-group".equals(actionType)) {
				String groupName = request.getParameter("groupName");
				SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
				Long groupId = sqlGroupComponent.getGroupId(groupName);
				
				if(groupId != 0L) {
					Group g = sqlGroupComponent.getGroup(groupId);
					GroupServlet groupServlet = new GroupServlet();
					groupServlet.redirectToRightServlet(request, response, g);
				} else {
					request.setAttribute("lookForGroupMsg", "Ce nom de groupe n'existe pas");
					RankComponent rankComponent = new RankComponent();
					request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
					this.getServletContext().getRequestDispatcher(VUE_CREATE_GROUP).forward(request, response);
				}
			}
		} else {
			Object comingFromGroupServletDelete = request.getAttribute("comingFromGroupServletDelete");
			
			if(comingFromGroupServletDelete != null) {
				this.getServletContext().getRequestDispatcher(VUE_CREATE_GROUP).forward(request, response);
			} else {
				List<FileItem> items;
		        String groupName = null;        
		        String description = null;
		        String imageName = null;
				int status = 0;
		
				try {
					items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
			        for (FileItem item : items) {
			            if (item.isFormField()) {
			                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
			                String fieldName = item.getFieldName();
			                String fieldValue = item.getString();
			               
			                if("groupName".equals(fieldName)) {
			                	groupName = fieldValue;
			                	
			                	if("".equals(groupName.trim())){
			            			request.setAttribute("error", "Vous devez renseigner un nom de groupe");
			            			this.getServletContext().getRequestDispatcher(VUE_CREATE_GROUP).forward(request, response);
			            			return;
			            		}
			                	
			            		request.setAttribute("groupName", groupName.trim());
			                }
			                
			                if("groupDescription".equals(fieldName)) {
			                	description = fieldValue;
			                }
			                
				            // Group status : 0 for public / 1 for private
			                String statusStr = null;
			                if("status".equals(fieldName)) {
			                	statusStr = fieldValue;
			                	status = ServletUtil.getGroupStatusFromString(statusStr);
			                }
			            } else {
			                // Process form file field (input type="file").
			                String fieldName = item.getFieldName();
			                imageName = groupName + ".png";
		
			                if("group-pic".equals(fieldName)) {
			                	File groupPicsDirectory = new File(Constants.GROUP_PICS_ROOT_FOLDER);
			                	if(!groupPicsDirectory.exists()) {
			                		groupPicsDirectory.mkdirs();
			                	}
			                	item.write(new File(Constants.GROUP_PICS_ROOT_FOLDER + File.separator + imageName));
			                }
			            }
			        }
		        } catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// The date the group has been created
				Date creationDate = new Date();
				
				// The group only has 1 member when created which is the group creator
				List<User> members = new ArrayList<User>();
				members.add(currentUser);
				
				Group g = new Group(0L, groupName, status, description, currentUser.getId(), creationDate, members);
				g.setImg(imageName);
				
				// Insert the group in the database
				SQLGroupComponent gc = new SQLGroupComponent();
				String msg = gc.insertGroup(g, currentUser);
				
				if(msg != null){
					request.setAttribute("error", msg);
					RankComponent rankComponent = new RankComponent();
					request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
					
					this.getServletContext().getRequestDispatcher(VUE_CREATE_GROUP).forward(request, response);
					
					return;
				}
				
				request.setAttribute("groupId", g.getId());
				
				response.sendRedirect(GROUP_SERVLET);
			}
		}
	}
}
