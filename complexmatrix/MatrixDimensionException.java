package complexmatrix;

public class MatrixDimensionException extends RuntimeException{
	String msg;
	public MatrixDimensionException(String msg) {
		super(msg);
		this.msg=msg;
	}
}