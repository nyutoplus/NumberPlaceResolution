package util;

public class NumberState {

	public int state[][];

	public NumberState() {
	}

	public NumberState(int size) {
		this(new int[size][size]);
	}

	public NumberState(int arr[][]) {
		setArray(arr);
	}

	public NumberState(NumberState state) {
		copy(state);
	}

	public void setArray(int arr[][]) {
		state = arr;
	}

	public int[][] getArray(){
		return state;
	}

	public int getNum(int x,int y) {
		return state[x][y];
	}

	public void setNum(int x,int y,int value) {
		state[x][y] = value;
	}

	public void copy(int arr[][]) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				state[i][j] = arr[i][j];
			}
		}
	}

	public void copy(NumberState state){
		copy(getArray());
	}

	public int getSize() {
		return state.length;
	}
}
