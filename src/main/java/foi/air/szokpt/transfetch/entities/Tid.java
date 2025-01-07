package foi.air.szokpt.transfetch.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tids")
public class Tid {
    @Id
    @Column(name = "pos_tid")
    private String posTid;

    @Column(name = "mcc")
    private String mcc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid_id")
    private Mid mid;

    @OneToMany(mappedBy = "tid", fetch = FetchType.LAZY)
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

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setTid(this);
    }
}
