package org.Alobject;

import java.util.Hashtable;

public interface Algorithm {
    public void input(Hashtable<String, Object> inputs);
    public void run();
    public double[] output();
}
