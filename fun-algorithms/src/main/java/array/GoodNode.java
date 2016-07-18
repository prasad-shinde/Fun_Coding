package array;

import java.io.*;
import java.util.*;


class GoodNode {
  
  public static int goodNodes(List<Integer> list,int n) {
    boolean[] visited = new boolean[n+1];
    boolean[] isGood = new boolean[n+1];
    
    for(int i=1;i<=n;i++) {
      visited[i] = false;
      isGood[i] = false;
    }
    visited[1] = true;
    isGood[1] = true;
    

    for(int i=1;i<=n;i++) {
      if(!isGood[i]) {
        dfs(i,visited,isGood,list);
      }
    }
    
    if(allGood(isGood))
      return 0;
    
    List<Set<Integer>> connectedComp = new ArrayList<Set<Integer>>();
    
    for(int i = 1;i<=n;i++) {
      int x=-1,y=-1;
      
      if(!isGood[i]) {
        System.out.println(i+" is not good");
        for(int j =0;j<connectedComp.size();j++) {
          if(connectedComp.get(j).contains(i)) {
            if(x == -1)
              x = j;
            else {
              y = j;
              break;
            }
          }
          if(connectedComp.get(j).contains(list.get(i-1)) && (i!=list.get(i-1))) {
            if(x == -1)
              x = j;
            else {
              y = j;
              break;
            }
          }
        }
        
        Set<Integer> temp = new HashSet<Integer>();
        if(x != -1 && y!=-1) {
          temp.addAll(connectedComp.get(x));
          temp.addAll(connectedComp.get(y));
          
          Set<Integer> s1,s2;
          s1 = connectedComp.get(x);
          s2 = connectedComp.get(y);
          connectedComp.remove(s1);
          connectedComp.remove(s2);
          connectedComp.add(temp);
        } else if(x!=-1) {
          connectedComp.get(x).add(i);
          connectedComp.get(x).add(list.get(i-1));
        } else {
          temp.add(i);
          temp.add(list.get(i-1));
          connectedComp.add(temp);
        }
      }
    }
    
    return connectedComp.size();
  }
  
  public static boolean allGood(boolean[] isGood) {
    for(boolean each:isGood) {
      if(!each)
        return false;
    }
    return true;
  }
  
  
  public static boolean dfs(int node,boolean[] visited,boolean[] isGood,List<Integer> list) {
    if(isGood[node])
      return true;

    if(visited[node])
      return false;
    
    visited[node] = true;
    boolean result = dfs(list.get(node-1),visited,isGood,list);
    if(result) {
      isGood[node] = true;
      return true;
    } else
      return false;
  }
  
  
  
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<Integer>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    System.out.println(goodNodes(list,5));
  }
}
