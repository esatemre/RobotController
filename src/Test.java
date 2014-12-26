
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[][] = {{3,6,2},{1,5,6},{2,1,5}};
		int B[][] = {{1,1,2},{3,3,3},{2,2,1}};
		int[][] liste_A = createNewMatrix(A);
		int[][] liste_B = createNewMatrix(B);
		display(liste_A);
		display(liste_B);
			
	}
	
	public static int average(int[][] matrix){
		int top=0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				top+=matrix[i][j];
			}
		}
		return (top/(matrix.length*matrix.length));
	}
	
	public static int[][] createNewMatrix(int[][]matrix){
		int average= average(matrix);
		int[][] newMatrix = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				newMatrix[i][j]= average - matrix[i][j];
			}
		}
		return newMatrix;		
	}
	
	public static void display(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][i]+" ");
			}
			System.out.println("  ");	
		}	
		
		System.out.println("\n\n\n");
	}

}
