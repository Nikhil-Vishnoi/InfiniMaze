package Brain;
/**
 * Uses a Uptree Array to create a disjoint set
 * @author Nikhil
 *
 */
public class DisjointSet {
	int[] uptree; 
	int size; 
	int sets; 
	int number_sets() {return sets;}
	/*
	 * Initilizes a Disjointset and fill the uptree 
	 * 
	 * @param size 		Size of the uptree 
	 */
	public DisjointSet(int size) {
		this.size = size; 
		uptree = new int[size];
		for (int i = 0; i < size; i++) {
			uptree[i] = -1; 
		}
		sets = size; 
	}
	/*
	 * Check to see if the two leafs are connected in the same 
	 * tree 
	 * @param ind1 		   first index to check 
	 * @param ind2 		   second index to check 
	 * @return boolean     Wether they are in the same tree
	 */
	public boolean connected(int ind1, int ind2) {
		return find(ind1)==find(ind2); 
	}
	/*
	 * Connects two of the Nodes 
	 * Will combine the smaller size to be under the 
	 * bigger sized one.  
	 */
	public void connect(int ind1, int ind2) {
//		System.out.println("ind1 "+ind1+" ind2 "+ind2+"\n"); 
		int i1 = find(ind1); 
		int i2 = find(ind2);
		if (i1 == i2) return;
		sets--; 
		// figure ouch which one is more negative and combines them 
		if (uptree[i1] < uptree[i2]) {
			uptree[i1] = uptree[i1] + uptree[i2]; 
			uptree[i2] = i1; 
		}
		else {
			uptree[i2] = uptree[i1] + uptree[i2]; 
			uptree[i1] = i2; 
		}
	}
	/*
	 * Finds the root of the uptree 
	 * @param    index to search 
	 * @return   index of the root 
	 */
	public int find(int index) {
		if (index < 0 || index >= size)
			return -1; 
		// Root has been found 
		if (uptree[index] < 0) 
			return index; 
		// We need to search up the tree while updating with path compression
		else {
			int root = find(uptree[index]); 
			uptree[index] = root; 
			return root; 
		}
	}
	
}
