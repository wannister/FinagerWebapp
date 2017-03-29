package com.finager.model;

/**
 * 
 * 
 *
 */
public class Digraph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
    /**
     * 
     * @param V
     */
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
    /**
     * 
     * @return
     */
	public int V() {
		return V;
	}
    /**
     * 
     * @return
     */
	public int E() {
		return E;
	}
    /**
     * 
     * @param v
     * @param w
     */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
    /**
     * 
     * @param v
     * @return
     */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
    /**
     * 
     * @return
     */
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for (int v = 0; v < V; v++)
			for (int w : adj(v))
				R.addEdge(w, v);
		return R;
	}
}