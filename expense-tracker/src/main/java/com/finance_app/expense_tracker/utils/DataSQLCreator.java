package com.finance_app.expense_tracker.utils;

import com.finance_app.expense_tracker.core.entities.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.io.FileWriter;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Component
@Profile("test")
@Order(1)
public class DataSQLCreator implements CommandLineRunner {

    Log LOG = LogFactory.getLog(DataSQLCreator.class);

    private final JdbcTemplate jdbcTemplate; //data.sql desconsidered
    String backUpContent = ""; // data.sql

    List<Bank> banks = new ArrayList<>();
    List<Role> roles = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
    List<CreditCard> creditCards = new ArrayList<>();
    List<CreditCardBill> creditCardBills = new ArrayList<>();
    List<Installment> installments = new ArrayList<>();
    //List<Budget> budgets = new ArrayList<>(); // TODO
    List<Notification> notifications = new ArrayList<>();
    // TODO

    public DataSQLCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        String content = "";
        String fileName = "src/main/resources/data.sql";
        cleanSqlFile(fileName);

        banks = ObjectFactory.createBanks();
        roles = ObjectFactory.createRoles();
        users = ObjectFactory.createUsersWithRoles(roles);
        accounts = ObjectFactory.createFullAccounts(banks, users);
        creditCards = ObjectFactory.createCreditCards(accounts);
        creditCardBills = ObjectFactory.createCreditCardBills(creditCards);
        transactions = ObjectFactory.createTransactions(users, accounts, creditCardBills);
        installments = ObjectFactory.createInstallments(transactions);
        notifications = ObjectFactory.createNotifications(users);

        //insertBanks();
        LOG.info("Creating SQL file with insert statements...");
        insert(banks);
        //insertRoles();
        insert(roles);
        insertUser();
        insert(accounts);
        insert(creditCards);
        // TODO: inserting other classes

        backUpContent += "-- backup created at " + Instant.now().toString() + " --";
        createSqlFile(fileName, backUpContent);
    }

    private <T> void insert(List<T> lista) {
        String cont = "";
        for  (Object object : lista) {
            cont = createInsertSql(object);
            if (cont != null) {
                jdbcTemplate.execute(cont); //ou executa melhor no final de tudo??
                backUpContent += cont + ";\n";
            }
        }
        backUpContent += "\n";
    }

    private void insertUser(){
        String userContent = "";
        String userRoleContent = "";
        for  (User user : users) {
            userContent = createInsertSql(user);
            if (userContent != null) {
                jdbcTemplate.execute(userContent);
                backUpContent += userContent + ";\n";

                Map<String, String> userRoles = new HashMap<>();
                for (Role role : user.getRoles()) {
                    userRoles.put("roleID", role.getId().toString());
                    userRoles.put("userID", user.getId().toString());

                    userRoleContent = createInsertSqlMap("tb_user_role", userRoles);

                    if (userRoleContent != null) {
                        jdbcTemplate.execute(userRoleContent);
                        backUpContent += userRoleContent + ";\n";
                    }
                }
            }
            backUpContent += "\n";
        }

    }

    private String createInsertSqlMap(String table, Map<String, String> columnValues) {
        if (table == null || columnValues == null) return null;

        String sqlContent = "INSERT INTO " + table + "(";
        String sqlValues = "VALUES(";
        int count = 0;

        for (String column : columnValues.keySet()) {

            try {
                if (count != 0) {
                    sqlContent += ", ";
                    sqlValues += ", ";
                }

                sqlContent += toSnakeCase(column);
                sqlValues += "'" + columnValues.get(column) + "'";

                count++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        sqlContent += ") ";
        sqlValues += ")";

        return sqlContent + sqlValues;
    }

    private String createInsertSqlOld(Object object) {

        if (object == null) { return null;}
        String sqlContent = "INSERT INTO " + "tb_"+toSnakeCase(object.getClass().getSimpleName()) + "(";
        String sqlValues = "VALUES(";
        String value = "";
        int count = 0;

        Class<?> classe = object.getClass();
        for (Field field : classe.getDeclaredFields()) {

            field.setAccessible(true);

            if (Map.class.isAssignableFrom(field.getType()) || Collection.class.isAssignableFrom(field.getType())) continue;

            try {
                if (count != 0) {
                    sqlContent += ", ";
                    sqlValues += ", ";
                }
                sqlContent += toSnakeCase(field.getName());
                value = field.get(object).toString();

                if (field.getType().equals(String.class) || field.getType().equals(UUID.class) || field.getType().equals(Instant.class) || field.getType().equals(LocalDate.class)) {
                    sqlValues += "'" + value + "'";
                } else {
                    sqlValues += value;
                }

                count++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sqlContent += ") ";
        sqlValues += ")";

        return sqlContent + sqlValues;
    }

    public static Object getNestedFieldValue(Object obj, String fieldName, String nestedFieldName)
            throws Exception {

        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            Object nestedObject = field.get(obj);
            if (nestedObject == null) return null;

            Field nestedField = nestedObject.getClass().getDeclaredField(nestedFieldName);
            nestedField.setAccessible(true);

            return nestedField.get(nestedObject);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String createInsertSql(Object object) {

        if (object == null) { return null;}
        String sqlContent = "INSERT INTO " + "tb_" + toSnakeCase(object.getClass().getSimpleName()) + "(";
        String sqlValues = "VALUES(";
        String value = "null";
        int count = 0;

        Class<?> classe = object.getClass();

        for (Field field : classe.getDeclaredFields()) {

            field.setAccessible(true);

            if (Map.class.isAssignableFrom(field.getType()) || Collection.class.isAssignableFrom(field.getType())) continue;

            try {
                if (count != 0) {
                    sqlContent += ", ";
                    sqlValues += ", ";
                }
                count++;

                if(isCustom(classe.getDeclaredField(field.getName()).getType().getPackageName())) {
                    sqlContent += toSnakeCase(field.getName() + "_id"); //example: account_id, credit_card_id, etc
                    sqlValues += "'" + getNestedFieldValue(object, field.getName(), "id") + "'";
                    continue;
                }

                if (field.getName().equals("limit")) {
                    sqlContent += toSnakeCase(object.getClass().getSimpleName() + "_limit");
                } else {
                    sqlContent += toSnakeCase(field.getName());
                }
                value = String.valueOf(field.get(object));

                if (field.getType().equals(Instant.class) && value.equals("null")) {
                    sqlValues += "'" + String.valueOf(Instant.now()) + "'";;
                } else if ((field.getType().equals(String.class) || field.getType().equals(UUID.class) || field.getType().equals(Instant.class) || field.getType().equals(LocalDate.class))) {
                    sqlValues += "'" + value + "'";
                } else {
                    sqlValues += value;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        sqlContent += ") ";
        sqlValues += ")";

        return sqlContent + sqlValues;
    }

    private void cleanSqlFile(String fileName){
        try (FileWriter fileWriter = new java.io.FileWriter("src/main/resources/data.sql", false)) {
            fileWriter.write("");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        };
    }

    private void createSqlFile(String fileName, String content){
        try (FileWriter fileWriter = new java.io.FileWriter(fileName, true)) {
            fileWriter.write(content);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        };
    }

    private String toSnakeCase(String camelCase) {
        return camelCase
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }

    private boolean isCustom(String obj) {
        if (obj == null) return false;
        return obj.startsWith("com.finance_app.");
    }

}
