package com.mybdqn.pb.codetemplate;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class Field {

    private String columnName;          // 数据表列名
    private String fieldName;           // 实体属性名
    private String fieldType;           // 实体属性java类型

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
