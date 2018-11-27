package generator;

import generator.config.DataSourceConfig;
import generator.config.GlobalConfig;
import generator.config.PackageConfig;
import generator.config.StrategyConfig;
import generator.config.TemplateConfig;
import generator.config.builder.ConfigBuilder;

/**
 * 插件基类，用于属性配置 设计成抽象类主要是用于后期可扩展，共享参数配置。
 */
public abstract class AbstractGenerator {

    protected ConfigBuilder config;
    protected InjectionConfig injectionConfig;
    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageInfo;
    /**
     * 模板 相关配置
     */
    private TemplateConfig template;
    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

    /**
     * 初始化配置
     */
    protected void initConfig() {
        if (null == config) {
            config = new ConfigBuilder(packageInfo, dataSource, strategy, template, globalConfig);
            if (null != injectionConfig) {
                injectionConfig.setConfig(config);
            }
        }
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
    }

    public StrategyConfig getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyConfig strategy) {
        this.strategy = strategy;
    }

    public PackageConfig getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageConfig packageInfo) {
        this.packageInfo = packageInfo;
    }

    public TemplateConfig getTemplate() {
        return template;
    }

    public void setTemplate(TemplateConfig template) {
        this.template = template;
    }

    public ConfigBuilder getConfig() {
        return config;
    }

    public void setConfig(ConfigBuilder config) {
        this.config = config;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public InjectionConfig getCfg() {
        return injectionConfig;
    }

    public void setCfg(InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
    }

}
