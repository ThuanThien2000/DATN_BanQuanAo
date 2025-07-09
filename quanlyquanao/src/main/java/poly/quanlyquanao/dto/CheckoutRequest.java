package poly.quanlyquanao.dto;
import java.util.List;

import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;

public class CheckoutRequest {
    private Invoice invoice;
    private List<InvoiceDetail> invoiceDetails;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
