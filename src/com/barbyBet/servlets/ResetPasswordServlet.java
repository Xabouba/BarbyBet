package com.barbyBet.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
			String urlSuffix = request.getQueryString();
			String key = null;
			
			if(urlSuffix != null) {
				key = urlSuffix.split("key=")[1];
			}
			
			if(key != null) {
				SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
				Long userId = sqlUsersComponent.getUserIdFromEncryptedKey(key.toString());
				
				if(userId != -1L) {
					request.setAttribute("changePasswordMode", "yes");
					request.setAttribute("key", key);
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
				
				String key = request.getParameter("key");
				SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
				Long userId = -1L;
				
				if(key != null) {
					userId = sqlUsersComponent.getUserIdFromEncryptedKey(key);
				}
				
				if(password.equals(repeatPassword)) {
					boolean isPasswordUpdated = sqlUsersComponent.updatePassword(userId, password);
					
					if(isPasswordUpdated) {
						request.setAttribute("changePasswordMode", "yes");
						request.setAttribute("key", key);
						request.setAttribute("changePasswordMsg", "Le mot de passe a été mis à jour avec succès. Veuillez cliquer <a href=\"login\">ici</a> pour vous connecter");
					} else {
						request.setAttribute("changePasswordMode", "yes");
						request.setAttribute("key", key);
						request.setAttribute("changePasswordMsg", "Il y a eu une erreur lors de la mise à jour du mot de passe. Merci de réessayer ou de nous contacter à : <a href=\"mailto:contact@barbylone.com\">contact@barbylone.com</a>");
					}
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp").forward(request, response);
				} else {
					request.setAttribute("changePasswordMode", "yes");
					request.setAttribute("key", key);
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
					String encryptedKey = encrypId(userId);
					StringBuilder sb = new StringBuilder("Bonjour,");
					sb.append("<br />");
					sb.append("<br />");
					sb.append("Pour réinitialiser le mot de passe de votre compte, merci de cliquer sur le lien suivant : ");
					sb.append("<br />");
					sb.append("<a href=\"http://www.barbylone.com/reset?key=").append(encryptedKey).append("\">http://www.barbylone.com/reset?key=").append(encryptedKey).append("</a>");
					sb.append("<br />");
					sb.append("Sportivement,");
					sb.append("<br />");
					sb.append("<br />");
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
		final String username = "contact@barbylone.com";
        final String password = "malikloickader";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "ssl0.ovh.net");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
            
            System.out.println("Done !");
        } catch (MessagingException e) {
        	System.out.println(e);
            throw new RuntimeException(e);
        }
	}
}

                                            