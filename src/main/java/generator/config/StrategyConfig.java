package generator.config;

import generator.config.rules.NamingStrategy;
import generator.config.toolkit.StringUtils;

/**
 * 策略配置项
 */
public class StrategyConfig {

    /**
     * 表名、字段名、是否使用下划线命名（默认 false）
     */
    public static boolean DB_COLUMN_UNDERLINE = false;

    /**
     * 是否大写命名
     */
    private boolean isCapitalMode = false;

    /**
     * 数据库表映射到实体的命名策略
     */
    private NamingStrategy naming = NamingStrategy.nochange;

    /**
     * 表前缀
     */
    private String[] tablePrefix;

    /**
     * 自定义继承的Entity类全称，带包名
     */
    private String superEntityClass;

    /**
     * 自定义基础的Entity类，公共字段
     */
    private String[] superEntityColumns;

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superMapperClass = ConstVal.SUPERD_MAPPER_CLASS;

    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass = ConstVal.SUPERD_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass = ConstVal.SUPERD_SERVICEIMPL_CLASS;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superControllerClass = ConstVal.SUPERD_CONTROLLER_CLASS;

    /*
     * 需要包含的表名（与exclude二选一配置）
     */
    private String[] include = null;

    /**
     * 需要排除的表名
     */
    private String[] exclude = null;
    /**
     * 【实体】是否生成字段常量（默认 false）<br>
     * -----------------------------------<br>
     * public static final String ID = "test_id";
     */
    private boolean entityColumnConstant = false;

    /**
     * 【实体】是否为构建者模型（默认 false）<br>
     * -----------------------------------<br>
     * public User setName(String name) { this.name = name; return this; }
     */
    private boolean entityBuilderModel = false;

    public void setDbColumnUnderline(boolean dbColumnUnderline) {
        DB_COLUMN_UNDERLINE = dbColumnUnderline;
    }

    /**
     * <p>
     * 大写命名、字段符合大写字母数字下划线命名
     * </p>
     *
     * @param word 待判断字符串
     * @return
     */
    public boolean isCapitalModeNaming(String word) {
        return isCapitalMode && StringUtils.isCapitalMode(word);
    }

    /**
     * <p>
     * 表名称包含指定前缀
     * </p>
     *
     * @param tableName 表名称
     * @return
     */
    public boolean containsTablePrefix(String tableName) {
        if (null != tableName) {
            String[] tps = getTablePrefix();
            if (null != tps) {
                for (String tp : tps) {
                    if (tableName.contains(tp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCapitalMode() {
        return isCapitalMode;
    }

    public void setCapitalMode(boolean isCapitalMode) {
        this.isCapitalMode = isCapitalMode;
    }

    public NamingStrategy getNaming() {
        return naming;
    }

    public void setNaming(NamingStrategy naming) {
        this.naming = naming;
    }

    public String[] getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String[] tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getSuperEntityClass() {
        return superEntityClass;
    }

    public void setSuperEntityClass(String superEntityClass) {
        this.superEntityClass = superEntityClass;
    }

    public boolean includeSuperEntityColumns(String fieldName) {
        if (null != superEntityColumns) {
            for (String column : superEntityColumns) {
                if (column.contains(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getSuperEntityColumns() {
        return superEntityColumns;
    }

    public void setSuperEntityColumns(String[] superEntityColumns) {
        this.superEntityColumns = superEntityColumns;
    }

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public void setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public void setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String[] include) {
        this.include = include;
    }

    public String[] getExclude() {
        return exclude;
    }

    public void setExclude(String[] exclude) {
        this.exclude = exclude;
    }

    public boolean isEntityColumnConstant() {
        return entityColumnConstant;
    }

    public void setEntityColumnConstant(boolean entityColumnConstant) {
        this.entityColumnConstant = entityColumnConstant;
    }

    public boolean isEntityBuilderModel() {
        return entityBuilderModel;
    }

    public void setEntityBuilderModel(boolean entityBuilderModel) {
        this.entityBuilderModel = entityBuilderModel;
    }
}
