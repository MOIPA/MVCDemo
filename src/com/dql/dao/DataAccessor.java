package com.dql.dao;

import com.dql.dao.domain.User;
import com.dql.scheme.AppText;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author dql
 */
public class DataAccessor {
    private static DataAccessor dataAccessor = null;
    private List<User> userList = null;

    private DataAccessor() {
        userList = new LinkedList<>();
        loadData();
    }

    public List<User> getUserList() {
        return userList;
    }

    public static synchronized DataAccessor getInstance() {
        if (dataAccessor == null){
            dataAccessor = new DataAccessor();
        }
        return dataAccessor;
    }

    private void loadData() {
        File dataFile = new File(AppText.DOC_LOCATION.getValue());
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            while ((line = reader.readLine()) != null) {
                String[] res = line.split(",",-1);
                if (res.length<7){
                    System.out.println(AppText.ERROR_LOG_PARSE_LINE.getValue()+line);
                    continue;
                }
                userList.add(new User(res[0], res[1], res[2], res[3], res[4], res[5], res[6]));
            }
            System.out.println(AppText.SUCCESS_LOG_PARSE_LINE.getValue()+userList.size());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(AppText.ERROR_LOG_NOT_FOUND.getValue());
        } catch (IOException e) {
            System.out.println(AppText.ERROR_LOG_IO.getValue());
        }
    }
}
