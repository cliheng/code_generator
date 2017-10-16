<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    <context:property-placeholder location="classpath:db.properties"/>
    <!-- datasource -->
    <bean id="datasource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="${'$'}{driver}" />
        <property name="url" value="${'$'}{url}" />
        <property name="username" value="${'$'}{user}" />
        <property name="password" value="${'$'}{password}" />
    </bean>
    <!-- sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
        <property name="typeAliasesPackage" value="${packageInfo}.pojo" />
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <property name="properties">
                        <value>
                            helperDialect=mysql
                            supportMethodsArguments=true
                            params=pageNum=start;pageSize=size;
                        </value>
                    </property>
                </bean>
            </array>
        </property>
    </bean>
    <!-- MyBatis MapperScanner -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="${packageInfo}.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>
    <!-- transaction manager -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="datasource" />
    </bean>
    <!-- annotation transaction driven -->
    <tx:annotation-driven transaction-manager="transactionManager"/>
    <!-- component scanner -->
    <context:component-scan base-package="${packageInfo}.service" />
</beans>