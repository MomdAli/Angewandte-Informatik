% Aufgabe 1.m
% 20.06.2024
clc, clearvars, close all

alpha = 5 * 10^-2;
beta = 4 * 10^-7;
gamma = 40 * 60; % Aufgabe (a)

x = 0:6600; % Zeit von 19:10 bis 21:00 in Sekunden Schritten
b = @(t) alpha * exp(-beta * (t - gamma).^2); % Die Funktion b(t)

% p(t) mit ganzzahligen Werten anwesender Besucher*innen
p = round(cumsum(b(x)));

% Die Funktion k(t)
k = @(t) floor(log(t) + 0.5);

% Plotten der Funktion
% (i)
subplot(2, 2, 1);
plot(x, p, Color='b');
xlabel('zeit in Sekunden');
ylabel('anzahl der ankommenden Besucher*innen');
title('anwesende Besucher*innen je nach Zeit');
grid on;

% (ii)
subplot(2, 2, 3);
plot(p, x, Color='m');
xlabel('anwesende Besucher*innen');
ylabel('zeit in Sekunden');
title('Zeit je nach anwesenden Besucher*innen');
grid on;

% (iii)
subplot(2, 2, 2);
plot(p, k(p), Color='g');
xlabel('anwesende Besucher*innen');
ylabel('offene Kassen');
title('offene Kassen je nach anwesenden Besucher*innen');
grid on;

% (iv)
subplot(2, 2, 4);
plot(x, k(p), Color='r');
xlabel('zeit in Sekunden');
ylabel('offene Kassen');
title('offene Kassen je nach Zeit');
grid on;