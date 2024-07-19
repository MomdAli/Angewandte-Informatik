% Newton_Verfahren.m 
clc, clearvars, close all

% Funktionen
N = @(y) y^2 + log(y)^2; % Die Funktion N
f = @(y) 2 * y + 2 * (log(y) / y); % Erste Ableitung
df = @(y) y^(-2) * (-2 * log(y) + 2) + 2; % Zweite Ableitung

% Newton-Verfahren
x = 1; % Startwert
TOL = 1.0e-08; % Toleranz
res = abs(f(x)); % Resultat
iter = 0;
fprintf("Iteration: %d, x = %.2f, |f(x)| = %.2e\n",iter,x,res);

while res>=TOL & iter<100
    x = x - f(x)/df(x); % xk+1 = xk - f(xk)/df(xk)
    res = abs(f(x));
    iter = iter + 1;
    fprintf("Iteration: %d, x = %.2f, |f(x)| = %.2e\n",iter,x,res);
end

subplot(2,2,1);
fplot(@(x) N(x));

subplot(2,2,2);
fplot(@(x) f(x));

subplot(2,2,3);
fplot(@(x) df(x));