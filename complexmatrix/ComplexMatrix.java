package complexmatrix;
import java.io.*;
import java.util.ArrayList;

public class ComplexMatrix {
	private Complex matrix[][];
	private int m;
	private int n;
	
	public ComplexMatrix(int m,int n) {
		matrix=new Complex[m][n];
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				this.matrix[x][y]=new Complex();
			}
		}
		this.n=n;
		this.m=m;
	}
	
	public ComplexMatrix(Complex[][] input) {
		int m=input.length;
		int n;
		if(input[0]!=null) {
			n=input[0].length;
		}else {
			n=0;
		}
		matrix=new Complex[m][n];
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				this.matrix[x][y]=input[x][y];
			}
		}
		this.n=n;
		this.m=m;
	}
	
	public ComplexMatrix add(ComplexMatrix cm) throws MatrixDimensionException{
		if(this.m!=cm.m||this.n!=cm.n) {
			throw new MatrixDimensionException("matrices must have the same dimensions for addition to be possible");
		}
		ComplexMatrix output=new ComplexMatrix(m,n);
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				output.matrix[x][y]=this.matrix[x][y].add(cm.matrix[x][y]);
			}
		}
		return output;
	}
	
	public ComplexMatrix mult(ComplexMatrix cm) throws MatrixDimensionException{
		int newm=m;
		int newn=cm.n;
		if(this.n!=cm.m) {
			throw new MatrixDimensionException("# of rows in matrix 1 must be equal to # of columns in matrix 2 for multiplication to be possible");
		}
		ComplexMatrix prod=new ComplexMatrix(newm,newn);
		for(int i=0;i<newm;i++) {
			for(int j=0;j<newn;j++) {
				for(int k=0;k<n;k++) {
					prod.matrix[i][j]=prod.matrix[i][j].add(this.matrix[i][k].multiply(cm.matrix[k][j]));
				}
			}
		}
		return prod;
	}
		
	public String toString() {
		String output="";
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				output=output+this.matrix[x][y].toString()+" , ";
			}
		}
		return output;
	}
	
	public void printmat() { //this is for testing
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				System.out.print(this.matrix[x][y].toString()+" ");
			}
			System.out.println();
		}
	}
	
	public void justreal() { //this is for testing
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				System.out.print(this.matrix[x][y].getReal()+" ");
			}
			System.out.println();
		}
	}
	
	public static ComplexMatrix read(String filename) throws IncompatibleMatrixException{
		int m,n;
		ArrayList<ArrayList<Complex>> hold=new ArrayList<ArrayList<Complex>>();
		String inline=null;
		FileReader f=null;
		try {
    		f=new FileReader(filename);
    	}catch(FileNotFoundException fnf) {
    		System.err.println("file not found: "+fnf.getMessage());
    	}
		BufferedReader in=new BufferedReader(f);
		try {
			inline=in.readLine();
			while(inline!=null) {
				try {
					inline=inline.trim();
					String[] nums=inline.split(" ");
					ArrayList<Complex> temp=new ArrayList<Complex>();
					for(int x=0;x<nums.length;x++) {
						String[] c=nums[x].split("_");
						double a=Double.parseDouble(c[0]);
						double b=Double.parseDouble(c[1]);
						Complex t=new Complex(a,b);
						//System.out.println(t.toString());
						temp.add(t);
					}
					hold.add(temp);
				}catch(NumberFormatException nfe) {
    				System.err.println("cannot read into array; number format problem while reading: "+nfe.getMessage());
    			}
				inline=in.readLine();
			}
			f.close();
		}catch(IOException ioe) {
    		System.err.println("error reading file: "+ioe.getMessage());
    	}
		m=hold.size();
		if(hold.get(0)!=null) {
			n=hold.get(0).size();
		}else {
			n=0;
		}
		for(int j=1;j<m;j++) {
			if(n!=hold.get(m-1).size()) {
				throw new IncompatibleMatrixException("all rows must have the same number of columns");
			}
		}
		Complex[][] mat=new Complex[m][n];
		for(int x=0;x<m;x++) {
			for(int y=0;y<n;y++) {
				mat[x][y]=hold.get(x).get(y);
			}
		}
		ComplexMatrix initializedmatrix=new ComplexMatrix(mat);
		return initializedmatrix;
	}
	
	public void write(String filename) {
		FileWriter fout=null;
		try {
			fout=new FileWriter(filename);
			BufferedWriter out=new BufferedWriter(fout);
			for(int i=0;i<this.m;i++) {
				String line="";
				for(int j=0;j<this.n;j++) {
					line=line+this.matrix[i][j].toOutputfileFormat()+" ";
				}
				out.write(line);
				out.newLine();
				out.flush();
			}
		}
		catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: "+e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (fout != null) {
                try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            } 
        }
	}
		
	public static void main(String[] args){
//		ComplexMatrix cm=new ComplexMatrix(1,2);
//		ComplexMatrix cmm=new ComplexMatrix(2,4);
//		ComplexMatrix m1=read("complexmatrix1.txt");
//		ComplexMatrix m2=read("complexmatrix2.txt");
//		ComplexMatrix m3=m1.mult(m2);
//		m3.printmat();
//		m1.write("brokenmatrix.txt");
//		ComplexMatrix y=read("brokenmatrix.txt");
//		y.justreal();
		
	}
}
