package search;

public class BinarySearchSpan {
    //a - int array, n - size of array
    // PRE: a[i] >= a[i + 1], 0 <= i < n - 1
    //POST: R = r && (R > 1 && a[R] <= x && a[R - 1] > x || R == 0 && a[R] <= x) ||
    // || (R == n && n > 0 && a[n - 1] > x) && a[i] == a'[i]
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


    //a - int array, n - size of array
    // PRE: a[i] >= a[i + 1], 0 <= i < n - 1 && -1 <= l < r <= n && (r == n || x >= a[r]) && (l == -1 || a[l] >= x)
    // && a[i] == a[i]'
    //POST
    //a[i] == a[i]' && l <= R <= r && (R < n - 1 && x < a[R + 1] || R == n - 1 && a[R] >= x)
    static int BinRecR(int[] a, int x, int l, int r) {
        if (r - l == 1)
            //R = l ---> l <= R <= r
            //
            return l;
        else{
            int m = (l + r) / 2;
            //m == (l + r) / 2
            //PRE
            if (a[m] < x) {
                return BinRecR(a, x, l, m);
                // a[i]' == a[i]
                // l' = l --> (l == -1 || a[l] >= x)
                // r' = m
                // r > l + 1 && r' = m && l < m < r ---> r' - l' >= 1
            }
            else {
                return BinRecR(a, x, m, r);
                // a[i]' == a[i]
                // l' = m
                // r' = r ---> (r == n ||
                // r > l + 1 && l' = m && l < m < r ---> r' - l' >= 1
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
        int r = BinRecR(a, x, -1, n);
        System.out.println(l + " " + (r - l + 1));
    }
}
