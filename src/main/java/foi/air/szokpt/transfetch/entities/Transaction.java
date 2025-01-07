package foi.air.szokpt.transfetch.entities;

import foi.air.szokpt.transfetch.enums.CardBrand;
import foi.air.szokpt.transfetch.enums.InstallmentsCreditor;
import foi.air.szokpt.transfetch.enums.TrxType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "guid")
    private String guid;

    @Column(name = "host_tid")
    private String hostTid;

    @Column(name = "host_mid")
    private String hostMid;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "trx_type")
    private TrxType trxType;

    @Column(name = "installments_number")
    private int installmentsNumber;

    @Column(name = "installments_creditor")
    private InstallmentsCreditor installmentsCreditor;

    @Column(name = "bank_host_id")
    private int bankHostId;

    @Column(name = "card_brand")
    private CardBrand cardBrand;

    @Column(name = "transaction_timestamp")
    private LocalDateTime transactionTimestamp;

    @Column(name = "masked_pin")
    private String maskedPin;

    @Column(name = "pin_used")
    private boolean pinUsed;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "approval_code")
    private String approvalCode;

    @Column(name = "rrn")
    private String rrn;

    @Column(name = "emv_1")
    private String emv1;

    @Column(name = "emv_2")
    private String emv2;

    @Column(name = "psn")
    private String psn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_tid")
    private Tid tid;

    public Transaction() {
    }

    public Transaction(String guid, String hostTid, String hostMid, BigDecimal amount, String currency, TrxType trxType, int installmentsNumber, InstallmentsCreditor installmentsCreditor, int bankHostId, CardBrand cardBrand, LocalDateTime transactionTimestamp, String maskedPin, boolean pinUsed, String responseCode, String approvalCode, String rrn, String emv1, String emv2, String psn) {
        this.guid = guid;
        this.hostTid = hostTid;
        this.hostMid = hostMid;
        this.amount = amount;
        this.currency = currency;
        this.trxType = trxType;
        this.installmentsNumber = installmentsNumber;
        this.installmentsCreditor = installmentsCreditor;
        this.bankHostId = bankHostId;
        this.cardBrand = cardBrand;
        this.transactionTimestamp = transactionTimestamp;
        this.maskedPin = maskedPin;
        this.pinUsed = pinUsed;
        this.responseCode = responseCode;
        this.approvalCode = approvalCode;
        this.rrn = rrn;
        this.emv1 = emv1;
        this.emv2 = emv2;
        this.psn = psn;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHostTid() {
        return hostTid;
    }

    public void setHostTid(String hostTid) {
        this.hostTid = hostTid;
    }

    public String getHostMid() {
        return hostMid;
    }

    public void setHostMid(String hostMid) {
        this.hostMid = hostMid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TrxType getTrxType() {
        return trxType;
    }

    public void setTrxType(TrxType trxType) {
        this.trxType = trxType;
    }

    public int getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(int installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

    public InstallmentsCreditor getInstallmentsCreditor() {
        return installmentsCreditor;
    }

    public void setInstallmentsCreditor(InstallmentsCreditor installmentsCreditor) {
        this.installmentsCreditor = installmentsCreditor;
    }

    public int getBankHostId() {
        return bankHostId;
    }

    public void setBankHostId(int bankHostId) {
        this.bankHostId = bankHostId;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public LocalDateTime getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(LocalDateTime transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getMaskedPin() {
        return maskedPin;
    }

    public void setMaskedPin(String maskedPin) {
        this.maskedPin = maskedPin;
    }

    public boolean isPinUsed() {
        return pinUsed;
    }

    public void setPinUsed(boolean pinUsed) {
        this.pinUsed = pinUsed;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getEmv1() {
        return emv1;
    }

    public void setEmv1(String emv1) {
        this.emv1 = emv1;
    }

    public String getEmv2() {
        return emv2;
    }

    public void setEmv2(String emv2) {
        this.emv2 = emv2;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public Tid getTid() {
        return tid;
    }

    public void setTid(Tid tid) {
        this.tid = tid;
    }
}
