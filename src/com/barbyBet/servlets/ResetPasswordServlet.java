package com.barbyBet.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.CipherUtils;
import com.barbyBet.tools.Constants;

/**
 * Servlet implementation class ResetPasswordServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		// If you are connected you get redirected to the index page
		if(currentUser.getId() != null) {
			response.sendRedirect(Constants.INDEX_SERVLET);
		} else {
			String key = request.getParameter("key");

			if(key != null) {
				SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
				Long userId = sqlUsersComponent.getUserIdFromEncryptedKey(key.toString());
				
				if(userId != -1L) {
					request.setAttribute("changePasswordMode", "yes");
					request.setAttribute("userId", userId);
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
				} else {
					response.sendRedirect(Constants.INDEX_SERVLET);
				}
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actionType");
		
		if(actionType != null) {
			if("changePassword".equals(actionType)) {
				String password = request.getParameter("password");
				String repeatPassword = request.getParameter("repeatPassword");
				
				if(password.equals(repeatPassword)) {
					String userIdStr = request.getParameter("userId");
					Long userId = Long.parseLong(userIdStr);
					
					SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
					boolean isPasswordUpdated = sqlUsersComponent.updatePassword(userId, password);
					
					if(isPasswordUpdated) {
						request.setAttribute("changePasswordMsg", "Le mot de passe a été mis à jour avec succès. Veuillez cliquer <a href=\"login\">ici</a> pour vous connecter");
					} else {
						request.setAttribute("changePasswordMsg", "Il y a eu une erreur lors de la mise à jour du mot de passe. Merci de réessayer ou de nous contacter à : <a href=\"mailto:contact@barbylone.com\">contact@barbylone.com</a>");
					}
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
				} else {
					request.setAttribute("changePasswordMsg", "Les deux mots de passes fournis ne sont pas identiques");
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
				}
			} else if("resetPassword".equals(actionType)) {
				String email = request.getParameter("email");
				
				SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
				Long userId = sqlUsersComponent.isEmailLinkedToUser(email);
				
				if(userId == -1L) {
					request.setAttribute("resetPasswordMsg", "Cet email n'est pas associé à un de nos utilisateurs. Merci de fournir votre email de connexion.");
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
				} else {
					// TODO : Send email
					String encryptedKey = encrypId(userId);
					StringBuilder sb = new StringBuilder("Bonjour,");
					sb.append("\n");
					sb.append("Pour réinitialiser le mot de passe de votre compte, merci de cliquer sur le lien suivant : ");
					sb.append("\n");
					sb.append("<a href=\"http://www.barbylone.com/reset?key=").append(encryptedKey).append("\">http://www.barbylone.com/reset?key=").append(encryptedKey).append("</a>");
					sb.append("\n");
					sb.append("Sportivement,");
					sb.append("L'équipe Barbylone");
					
					String content = sb.toString();
					sendEmail("contact@barbylone.com", email, "Réinitialisation de votre mot de passe sur barbylone.com", content);
					request.setAttribute("emailSent", email);
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
				}
			}
		}
		
	}

	private String encrypId(Long userId) {
		return CipherUtils.encrypt(CipherUtils.RESET_PASSWORD_KEY1, CipherUtils.RESET_PASSWORD_KEY2, String.valueOf(userId));
	}

	private void sendEmail(String from, String to, String subject, String content) {
		// server's smtp
		String host = "ssl0.ovh.net";
		
		  // Get system properties
		  Properties properties = System.getProperties();
		
		  // Setup mail server
		  properties.setProperty("mail.smtp.host", host);
		
		  // Get the default Session object.
		  Session session = Session.getDefaultInstance(properties);
		
		  try{
		     // Create a default MimeMessage object.
		     MimeMessage message = new MimeMessage(session);
		
		     // Set From: header field of the header.
		     message.setFrom(new InternetAddress(from));
		
		     // Set To: header field of the header.
		     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		     // Set Subject: header field
		     message.setSubject(subject);
		
		     // Now set the actual message
		     message.setText(content);
		
		     // Send message
		     Transport.send(message);
		     System.out.println("Sent message successfully....");
		  } catch (MessagingException mex) {
		     mex.printStackTrace();
		  }
	}
}
