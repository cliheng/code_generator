package ${packageInfo}.dao;

<#if (table.primaryKeyField.fieldType == "BigDecimal")>
import java.math.BigDecimal;
</#if>
import ${packageInfo}.pojo.${table.className};
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
* Mapper Interface ${table.className}
*
* ${createDate?string("yyyy-MM-dd")}
*/
public interface ${table.className}Mapper {

    public ${table.className} get${table.className}ById(@Param(value = "id") ${table.primaryKeyField.fieldType} ${table.primaryKeyField.fieldName})throws Exception;

    public List<${table.className}> get${table.className}ListByMap(Map<String,Object> param)throws Exception;

	public Integer get${table.className}RecordCount(Map<String,Object> param)throws Exception;

    public Integer insert${table.className}(${table.className} ${table.className?uncap_first})throws Exception;

    public Integer update${table.className}(${table.className} ${table.className?uncap_first})throws Exception;

    public Integer delete${table.className}ById(@Param(value = "id") ${table.primaryKeyField.fieldType} ${table.primaryKeyField.fieldName})throws Exception;

}