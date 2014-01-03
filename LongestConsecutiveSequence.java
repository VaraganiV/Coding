import java.util.*;

class Solution {

    public int longestConsecutive(int[] num) {
        if(num.length == 0) return 0;
        HashSet<Integer> set = new HashSet<Integer>();
        // cache
        for(int i : num) {
            set.add(i);
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        // calculate
        int longest = 1;
        for(int i : num) {
            if(visited.contains(i)) continue;
            else {
                int local = 1;
                // search left
                int p = i;
                while(true) {
                    p--;
                    if(set.contains(p)) {
                        local++;
                        visited.add(p);
                    }
                    else break;
                }
                // search right
                p = i;
                while(true) {
                    p++;
                    if(set.contains(p)) {
                        local++;
                        visited.add(p);
                    }
                    else break;
                }
                if(local > longest) longest = local;
            }
        }
        return longest;
    }

    private int merge(HashMap<Integer, Integer> map, int left, int right) {
        int upper = right + map.get(right) - 1;
        int lower = left - map.get(left) + 1;
        int len = upper - lower + 1;
        map.put(upper, len);
        map.put(lower, len);
        return len;
    }

    // cluster merge
    public int longestConsecutive2(int[] num) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 1;
        for(int i : num) {
            if(map.containsKey(i)) continue;
            map.put(i, 1);
            if(map.containsKey(i - 1)) {
                max = Math.max(max, merge(map, i - 1, i));
            }
            if(map.containsKey(i + 1)) {
                max = Math.max(max, merge(map, i, i + 1));
            }
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive2(num));
    }
}