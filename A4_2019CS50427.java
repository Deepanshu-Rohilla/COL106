import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class A4_2019CS50427 {
    public static void main(String[] args) throws FileNotFoundException{
        Graph g = new Graph(); // Initialising the variables
        String nodes = args[0]; 
        String edges = args[1];
        String doFunction = args[2];
        Scanner sc1 = new Scanner(new File(nodes));
        sc1.useDelimiter("\n");  // Using comma(",") and nextLineline character ("\n") as delimeter
        sc1.nextLine();
        int count = 0;
        while (sc1.hasNext()){
            count += 1;
            String s = sc1.nextLine().replaceAll("\\P{Print}", ""), temp;
            int n = s.length();
            if(s.charAt(0)=='\"'){
                temp = s.substring(1, n/2-1);
            }
            else{
                temp = s.substring(0, n/2);
            } 

            g.insertVertex(temp);
            // System.out.println(temp + "  ha  " + s + " ha " + n);
            // if (temp.equals("AaSpider-Man (Peter Parker)Aa".replaceAll("\\P{Print}", ""))) System.out.println("haha"); 
            // break;
        }
        // System.out.println(count+" "+g.getV_G());
        Scanner sc = new Scanner(new File(edges));
        sc.useDelimiter("\n"); 
        sc.nextLine();
        String s1="", s2="";
        while(sc.hasNext()){
            s1="";
            s2="";
            String s = sc.nextLine();
            int i=0,n=s.length();
            while(i<n && s.charAt(i)!=','){
                if(s.charAt(i)=='\"'){
                    i++;
                    while(i<n && s.charAt(i)!='\"'){
                        s1+=s.charAt(i); 
                        i++;   
                    }
                    i++;
                }
                else{
                    s1+=s.charAt(i);
                    i++;
                }
            }
            int j=i+1;
            while(j<n && s.charAt(j)!=','){
                if(s.charAt(j)=='\"'){
                    j++;
                    while(j<n && s.charAt(j)!='\"' ){
                        s2+=s.charAt(j); 
                        j++;
                    }
                    j++;
                }
                else{
                    s2+=s.charAt(j); 
                    j++;
                }
            }
            String s3 ="";
            j++;
            while(j<n){
                s3+=s.charAt(j);
                j++;
            }
            s3 = s3.replaceAll("\\P{Print}", "");
            s1 = s1.replaceAll("\\P{Print}", "");
            s2 = s2.replaceAll("\\P{Print}", "");
            int l = Integer.parseInt(s3);
            g.insertEdge(new String(s1), new String(s2), l); 
        }
        // System.out.println(g.getV_G());
        // System.out.println(g.getE_G());
        if(doFunction.equals("average")){
            float f=g.average();
            System.out.println(String.format("%.2f", f));
        }
        else if(doFunction.equals("rank")){
            g.rank();
        }
        else{
            g.independent_storylines_dfs();
        }
    }
    
}

