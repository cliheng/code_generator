package com.mybdqn.pb.codetemplate;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenliheng on 2017/10/12.
 */
public class TableHandler {

    private static final String PROPERTIES_FILE_NAME = "db.properties";

    private List<Table> tables;

    public TableHandler() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        FileReader reader = new FileReader(Constant.properties_path + PROPERTIES_FILE_NAME);

        properties.load(reader);

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);
        Connection con  = DriverManager.getConnection(url, user, password);

        // 获取数据库中所有的表
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet rsTable =  metaData.getTables(null, "%", "%", new String[]{"TABLE"});

        tables = new ArrayList<Table>();
        while(rsTable.next()){
            Table table = new Table();
            tables.add(table);
            String tablename = rsTable.getString("TABLE_NAME");
            String classname = getClassName(tablename, null);
            table.setTableName(tablename);
            table.setClassName(classname);

            // 获取当前表的列
            ResultSet crs = metaData.getColumns(null, "%", tablename, "%");
            // 获取表中的主键列
            ResultSet pkrs = metaData.getPrimaryKeys(null, null, tablename);

            String pk = "";
            if(pkrs.next()){
                pk = pkrs.getString("COLUMN_NAME");
            }

            while (crs.next()) {
                Field field = new Field();
                table.getFieldList().add(field);
                String columnname = crs.getString("COLUMN_NAME");
                String columntype = crs.getString("TYPE_NAME");
                field.setColumnName(columnname);
                field.setFieldName(getFieldName(columnname, null));
                field.setFieldType(getFieldType(columntype));

                if(columnname.equals(pk)){
                    table.setPrimaryKeyField(field);
                }
            }
        }
    }

    /**
     * 根据表名获取类名称
     *
     * 适合表名为单个单词或使用下划线_分隔的表名， 例如：
     * 表名是TBLUSER 类名是User;
     * 表名是TBL_USER 表名前缀是TBL_ 类名是User;
     * 表名是USER_TYPE(两个单词)时，类名是UserType。
     *
     * @param tablename 表名
     * @param tablePrefix 表名前缀
     * @return
     */
    public String getClassName(String tablename, String tablePrefix) {
    	String tempName = getFieldName(tablename, tablePrefix);
        return tempName.substring(0, 1).toUpperCase() + tempName.substring(1);
    }

    /**
     * 根据列名获取属性名称
     *
     * 适合列名为单个单词或使用下划线_分隔的列名， 例如：
     * 列名是COLUSER 属性名是user;
     * 列名是COL_USER 列名前缀是COL_ 属性名是user;
     * 列名是USER_STATUS(两个单词)时，属性名是userStatus。
     *
     * @param columnName 列名
     * @param colPrefix 列名前缀
     * @return
     */
    public String getFieldName(String columnName, String colPrefix){
        String res = columnName.toLowerCase();
        // 消除表前缀
        if(colPrefix != null && res.startsWith(colPrefix.toLowerCase())) {
            res = res.substring(colPrefix.length());
        }
        String[] columnNames = res.split("_");
        int i = 0;
        StringBuffer buffer = new StringBuffer();
        while(columnNames[i].equals("")) i++;
        buffer.append(columnNames[i].substring(0, 1) + columnNames[i].substring(1));
        while(++i < columnNames.length) {
			buffer.append(columnNames[i].substring(0,1).toUpperCase() + columnNames[i].substring(1));
        }
        return buffer.toString();
    }

    /**
     * 设置字段类型 MySql数据类型
     *
     * @param columnType
     *            列类型字符串
     * @return
     */
    public static String getFieldType(String columnType) {

        columnType = columnType.toLowerCase();
        if (columnType.equals("varchar") || columnType.equals("nvarchar")
                || columnType.equals("char")
                || columnType.equals("tinytext")
                || columnType.equals("text")
                || columnType.equals("mediumtext")
                || columnType.equals("longtext")
                ) {
            return "String";
        } else if (columnType.equals("tinyblob")
                ||columnType.equals("blob")
                ||columnType.equals("mediumblob")
                ||columnType.equals("longblob")) {
            return "byte[]";
        } else if (columnType.equals("datetime")
                ||columnType.equals("date")
                ||columnType.equals("timestamp")
                ||columnType.equals("time")
                ||columnType.equals("year")) {
            return "Date";
        } else if (columnType.equals("bit")
                ||columnType.equals("int")
                ||columnType.equals("int unsigned")
                ||columnType.equals("tinyint")
                ||columnType.equals("smallint")
                ||columnType.equals("bool")
                ||columnType.equals("mediumint")
                ||columnType.equals("bigint")
                ) {
            return "Integer";
        } else if (columnType.equals("float")) {
            return "Float";
        } else if (columnType.equals("double")) {
            return "Double";
        } else if (columnType.equals("decimal")) {
            return "BigDecimal";
        }
        return "ErrorType";
    }

    public List<Table> getTables(){
        return this.tables;
    }

}
