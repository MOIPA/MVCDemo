package com.dql.dao;

import com.dql.dao.domain.User;
import com.dql.I18.AppText;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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
        if (dataAccessor == null) {
            dataAccessor = new DataAccessor();
        }
        return dataAccessor;
    }

    private void loadData() {
        boolean isIncompleteData = true;
        File dataFile = new File(AppText.DOC_LOCATION.getValue());
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            while ((line = reader.readLine()) != null) {
                String[] res = line.split(",", -1);
                if (res.length < 7) {
                    System.out.println(AppText.ERROR_LOG_PARSE_LINE.getValue() + line);
                    continue;
                }
                // 第一次加载残缺数据情况下的初始化
                if (res.length == 7) {
                    userList.add(new User(res[0], res[1], res[2], res[3], res[4], res[5], res[6], "", "", "", "", "", "", ""));
                }
                // 数据完整情况下 初始化
                if (res.length == 14) {
                    isIncompleteData = false;
                    userList.add(new User(res[0], res[1], res[2], res[3], res[4], res[5], res[6]
                            , res[7], res[8], res[9], res[10], res[11], res[12], res[13]));
                }
            }
            System.out.println(AppText.SUCCESS_LOG_PARSE_LINE.getValue() + userList.size());
            reader.close();
            // 数据需要清洗整理
            if (isIncompleteData) {
                cleanData();
            }
        } catch (FileNotFoundException e) {
            System.out.println(AppText.ERROR_LOG_NOT_FOUND.getValue());
        } catch (IOException e) {
            System.out.println(AppText.ERROR_LOG_IO.getValue());
        }
    }

    /**
     * 数据清洗 第一次加载残缺数据时调用
     */
    private void cleanData() {
        System.out.println("LOG: 数据清洗开始，原始数据量："+this.userList.size());
        // 去重
        List<User> cleanedUserList = new LinkedList<>();
        this.userList.forEach(x->{
            if (cleanedUserList.stream().filter(item -> {
                if ((item.getFirstName() + item.getLastName()).equals(x.getFirstName() + x.getLastName())) return true;
                return false;
            }).count() == 0) {
                cleanedUserList.add(x);
            }
        });
        // 赋值编号 抛弃原来的编号
        cleanedUserList.forEach(x->{
            x.setNumber(UUID.randomUUID().toString());
        });
        this.userList = cleanedUserList;
        System.out.println("LOG: 数据清洗结束，清洗后数据量："+this.userList.size());
        reWriteData();
    }

    /**
     * 数据回写
     */
    public void reWriteData() {
        System.out.println("LOG: 数据回写");
        File dataFile = new File(AppText.DOC_LOCATION.getValue());
        String line = "";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            this.userList.forEach(x->{
                try {
                    writer.write(x.toString());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
