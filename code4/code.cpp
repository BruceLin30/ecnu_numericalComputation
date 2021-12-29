#include <stdio.h>
int main()
{
    return 0;
}
void jacobi()
{
        for (int i = 0; i < n; i++)
    {
                for (int j = 0; j < n; j++)
        {
                            if (j != i)
                    a[i][j] = -a[i][j] / a[i][i];
                   
        }
                b[i] = b[i] / a[i][i];
                a[i][i] = 0;
           
    }
        for (int i = 0; i < n; i++)
    {
                for (int j = 0; j < n; j++)
        {
                        printf("%f ", a[i][j]);

                   
        }
                printf("\n");
           
    }
        shuruchushix();
        for (int i = 0; i < n; i++)
    {
                x[count + 1][i] = a[i][0] * x[count][0] + a[i][1] * x[count][1] + a[i][2] * x[count][2] + b[i];
           
    }
        for (int i = 0; i < n; i++)
    {
                printf("%f ", x[count + 1][i]);
           
    }
        printf("\n");
        count++;
        double Max = 0;
        Max = bijiao(bijiao((x[count][0] - x[count - 1][0]), (x[count][1] - x[count - 1][1])), (x[count][2] - x[count - 1][2]));

        while (1)
    {
                for (int i = 0; i < n; i++)
        {
                        x[count + 1][i] = a[i][0] * x[count][0] + a[i][1] * x[count][1] + a[i][2] * x[count][2] + b[i];
                   
        }
                
        count++;
                for (int i = 0; i < n; i++)
        {
                        printf("%f ", x[count][i]);
                   
        }
                printf("\n");
                Max = bijiao(bijiao((x[count][0] - x[count - 1][0]), (x[count][1] - x[count - 1][1])), (x[count][2] - x[count - 1][2]));
                printf("%f\n", Max);
                
        if (fabs(Max) < 0.01)
        {
                        break;
                   
        }
           
    }
        for (int i = 0; i < n; i++)
    {
                printf("%f", x[count][i]);
           
    }
}
void shuruchushix2()
{
        for (int i = 0; i < n; i++)
    {
                scanf("%lf", &x2[i]);
           
    }
}
void gaosi()
{
        for (int i = 0; i < n; i++)
    {
                for (int j = 0; j < n; j++)
        {
                            if (j != i)
                    a[i][j] = -a[i][j] / a[i][i];
                   
        }
                b[i] = b[i] / a[i][i];
                a[i][i] = 0;
           
    }
        for (int i = 0; i < n; i++)
    {
                for (int j = 0; j < n; j++)
        {
                        printf("%f ", a[i][j]);

                   
        }
                printf("\n");
           
    }
        shuruchushix2();
        for (int i = 0; i < n; i++)
    {
                double sum = 0;
                for (int j = 0; j < n; j++)
        {
                        sum += a[i][j] * x2[j];
                   
        }
                x2[i] = sum + b[i];
           
    }
        for (int i = 0; i < n; i++)
    {
                printf("%f ", x2[i]);

           
    }
        double Max = 0;
        while (1)
    {
                double x3 = x2[0], y = x2[1], z = x2[2];

                for (int i = 0; i < n; i++)
        {
                        double sum = 0;
                           for (int j = 0; j < n; j++)
            {
                                sum += a[i][j] * x2[j];
                           
            }
                        x2[i] = sum + b[i];
                   
        }
                for (int i = 0; i < n; i++)
        {
                        printf("%f ", x2[i]);
                   
        }
                printf("\n");
                
        Max = bijiao(bijiao((x2[0] - x3), (x2[1] - y)), (x2[2] - z));
                printf("%f\n", Max);
                
        if (fabs(Max) < 0.01)
        {
                        break;
                   
        }
           
    }
        for (int i = 0; i < n; i++)
    {
                printf("%f ", x2[i]);
           
    }
        printf("\n");
}