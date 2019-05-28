package club.eugeneliu.information.test;

public class TestSituation {
    public static void main(String[] args) {
//        WaveForm waveForm = new WaveForm();
        int i = 0;
        int a = i++;
        System.out.println("a:" + a);
        System.out.println("i:" + i);

        System.out.println();

        int j = 0;
        int b = ++j;
        System.out.println("b:" + b);
        System.out.println("j:" + j);
    }
}


class WaveForm {
    private static long counter;
    private final long id = counter++;

    public String toString() {
        return "WaveForm " + id;
    }
}

class Filter {
    public String name() {
        return getClass().getSimpleName();
    }

    public WaveForm process(WaveForm input) {
        return input;
    }
}

class LowPass extends Filter {
    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public WaveForm process(WaveForm input) {
        return input;
    }
}

class HighPass extends Filter {
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public WaveForm process(WaveForm input) {
        return input;
    }
}

class BandPass extends Filter {
    double lowCutOff, highCutOff;

    public BandPass(double lowCutOff, double highCutOff) {
        this.lowCutOff = lowCutOff;
        this.highCutOff = highCutOff;
    }

    @Override
    public WaveForm process(WaveForm input) {
        return input;
    }
}

