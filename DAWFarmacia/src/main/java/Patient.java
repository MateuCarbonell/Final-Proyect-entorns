import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Person {

    public Patient() {
        super();
    }

    public Patient(String name, String mail) {
        super(name, mail);
    }

    
    public void load(String id) {
        String query = "SELECT * FROM patient WHERE mail='" + id + "';";
        BBDD bd = new BBDD();
        bd.conectar();
        ResultSet rs = bd.loadSelect(query);
        try {
            if (rs != null && rs.next()) {
                this.setMail(rs.getString("mail"));
                this.setName(rs.getString("name"));
             
            }
        } catch (SQLException e) {
            System.out.println("Error in Patient.load: " + e.getMessage());
        }
    }
    
}

