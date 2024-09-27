package projects;
import java.sql.Connection;
import java.sql.SQLException;
import projects.dao.DbConnection;

public class ProjectsApp {

    public static void main(String[] args) {
            DbConnection.getConnection();
    }
}
