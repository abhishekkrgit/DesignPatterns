package creational.builder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class SqlQuery {
    private final String table;
    private final List<String> columns;
    private final List<String> conditions;
    private final String orderBy;
    private final String orderDirn;
    private final int limit;
    private final int offSet;

    private SqlQuery(Builder builder) {
        this.table = builder.table;
        this.columns = new ArrayList<>(builder.columns);
        this.conditions = new ArrayList<>(builder.conditions);
        this.orderBy = builder.orderBy;
        this.orderDirn = builder.orderDirn;
        this.limit = builder.limit;
        this.offSet = builder.offSet;
    }


    public String toSql(){
        StringBuilder sql = new StringBuilder("SELECT " );
        sql.append(columns.isEmpty()? "*" : String.join(",", columns) );
        sql.append(" FROM ");
        sql.append(table);
        if(!conditions.isEmpty()){
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }
        if(!Objects.isNull(orderBy) ) {
            sql.append(" ORDER BY ").append(orderBy);
            if(!Objects.isNull(orderDirn)){
                sql.append(" ").append(orderDirn);
            }
        }
        if(limit > 0) {
            sql.append(" LIMIT ").append(limit);
        }

        if(offSet > 0) {
            sql.append(" OFFSET ").append(offSet);
        }

        return sql.toString();
    }

    public static class Builder {
        private String table;
        private List<String> columns = new ArrayList<>();
        private List<String> conditions = new ArrayList<>();
        private String orderBy;
        private String orderDirn;
        private int limit;
        private int offSet;

        public Builder(String table){
            this.table = table;
        }

        public Builder addColums(List<String>columns){
            this.columns.addAll(columns);
            return this;
        }

        public Builder addConditions(List<String>conditions){
            this.conditions.addAll(conditions);
            return this;
        }

        public Builder orderBy(String orderBy){
            this.orderBy = orderBy;
            return this;
        }

        public Builder orderDirn(String orderDirn) {
            this.orderDirn = orderDirn;
            return this;
        }

        public Builder limit (int limit){
            this.limit = limit;
            return this;
        }

        public Builder offset(int offSet){
            this.offSet = offSet;
            return this;
        }

        public SqlQuery build (){
            return new SqlQuery(this);
        }
    }

    
}