package generator.config.po;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import generator.config.StrategyConfig;
import generator.config.toolkit.CollectionUtils;
import generator.config.toolkit.StringUtils;


/**
 * 表信息，关联到当前字段信息
 */
public class TableInfo {

    private boolean convert;
    private String name;
    private String comment;

    private String entityName;
    private String mapperName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private String jsName;
    private String vueName;

    private List<TableField> fields;
    private List<TableField> commonFields;// 公共字段
    private List<String> importPackages = new ArrayList<>();
    private String fieldNames;

    private String entityLowerCaseName;

    private String primaryKeyName;

    private String propertyKeyName;

    public boolean isConvert() {
        return convert;
    }

    protected void setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.containsTablePrefix(name)) {
            // 包含前缀
            this.convert = true;
        } else if (strategyConfig.isCapitalModeNaming(name)) {
            // 包含
            this.convert = false;
        } else {
            // 转换字段
            if (StrategyConfig.DB_COLUMN_UNDERLINE) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!entityName.equalsIgnoreCase(name)) {
                this.convert = true;
            }
        }
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntityPath() {
        StringBuilder ep = new StringBuilder();
        ep.append(entityName.substring(0, 1).toLowerCase());
        ep.append(entityName.substring(1));
        return ep.toString();
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(StrategyConfig strategyConfig, String entityName) {
        this.entityName = entityName;
        this.setConvert(strategyConfig);
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getJsName() {
        return jsName;
    }

    public void setJsName(StrategyConfig strategyConfig, String jsName) {
        this.jsName = jsName;
        this.setConvert(strategyConfig);
    }

    public String getVueName() {
        return vueName;
    }

    public void setVueName(StrategyConfig strategyConfig, String vueName) {
        this.vueName = vueName;
        this.setConvert(strategyConfig);
    }

    public List<TableField> getFields() {
        return fields;
    }


    public String getEntityLowerCaseName() {
        return entityLowerCaseName;
    }

    public void setEntityLowerCaseName(String entityLowerCaseName) {
        this.entityLowerCaseName = entityLowerCaseName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }

    public void setFields(List<TableField> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            this.fields = fields;
            // 收集导入包信息
            Set<String> pkgSet = new HashSet<>();
            for (TableField field : fields) {
                if (null != field.getColumnType() && null != field.getColumnType().getPkg()) {
                    pkgSet.add(field.getColumnType().getPkg());
                }
                if (field.isKeyFlag()) {
                    // 主键
                    if (field.isConvert() || field.isKeyIdentityFlag()) {
                        pkgSet.add("javax.persistence.Id");
                    }
                    // 自增
                    /*if (field.isKeyIdentityFlag()) {
                        pkgSet.add("");
                    }*/
                } else if (field.isConvert()) {
                    // 普通字段
                    pkgSet.add("javax.persistence.Column");
                }
            }
            if (!pkgSet.isEmpty()) {
                this.importPackages = new ArrayList<>(Arrays.asList(pkgSet.toArray(new String[]{})));
            }
        }
    }

    public List<TableField> getCommonFields() {
        return commonFields;
    }

    public void setCommonFields(List<TableField> commonFields) {
        this.commonFields = commonFields;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String pkg) {
        importPackages.add(pkg);
    }


    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }


    public String getPropertyKeyName() {
        return propertyKeyName;
    }

    public void setPropertyKeyName(String propertyKeyName) {
        this.propertyKeyName = propertyKeyName;
    }

    /**
     * 转换filed实体为xmlmapper中的basecolumn字符串信息
     *
     * @return
     */
    public String getFieldNames() {
        if (StringUtils.isEmpty(fieldNames)) {
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < fields.size(); i++) {
                TableField fd = fields.get(i);
                if (i == fields.size() - 1) {
                    names.append(cov2col(fd));
                } else {
                    names.append(cov2col(fd)).append(", ");
                }
            }
            fieldNames = names.toString();
        }
        return fieldNames;
    }

    /**
     * mapper xml中的字字段添加as
     *
     * @param field 字段实体
     * @return 转换后的信息
     */
    private String cov2col(TableField field) {
        if (null != field) {
            return field.isConvert() ? field.getName() + " AS " + field.getPropertyName() : field.getName();
        }
        return StringUtils.EMPTY;
    }

}
