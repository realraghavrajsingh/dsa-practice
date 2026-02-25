package com.leetcode.medium.graph;

/**
 * LC200: Number of Islands
 * Difficulty: Medium | Google: L4
 * Type: Graph, DFS/BFS
 * Tags: graph, dfs, faang
 *
 * <h2>Problem Statement</h2>
 * <p>Given an {@code m x n} 2D binary grid {@code grid} which represents a map of '1's (land) and '0's
 * (water), return the number of islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of the grid are surrounded
 * by water.</p>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>m == grid.length</li>
 *   <li>n == grid[i].length</li>
 *   <li>1 ≤ m, n ≤ 300</li>
 *   <li>grid[i][j] is '0' or '1'.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>
 * Example 1:
 *   Input:  grid = [["1","1","1","1","0"],
 *                   ["1","1","0","1","0"],
 *                   ["1","1","0","0","0"],
 *                   ["0","0","0","0","0"]]
 *   Output: 1
 *
 * Example 2:
 *   Input:  grid = [["1","1","0","0","0"],
 *                   ["1","1","0","0","0"],
 *                   ["0","0","1","0","0"],
 *                   ["0","0","0","1","1"]]
 *   Output: 3
 * </pre>
 *
 * <p>Approach: DFS from each unvisited '1', mark visited.</p>
 * <p>Time: O(m*n) | Space: O(m*n) - recursion</p>
 */
public class LC200_NumberOfIslands_Medium_Graph {

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1')
            return;
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println(numIslands(grid)); // 1
    }
}
