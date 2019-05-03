package dal;

import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOCDIO3 implements IUserDAO {
    private Connection createConnection() throws SQLException {
        return  DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185118?"
                + "user=s185118&password=SNX64wUCCqEHKNVwEwumg");
    }

    @Override
    public void createUser(IUserDTO user){
        try (Connection c = createConnection()) {

            PreparedStatement statement = c.prepareStatement(
                            "INSERT INTO cdio3_users (userName, ini) VALUES (?, ?);");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getIni());
            statement.executeUpdate();

            for (int n=0 ; n < user.getRoles().size() ; n++) {
                statement = c.prepareStatement(
                        "INSERT INTO cdio3_jobs (userID, job) VALUES (LAST_INSERT_ID(), ?);");
                statement.setString(1, user.getRoles().get(n));
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IUserDTO getUser(int userId) throws DALException {

       try (Connection c = createConnection()){
           PreparedStatement statement = c.prepareStatement(
                   "SELECT * FROM cdio3_users NATURAL JOIN cdio3_jobs WHERE userID = ?;");
           statement.setInt(1, userId);
           ResultSet resultSet = statement.executeQuery();

           IUserDTO user = new UserDTO();

           if(resultSet.next())
               user = createUserDTO(resultSet);

           //TODONE: Make a user from the resultset
           return user;
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }



    @Override
    public List<IUserDTO> getUserList() throws DALException {

        try (Connection c = createConnection()){
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM cdio3_users NATURAL JOIN cdio3_jobs ORDER BY userID;");
            ResultSet resultSet = statement.executeQuery();

            List<IUserDTO> userList = new ArrayList<>();

            while(resultSet.next()){
                userList.add(createUserDTO(resultSet));
            }

            return userList;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }



    @Override
    public void updateUser(IUserDTO user){

        try (Connection c = createConnection()) {
            PreparedStatement statement = c.prepareStatement(
                    "UPDATE cdio3_users SET userName = ?, ini = ? WHERE userID = ?;");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getIni());
            statement.setInt(3, user.getUserId());
            statement.executeUpdate();

            statement = c.prepareStatement(
                    "DELETE FROM cdio3_jobs WHERE userID = ?;");
            statement.setInt(1, user.getUserId());
            statement.executeUpdate();

            for (int n=0 ; n < user.getRoles().size() ; n++) {
                statement = c.prepareStatement(
                        "INSERT INTO cdio3_jobs (userID, job) VALUES (?, ?);");
                statement.setInt(1, user.getUserId());
                statement.setString(2, user.getRoles().get(n));
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId){

        try (Connection c = createConnection()) {
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM cdio3_users WHERE userID = ?;");
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserDTO createUserDTO(ResultSet resultSet) throws SQLException {
        UserDTO newUser = new UserDTO();

        int newUserID = resultSet.getInt("userID");
        newUser.setUserId(newUserID);

        String userName = resultSet.getString("userName");
        newUser.setUserName(userName);

        String ini = resultSet.getString("ini");
        newUser.setIni(ini);

        List<String> jobArray = new ArrayList<>();
        String job;

        while(!resultSet.isAfterLast() && (resultSet.getInt("userID") == newUserID)){
            job = resultSet.getString("job");
            jobArray.add(job);

            resultSet.next();
        }
        resultSet.previous(); // metoden starter med resultSet.next(), så hvis den køres flere gange i træk skal cursoren en gang tilbage her.

        newUser.setRoles(jobArray);

        return newUser;
    }
}
