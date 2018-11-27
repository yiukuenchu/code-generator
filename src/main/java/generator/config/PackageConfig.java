package generator.config;

import generator.config.toolkit.StringUtils;

/**
 * 跟包相关的配置项
 */
public class PackageConfig {

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = "com.yiukuenchu.demo";

    /**
     * 父包模块名。
     */
    private String moduleName = null;

    private String dto = "dto";
    /**
     * Entity包名
     */
    private String entity = "entity";

    /**
     * Service包名
     */
    private String service = "service";

    /**
     * Service Impl包名
     */
    private String serviceImpl = "service.impl";
    /**
     * Mapper包名
     */
    private String mapper = "mapper";

    /**
     * Mapper XML包名
     */
    private String xml = "mapper.xml";

    /**
     * Controller包名
     */
    private String controller = "controller";

    /**
     * js包名
     */
    private String js = "js";

    /**
     * vue包名
     */
    private String vue = "vue";

    public String getParent() {
        if (StringUtils.isNotEmpty(moduleName)) {
            return parent + "." + moduleName;
        }
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getController() {
        if (StringUtils.isEmpty(controller)) {
            return "web";
        }
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }
}
