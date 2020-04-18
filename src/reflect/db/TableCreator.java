package reflect.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class TableCreator {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Argument: annotated class");
            System.exit(0);
        }
        selfTest(args);
    }

    public static void selfTest(String[] args) {
        for (String clazzName : args) {
            try {
                Class<?> clazz = Class.forName(clazzName);
                DBTable dbTable = clazz.getAnnotation(DBTable.class);
                if (dbTable == null) {
                    System.out.println("No table annotation in class " + clazzName);
                    continue;
                }
                String tableName = dbTable.name();
                if (tableName.length() < 1) {
                    tableName = clazz.getName().toUpperCase();
                }
                List<String> columnNameList = new ArrayList<>();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Annotation[] annotations = field.getDeclaredAnnotations();
                    String columnName = null;
                    if (annotations.length < 1) {
                        continue;
                    }
                    if (annotations[0] instanceof SQLString) {
                        SQLString sqlStr = (SQLString) annotations[0];
                        if (sqlStr.name().length() < 1) {
                            columnName = field.getName().toUpperCase();
                        } else {
                            columnName = sqlStr.name();
                        }
                        columnNameList.add(columnName + " VARCHAR(" + sqlStr.value() + ") " + getConstraint(sqlStr.constraints()));
                    }
                    if (annotations[0] instanceof SQLInteger) {
                        SQLInteger sqlInt = (SQLInteger) annotations[0];
                        if (sqlInt.name().length() < 1) {
                            columnName = field.getName().toUpperCase();
                        } else {
                            columnName = sqlInt.name();
                        }
                        columnNameList.add(columnName + " INT" + getConstraint(sqlInt.constraints()));
                    }
                }
                StringBuilder sbSql = new StringBuilder("CREATE TABLE " + tableName + "(");
                for (String column : columnNameList) {
                    sbSql.append("\n     " + column + ",");
                }
                String creationSQL = sbSql.substring(0, sbSql.length() - 1) + ");";
                System.out.println("Table creation SQL for " + clazzName + " is :\n" + creationSQL);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getConstraint(Constraints constraints) {
        StringBuilder sb = new StringBuilder("");
        if (!constraints.allowNotNull()) {
            sb.append(" NOT NULL");
        }
        if (constraints.primaryKey()) {
            sb.append(" PRIMARY KEY");
        }
        if (constraints.unique()) {
            sb.append(" UNIQUE");
        }
        return sb.toString();
    }
}
