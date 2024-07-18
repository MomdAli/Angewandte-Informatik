% Aufgabe 1b.m
% 11.05.2024
clc, clearvars, close all

n = 100;
fibb = [0, 1];

for i = 3:n
    fibb(i) = fibb(i - 1) + fibb(i - 2);
end

x = linspace(2, 100, 99);
f = @(n) (fibb(n) ./ fibb(n-1));
plot(x, f(x));