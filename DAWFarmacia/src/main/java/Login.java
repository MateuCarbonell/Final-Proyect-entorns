
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		Doctor d = new Doctor();
		d.setMail(mail);
		d.setPass(pass);
		System.out.println("mail: " + mail + " password: " + pass);
		try {
			d.login(mail, pass);
			if(d.getSession()>0) {
				response.getWriter().append(String.valueOf(d.getSession()));
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Credenciales inválidos");
			}
		}catch (Exception e) {
			 System.out.println("Error en Login.doGet" + e.getMessage());
             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
