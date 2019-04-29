import dal.IUserDAO;
import test.TUI;

public class main {
    public static void main(String[] args) throws IUserDAO.DALException {
        TUI t = new TUI();
        t.run();
    }
}
