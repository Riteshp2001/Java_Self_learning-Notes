package Algorithms_Part1;

public class Union_Find_Connectivity {
    //Array Data Structure
    private static int[] connectivity_Array;
    private static int[] connectivity_Size;

    Union_Find_Connectivity(int n) {
        connectivity_Array = new int[n];
        connectivity_Size = new int[n];
        for (int i = 0; i < connectivity_Array.length; i++) connectivity_Array[i] = i;
    }

//     boolean is_Connected(int p , int q){
//        return connectivity_Array[p] == connectivity_Array[q];
//    }
////    [0,1,2,3,3,5,6,7,8,9]
//    static void establish_Union(int p , int q){
//        //Worst Algorithm
//        int p_id = connectivity_Array[p];
//        int q_id = connectivity_Array[q];
//        for(int i = 0;i < connectivity_Array.length;i++){
//            if(connectivity_Array[i]==p_id)connectivity_Array[i]=q_id;
//        }
//    }

    //lazy approach
    private static int root(int i) {
        while (i != connectivity_Array[i]) i = connectivity_Array[i];
        return i;
    }

    boolean is_Connected(int p, int q) {
        return root(p) == root(q);
    }

    //    [0,1,2,3,3,5,6,7,8,9]
//    static void establish_Union(int p , int q){
//        int i = root(p);
//        int j = root(q);
//        connectivity_Array[i] = j;
//    }
    // Improvement Weighted Quick Union
    public static void establish_Union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (connectivity_Size[i] < connectivity_Size[j]) {
            connectivity_Array[i] = j;
            connectivity_Size[j] += connectivity_Size[i];
        } else {
            connectivity_Array[j] = i;
            connectivity_Size[i] += connectivity_Size[j];
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int total_Connections = 10;
//        Union_Find_Connectivity uf = new Union_Find_Connectivity(total_Connections);
//        establish_Union(4,3);
//        establish_Union(5,4);
//        establish_Union(6,5);
//        establish_Union(2,1);
//        establish_Union(5,0);
//        for(int i : connectivity_Array){
//            System.out.print(i+" ");
//        }
//        while(total_Connections-->=0){
//            int p = sc.nextInt();
//            int q = sc.nextInt();
//            if(!uf.is_Connected(p,q)){
//                System.out.println(p+" "+q);
//            }else{
//                System.out.println(p+" "+q+" - Connected");
//            }
//        }
    }
}
