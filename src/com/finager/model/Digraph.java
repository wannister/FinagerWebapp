package com.finager.model;

/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Digraph} class represents a directed graph of vertices
 * named 0 through V-1.
 */
public class Digraph {
	private final int V; // number of vertices in this digraph
	private int E; // number of edges in this digraph
	private Bag<Integer>[] adj; // adj[v] = adjacency list for vertex v
    /**
     * Initializes an empty digraph with V vertices.
     * @param V-The number of vertices.
     */
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
    /**
     * Returns the number of vertices in this digraph.
     * @return-The number of vertices in this digraph.
     */
	public int V() {
		return V;
	}
    /**
     * Returns the number of edges in this digraph.
     * @return-The number of edges in this digraph.
     */
	public int E() {
		return E;
	}
    /**
     * Adds the directed edge v→w to this digraph.
     * @param v-The tail vertex.
     * @param w-The head vertex.
     */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     * @param v-The vertex.
     * @return-The vertices adjacent from vertex {@code v} in this digraph, as an iterable.
     */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
    /**
     * Returns the reverse of the digraph.
     * @return-The reverse of the digraph.
     */
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for (int v = 0; v < V; v++)
			for (int w : adj(v))
				R.addEdge(w, v);
		return R;
	}
}