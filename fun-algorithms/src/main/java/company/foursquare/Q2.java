package company.foursquare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2 {
	static class TrieNode {
		private String component;
		private Map<String,TrieNode> childrens;
		private String endpoint;
		
		public TrieNode(String component) {
			this.component = component;
			this.endpoint = "404";
			this.childrens = new HashMap<String, Q2.TrieNode>();
		}
		
		public TrieNode(String component,String endpoint) {
			this.component = component;
			this.endpoint = endpoint;
			this.childrens = new HashMap<String, Q2.TrieNode>();
		}
		
		public void add(String[] pathComponents,String endpoint) {
			if(0 == pathComponents.length) {
				this.endpoint = endpoint;
				return;
			}
			add(pathComponents,endpoint,1);
		}
		
		private void add(String[] pathComponents,String endpoint,int index) {
			String first = pathComponents[index];
			if(!childrens.containsKey(first)) {
				childrens.put(first,new TrieNode(first));
			}
			TrieNode child = childrens.get(first);
			if(pathComponents.length == index + 1) {
				child.setEndpoint(endpoint);
				return;
			}
			
			child.add(pathComponents, endpoint,index+1);
		}
		
		private void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}
		
		private String getEndpoint(String[] pathComponent) {
			return getEndpoint(pathComponent,1);
		}
		
		public String getEndpoint(String[] pathComponent,int index) {
			if(index >= pathComponent.length) {
				return endpoint;
			} 
			String component = pathComponent[index];
			TrieNode child;
			if(childrens.containsKey(component)) {
				child = childrens.get(component);
				if(child != null) {
					String endpoint = child.getEndpoint(pathComponent,index+1);
					if(!endpoint.equals("404"))
						return endpoint;
				}
			}
			child = childrens.get("X");
			if(child != null) {
				return child.getEndpoint(pathComponent,index+1);
			}
			return "404";
		}
	}
	
	
    /** 
     * Since we have all the routes given already. We want to process them and store in such
     * a way that we don't want every query take O(n) time where n would be the number of 
     * routes. We need to design our storage structure for the routes in such a way that we
     * don't have to query all of them to find the right answer. Here I have decided to store
     * them in form of a Trie, where the root would be the root and all the components in the
     * path would be a node nested inside the tree downwards.   
     * 
     * Algorithm : 
     * Stage 1 :
     * Convert the routes into Trie structure 
     * 	  -> takes O(n*h) time
     *    where n = number of routes, h = max number of component in any route path
     * 
     * Stage 2 : 
     * To find where to route a path, we need to break it down into component and then
     * follow the Trie from root to leaf to find its endpoint.
     *    -> takes O(h) times
     *    where h = height of the Trie
     * 
     * @param routes
     * @param paths
     * @return
     */
    private static List<String> routeAll(List<Route> routes, List<String> paths) {
        List<String> endpoints = new ArrayList<String>();
        // Your code here
        TrieNode root = new TrieNode("");
        for(Route route:routes) {
        	root.add(route.path.split("/"), route.endpoint);
        }
        
        for(String path:paths) {
        	endpoints.add(root.getEndpoint(path.split("/")));
        }
        return endpoints;
    }

    /**
     *      Hey! You probably won't need to edit anything below here.
     */

    static class Route {
        String path;
        String endpoint;
        public Route(String path, String endpoint) {
            this.path = path;
            this.endpoint = endpoint;
        }
    }

    private static List<Route> getRoutes(InputStream is) throws IOException {
        List<Route> routes = new ArrayList<Route>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null && line.length() != 0) {
            String[] tokenizedLine = line.split(" ");
            routes.add(new Q2.Route(tokenizedLine[0], tokenizedLine[1]));
        }
        return routes;
    }

    private static List<String> getPaths(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> paths = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null && line.length() != 0) {
            paths.add(line);
        }
        return paths;
    }

    public static void main(String... args) throws IOException {
        List<Route> routes = Q2.getRoutes(new FileInputStream(args[0]));
        List<String> paths = Q2.getPaths(System.in);

        for(String endpoint : Q2.routeAll(routes, paths)) {
            System.out.println(endpoint);
        }
    }
}
