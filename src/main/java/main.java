import dal.IUserDAO;
import dal.TransientDAO;
import dal.dto.IUserDTO;
import dal.dto.UserDTO;
import test.TransientUI;

public class main {
    public static void main(String[]args) throws IUserDAO.DALException {
        TransientUI t = new TransientUI();
        t.ChooseTorP();
    }
}
