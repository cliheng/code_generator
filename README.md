# code_generator
为北大青鸟学士后JAVA6.0机试考试准备的代码生成工具
2017/10/17

应用技术:freemarker2.3.26、maven、mysql

使用说明:
编辑Constant.java文件中的文件路径，分别指定
  properties_path=database.properties文件所在目录
  template_path=templates模板文件所在目录
  generator_code_path=生成代码文件所在目录

调用Generator类main方法生成源代码。其中
  mvcHandler.setPackageInfo("cn.bdqn"); // 设置源码文件所在父包名称
  
  mvcHandler.executeModel(table);       // 生成实体类
  mvcHandler.executeMapperClass(table); // 生成Mapper接口类
  mvcHandler.executeMapper(table);      // 生成Mapper.xml文件
  mvcHandler.executeService(table);     // 生成Service接口及实现子类
  mvcHandler.executeConfigFile();       // 生成对应配置文件
  
创建新的web项目，导入生成的源码，使用maven管理项目中jar文件的依赖项
pom.xml 关键内容截取
  <properties>
  	<spring-version>4.3.11.RELEASE</spring-version>
  </properties>
  <dependencies>
  	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>${spring-version}</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>${spring-version}</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-jdbc</artifactId>
	    <version>${spring-version}</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-tx</artifactId>
	    <version>${spring-version}</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-test</artifactId>
	    <version>${spring-version}</version>
	</dependency>
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.1.44</version>
	</dependency>
	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>jstl</artifactId>
	    <version>1.2</version>
	</dependency>
	<dependency>
	    <groupId>org.aspectj</groupId>
	    <artifactId>aspectjweaver</artifactId>
	    <version>1.8.10</version>
	</dependency>
	<dependency>
	    <groupId>org.mybatis</groupId>
	    <artifactId>mybatis</artifactId>
	    <version>3.4.1</version>
	</dependency>
	<dependency>
	    <groupId>org.mybatis</groupId>
	    <artifactId>mybatis-spring</artifactId>
	    <version>1.3.0</version>
	</dependency>
	<dependency>
	    <groupId>commons-dbcp</groupId>
	    <artifactId>commons-dbcp</artifactId>
	    <version>1.2.2</version>
	</dependency>
	<dependency>
	    <groupId>com.github.pagehelper</groupId>
	    <artifactId>pagehelper</artifactId>
	    <version>5.0.4</version>
	</dependency>
	<dependency>
		<groupId>junit</groupId>
	    <artifactId>junit</artifactId>
	    <version>4.12</version>
	    <scope>test</scope>
	</dependency>
  </dependencies>
