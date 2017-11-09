package ${packageInfo}.service;

<#if (table.primaryKeyField.fieldType == "BigDecimal")>
import java.math.BigDecimal;
</#if>
import ${packageInfo}.pojo.${table.className};
import ${packageInfo}.dao.${table.className}Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Service Class ${table.className}
*
* ${createDate?string("yyyy-MM-dd")}
*/

@Service
@Transactional
public class ${table.className}ServiceImpl implements ${table.className}Service {

    @Resource
    private ${table.className}Mapper ${table.className?uncap_first}Mapper;

    @Transactional(readOnly = true)
    public ${table.className} get${table.className}ById(${table.primaryKeyField.fieldType} ${table.primaryKeyField.fieldName})throws Exception{
        return ${table.className?uncap_first}Mapper.get${table.className}ById(${table.primaryKeyField.fieldName});
    }

    @Transactional(readOnly = true)
    public List<${table.className}> get${table.className}ListByMap(Map<String,Object> param)throws Exception {
        return ${table.className?uncap_first}Mapper.get${table.className}ListByMap(param);
    }
    
    @Transactional(readOnly = true)
    public Integer get${table.className}RecCountByMap(Map<String,Object> param)throws Exception {
    	return ${table.className?uncap_first}Mapper.get${table.className}RecordCount(param);
    }
    

    public Integer insert${table.className}(${table.className} ${table.className?uncap_first})throws Exception {
        return ${table.className?uncap_first}Mapper.insert${table.className}(${table.className?uncap_first});
    }

    public Integer update${table.className}(${table.className} ${table.className?uncap_first})throws Exception {
        return ${table.className?uncap_first}Mapper.update${table.className}(${table.className?uncap_first});
    }

    public Integer delete${table.className}ById(${table.primaryKeyField.fieldType} ${table.primaryKeyField.fieldName})throws Exception {
        return ${table.className?uncap_first}Mapper.delete${table.className}ById(${table.primaryKeyField.fieldName});
    }

}