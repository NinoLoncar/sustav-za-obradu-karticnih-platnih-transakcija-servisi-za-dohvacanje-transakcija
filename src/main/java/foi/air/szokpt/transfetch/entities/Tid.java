package foi.air.szokpt.transfetch.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tids")
public class Tid {
    @Id
    @JsonProperty("pos_tid")
    @Column(name = "pos_tid")
    private String posTid;

    @Column(name = "mcc")
    @JsonProperty("mcc")
    private String mcc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "pos_mid")
    private Mid mid;

    @OneToMany(mappedBy = "tid", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Transaction> transactions;

    public Tid() {
    }

    public Tid(String posTid, String mcc) {
        this.posTid = posTid;
        this.mcc = mcc;
    }

    public String getPosTid() {
        return posTid;
    }

    public void setPosTid(String pos_tid) {
        this.posTid = pos_tid;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public Mid getMid() {
        return mid;
    }

    public void setMid(Mid mid) {
        this.mid = mid;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transaction) {
        this.transactions.clear();
        for (Transaction t : transaction) {
            t.setTid(this);
            this.transactions.add(t);
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setTid(this);
    }
}
