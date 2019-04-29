import dal.IUserDAO;
import ui.TUI;

public class main {
    public static void main(String[] args) throws IUserDAO.DALException {
        TUI t = new TUI();
        t.run();
    }
}
