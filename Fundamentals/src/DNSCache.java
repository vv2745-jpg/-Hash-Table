public class DNSCache import java.util.*;

class DNSEntry {
    String ipAddress;
    long expiryTime;

    public DNSEntry(String ipAddress, int ttlSeconds) {
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000L);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCache {
    private Map<String, DNSEntry> cache = new HashMap<>();

    public void addMapping(String domain, String ip, int ttl) {
        cache.put(domain.toLowerCase(), new DNSEntry(ip, ttl));
        System.out.println("Cached: " + domain + " -> " + ip);
    }

    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain.toLowerCase());

        if (entry == null || entry.isExpired()) {
            if (entry != null) cache.remove(domain.toLowerCase());
            return "Cache Miss / Expired";
        }

        return entry.ipAddress;
    }

    public static void main(String[] args) throws InterruptedException {
        DNSCache dns = new DNSCache();
        dns.addMapping("google.com", "142.250.190.46", 2); // 2 second TTL

        System.out.println("Lookup 1: " + dns.resolve("google.com"));
        Thread.sleep(3000); // Wait for expiration
        System.out.println("Lookup 2: " + dns.resolve("google.com"));
    }
}{
}
