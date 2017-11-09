package ${packageInfo}.pojo;

<#-- 类型import -->
<#list table.fieldList as field>
<#if (field.fieldType == "Date")>
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
</#if>
<#if (field.fieldType == "BigDecimal")>
import java.math.BigDecimal;
</#if>
</#list>

/**
 * Entity Class ${table.className}
 *
 * ${createDate?string("yyyy-MM-dd")}
 */
public class ${table.className} {

    <#-- 字段声明 -->
    <#list table.fieldList as field>
    <#if (field.fieldType == "Date")>@DateTimeFormat(pattern = "yyyy-MM-dd")</#if>
    private ${field.fieldType} ${field.fieldName};
    </#list>

    <#-- 构造方法 -->
    public ${table.className}(){
    }

    public ${table.className}(<#list table.fieldList as field>${field.fieldType} ${field.fieldName}<#if field_has_next>, </#if></#list>){
    <#list table.fieldList as field>
        this.${field.fieldName} = ${field.fieldName};
    </#list>
    }

    <#-- getter、setter访问器 -->
    <#list table.fieldList as field>

    public void set${field.fieldName?cap_first}(${field.fieldType} ${field.fieldName}){
        this.${field.fieldName} = ${field.fieldName};
    }

    public ${field.fieldType} get${field.fieldName?cap_first}(){
        return ${field.fieldName};
    }
    </#list>
}
