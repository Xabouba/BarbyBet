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

/**
 * Servlet implementation class CreateGroupServlet
 */
@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String VUE_CREATE_GROUP = "/WEB-INF/jsp/createGroup.jsp";
	private static final String VUE_GROUP = "/WEB-INF/jsp/group.jsp";
    
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
		
		if(!usersComponent.isCurrentUser(currentUser)) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
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

		if(!usersComponent.isCurrentUser(currentUser)){
			response.sendRedirect(Constants.LOGIN_SERVLET);
		}
		
		String actionType = request.getParameter("actionType");
		
		if(actionType != null) {
			if("look-for-group".equals(actionType)) {
				String groupName = request.getParameter("groupName");
				SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
				Long groupId = sqlGroupComponent.getGroupId(groupName);
				
				if(groupId != 0L) {
					Group g = sqlGroupComponent.getGroup(groupId);
					if(g.getStatus() == 1) {
						request.setAttribute("lookForGroupMsg", "Ce groupe est privé, vous n'avez pas le droit d'y accéder");
						doGet(request, response);
					} else {
						redirectToRightServlet(request, response, g);
					}
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
		
			                if("group-pic".equals(fieldName)) {
			                	if(item.getName().isEmpty()) {
			                		imageName = null;
			                	} else {
			                		imageName = groupName + ".png";
			                		File groupPicsDirectory = new File(Constants.GROUP_PICS_ROOT_FOLDER);
				                	if(!groupPicsDirectory.exists()) {
				                		groupPicsDirectory.mkdirs();
				                	}
				                	
				                	File groupImage = new File(Constants.GROUP_PICS_ROOT_FOLDER + File.separator + imageName);
				                	double imageSizeInMegaBytes = item.getSize() / 1024 / 1024;
				                	
				                	if(imageSizeInMegaBytes > 5) {
				                		request.setAttribute("error", "Nous n'acceptons pas d'images de plus de 5 Mo. Merci de réuploader une autre image de plus petite taille.");
				                		this.getServletContext().getRequestDispatcher(VUE_CREATE_GROUP).forward(request, response);
				                		return;
				                	}
				                	item.write(groupImage);
			                	}
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

				redirectToRightServlet(request, response, g);
			}
		}
	}
	
	public void redirectToRightServlet(HttpServletRequest request, HttpServletResponse response, Group group) throws ServletException, IOException {
		if(group == null || group.getId() == null) {
			// Remove all request attributes
			Enumeration<?> e = request.getAttributeNames();
			while(e.hasMoreElements()){
				String attName = (String)e.nextElement();
				
				if(Constants.COOKIE_CURRENT_USER_ID.equals(attName) || Constants.COOKIE_CURRENT_USER_EMAIL.equals(attName) || 
						Constants.COOKIE_CURRENT_USER_NAME.equals(attName) || 
						Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS.equals(attName)) {
					continue;
				} else {
					request.removeAttribute(attName);
				}
			}
			  
			response.sendRedirect(Constants.INDEX_SERVLET);
		} else {
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			List<Group> userGroups = sqlGroupComponent.getUserGroups(currentUser.getId());

			request.setAttribute("group", group.toHashMap());
			request.setAttribute("isUserInGroup", false);
			List<HashMap<String, String>> members = new ArrayList<HashMap<String, String>>();
			for (User u : group.getMembers()) {
				members.add(u.toHashMap());
				if(currentUser.getId() == u.getId()) {
					request.setAttribute("isUserInGroup", true);
				}
			}
			
			request.setAttribute("members", members);
			
			// Get the last five added users (the sorting should have been done in the "getGroup" function in SQLGroupComponent)
			List<HashMap<String, String>> lastFiveMembers = new ArrayList<HashMap<String, String>>();
			int max = group.getMembers().size();
			if(max > 5) {
				max = 5;
			}
			
			for(int i = 0; i < max; i++) {
				lastFiveMembers.add(group.getMembers().get(i).toHashMap());
			}
			
			request.setAttribute("lastFiveMembers", lastFiveMembers);
			
			if(userGroups != null) {
				// Gets the groups where the user is in
				List<HashMap<String, String>> userGroupList = new ArrayList<HashMap<String, String>>();
				for(Group userGroup : userGroups) {
					userGroupList.add(userGroup.toHashMap());
				}
				
				request.setAttribute("userGroupList", userGroupList);
			}
			
			if(group.getImg() != null) {
				request.setAttribute("groupImagePath", Constants.GROUP_PICS_FORMATED_ROOT_FOLDER + File.separator + group.getImg());
			} else {
				request.setAttribute("groupImagePath", "");
			}
			request.setAttribute("currentGroupId", group.getId());

			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(group.getId(), currentUser.getUsername()));
			
			/** Group */
			Map<Long, Map<String, String>> userGroupsToSend = sqlGroupComponent.getGroups(currentUser.getId());
			// TODO : Check if the group being look at is in the user's group list, otherwise add it to the ranking
			request.setAttribute("userGroups", userGroupsToSend);
			
			this.getServletContext().getRequestDispatcher(VUE_GROUP).forward(request, response);
		}
	}
}
