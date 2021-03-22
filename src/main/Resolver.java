package main;

import util.NumberState;
import viewer.ViewControl;

public class Resolver {
    public static ViewControl vc;

    public static NumberResolver nr;

    public static void main(String[] args) {
	vc = new ViewControl(9);
	nr = new NumberResolver();

    }

    public static NumberState resolve(NumberState state) {
    	return nr.resolve(state);
    }

    public static void cancel() {
    	nr.cancel();
    }

}
