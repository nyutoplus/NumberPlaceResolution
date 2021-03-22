package util;

import java.util.ArrayList;
import java.util.List;

public class NumberAbles {

	List<Integer> states[];

	@SuppressWarnings({ "unchecked" })
	public NumberAbles(int size) {
		states = new ArrayList[size];
		for(int i=0;i<states.length;i++) {
				states[i] = new ArrayList<Integer>(size);
		}
	}

	public void putNum(int num,int t) {
		states[t].add(num);
	}

	public int getSize(int t) {
		return states[t].size();
	}

	public int[] getNumbers(int t) {
		Integer num[] = (Integer[])states[t].toArray(new Integer[0]);
		int tmp[] = new int[num.length];
		for(int i=0;i<num.length;i++) {
			tmp[i] = (int) num[i];
		}
		return tmp;
	}
}
