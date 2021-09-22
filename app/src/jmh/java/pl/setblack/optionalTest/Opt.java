package pl.setblack.optionalTest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Optional;
import java.util.Random;

public class Opt {

    private Long randomNumber(Random rnd) {
        int i = rnd.nextInt(100);
        if (i > 10) {
            return  null;
        } else {
            return (long)i;
        }
    }

    private Optional<Long> randomNumberOpt(Random rnd) {
        int i = rnd.nextInt(100);
        if (i > 10) {
            return  Optional.empty();
        } else {
            return Optional.of((long)i);
        }
    }

    @Benchmark
    public long sumClassic() {
        final Random rnd = new Random(42);
        long sum = 0;
        for (int i=0; i<1_000_000;++i) {
            Long n = randomNumber(rnd);
            if (n != null) {
                sum+= n;
            }
        }
        assert sum == 549645;
        return sum;
    }

    @Benchmark
    public long sumOptional() {
        final Random rnd = new Random(42);
        long sum = 0;
        for (int i=0; i<1_000_000;++i) {
            Optional<Long> n = randomNumberOpt(rnd);
            if (n.isPresent()) {
                sum+= n.get();
            }
        }
        assert sum == 549645;
        return sum;
    }
}
