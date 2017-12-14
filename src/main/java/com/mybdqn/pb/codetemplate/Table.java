package com.mybdqn.pb.codetemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class Table {

    private String tableName;
    private String className;
    private List<Field> fieldList = new ArrayList<>();
    private Field primaryKeyField;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public Field getPrimaryKeyField() {
        return primaryKeyField;
    }

    public void setPrimaryKeyField(Field primaryKeyField) {
        this.primaryKeyField = primaryKeyField;
    }
}
