package flagship;
/**---
# ✅ Problem Title
**Self-Crossing Path on a 2D Plane**
---
## 1. Problem Statement
You are given an array of integers `distance`, where each element represents the length of a move on a 2-D X–Y plane.
You start at the origin point:
```
(0, 0)
```
You move according to the values in `distance` following a fixed repeating direction pattern in **counter-clockwise order**:
```
north → west → south → east → north → ...
```
Specifically:
* `distance[0]` meters north
* `distance[1]` meters west
* `distance[2]` meters south
* `distance[3]` meters east
and this pattern repeats for the remaining elements of the array.
Your task is to determine whether the path **crosses itself at any point**.
A path is said to cross itself if, at any step, you visit a position that has already been visited before (including the origin or any other previously visited point).
Return `true` if the path intersects itself, and `false` otherwise.
---
## 2. Input / Output
### Input
* An integer array `distance`
### Output
* A boolean value:
* `true` if the path crosses itself
* `false` otherwise
---
## 3. Constraints
* (1 \le \text{distance.length} \le 10^3)
* (1 \le \text{distance}[i] \le 10^3)
---
## 4. Rules / Clarifications
* You always start at the origin `(0, 0)`.
* Movement directions repeat in the order:
```
north, west, south, east
```
* The path crosses itself if **any point** is visited more than once.
* Revisiting the origin also counts as a self-intersection.
---
## 5. Example (conceptual)
If:
```
distance = [2, 1, 1, 2]
```
The path returns to a previously visited point, so the answer is:
```
true
```
*/
//20260205
public class CrossingDirection {
    class Point {
        int x;
        int y;
    }

    public boolean isSelfCrossing(int[] distance)
    {
        // Replace this placeholder return statement with your code
        return true;
    }
    public static void main(String[] args) {
        CrossingDirection cd = new CrossingDirection();
        int[] distance = {2, 1, 1, 2};
        System.out.println(cd.isSelfCrossing(distance));
    }
}
