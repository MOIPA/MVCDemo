package com.dql.dao;

import com.dql.dao.domain.AccessLog;
import com.dql.dao.domain.User;
import com.dql.I18.AppEnum;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author dql
 */
public class DataAccessor {
    private static DataAccessor dataAccessor = null;
    private boolean isFamilyMember = false;
    private boolean isRegister = false;
    private String userId = "";
    private List<User> userList = null;
    private List<AccessLog> accessLogList = null;

    private DataAccessor() {
        userList = new LinkedList<>();
        accessLogList = new LinkedList<>();
        loadUserData();
        loadAccessLogData();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public boolean isFamilyMember() {
        return isFamilyMember;
    }

    public void setFamilyMember(boolean familyMember) {
        isFamilyMember = familyMember;
    }

    public List<User> getUserList() {
        return userList;
    }

    public boolean isExistByPhone(String phone) {
        return this.userList.stream().filter(x->x.getPhone().equals(phone.trim())).count()>0?true:false;
    }

    public static synchronized DataAccessor getInstance() {
        if (dataAccessor == null) {
            dataAccessor = new DataAccessor();
        }
        return dataAccessor;
    }

    /**
     * 加载用户数据
     */
    private void loadUserData() {
        boolean isIncompleteData = true;
        File dataFile = new File(AppEnum.DOC_LOCATION.getValue());
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            while ((line = reader.readLine()) != null) {
                String[] res = line.split(",", -1);
                if (res.length < 7) {
                    System.out.println(AppEnum.ERROR_LOG_PARSE_LINE.getValue() + line);
                    continue;
                }
                // 第一次加载残缺数据情况下的初始化
                if (res.length == 7) {
                    userList.add(new User(res[0], res[1], res[2], res[3], res[4], res[5], res[6], "", "", "", "", "", "", "", ""));
                }
                // 数据完整情况下 初始化
                if (res.length == 15) {
                    isIncompleteData = false;
                    userList.add(new User(res[0], res[1], res[2], res[3], res[4], res[5], res[6]
                            , res[7], res[8], res[9], res[10], res[11], res[12], res[13], res[14]));
                }
            }
            System.out.println(AppEnum.SUCCESS_LOG_PARSE_LINE.getValue() + userList.size());
            reader.close();
            // 数据需要清洗整理
            if (isIncompleteData) {
                cleanData();
            }
        } catch (FileNotFoundException e) {
            System.out.println(AppEnum.ERROR_LOG_NOT_FOUND.getValue());
        } catch (IOException e) {
            System.out.println(AppEnum.ERROR_LOG_IO.getValue());
        }
    }

    /**
     * 加载用户访问记录
     */
    private void loadAccessLogData() {
        System.out.println("LOG: init access log");
        File dataFile = new File(AppEnum.LOG_LOCATION.getValue());
        if (!dataFile.exists()) {
            try {
                System.out.println("LOG: create access log");
                dataFile.createNewFile();
                return;
            } catch (IOException e) {
                System.out.println("ERROR: create log file failed");
            }
        }
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            while ((line = reader.readLine()) != null) {
                String[] res = line.split(",", -1);
                if (res.length < 2) {
                    System.out.println(AppEnum.ERROR_LOG_PARSE_LINE.getValue() + line);
                    continue;
                }
                if (res.length == 2) {
                    this.accessLogList.add(new AccessLog(res[0], res[1]));
                }
            }
            System.out.println(AppEnum.SUCCESS_LOG_PARSE_LINE.getValue() + this.accessLogList.size());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(AppEnum.ERROR_LOG_NOT_FOUND.getValue());
        } catch (IOException e) {
            System.out.println(AppEnum.ERROR_LOG_IO.getValue());
        }
    }

    /**
     * 数据清洗 第一次加载残缺数据时调用
     */
    private void cleanData() {
        System.out.println("LOG: 数据清洗开始，原始数据量：" + this.userList.size());
        // 去重
        List<User> cleanedUserList = new LinkedList<>();
        this.userList.forEach(x -> {
            if (cleanedUserList.stream().filter(item -> {
                if ((item.getFirstName() + item.getLastName()).equals(x.getFirstName() + x.getLastName())) return true;
                return false;
            }).count() == 0) {
                cleanedUserList.add(x);
            }
        });
        // 赋值编号 抛弃原来的编号
        cleanedUserList.forEach(x -> {
            x.setNumber(UUID.randomUUID().toString());
        });
        this.userList = cleanedUserList;
        System.out.println("LOG: 数据清洗结束，清洗后数据量：" + this.userList.size());
        reWriteUserData();
    }

    /**
     * 用户数据回写
     */
    public void reWriteUserData() {
        System.out.println("LOG: 数据回写");
        File dataFile = new File(AppEnum.DOC_LOCATION.getValue());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            this.userList.forEach(x -> {
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
    /**
     * 访问记录数据回写
     */
    public void reWriteAccessLogData() {
        System.out.println("LOG: access data log write");
        File dataFile = new File(AppEnum.LOG_LOCATION.getValue());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            this.accessLogList.forEach(x -> {
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

    /**
     * 修改用户
     */
    public void updateUserById(String number, User updateUser) {
        User user = this.getUserById(number);
        user.setFirstName(updateUser.getFirstName());
        user.setAge(updateUser.getAge());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        user.setBirthDate(updateUser.getBirthDate());
        user.setAddress(updateUser.getAddress());
        user.setHealthCondition(updateUser.getHealthCondition());
        user.setAllergy(updateUser.getAllergy());
        user.setGender(updateUser.getGender());
        reWriteUserData();
    }

    /**
     * 修改用户会员信息
     *
     * @param number
     * @param type
     */
    public void updateUserMemberType(String number, String type) {
        this.userList.forEach(x -> {
            if (x.getNumber().equals(number)) {
                x.setMemberType(type);
            }
        });
        reWriteUserData();
    }

    /**
     * 删除用户
     */
    public void deleteUserById(String number) {
        this.userList = this.userList.stream().filter(x -> !x.getNumber().equals(number)).collect(Collectors.toList());
        reWriteUserData();
    }

    /**
     * 新增用户
     */
    public void addUser(User user) {
        this.userList.add(user);
        reWriteUserData();
    }

    /**
     * 获取用户
     */
    public User getUserById(String number) {
        Optional<User> first = this.userList.stream().filter(x -> x.getNumber().equals(number)).findFirst();
        if (first.isEmpty()) {
            System.out.println("ERROR: no user found");
        }
        return first.get();
    }

    /**
     * 通过手机获取用户
     */
    public User getUserByPhone(String phone) {
        Optional<User> first = this.userList.stream().filter(x -> x.getPhone().equals(phone)).findFirst();
        if (first.isEmpty()) {
            System.out.println("ERROR: no user found");
        }
        return first.get();
    }

    public void addLog(AccessLog accessLog) {
        this.accessLogList.add(accessLog);
    }

    public String getAccessNum() {
        String day = new SimpleDateFormat("yyy-MM-dd").format(new Date());
        long count = this.accessLogList.stream().filter(x -> x.getDate().equals(day)).count();
        return count+"";
    }
}
