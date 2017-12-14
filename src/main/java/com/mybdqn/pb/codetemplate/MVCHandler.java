package com.mybdqn.pb.codetemplate;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class MVCHandler {

    private String packageInfo;     // 项目包名
    private String generatorCodePath; // 代码生成目录

    private FreemarkerBase base;

    public MVCHandler(String generatorCodepath){
        base = new FreemarkerBase(Constant.template_path);
        
        this.generatorCodePath = generatorCodepath;
        
        if(generatorCodePath == null || generatorCodePath.equals("")){
        	throw new RuntimeException("请设置代表生成的目录");
        }
        
        File path = new File(generatorCodePath);
        if(!path.exists()){
        	path.mkdirs();
        }
    }

    /**
     * 生成项目配置文件
     */
    public void executeConfigFile(){
        Map<String,Object> modelDate = new HashMap<String, Object>();
        modelDate.put("packageInfo", packageInfo);

        String configpath = generatorCodePath + "/resources/";
        File configDir = new File(configpath);
        if(!configDir.exists()){
            configDir.mkdirs();
        }

        String springFileName = configpath + "spring-mybatis.xml";
        String springMVCFileName = configpath + "springmvc.xml";
        String webFileName = generatorCodePath + "/web.xml";
        try {
            BufferedWriter bws = new BufferedWriter(new FileWriter(springFileName));
            BufferedWriter bwmvc = new BufferedWriter(new FileWriter(springMVCFileName));
            BufferedWriter bww = new BufferedWriter(new FileWriter(webFileName));

            Template temps = base.config.getTemplate("SpringMybatisTemplate.ftl");
            Template tempmvc = base.config.getTemplate("SpringMVCTemplate.ftl");
            Template tempweb = base.config.getTemplate("WEBConfigTemplate.ftl");

            temps.process(modelDate, bws);
            tempmvc.process(modelDate, bwmvc);
            tempweb.process(modelDate, bww);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过表生成实体类
     * @param table
     */
    public void executeModel(Table table){
        if(table.getPrimaryKeyField() == null) {
            throw new RuntimeException("表" + table.getTableName() + "缺少主键约束，Service接口生成失败！");
        }
        String pojoPackage = packageInfo + ".pojo";
        String packages = makePackageDirectory(generatorCodePath, pojoPackage);

        Map<String,Object> modelDate = new HashMap<String, Object>();

        modelDate.put("table", table);
        modelDate.put("packageInfo", packageInfo);
        modelDate.put("createDate", new Date());

        String fileName = packages + table.getClassName() + ".java";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            Template temp = base.config.getTemplate("ModelTemplate.ftl");

            temp.process(modelDate, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过表生成Mapper接口
     * @param table
     */
    public void executeMapperClass(Table table){
        if(table.getPrimaryKeyField() == null) {
            throw new RuntimeException("表" + table.getTableName() + "缺少主键约束，Service接口生成失败！");
        }
        String pojoPackage = packageInfo + ".dao";
        String packages = makePackageDirectory(generatorCodePath, pojoPackage);

        Map<String,Object> modelDate = new HashMap<String, Object>();

        modelDate.put("table", table);
        modelDate.put("packageInfo", packageInfo);
        modelDate.put("createDate", new Date());

        String fileName = packages + table.getClassName() + "Mapper.java";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            Template temp = base.config.getTemplate("MapperClassTemplate.ftl");

            temp.process(modelDate, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过表生成业务接口
     * @param table
     */
    public void executeService(Table table){
        if(table.getPrimaryKeyField() == null) {
            throw new RuntimeException("表" + table.getTableName() + "缺少主键约束，Service接口生成失败！");
        }
        String servicePackage = packageInfo + ".service";
        String packages = makePackageDirectory(generatorCodePath,servicePackage);

        Map<String,Object> modelDate = new HashMap<String, Object>();

        modelDate.put("table", table);
        modelDate.put("packageInfo", packageInfo);
        modelDate.put("createDate", new Date());

        String servicefileName = packages + table.getClassName() + "Service.java";
        String serviceImplfileName = packages + table.getClassName() + "ServiceImpl.java";

        try {
            BufferedWriter bws = new BufferedWriter(new FileWriter(servicefileName));
            BufferedWriter bwsi = new BufferedWriter(new FileWriter(serviceImplfileName));

            Template temps = base.config.getTemplate("ServiceTemplate.ftl");
            Template tempsi = base.config.getTemplate("ServiceImplTemplate.ftl");

            temps.process(modelDate, bws);
            tempsi.process(modelDate, bwsi);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过表生成Mapper映射文件
     * @param table
     */
    public void executeMapper(Table table){
        if(table.getPrimaryKeyField() == null) {
            throw new RuntimeException("表" + table.getTableName() + "缺少主键约束，Service接口生成失败！");
        }
        String servicePackage = packageInfo + ".dao";
        String packages = makePackageDirectory(generatorCodePath,servicePackage);

        Map<String,Object> modelDate = new HashMap<String, Object>();

        modelDate.put("table", table);
        modelDate.put("packageInfo", packageInfo);

        String fileName = packages + table.getClassName() + "Mapper.xml";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            Template temp = base.config.getTemplate("MapperTemplate.ftl");

            temp.process(modelDate, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过指定的文件夹和包名创建对应的目录
     * @param savePath
     * @return
     */
    private String makePackageDirectory(String savePath, String packageInfo) {
        String packages = savePath +
                "/src/" + packageInfo.replace(".","/") + "/";
        File packageDir = new File(packages);
        if(!packageDir.exists()){
            packageDir.mkdirs();
        }
        return packages;
    }

    public String getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
    }

	public String getGeneratorCodePath() {
		return generatorCodePath;
	}

	public void setGeneratorCodePath(String generatorCodePath) {
		this.generatorCodePath = generatorCodePath;
	}
    
    
}
