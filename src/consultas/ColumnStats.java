/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "column_stats", catalog = "mysql", schema = "")
@NamedQueries({
    @NamedQuery(name = "ColumnStats.findAll", query = "SELECT c FROM ColumnStats c")
    , @NamedQuery(name = "ColumnStats.findByDbName", query = "SELECT c FROM ColumnStats c WHERE c.columnStatsPK.dbName = :dbName")
    , @NamedQuery(name = "ColumnStats.findByTableName", query = "SELECT c FROM ColumnStats c WHERE c.columnStatsPK.tableName = :tableName")
    , @NamedQuery(name = "ColumnStats.findByColumnName", query = "SELECT c FROM ColumnStats c WHERE c.columnStatsPK.columnName = :columnName")
    , @NamedQuery(name = "ColumnStats.findByMinValue", query = "SELECT c FROM ColumnStats c WHERE c.minValue = :minValue")
    , @NamedQuery(name = "ColumnStats.findByMaxValue", query = "SELECT c FROM ColumnStats c WHERE c.maxValue = :maxValue")
    , @NamedQuery(name = "ColumnStats.findByNullsRatio", query = "SELECT c FROM ColumnStats c WHERE c.nullsRatio = :nullsRatio")
    , @NamedQuery(name = "ColumnStats.findByAvgLength", query = "SELECT c FROM ColumnStats c WHERE c.avgLength = :avgLength")
    , @NamedQuery(name = "ColumnStats.findByAvgFrequency", query = "SELECT c FROM ColumnStats c WHERE c.avgFrequency = :avgFrequency")
    , @NamedQuery(name = "ColumnStats.findByHistSize", query = "SELECT c FROM ColumnStats c WHERE c.histSize = :histSize")
    , @NamedQuery(name = "ColumnStats.findByHistType", query = "SELECT c FROM ColumnStats c WHERE c.histType = :histType")})
public class ColumnStats implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ColumnStatsPK columnStatsPK;
    @Column(name = "min_value")
    private String minValue;
    @Column(name = "max_value")
    private String maxValue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nulls_ratio")
    private BigDecimal nullsRatio;
    @Column(name = "avg_length")
    private BigDecimal avgLength;
    @Column(name = "avg_frequency")
    private BigDecimal avgFrequency;
    @Column(name = "hist_size")
    private Short histSize;
    @Column(name = "hist_type")
    private String histType;
    @Lob
    @Column(name = "histogram")
    private byte[] histogram;

    public ColumnStats() {
    }

    public ColumnStats(ColumnStatsPK columnStatsPK) {
        this.columnStatsPK = columnStatsPK;
    }

    public ColumnStats(String dbName, String tableName, String columnName) {
        this.columnStatsPK = new ColumnStatsPK(dbName, tableName, columnName);
    }

    public ColumnStatsPK getColumnStatsPK() {
        return columnStatsPK;
    }

    public void setColumnStatsPK(ColumnStatsPK columnStatsPK) {
        this.columnStatsPK = columnStatsPK;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        String oldMinValue = this.minValue;
        this.minValue = minValue;
        changeSupport.firePropertyChange("minValue", oldMinValue, minValue);
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        String oldMaxValue = this.maxValue;
        this.maxValue = maxValue;
        changeSupport.firePropertyChange("maxValue", oldMaxValue, maxValue);
    }

    public BigDecimal getNullsRatio() {
        return nullsRatio;
    }

    public void setNullsRatio(BigDecimal nullsRatio) {
        BigDecimal oldNullsRatio = this.nullsRatio;
        this.nullsRatio = nullsRatio;
        changeSupport.firePropertyChange("nullsRatio", oldNullsRatio, nullsRatio);
    }

    public BigDecimal getAvgLength() {
        return avgLength;
    }

    public void setAvgLength(BigDecimal avgLength) {
        BigDecimal oldAvgLength = this.avgLength;
        this.avgLength = avgLength;
        changeSupport.firePropertyChange("avgLength", oldAvgLength, avgLength);
    }

    public BigDecimal getAvgFrequency() {
        return avgFrequency;
    }

    public void setAvgFrequency(BigDecimal avgFrequency) {
        BigDecimal oldAvgFrequency = this.avgFrequency;
        this.avgFrequency = avgFrequency;
        changeSupport.firePropertyChange("avgFrequency", oldAvgFrequency, avgFrequency);
    }

    public Short getHistSize() {
        return histSize;
    }

    public void setHistSize(Short histSize) {
        Short oldHistSize = this.histSize;
        this.histSize = histSize;
        changeSupport.firePropertyChange("histSize", oldHistSize, histSize);
    }

    public String getHistType() {
        return histType;
    }

    public void setHistType(String histType) {
        String oldHistType = this.histType;
        this.histType = histType;
        changeSupport.firePropertyChange("histType", oldHistType, histType);
    }

    public byte[] getHistogram() {
        return histogram;
    }

    public void setHistogram(byte[] histogram) {
        byte[] oldHistogram = this.histogram;
        this.histogram = histogram;
        changeSupport.firePropertyChange("histogram", oldHistogram, histogram);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (columnStatsPK != null ? columnStatsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColumnStats)) {
            return false;
        }
        ColumnStats other = (ColumnStats) object;
        if ((this.columnStatsPK == null && other.columnStatsPK != null) || (this.columnStatsPK != null && !this.columnStatsPK.equals(other.columnStatsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "consultas.ColumnStats[ columnStatsPK=" + columnStatsPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
