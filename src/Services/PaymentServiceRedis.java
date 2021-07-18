package Services;

import Model.Payment;
import Model.Student;
import Services.exception.DuplicateEntryException;
import Services.exception.NotFoundException;
import Services.util.JedisClient;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PaymentServiceRedis {
    private static final String DB_PREFIX = "P#";
    private final Jedis client;

    public PaymentServiceRedis() {
        client = JedisClient.getInstance().getClient();
    }

    public void savePayment(Payment payment) throws DuplicateEntryException {

        if (client.exists(DB_PREFIX + payment.getCid())) {
            throw new DuplicateEntryException();
        }
        client.hset(DB_PREFIX + payment.getCid(), payment.toMap());
    }

    public void updatePayment(Payment payment) throws NotFoundException {

        if (!client.exists(DB_PREFIX + payment.getCid())) {
            throw new NotFoundException();
        }
        client.hset(DB_PREFIX + payment.getCid(), payment.toMap());
    }

    public void deletePayment(String cid) throws NotFoundException {
        if (!client.exists(DB_PREFIX + cid)) {
            throw new NotFoundException();
        }
        client.del(DB_PREFIX + cid);
    }

    private boolean exitsPayment(String cid) {
        return client.exists(DB_PREFIX + cid);
    }

    public Payment findPayment(String cid) throws NotFoundException {
        if (!client.exists(DB_PREFIX + cid)) {
            throw new NotFoundException();
        }
        return Payment.fromMap(cid.replace(DB_PREFIX, ""), client.hgetAll(DB_PREFIX + cid));
    }

    public List<Payment> findAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        Set<String> cidList = client.keys(DB_PREFIX + "*");

        for (String cid : cidList) {
            paymentList.add(Payment.fromMap(cid.replace(DB_PREFIX, ""), client.hgetAll(DB_PREFIX + cid)));
        }
        return paymentList;
    }

    public List<Payment> findPayments(String query) {
        List<Payment> searchResult = new ArrayList<>();
        Set<String> cidList = client.keys(DB_PREFIX + "*");

        for (String cid : cidList) {

            if (cid.contains(query)) {
                searchResult.add(Payment.fromMap(cid.replace(DB_PREFIX, ""), client.hgetAll(cid)));
            } else {
                List<String> data = client.hvals(DB_PREFIX + cid);

                for (String value : data) {
                    if (value.contains(query)) {
                        searchResult.add(Payment.fromMap(cid.replace(DB_PREFIX, ""), client.hgetAll(cid)));
                        break;
                    }
                }
            }
        }
        return searchResult;
    }
}
