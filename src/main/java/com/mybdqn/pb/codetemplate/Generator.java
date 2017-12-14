package com.mybdqn.pb.codetemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class Generator {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        TableHandler tableHandler = new TableHandler(null,null); // 设置表名前缀
        MVCHandler mvcHandler = new MVCHandler("/Users/chenliheng/Documents/工作/shiro/shiroCustomRealm/");	// 设置输出目录名
        mvcHandler.setPackageInfo("com.mybdqn");					// 设置源码包名

        List<Table> tableList = tableHandler.getTables();
        for (Table table:tableList) {
            mvcHandler.executeModel(table);
            mvcHandler.executeMapperClass(table);
            mvcHandler.executeMapper(table);
            mvcHandler.executeService(table);
            mvcHandler.executeConfigFile();
        }
    }
}
