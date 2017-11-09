package ${packageInfo}.service;

<#if (table.primaryKeyField.fieldType == "BigDecimal")>
import java.math.BigDecimal;
</#if>
import ${packageInfo}.pojo.${table.className};
import java.util.List;
import java.util.Map;

/**
* Service Interface ${table.className}
*
* ${createDate?string("yyyy-MM-dd")}
*/
public interface ${table.className}Service {

    /**
     * 通过主键Id查询${table.className}
     */
    public ${table.className} get${table.className}ById(${table.primaryKeyField.fieldType} ${table.primaryKeyField.fieldName})throws Exception;

    /**
     * 通过条件Map集合查询${table.className}
     */
    public List<${table.className}> get${table.className}ListByMap(Map<String,Object> param)throws Exception;

	/**
	 * 通过条件Map集合查询${table.className}记录总数
	 */
	public Integer get${table.className}RecCountByMap(Map<String,Object> param)throws Exception;

    /**
     * 添加${table.className}新记录
     */
    public Integer insert${table.className}(${table.className} ${table.className?uncap_first})throws Exception;

    /**
      * 更新${table.className}记录
      */
    public Integer update${table.className}(${table.className} ${table.className?uncap_first})throws Exception;

    /**
      * 通过主键Id删除${table.className}
      */
    public Integer delete${table.className}ById(${table.primaryKeyField.fieldType} ${table.primaryKeyField.fieldName})throws Exception;

}