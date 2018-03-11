package search;

public class BinarySearch {
    //a - int array, n - size of array
    // PRE: a[i] >= a[i + 1], 0 <= i < n - 1
    static int BinIter(int x, int a[]) {
        int l = -1;
        int r = a.length;
        //INV:r > l + 1 && r - l > r' - l' && (l == -1 || (0 <= l < r && a[l] > x)) && (r == n || (l < r < n && a[r] <= x))
        while(r- l > 1){
            int m = (l + r) / 2;
            // m = (l + r) / 2 && (l <= m < r || l < m <= r)
            if (a[m] <= x){
                r = m;
                //l' = l, r' = m
                // a[m] <= x ---> a[r'] <= x
                // m < r ---> r' < r
                //l' = l, r' < r ---> r - l > r' - l'
            }
            else{
                l = m;
                //l' = m, r' = r
                // a[m] > x ---> a[l'] > x
                // m > l ---> l' >l
                // l' > l, r' == r ---> r - l > r' - l'

            }
        }
        return r;
    }
    //POST: R = r && (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) ||
    // || (R == n && n > 0 && a[n - 1] > x) || (n == 0 && R == 0)


    //a - int array, n - size of array
    // PRE: a[i] >= a[i + 1], 0 <= i < n - 1 && -1 <= l < r <= n && (r == n || x >= a[r]) && (l == -1 || a[l] > x)
    //POST
    //l <= R <= r && ((R > 0 && a[R - 1] > x) || (R == 0 && a[0] <= x))  && ((R < n && x >= a[R]) || (R == n && a[n - 1] > x)
    static int BinRec(int[] a, int x, int l, int r){
        if (r - l == 1){
            //l == r - 1 && (l < r < n && a[r] <= x) && (l == -1 || (0 <= l < r && a[l] > x)) ---->
            //(0 < R < n && a[R] <= x && a[R - 1] > x) || (R == n && a[n - 1] > x) || (R == 0 && a[R] <= x)
            return r;
        }
        else{
            int m = (l + r) / 2;
            // m = (l + r) / 2 && r > l + 1 && (l <= m < r || l < m <= r) &&
            if (a[m] <= x){
                // PRE: a[i] >= a[i + 1], 0 <= i < n - 1 && -1 <= l < r <= n && (r == n || x >= a[r]) && (l == -
                // || a[l] > x) && m = (l + r) / 2 && a[m] <= x && l != r - 1
                // r' = m
                // l' = l
                // a[m] <= x ---> a[r'] <= x ---> (r == n || x >= a[r])
                return BinRec(a, x, l, m);
            }
            else{
                // PRE: a[i] >= a[i + 1], 0 <= i < n - 1 && -1 <= l < r <= n && (r == n || x >= a[r]) && (l == -1 || a[l] > x) &&
                //&& m = (l + r) / 2 && a[m] > x && l != r - 1
                // r' = r
                // l' = m
                // a[m] > x ---> a[l'] > x ---> (l == -1 || a[l] > x)
                return BinRec(a, x, m, r);
            }
        }
    }
    //POST
    // a[i] = a[i]'
    //l <= R <= r && ((R > 0 && a[R - 1] > x) || (R == 0 && a[0] <= x))  && ((R < n && x >= a[R]) || (R == n && a[n - 1] > x)


    public static void main(String[] args){
        int n = args.length - 1;
        int x = Integer.parseInt(args[0]);
        int[] a = new int[n];
        for (int i = 1; i <= n; i++)
            a[i - 1] = Integer.parseInt(args[i]);
        int l = BinIter(x, a);
        System.out.println(l);
    }
}
