package generator.config.toolkit;

import java.util.List;

import generator.config.po.TableField;

/**
 * Created by zhuang on 2017年12月5日
 */
public class IntfSqlMapUtil {

    public static String genBaseResultMapSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                             String pkName) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            String filed = tableFields.get(i).getPropertyName();
			// String type = tableFields.get(i).getType();
            if (tableFields.get(i).isKeyFlag()) {
                sql.append("<id column=\"").append(clumn + "\" ").append(" property=\"").append(filed + "\" ")
                        .append("/>").append("\n\t");
//				.append("jdbcType=\"").append(type).append("\"").append("/>").append("\n\t");
            } else {

                sql.append("<result column=\"").append(clumn + "\" ").append(" property=\"").append(filed + "\" ")
                        .append("/>").append("\n\t");
//						.append("jdbcType=\"").append(type).append("\"").append("/>").append("\n\t");
            }
        }
        return sql.toString();
    }

    public static String genBatchDeleteSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                           String pkName) {
        StringBuilder sql = new StringBuilder();
        sql.append("update  ").append(entityTableName).append(" set DELETE_STATUS = 0 where ").append(pkClumn).append(" in")
                .append("\n\t");
        sql.append("  <foreach item=\"").append(pkName + "\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">")
                .append("\n\t");
        sql.append("  #{").append(pkName).append("}").append("\n\t");
        sql.append("  </foreach>");
        return sql.toString();
    }

    public static String genDeleteSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                      String pkName) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(entityTableName);
        sql.append(" set DELETE_STATUS = 0 where ").append(pkClumn).append("=#{").append(pkName).append("}");
        return sql.toString();
    }

    public static String genBaseColumn(List<TableField> tableFields) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            if (i < size - 1) {
                sql.append(clumn).append(",");
            } else {
                sql.append(clumn);
            }
            if (i > 0 && i % 6 == 0) {
                sql.append("\n\t");
            }
        }
        return sql.toString();
    }

    public static String genEntityWhereClause(List<TableField> tableFields) {
        StringBuilder sql = new StringBuilder();
        sql.append("<where>\n\t");
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            String field = tableFields.get(i).getPropertyName();
            StringBuilder ifSql = new StringBuilder();
            ifSql.append("      <if test=\"%s!=null and %s!=\'\'\" >\n\t");
            ifSql.append("\t%s=#{%s},\n\t");
            ifSql.append("      </if>\t");
            sql.append(String.format(ifSql.toString(), field, field, clumn, field));
            sql.append("\n\t");
        }
        sql.append("  </where>\n\t");
        return sql.toString();
    }

    public static String genInsertSql(List<TableField> tableFields, String entityTableName) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(entityTableName).append("\n\t");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">").append("\n\t");
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            String filed = tableFields.get(i).getPropertyName();
            StringBuilder ifSql = new StringBuilder();
            ifSql.append("  <if test=\"%s!=null\" >\n\t");
            ifSql.append("\t  %s,\n\t");
            ifSql.append("  </if>\t");
            sql.append(String.format(ifSql.toString(), filed, clumn));
            sql.append("\n\t");
        }
        sql.append("</trim>\n\t");
        sql.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n\t");
        for (int i = 0, size = tableFields.size(); i < size; i++) {
			// String clumn = tableFields.get(i).getName();
            String filed = tableFields.get(i).getPropertyName();
            StringBuilder ifSql = new StringBuilder();
            ifSql.append("  <if test=\"%s!=null\" >\n\t");
            ifSql.append("\t  #{%s},\n\t");
            ifSql.append("  </if>\t");
            sql.append(String.format(ifSql.toString(), filed, filed));
            sql.append("\n\t");
        }
        sql.append("</trim>");
        return sql.toString();
    }

    public static String genUpdateSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                      String pkName) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(entityTableName).append("\n\t").append(" <set> ").append("\n\t");
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            String field = tableFields.get(i).getPropertyName();
            StringBuilder ifSql = new StringBuilder();
            ifSql.append("  <if test=\"%s!=null\" >\n\t");
            ifSql.append("\t  %s=#{%s},\n\t");
            ifSql.append("  </if>\t");
            sql.append(String.format(ifSql.toString(), field, clumn, field));
            sql.append("\n\t");
        }
        sql.append("</set>").append("\n\t");
        sql.append("where ").append(pkClumn).append("=#{").append(pkName).append("}");
        return sql.toString();
    }

    public static String genGetByIdSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                       String pkName) {
        StringBuilder sql = new StringBuilder();
        sql.append("select \n\t");
        sql.append("  <include refid=\"Base_Column_List\" />");
        sql.append("\n\t  from ").append(entityTableName).append(" where ").append(pkClumn).append("=#{").append(pkName)
                .append("}");
        return sql.toString();
    }

    public static String genGetListSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                       String pkName) {
        // select
        // <include refid="Base_Column_List" />
        // from MOB_APP
        // where APP_ID = #{appId,jdbcType=VARCHAR}
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n\t");
		sql.append("  <include refid=\"Base_Column_List\" />");
		// for (int i = 0, size = tableFields.size(); i < size; i++) {
		// String clumn = tableFields.get(i).getName();
		// String field = tableFields.get(i).getPropertyName();
		// if (clumn.equalsIgnoreCase(field)) {
		// sql.append(clumn);
		// } else {
		// sql.append(clumn).append(" AS ").append(field);
		// }
		// if (i < size - 1) {
		// sql.append(",");
		// }
		// if (i > 0 && i % 6 == 0) {
		// sql.append("\n\t");
		// }
		// }
        sql.append("\n\tFROM ").append(entityTableName).append(" WHERE 1=1");
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            String field = tableFields.get(i).getPropertyName();
            StringBuilder ifSql = new StringBuilder();
            ifSql.append("\n\t<if test=\" %s!=null and %s!=\'\'\" >\n");
            ifSql.append("\t\tAND %s = #{ %s }\n");
            ifSql.append("\t</if>");
            sql.append(String.format(ifSql.toString(), field, field, clumn, field));
        }
        return sql.toString();
    }

    public static String genGetTotalSql(List<TableField> tableFields, String entityTableName, String pkClumn,
                                        String pkName) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) FROM ").append(entityTableName).append(" WHERE 1=1");
        for (int i = 0, size = tableFields.size(); i < size; i++) {
            String clumn = tableFields.get(i).getName();
            String field = tableFields.get(i).getPropertyName();
            StringBuilder ifSql = new StringBuilder();
            ifSql.append("\n\t<#if %s?exists && %s!=\"\" >\n");
            if (i < size - 1) {
                ifSql.append("\t\tAND %s=:%s\n");
            } else {
                ifSql.append("\t\tAND %s=:%s\n");
            }
            ifSql.append("\t</#if>");
            sql.append(String.format(ifSql.toString(), field, field, clumn, field));
        }
        return sql.toString();
    }

}