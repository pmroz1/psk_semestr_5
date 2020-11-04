using System;

namespace Simpson
{
    public class Program
    {

        //Function
        static float Func(float x)
        {
            return (float)Math.Sqrt(4+Math.Pow(x,3));
        }
        static float Simpsons_(float ll, float ul,
                                            int n)
        {
            float h = (ul - ll) / n;
            float[] x = new float[10000];
            float[] fx = new float[10000];

            for (int i = 0; i <= n; i++)
            {
                x[i] = ll + i * h;
                fx[i] = Func(x[i]);
            }
            float res = 0;
            for (int i = 0; i <= n; i++)
            {
                if (i == 0 || i == n)
                    res += fx[i];
                else if (i % 2 != 0)
                    res += 4 * fx[i];
                else
                    res += 2 * fx[i];
            }

            res *= (h / 3);
            return res;
        }

        public static void Main()
        {
            
            float lower_limit = 0; // Lower limit 
            float upper_limit = 3; // Upper limit 
            int n = 1000; // Number of interval 
            float result = Simpsons_(lower_limit, upper_limit, n);//result
            Console.WriteLine("The result: {0} for n= {1}",Math.Round(result,15),n);
            Console.ReadKey();
        }
    }
}
