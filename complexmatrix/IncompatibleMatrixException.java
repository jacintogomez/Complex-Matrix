package complexmatrix;

public class IncompatibleMatrixException extends RuntimeException{
	String msg;
	public IncompatibleMatrixException(String msg) {
		super(msg);
		this.msg=msg;
	}
}