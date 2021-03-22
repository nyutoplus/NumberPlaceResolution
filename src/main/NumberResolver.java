package main;

import util.NumberAbles;
import util.NumberState;

public class NumberResolver {

	private boolean runnable;

	public NumberState resolve(NumberState state){
		runnable = true;
		boolean resolve = true;
		int size = state.getSize();
		int ssize = (int) Math.sqrt(size);
		NumberState tmpstate = new NumberState(state);
		do {
			NumberAbles va = new NumberAbles(size);
			NumberAbles ta = new NumberAbles(size);
			NumberAbles sa = new NumberAbles(size);


		}while(runnable && resolve);

		return null;
	}

	public void cancel() {
		runnable = false;
	}

}
