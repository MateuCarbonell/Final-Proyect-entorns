
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServeXips*/
@WebServlet("/DAWFarmacia/ServeXips")
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeXips() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        
        Doctor doctor = new Doctor();
        boolean isLogged = doctor.isLogged(mail, session);
        
        if (isLogged) {
            doctor.load(mail);

            String altesTable = doctor.getTable();

            response.setContentType("text/html");
            response.getWriter().write(altesTable);
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("Doctor not logged in");
        }
    }
}
