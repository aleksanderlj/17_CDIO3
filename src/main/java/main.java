import dal.IUserDAO;
import test.TransientUI;

public class main {
    public static void main(String[]args) throws IUserDAO.DALException {
        TransientUI t = new TransientUI();
        t.ChooseTorP();
    }
}
