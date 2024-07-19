clear all
close all

TOL = 1.0e-08;
I = [0, pi/2];
f = @(x) x-cos(x);
df = @(x) sin(x) + 1;

%%
x = 1;
res = abs(f(x));
iter = 0;
fprintf("x_%d = %.2f, res = %.2e\n",iter,x,res);

while res>=TOL & iter<100
    x = x - f(x)/df(x);
    res = abs(f(x));
    iter = iter+1;
    fprintf("x_%d = %.2f, res = %.2e\n",iter,x,res);
end

%%
xx = linspace(I(1),I(2),1000);

plot(xx,f(xx),'k-');
grid on
hold on
plot(x,0,'ro');
set(gca,'XAxisLocation','origin','YAxisLocation','origin')
legend('f','Nullstelle')