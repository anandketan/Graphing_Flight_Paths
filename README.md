# Flight Routes 
Planning flight routes is done most effectively using graph theory where mathematical structures are used to model pairwise relations between destinations. A graph in this context is made up of vertices, also called nodes which are connected by edges. A distinction is made between undirected graphs, where edges link two vertices symmetrically, and directed graphs, where edges link two vertices asymmetrically. 

Modelling airports as nodes and applying Dijkstra's algorithm to eliminate routes with excessive nodes, we can visualize the above problem as the network below: <br>

   ![flight_planning](https://user-images.githubusercontent.com/72498471/97412811-0d73a100-1928-11eb-9203-d00b80c2e6d6.png)   <br>
   
## Dijkstra's Algorithm
Dijkstra's algorithm allows us to find the shortest path between any two vertices of a graph. It differs from the minimum spanning tree because the shortest distance between two vertices might not include all the vertices of the graph. It involves starting with a weighted graph, choosing a starting vertex and assigning infinity path values to all other nodes.  <br>
Each vertex is visited and its path length updated. If the path length of the adjacent vertex is lesser than new path length, don't update it and avoid updating path lengths of already visited vertices. After each iteration, we pick the unvisited vertex with the least path length. Repeat until all the vertices have been visited and longer routes have been removed.

## Kruskal's Algorithm
Kruskal's algorithm to find the minimum cost spanning tree uses the greedy approach. This algorithm treats the graph as a forest and every node it has as an individual tree: <br>
1. Remove all loops and parallel edges. <br>
2. Arrange all edges in their increasing order of weight. <br>
3. Add the edge which has the least weightage. <br>

## References
Algorithms in Graph Theory: https://www.tutorialspoint.com/graph_theory/index.htm  <br>
Dijkstra's algorithm: https://brilliant.org/wiki/dijkstras-short-path-finder/  <br>
Seven Bridges of Königsberg problem: https://mathworld.wolfram.com/KoenigsbergBridgeProblem.html  <br>
Graph Theory introduction: http://stanford.edu/~jolivier/305_refresher_notes/Basic_graph_theory_and_algorithms.pdf  <br>
Graph Theory certification: https://coursera.org/share/7295db0e23458e17d3c31c22961cef75