class Edge{
    String destination;
    int weight;
    Edge(String destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph{ 
    private static Map<String, LinkedList<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void insertVertex(String s){
        adjacencyList.put(s, new LinkedList<Edge>()); 
    } 
    public void insertEdge(String source,String destination,int weight){ 
        if (adjacencyList.get(source) == null){
            insertVertex(source); 
        }
        if (adjacencyList.get(destination) == null){
            insertVertex(destination);
        }
        adjacencyList.get(source).add(new Edge(destination,weight));
        adjacencyList.get(destination).add(new Edge(source,weight));
    }
    public float average(){ // Get average degree for a vertex
        int avg = 0;
        for (String v : adjacencyList.keySet()){ 
            avg += getDegree(v); 
        }
        int vg = this.getV_G();
        float ans  = (float) avg/vg;
        return ans;
    }

    public int getWeightSum(String s){ // Get total weight sum for a node
        int w=0;
        for(Edge e: adjacencyList.get(s)){
            w += e.weight;
        }
        return w;
    }

    public boolean firstSmall(String s1, String s2){ // Comparator to return true if first parameter string is "smaller"
        int w1 = getWeightSum(s1);
        int w2 = getWeightSum(s2);
        if(w1<w2){
            return true;
        }
        else if(w1>w2){
            return false;
        }
        else{
            int x = s1.compareTo(s2);
            if(x>0){
                return false;
            }
            else{
                return true;
            }
        }
    }

    public void merge(String[] vert,int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        String l1[] = new String[n1];
        String l2[] = new String[n2];
        for (int i = 0; i < n1; ++i)
            l1[i] = vert[l + i];
        for (int j = 0; j < n2; ++j)
            l2[j] = vert[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (this.firstSmall(l1[i], l2[j])) {
                vert[k] = l1[i];
                i++;
            }
            else {
                vert[k] = l2[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            vert[k] = l1[i];
            i++;
            k++;
        }
        while (j < n2) {
            vert[k] = l2[j];
            j++;
            k++;
        }

    }
    public void sort(String[] vert,int l, int r){ // Perform merge sort
        if(l<r){
            int m = (l + r) / 2;
            sort(vert, l, m);
            sort(vert,m + 1, r);
            merge(vert,l, m, r);
        }
    }
    public void rank(){
        int n = this.getV_G();
        String[] vert = new String[n];
        int i=0;
        for (String v : adjacencyList.keySet()){ 
            vert[i] = v;
            i++;
        }
        sort(vert, 0, n-1); 
        for(i=n-1;i>0;i--){
            System.out.print(vert[i]);
            System.out.print(",");
        }
        System.out.print(vert[i]);

    } 

    public void sortList(ArrayList<String> arr,int l, int r){
        if(l<r){
            int m = (l + r) / 2;
            sortList(arr, l, m);
            sortList(arr,m + 1, r);
            mergeList(arr,l, m, r);
        }
    }

    public void mergeList(ArrayList<String> arr,int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        String l1[] = new String[n1];
        String l2[] = new String[n2];
        for (int i = 0; i < n1; ++i)
            l1[i] = arr.get(l + i);
        for (int j = 0; j < n2; ++j)
            l2[j] = arr.get(m+1+j);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (l1[i].compareTo(l2[j])>0) { // Only this line changed for descending 
                arr.set(k, l1[i]);
                i++;
            }
            else {
                arr.set(k, l2[j]);
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, l1[i]);
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, l2[j]);
            j++;
            k++;
        }

    }

    public void sortListOfList(ArrayList<ArrayList<String>> arr,int l, int r){
        if(l<r){
            int m = (l + r) / 2;
            sortListOfList(arr, l, m);
            sortListOfList(arr,m + 1, r);
            mergeListOfList(arr,l, m, r);
        }
    }

    public void mergeListOfList(ArrayList<ArrayList<String>> arr,int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        ArrayList<ArrayList<String>> l1 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> l2 = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < n1; ++i)
            l1.add(arr.get(l + i));
        for (int j = 0; j < n2; ++j)
            l2.add(arr.get(m+1+j));

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (largerList(l1.get(i), l2.get(j))) { // Only this line changed for descending 
                arr.set(k, l1.get(i));
                i++;
            }
            else {
                arr.set(k, l2.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, l1.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, l2.get(j));
            j++;
            k++;
        }

    }

    public boolean largerList(ArrayList<String> l1, ArrayList<String> l2){ // Comparator to return true if first parameter string is "smaller"
        int w1 = l1.size();
        int w2 = l2.size();
        if(w1<w2){
            return false;
        }
        else if(w1>w2){
            return true;
        }
        else{
            int i=0;
            while(i<w1){
                if(l1.get(i).compareTo(l2.get(i))>0){
                    return true;
                }
                else if(l1.get(i).compareTo(l2.get(i))<0){
                    return false;
                }
                else{
                    i++;
                }
            }
        }
        return true;
    }


    public void independent_storylines_dfs(){
        Map<String, Boolean> visited = new HashMap<>();
        ArrayList<ArrayList<String>> different_components  = new ArrayList<ArrayList<String>>();
        for(String v: adjacencyList.keySet()){
            visited.put(v, false);
        }
        for(String v: adjacencyList.keySet()){
            if(!visited.get(v)){
                ArrayList<String> component = new ArrayList<String>();
                this.DFS(v,visited,component);
                this.sortList(component, 0, component.size()-1);
                different_components.add(component);
            }
        }
        this.sortListOfList(different_components, 0, different_components.size()-1);
        for(ArrayList<String> l: different_components){
            int n = l.size();
            int it =0;
            while(it<n-1){
                System.out.print(l.get(it));
                System.out.print(',');
                it++;
            }
            System.out.print(l.get(it));
            System.out.println("");
        }
    }
    public void DFS(String v, Map<String, Boolean> visited,ArrayList<String> component){
        visited.replace(v, false, true);
        component.add(v);
        for(Edge e: adjacencyList.get(v)){
            String a = e.destination;
            if(!visited.get(a)){
                DFS(a,visited,component);
            }
        }
    }



    public int getDegree(String v){ // Get degree of a vertex
        return adjacencyList.get(v).size();
    }
    public int getV_G(){ // Get total vertex count
        return adjacencyList.size();
    } 
  
    public int getE_G(){ // Get total edge count
        int count = 0; 
        for(String v : adjacencyList.keySet()){
            count += adjacencyList.get(v).size(); 
        }
        return count / 2; 
    } 
}