package poly.quanlyquanao.dto;

import java.math.BigDecimal;

public class ChartItem {
    private String label;
    private BigDecimal revenue;
    private int countSuccess;
    private int countCanceled;

    public ChartItem() {}

    public ChartItem(String label, BigDecimal revenue, int countSuccess, int countCanceled) {
        this.label = label;
        this.revenue = revenue;
        this.countSuccess = countSuccess;
        this.countCanceled = countCanceled;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public int getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(int countSuccess) {
        this.countSuccess = countSuccess;
    }

    public int getCountCanceled() {
        return countCanceled;
    }

    public void setCountCanceled(int countCanceled) {
        this.countCanceled = countCanceled;
    }
}
