/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author diego
 */
@Embeddable
public class ColumnStatsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "db_name")
    private String dbName;
    @Basic(optional = false)
    @Column(name = "table_name")
    private String tableName;
    @Basic(optional = false)
    @Column(name = "column_name")
    private String columnName;

    public ColumnStatsPK() {
    }

    public ColumnStatsPK(String dbName, String tableName, String columnName) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbName != null ? dbName.hashCode() : 0);
        hash += (tableName != null ? tableName.hashCode() : 0);
        hash += (columnName != null ? columnName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColumnStatsPK)) {
            return false;
        }
        ColumnStatsPK other = (ColumnStatsPK) object;
        if ((this.dbName == null && other.dbName != null) || (this.dbName != null && !this.dbName.equals(other.dbName))) {
            return false;
        }
        if ((this.tableName == null && other.tableName != null) || (this.tableName != null && !this.tableName.equals(other.tableName))) {
            return false;
        }
        if ((this.columnName == null && other.columnName != null) || (this.columnName != null && !this.columnName.equals(other.columnName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "consultas.ColumnStatsPK[ dbName=" + dbName + ", tableName=" + tableName + ", columnName=" + columnName + " ]";
    }
    
}
