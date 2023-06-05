
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Servlet implementation class ServeMedicines
 */
@WebServlet("/DAWFarmacia/ServeMedicines")
public class ServeMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeMedicines() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        ArrayList<String> listaMedicinas = new ArrayList<>();
        BBDD bd = new BBDD();
        bd.conectar();
        
        
    	try {
        	System.out.println("Se están cargando las medicinas");

    		String query = "SELECT id,name FROM medicine";
        	ResultSet rs = bd.loadSelect(query);
			while(rs.next()) {
				String idMedicine = rs.getString("id");
				String nombreMedicina = rs.getString("name");
				
				listaMedicinas.add(nombreMedicina);
				listaMedicinas.add(idMedicine);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONArray arrayJSONMedicine = new JSONArray(listaMedicinas);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(arrayJSONMedicine.toString());
        response.getWriter().flush();
    }
        
        
        

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
