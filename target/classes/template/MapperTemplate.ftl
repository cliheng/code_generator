<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
       PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
       "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageInfo}.dao.${table.className}Mapper">

   <select id="get${table.className}ById" resultType="${packageInfo}.pojo.${table.className}">
       select
       <#list table.fieldList as field>
       ${field.columnName} as ${field.fieldName}<#if field_has_next>,</#if>
       </#list>
       from ${table.tableName}
       <trim prefix="where" prefixOverrides="and | or">
           <if test="id != null">
               and ${table.primaryKeyField.columnName}=${'#'}{id}
           </if>
       </trim>
   </select>

   <select id="get${table.className}ListByMap" resultType="${packageInfo}.pojo.${table.className}"
           parameterType="java.util.Map">
           select
           <#list table.fieldList as field>
           ${field.columnName} as ${field.fieldName}<#if field_has_next>,</#if>
           </#list>
           from ${table.tableName}
       <trim prefix="where" prefixOverrides="and | or">
           <#list table.fieldList as field>
           <if test="${field.columnName} != null<#if (field.fieldType == 'String')> and ${field.fieldName}!=''</#if>">
               and ${field.columnName} <#if (field.fieldType == 'String')>like<#else>=</#if> ${'#'}{${field.columnName}}
           </if>
           </#list>
       </trim>
   </select>

   <insert id="insert${table.className}" parameterType="${packageInfo}.pojo.${table.className}">
       insert into ${table.tableName}(
       <#list table.fieldList as field>
       ${field.columnName}<#if field_has_next>,</#if>
       </#list>)
       values(
       <#list table.fieldList as field>
       ${'#'}{${field.fieldName}}<#if field_has_next>,</#if>
       </#list>)
   </insert>

   <update id="update${table.className}" parameterType="${packageInfo}.pojo.${table.className}">
       update ${table.tableName}
       <trim prefix="set" suffixOverrides="," suffix="where ${table.primaryKeyField.columnName}=${'#'}{id}">
           <#list table.fieldList as field>
           <if test="${field.fieldName} != null<#if (field.fieldType == 'String')> and ${field.fieldName}!=''</#if>">
               ${field.columnName}=${'#'}{${field.fieldName}}<#if field_has_next>,</#if>
           </if>
           </#list>
       </trim>
   </update>

   <delete id="delete${table.className}ById" parameterType="${table.primaryKeyField.fieldType}">
       delete from ${table.tableName} where ${table.primaryKeyField.columnName} = ${'#'}{id}
   </delete>
</mapper>